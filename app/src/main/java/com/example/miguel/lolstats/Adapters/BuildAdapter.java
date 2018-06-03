package com.example.miguel.lolstats.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.miguel.lolstats.ApisHelper.ChampionggApiHelper;
import com.example.miguel.lolstats.ApisHelper.RiotApiHelper;
import com.example.miguel.lolstats.Models.Campeon;
import com.example.miguel.lolstats.R;
import com.example.miguel.lolstats.Utils.BitmapThread;
import java.util.ArrayList;

public class BuildAdapter extends BaseAdapter {

    Context context;
    ArrayList<Campeon> campeon;


    public BuildAdapter(Context context, ArrayList<Campeon> campeon){
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
            v = inf.inflate(R.layout.build_list, null);
        }

        RiotApiHelper riot = new RiotApiHelper();
        ChampionggApiHelper gg = new ChampionggApiHelper();
        Campeon camp = campeon.get(position);
        String nombre = riot.getChampion(camp.getChampionId()).getKey();
        String role = camp.getRole();

        String sourceData = gg.getSourceCode(nombre, gg.convertRole(camp.getRole()));
        String[] mostFrequentBuild = gg.getMostFrequentBuild(sourceData);
        String[] mostWinnableBuild = gg.getMostWinnableBuild(sourceData);

        if(mostFrequentBuild == null || mostWinnableBuild == null){

            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v2 = inf.inflate(R.layout.nobuild_list, null);
            TextView txtRol = v2.findViewById(R.id.txtBuildRol2);
            txtRol.setText(ChampionggApiHelper.convertRole(camp.getRole()));

            return v2;
        }


        ImageView imgFre1 = v.findViewById(R.id.imgFre1);
        imgFre1.setImageBitmap(getImg(mostFrequentBuild[0]));
        ImageView imgFre2 = v.findViewById(R.id.imgFre2);
        imgFre2.setImageBitmap(getImg(mostFrequentBuild[1]));
        ImageView imgFre3 = v.findViewById(R.id.imgFre3);
        imgFre3.setImageBitmap(getImg(mostFrequentBuild[2]));
        ImageView imgFre4 = v.findViewById(R.id.imgFre4);
        imgFre4.setImageBitmap(getImg(mostFrequentBuild[3]));
        ImageView imgFre5 = v.findViewById(R.id.imgFre5);
        imgFre5.setImageBitmap(getImg(mostFrequentBuild[4]));
        ImageView imgFre6 = v.findViewById(R.id.imgFre6);
        imgFre6.setImageBitmap(getImg(mostFrequentBuild[5]));

        ImageView imgWin1 = v.findViewById(R.id.imgWin1);
        imgWin1.setImageBitmap(getImg(mostWinnableBuild[0]));
        ImageView imgWin2 = v.findViewById(R.id.imgWin2);
        imgWin2.setImageBitmap(getImg(mostWinnableBuild[1]));
        ImageView imgWin3 = v.findViewById(R.id.imgWin3);
        imgWin3.setImageBitmap(getImg(mostWinnableBuild[2]));
        ImageView imgWin4 = v.findViewById(R.id.imgWin4);
        imgWin4.setImageBitmap(getImg(mostWinnableBuild[3]));
        ImageView imgWin5 = v.findViewById(R.id.imgWin5);
        imgWin5.setImageBitmap(getImg(mostWinnableBuild[4]));
        ImageView imgWin6 = v.findViewById(R.id.imgWin6);
        imgWin6.setImageBitmap(getImg(mostWinnableBuild[5]));

        TextView txtRol = v.findViewById(R.id.txtBuildRol);
        txtRol.setText(ChampionggApiHelper.convertRole(camp.getRole()));

        return v;
    }

    private Bitmap getImg(String url){

        BitmapThread hilo = new BitmapThread(url);
        hilo.start();

        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return hilo.getBitmap();
    }
}
