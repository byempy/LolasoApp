package com.example.miguel.lolstats.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.miguel.lolstats.ApisHelper.ChampionggApiHelper;
import com.example.miguel.lolstats.Models.Campeon;
import com.example.miguel.lolstats.R;

import java.util.ArrayList;

public class RateAdapter extends BaseAdapter {

    Context context;
    ArrayList<Campeon> campeon;

    public RateAdapter(Context context, ArrayList<Campeon> campeon){
        this.context = context;
        this.campeon = campeon;
    }

    @Override
    public int getCount() {
        return campeon.size();
    }

    @Override
    public Object getItem(int position) {
        return campeon.get(position);
    }

    @Override
    public long getItemId(int position) {
        return campeon.get(position).getChampionId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.rate_list, null);
        }

        Campeon c = campeon.get(position);

        TextView txtWinRate = v.findViewById(R.id.txtWinRate);
        txtWinRate.setText(String.format("%.2f", c.getWinRate()*100) + "%");
        if(c.getWinRate()>=0.50) {
            txtWinRate.setTextColor(Color.GREEN);
        }else{
            txtWinRate.setTextColor(Color.RED);
        }

        TextView txtPickRate = v.findViewById(R.id.txtPickRate);
        txtPickRate.setText(String.format("%.2f", c.getPlayRate()*100) + "%");

        TextView txtBanRate = v.findViewById(R.id.txtBanRate);
        txtBanRate.setText(String.format("%.2f", c.getBanRate()*100) + "%");

        double kda = (c.getKills()+c.getAssists())/c.getDeaths();
        TextView txtKda = v.findViewById(R.id.txtKda);
        txtKda.setText(String.format("%.2f", kda));

        TextView txtPartidas = v.findViewById(R.id.txtPartidas);
        txtPartidas.setText("" + c.getGamesPlayed());

        TextView txtRol = v.findViewById(R.id.txtBuildRol);
        txtRol.setText(ChampionggApiHelper.convertRole(c.getRole()));

        return v;
    }
}
