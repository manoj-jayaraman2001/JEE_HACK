package com.example.jeehack;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class mockQuiz extends AppCompatActivity {
    private static final int REQUEST_CODE_QUIZ = 1;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "KeyHighScore";

    private TextView textviewHighscore;
    private int highscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_quiz);


        textviewHighscore = findViewById(R.id.textViewHighScore);
        LoadHighScore();
    }

    public void startTest(View view) {
        Intent i = new Intent(mockQuiz.this,mockTest.class);
        startActivityForResult(i,REQUEST_CODE_QUIZ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_QUIZ){
            if(resultCode== RESULT_OK){
                int score= data.getIntExtra(mockTest.EXTRA_SCORE,0);

                if(score > highscore){
                    updateHighScore(score);
                }
            }
        }
    }

    private void LoadHighScore(){
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        highscore= prefs.getInt(KEY_HIGHSCORE,0);
        textviewHighscore.setText("HighSchore: "+ highscore);
    }

    private void updateHighScore(int highscoreNew) {
        highscore = highscoreNew;
        textviewHighscore.setText("HighSchore: "+ highscore);
        Intent i = new Intent(mockQuiz.this,ProgressActivity.class);
        i.putExtra("Score",highscore);
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highscore);
        editor.apply();

    }
}