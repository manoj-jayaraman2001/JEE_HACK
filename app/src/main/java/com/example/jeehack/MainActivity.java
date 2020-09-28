package com.example.jeehack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    public static final String TAG = "TAG";
    EditText Name,Email,Password,Phone;
    Button mSignup,Gsignin;
    RadioButton rbtn;
    TextView mLogin;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    ProgressBar progressBar;
    String userID;
    GoogleSignInClient mGoogleSignInClient;
    final static int RC_SIGN_IN = 1;
     Object InputMethodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name = findViewById(R.id.name);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        mSignup = findViewById(R.id.SignupBtn);
        Phone = findViewById(R.id.phone);
        mLogin = findViewById(R.id.Login);
        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
        Gsignin = findViewById(R.id.google_button);
        rbtn = findViewById(R.id.Radio);


        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });


        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fullname = Name.getText().toString().trim();
                final String email  = Email.getText().toString().trim();
                final String pno = Phone.getText().toString().trim();
                String pass = Password.getText().toString().trim();


                if(TextUtils.isEmpty(fullname)){
                    Email.setError("Your Name is Required");
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    Email.setError("Email is Required");
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                      Email.setError("Invalid Email Address");
                }

                if(TextUtils.isEmpty(pass)){
                    Password.setError("Password is Required");
                    return;
                }
                if(pass.length() < 6){
                    Password.setError("Atleast 6 characters Required");
                    return;
                }if(!rbtn.isChecked()){
                    rbtn.setError("Accept User License Agreement");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
               mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){
                           mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                               @Override
                               public void onComplete(@NonNull Task<Void> task) {
                                   if(task.isSuccessful()){
                                       userID = mAuth.getCurrentUser().getUid();
                                       DocumentReference documentReference = fStore.collection("users").document(userID);
                                       Map<String,Object> user = new HashMap<>();
                                       user.put("UserName",fullname);
                                       user.put("Email",email);
                                       user.put("Phone",pno);
                                       documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                           @Override
                                           public void onSuccess(Void aVoid) {
                                               Log.d(TAG,"profile is created for"+userID);
                                           }
                                       });
                                       Toast.makeText(MainActivity.this,"Email Verification link has been sent to your email,please Verify",Toast.LENGTH_SHORT).show();
                                       progressBar.setVisibility(View.GONE);
                                       startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                                   }else{
                                       Toast.makeText(MainActivity.this,"Error!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                   }
                               }
                           });

                       }else{
                           Toast.makeText(MainActivity.this,"Error!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                           progressBar.setVisibility(View.GONE);
                       }
                   }
               });
            }
        });

    }




    public void userAgreement(View view) {
        startActivity(new Intent(getApplicationContext(),Termsandconditions.class));
    }


    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);

    }
}