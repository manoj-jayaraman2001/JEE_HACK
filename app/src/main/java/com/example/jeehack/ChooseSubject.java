package com.example.jeehack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChooseSubject extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_subject);
    }



    public void chooseQuiz(View view) {
        startActivity(new Intent(ChooseSubject.this,mathsQuizzes.class));
    }
}