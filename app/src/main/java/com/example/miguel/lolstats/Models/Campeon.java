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

    public void setChampionId(int championId) {
        this.championId = championId;
    }

    public double getWinRate() {
        return winRate;
    }

    public void setWinRate(double winRate) {
        this.winRate = winRate;
    }

    public double getPlayRate() {
        return playRate;
    }

    public void setPlayRate(double playRate) {
        this.playRate = playRate;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public double getPercentRolePlayed() {
        return percentRolePlayed;
    }

    public void setPercentRolePlayed(double percentRolePlayed) {
        this.percentRolePlayed = percentRolePlayed;
    }

    public double getBanRate() {
        return banRate;
    }

    public void setBanRate(double banRate) {
        this.banRate = banRate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getKills() {
        return kills;
    }

    public void setKills(double kills) {
        this.kills = kills;
    }

    public double getAssists() {
        return assists;
    }

    public void setAssists(double assists) {
        this.assists = assists;
    }

    public double getDeaths() {
        return deaths;
    }

    public void setDeaths(double deaths) {
        this.deaths = deaths;
    }
}
