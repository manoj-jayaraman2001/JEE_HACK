package com.example.jeehack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class mathsQuizzes extends AppCompatActivity {


    CardView setsQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maths_quizzes);
        setsQuiz = findViewById(R.id.sets);


        setsQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mathsQuizzes.this,mockQuiz.class));
            }
        });
    }
}