package com.example.jeehack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ShowPreviousPapers extends AppCompatActivity {
    String pdfurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_previous_papers);

        final WebView webView = findViewById(R.id.webview);
        final ProgressBar progressBar = findViewById(R.id.pdfprogress);

        progressBar.setVisibility(View.VISIBLE);

        Intent intent = getIntent();
        pdfurl = intent.getStringExtra("pdfurl");

        try {
            pdfurl= URLEncoder.encode(pdfurl,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        final String finalurl = "https://drive.google.com/viewerng/viewer?url="+pdfurl;

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.loadUrl(finalurl);
        webView.setWebViewClient(new WebViewClient(){
            boolean pageStarted = false;

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                pageStarted = true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if(pageStarted){
                    progressBar.setVisibility(View.GONE);
                }else{
                    webView.loadUrl(finalurl);
                }

            }
        });


    }
}