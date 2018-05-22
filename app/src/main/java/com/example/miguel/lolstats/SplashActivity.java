package com.example.miguel.lolstats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        ImageView imgIcon = findViewById(R.id.imgIcon);
        Glide.with(this).load(R.drawable.ic_zilean).into(imgIcon);

        Thread hilo = new Thread(){
            @Override
            public void run() {
                RiotApiHelper.loadChampions();
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        };

         hilo.start();
    }
}
