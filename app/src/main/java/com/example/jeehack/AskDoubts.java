package com.example.jeehack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AskDoubts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_doubts);
    }


    public void post(View view) {
        Toast.makeText(AskDoubts.this,"Posted successfully",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(AskDoubts.this,Dashboard.class));

    }
}