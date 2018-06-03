package com.example.miguel.lolstats.ApisHelper;

import android.util.Log;

import com.google.gson.Gson;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.endpoints.static_data.constant.ChampionListTags;
import net.rithms.riot.api.endpoints.static_data.constant.Locale;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;
import net.rithms.riot.api.endpoints.static_data.dto.ChampionList;
import net.rithms.riot.constant.Platform;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class RiotApiHelper {
    private static final String APIKEY = "RGAPI-a8b91def-e5b5-49ac-bc45-73d5d280dd84";
    private static final String VERSIONURL = "http://ddragon.leagueoflegends.com/api/versions.json";

    private static ApiConfig config = new ApiConfig().setKey(APIKEY);
    private static final RiotApi api = new RiotApi(config);

    public static ArrayList<Champion> championsData = new ArrayList<>();


    public Champion getChampion(int id){
        Champion champ = null;
        for(Champion c: championsData){
            if(c.getId()==id){
                champ = c;
                break;
            }
        }

        return champ;
    }
    public static void loadChampions(){
        championsData.clear();
        Thread hilo = new Thread(){
            @Override
            public void run(){
                try{
                    ChampionList championList = api.getDataChampionList(Platform.EUW, Locale.ES_ES, null, false, ChampionListTags.ALL);
                    Map<String, Champion> map = championList.getData();
                    for (Champion champion : map.values()) {
                        championsData.add(champion);
                    }

                }catch(Exception e){
                    Log.e("ERROR API RIOT",e.getMessage());
                }
            }
        };
        hilo.start();
        try{
            hilo.join();
        }catch(Exception e){
            Log.e("ERROR HILO", e.getMessage());
        }
    }

    public static String getCurrentVersion(){
        ArrayList<String> versions = new ArrayList<>();
        Gson gson = new Gson();
        versions = gson.fromJson(getJsonVersion(), versions.getClass());
        return versions.get(0);
    }

    private static String getJsonVersion(){

        final StringBuilder stringBuilder = new StringBuilder();

        Thread hilo = new Thread(){
            @Override
            public void run(){
                try {
                    URL url = new URL(VERSIONURL);
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

}
