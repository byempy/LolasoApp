package com.example.miguel.lolstats.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.miguel.lolstats.ApisHelper.ChampionggApiHelper;
import com.example.miguel.lolstats.R;
import com.example.miguel.lolstats.ApisHelper.RiotApiHelper;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        ImageView imgIcon = findViewById(R.id.imgIcon);
        Glide.with(this).load(R.drawable.ic_zilean).into(imgIcon);

        Thread hilo = new Thread() {
            @Override
            public void run() {
                RiotApiHelper.loadChampions();
                ChampionggApiHelper.loadChampionggData();
                Intent i = new Intent(SplashActivity.this, ChampionsActivity.class);
                startActivity(i);
                finish();

            }
        };

        hilo.start();

    }
}