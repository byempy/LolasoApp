package com.example.miguel.lolstats.ApisHelper;

import com.example.miguel.lolstats.Models.Campeon;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ChampionggApiHelper {

    private final static String APIKEY = "e659324e3e46be79f0b7d4b879496eb6";
    private final static String URL= "http://api.champion.gg/v2/champions?champData=kda&limit=500&api_key=" + APIKEY;
    private final static ArrayList<Campeon> campeonesData = new ArrayList<>();

    public ArrayList<Campeon> getCampeon(int id){
        ArrayList<Campeon> campeon = new ArrayList<>();
        for(Campeon c: campeonesData){
            if(c.getChampionId()==id){
                campeon.add(c);
            }
        }
        return campeon;
    }

    public static void loadChampionggData(){
        campeonesData.clear();
        Thread hilo = new Thread(){
            @Override
            public void run(){
                Gson gson = new Gson();
                ArrayList<Campeon> campeones = gson.fromJson(getJson(), new TypeToken<ArrayList<Campeon>>(){}.getType());
                campeonesData.addAll(campeones);
            }
        };
        hilo.start();
        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String getJson(){
        final StringBuilder stringBuilder = new StringBuilder();

        Thread hilo = new Thread(){
            @Override
            public void run(){
                try {
                    java.net.URL url = new URL(URL);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    String line;

                    while((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }


                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        hilo.start();

        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    public String[] getMostFrequentBuild(String sourceData){
        String[] items = new String[6];
        String[] wrappers = sourceData.split("build-wrapper");
        String frequentWrapper = wrappers[1];

        try {
            for (int i = 0; i < items.length; i++) {
                items[i] = frequentWrapper.split("<img src=\"")[i + 1].split("\" class=")[0];
                items[i] = items[i].replace("//", "http://");
            }
        }catch(Exception e){
            return null;
        }
        return items;
    }

    public String[] getMostWinnableBuild(String sourceData){


        String[] items = new String[6];
        String[] wrappers = sourceData.split("build-wrapper");
        String frequentWrapper = wrappers[2];

        try {
            for (int i = 0; i < items.length; i++) {
                items[i] = frequentWrapper.split("<img src=\"")[i + 1].split("\" class=")[0];
                items[i] = items[i].replace("//", "http://");
            }
        }catch(Exception e){
            return null;
        }

        return items;
    }

    public String getSourceCode(String name, String role){
        final String enlace = "http://champion.gg/champion/" + name + "/" + role;
        final StringBuilder stringBuilder = new StringBuilder();
        Thread hilo = new Thread(){
            @Override
            public void run(){
                try {
                    URL url = new URL(enlace);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    String line;

                    while((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }


                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        hilo.start();
        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    public static String convertRole(String role){
        String rol = role;
        if(rol.equals("TOP")){
            rol = "Top";
        }else if(rol.equals("MIDDLE")){
            rol = "Middle";
        }else if(rol.equals("JUNGLE")){
            rol = "Jungle";
        }else if(rol.equals("DUO_SUPPORT")){
            rol = "Support";
        }else{
            rol = "ADC";
        }
        return rol;
    }
}
