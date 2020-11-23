package com.example.jeehack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressActivity extends AppCompatActivity {

    int finalScore;
    private int progr = 0;
    ProgressBar maths,physics,chemistry;
    TextView textmath,textPhy,textchem;
     int oldProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);



        maths = findViewById(R.id.progressBarMaths);
        physics = findViewById(R.id.progressBarPhysics);
        chemistry = findViewById(R.id.progressBarChemistry);
        textmath = findViewById(R.id.textmaths);
        textPhy = findViewById(R.id.textphysics);
        textchem = findViewById(R.id.textchemistry);
        oldProgress = maths.getProgress();

        Intent i = getIntent();
        finalScore = i.getIntExtra("Score",3);
        if(finalScore>=3){
            if(oldProgress==0){
                maths.setProgress(10);
                physics.setProgress(0);
                chemistry.setProgress(0);
                textmath.setText(10+"%");
            }

        }

    }



}