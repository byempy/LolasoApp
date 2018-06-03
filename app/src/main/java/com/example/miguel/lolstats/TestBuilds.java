package com.example.miguel.lolstats;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;
import net.rithms.riot.constant.Platform;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestBuilds {
    public static void main(String[] args) throws RiotApiException {
        final String APIKEY = "RGAPI-f6248f69-9365-41ab-a69c-40981258cd6b";

        ApiConfig config = new ApiConfig().setKey(APIKEY);
        RiotApi api = new RiotApi(config);

        Champion champion = api.getDataChampion(Platform.EUW, 14);
        System.out.println(champion.getKey());

        String sourceData = getSourceData("http://champion.gg/champion/" + champion.getKey());
        String[] build = getMostFrequentBuild(sourceData);

        for(String item: build){
            System.out.println(item);
        }
    }
    public static String[] getMostFrequentBuild(String sourceData){
        String[] items = new String[6];
        String[] wrappers = sourceData.split("build-wrapper");
        String frequentWrapper = wrappers[1];

        items[0] = frequentWrapper.split("<img src=\"")[1].split("\" class=")[0];
        items[1] = frequentWrapper.split("<img src=\"")[2].split("\" class=")[0];
        items[2] = frequentWrapper.split("<img src=\"")[3].split("\" class=")[0];
        items[3] = frequentWrapper.split("<img src=\"")[4].split("\" class=")[0];
        items[4] = frequentWrapper.split("<img src=\"")[5].split("\" class=")[0];
        items[5] = frequentWrapper.split("<img src=\"")[6].split("\" class=")[0];

        return items;
    }

    private static String getSourceData(String enlace){
        final StringBuilder stringBuilder = new StringBuilder();
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

        return stringBuilder.toString();
    }
}
