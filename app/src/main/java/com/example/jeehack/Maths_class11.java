package com.example.jeehack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.view.View;

public class Maths_class11 extends AppCompatActivity {

    CardView Sets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maths_class11);
        Sets = findViewById(R.id.sets);


        Sets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://firebasestorage.googleapis.com/v0/b/jee-hack.appspot.com/o/Sets.pdf?alt=media&token=a62d1607-0911-4f97-8936-691cae7b4cac";
                Intent intent = new Intent(Maths_class11.this,viewMathsBook11.class);
                intent.putExtra("pdfurl",url);
                startActivity(intent);
            }
        });
    }



}