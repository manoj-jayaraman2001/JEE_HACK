package com.example.jeehack;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class Video extends AppCompatActivity {
    VideoView videoView;
    Button play,pause;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView = findViewById(R.id.videoView);
        play = findViewById(R.id.play);
        pause = findViewById(R.id.pause);

        String str  = "https://firebasestorage.googleapis.com/v0/b/jee-hack.appspot.com/o/Videos%2FSets.mp4?alt=media&token=f79c3a8a-339f-4b34-9d23-6b5ca46668cd";

        Uri uri = Uri.parse(str);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.start();
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.pause();
            }
        });
    }
}