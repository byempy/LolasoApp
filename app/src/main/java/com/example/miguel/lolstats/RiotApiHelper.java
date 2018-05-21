package com.example.miguel.lolstats;

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
import java.util.Comparator;
import java.util.Map;

public class RiotApiHelper {
    private static final String APIKEY = "RGAPI-7ad761a9-67e2-4f9c-a1cc-8fdb7d7eba69";
    private static final String VERSIONURL = "http://ddragon.leagueoflegends.com/api/versions.json";

    private static ApiConfig config = new ApiConfig().setKey(APIKEY);
    private static final RiotApi api = new RiotApi(config);

    public static ArrayList<Champion> getAllChampions(){
        final ArrayList<Champion> championsData = new ArrayList<>();
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
                    Log.e("ERROR API",e.getMessage());
                }
            }
        };
        hilo.start();
        try{
            hilo.join();
        }catch(Exception e){
            Log.e("ERROR HILO", e.getMessage());
        }

        return championsData;
    }

    public static String getCurrentVersion(){
        ArrayList<String> versions = new ArrayList<>();
        Gson gson = new Gson();
        versions = gson.fromJson(getJsonVersion(), versions.getClass());
        return versions.get(0);
    }

    private static String getJsonVersion(){
        HttpURLConnection connection = null;
        BufferedReader reader =  null;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            URL url = new URL(VERSIONURL);
            connection = (HttpURLConnection) url.openConnection();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;

            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }


        }catch(Exception e){
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

}
