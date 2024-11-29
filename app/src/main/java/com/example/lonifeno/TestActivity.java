package com.example.lonifeno;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class TestActivity extends AppCompatActivity {
//    private long backPressedTime;
//    private Toast backToast;

    private Test1Array test1Array = new Test1Array();
    private Random random = new Random();
    private int currentQuestionIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.relativ_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ImageButton homeButton = findViewById(R.id.home_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestActivity.this, ModuleActivity.class);
                startActivity(intent);
            }
        });

        final ImageView img_left = findViewById(R.id.img_left);
        final ImageView img_right = findViewById(R.id.img_right);
        final TextView textQuestion = findViewById(R.id.text_question);
//        final Button nextButton = findViewById(R.id.next_button);
        final TextView textLeft = findViewById(R.id.text_left);
        final TextView textRight = findViewById(R.id.text_right);

        generateQuestion(img_left, img_right, textLeft, textRight, textQuestion);

        // Обработчик нажатия на кнопку "Следующий"
//        nextButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                generateQuestion(img_left, img_right, textQuestion);
//            }
//        });
    }

    private void generateQuestion(ImageView imgLeft, ImageView imgRight, TextView textLeft, TextView textRight, TextView textQuestion) {
        currentQuestionIndex = random.nextInt(test1Array.transcriptions.length);

        String correctTranscription = getString(test1Array.transcriptions[currentQuestionIndex]);

        textQuestion.setText(correctTranscription);

        int wrongIndex;
        do {
            wrongIndex = random.nextInt(test1Array.transcriptions.length);
        } while (wrongIndex == currentQuestionIndex);

        boolean isLeftCorrect = random.nextBoolean(); // true - правильная буква слева, false - справа

        if (isLeftCorrect) {
            imgLeft.setImageResource(test1Array.lettersImg[currentQuestionIndex]);
            textLeft.setText(test1Array.letters[currentQuestionIndex]);
            imgRight.setImageResource(test1Array.lettersImg[wrongIndex]);
            textRight.setText(test1Array.letters[wrongIndex]);
        } else {
            imgLeft.setImageResource(test1Array.lettersImg[wrongIndex]);
            textLeft.setText(test1Array.letters[wrongIndex]);
            imgRight.setImageResource(test1Array.lettersImg[currentQuestionIndex]);
            textRight.setText(test1Array.letters[currentQuestionIndex]);
        }
    }
}
