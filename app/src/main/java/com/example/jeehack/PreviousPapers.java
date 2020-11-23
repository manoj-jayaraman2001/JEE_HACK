package com.example.jeehack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PreviousPapers extends AppCompatActivity {
    CardView mains2020,mains2019,adv2019;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_papers);

        mains2020 = findViewById(R.id.mains2020);
        mains2019 = findViewById(R.id.mains2019);
        adv2019 = findViewById(R.id.advanced2019);

        mains2020.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://firebasestorage.googleapis.com/v0/b/jee-hack.appspot.com/o/previous_papers%2FJEE-Advanced-2019-Paper-1-MathonGo.pdf?alt=media&token=87f650ce-6545-43c3-a18b-30f55af91311";

                Intent intent = new Intent(PreviousPapers.this,ShowPreviousPapers.class);
                intent.putExtra("pdfurl",url);
                startActivity(intent);
            }


        });

        mains2019.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://firebasestorage.googleapis.com/v0/b/jee-hack.appspot.com/o/previous_papers%2FJEE-Advanced-2019-Paper-1-MathonGo.pdf?alt=media&token=87f650ce-6545-43c3-a18b-30f55af91311";

                Intent intent = new Intent(PreviousPapers.this,ShowPreviousPapers.class);
                intent.putExtra("pdfurl",url);
                startActivity(intent);
            }
        });

    }


}