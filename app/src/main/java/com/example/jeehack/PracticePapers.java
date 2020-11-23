package com.example.jeehack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PracticePapers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_papers);
    }

    public void gotoquizzes(View view) {
        startActivity(new Intent (PracticePapers.this,ChooseSubject.class));
    }

    public void mathspractice(View view) {
    }

    public void physicspractice(View view) {
    }

    public void chemistrypractice(View view) {
    }
}