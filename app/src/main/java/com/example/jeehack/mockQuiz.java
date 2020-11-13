package com.example.jeehack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class mockQuiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_quiz);
    }

    public void startTest(View view) {
        startActivity(new Intent(mockQuiz.this, mockTest.class));
    }
}