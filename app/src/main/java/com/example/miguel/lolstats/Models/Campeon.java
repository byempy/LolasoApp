package com.example.miguel.lolstats.Models;

import net.rithms.riot.api.endpoints.static_data.dto.Champion;

public class Campeon extends Champion {
    private int wins;
    private int loses;

    protected int getWins(){
        return wins;
    }

    protected int getLoses(){
        return loses;
    }

}
