package com.example.jeehack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Ncertbooks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ncertbooks);
    }

    public void mathsclass11(View view) {
        Intent intent = new Intent(Ncertbooks.this,Maths_class11.class);
        startActivity(intent);


    }

    public void mathsclass12(View view) {

    }

    public void physicsclass12(View view) {
    }

    public void physicsclass11(View view) {

    }

    public void chemistryclass11(View view) {

    }

    public void chemistryclass12(View view) {

    }
}