package com.example.jeehack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
    }

    public void sendFeedback(View view) {

        Toast.makeText(Feedback.this,"Feedback sent successfully",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Feedback.this,Dashboard.class));
    }
}