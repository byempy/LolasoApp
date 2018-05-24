package com.example.miguel.lolstats.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class BitmapThread extends Thread{
    private Bitmap bitmap;
    private String url;

    public BitmapThread(String url){
        this.url = url;
    }

    public Bitmap getBitmap(){
        return bitmap;
    }

    @Override
    public void run(){
        try {
            bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
