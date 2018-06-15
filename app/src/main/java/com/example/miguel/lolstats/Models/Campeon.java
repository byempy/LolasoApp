package com.example.miguel.lolstats.Models;

import java.io.Serializable;

public class Campeon implements Serializable{
    int championId;
    double winRate;
    double playRate;
    int gamesPlayed;
    double percentRolePlayed;
    double banRate;
    String role;
    double kills;
    double assists;
    double deaths;

    public int getChampionId() {
        return championId;
    }

    public double getWinRate() {
        return winRate;
    }

    public double getPlayRate() {
        return playRate;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public double getBanRate() {
        return banRate;
    }

    public String getRole() {
        return role;
    }

    public double getKills() {
        return kills;
    }

    public double getAssists() {
        return assists;
    }

    public double getDeaths() {
        return deaths;
    }

}
