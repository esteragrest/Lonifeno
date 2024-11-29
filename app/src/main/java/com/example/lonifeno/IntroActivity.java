package com.example.lonifeno;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class IntroActivity extends AppCompatActivity {

    private LinearLayout lesson1;
    private LinearLayout lesson2;
    private LinearLayout test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_intro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.relativ_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ImageButton homeButton = findViewById(R.id.home_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, ModuleActivity.class);
                startActivity(intent);
            }
        });

        lesson1 = findViewById(R.id.lesson_1);
        lesson2 = findViewById(R.id.lesson_2);
        test = findViewById(R.id.test);

        lesson1.setOnClickListener(v -> {
            Intent intent = new Intent(IntroActivity.this, Lesson1Activity.class);
            startActivity(intent);
            lesson1Completed();
        });

        lesson2.setOnClickListener(v -> {
            if (isLesson1Completed()) {
                Intent intent = new Intent(IntroActivity.this, Lesson2Activity.class);
                startActivity(intent);
                lesson2Completed();
            }
        });

        test.setOnClickListener(v -> {
            Intent intent = new Intent(IntroActivity.this, TestActivity.class);
            startActivity(intent);
            testCompleted();
        });
    }

    private void lesson1Completed() {
        // Логика завершения Урока 1
        // Например, обновление интерфейса
        // Установить статус "Пройдено" для Урока 1
    }

    private void lesson2Completed() {
        // Логика завершения Урока 2
        // Например, обновление интерфейса
        // Установить статус "Пройдено" для Урока 2
    }

    private void testCompleted() {
        // Логика завершения Теста
        // Например, обновление интерфейса
        // Установить звезды для Теста
    }

    private boolean isLesson1Completed() {
        // Логика проверки, пройден ли Урок 1
        return true; // Замените на реальную проверку
    }

    private boolean isLesson2Completed() {
        // Логика проверки, пройден ли Урок 2
        return false; // Замените на реальную проверку
    }
}
