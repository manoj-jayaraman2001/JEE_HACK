package com.example.jeehack;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Objects;
import java.util.ResourceBundle;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView mLogout;
    TextView Name;
    FirebaseFirestore fstore;
    FirebaseAuth mAuth;
    Toolbar toolbar;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mLogout = findViewById(R.id.logout);

        Name = findViewById(R.id.DName);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav);

        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        fstore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        final DocumentReference documentReference = fstore.collection("users").document(userid);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
               Name.setText("Hi,"+value.getString("UserName"));

            }
        });


        navigationView.bringToFront();
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


    }
    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
        }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.nav_profile:
                Intent i = new Intent(Dashboard.this,profile.class);
                startActivity(i);
                break;
            case R.id.nav_logout:
                Intent intent = new Intent(Dashboard.this,LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_ncert:
                Intent j = new Intent(Dashboard.this,Ncertbooks.class);
                startActivity(j);
                break;
            case R.id.nav_progress:
                startActivity(new Intent(Dashboard.this,ProgressActivity.class));
                break;
            case R.id.nav_feedback:
                startActivity(new Intent(Dashboard.this,Feedback.class));

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void learn(View view) {
        startActivity(new Intent(Dashboard.this,Subject.class));
    }

    public void subjectSelect(View view) {
        startActivity(new Intent(Dashboard.this,ChooseSubject.class));
    }

    public void previouspapers(View view) {
        startActivity(new Intent (Dashboard.this,PreviousPapers.class));
    }

    public void practicepapers(View view) {
        startActivity(new Intent (Dashboard.this,PracticePapers.class));
    }
}
