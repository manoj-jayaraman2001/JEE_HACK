package com.example.jeehack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;


public class changePassword extends AppCompatActivity {
    EditText Email;
    Button Sendbtn;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        mAuth = FirebaseAuth.getInstance();

        Email = findViewById(R.id.email);
        Sendbtn = findViewById(R.id.send);
        progressBar = findViewById(R.id.progressBar3);

       Sendbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String email_input  = Email.getText().toString().trim();
               if(TextUtils.isEmpty(email_input)){

                   Toast.makeText(changePassword.this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();

               }else{
                   progressBar.setVisibility(View.VISIBLE);
                   mAuth.sendPasswordResetEmail(email_input).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           if(task.isSuccessful()){
                               Toast.makeText(changePassword.this, "Email has been sent,please check your email Account to change password", Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(changePassword.this,LoginActivity.class));
                           }else{
                               Toast.makeText(changePassword.this, "Error!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                               progressBar.setVisibility(View.GONE);
                           }
                       }
                   });
               }
           }
       });

    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}