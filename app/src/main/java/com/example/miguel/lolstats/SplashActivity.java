package com.example.miguel.lolstats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import net.rithms.riot.api.endpoints.static_data.dto.Champion;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        final Intent i = new Intent(this, MainActivity.class);
        ImageView imgIcon = findViewById(R.id.imgIcon);

        Glide.with(this).load(R.drawable.ic_zilean).into(imgIcon);

        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(i);
                finish();
            }
        });

         hilo.start();
    }
}
