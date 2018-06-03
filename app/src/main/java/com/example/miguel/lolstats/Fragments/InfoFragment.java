package com.example.miguel.lolstats.Fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.miguel.lolstats.R;
import com.example.miguel.lolstats.ApisHelper.RiotApiHelper;
import com.example.miguel.lolstats.Utils.BitmapThread;

import net.rithms.riot.api.endpoints.static_data.dto.Champion;

public class InfoFragment extends Fragment {

    public InfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_info, container, false);

        Champion champ = (Champion)getArguments().get("champion");

        TextView txtLore = v.findViewById(R.id.txtLore);
        txtLore.setText(champ.getLore());

        //Nombres Habilidades
        TextView txtQName = v.findViewById(R.id.txtQName);
        txtQName.setText("Q - " + champ.getSpells().get(0).getName());

        TextView txtWName = v.findViewById(R.id.txtWName);
        txtWName.setText("W - " + champ.getSpells().get(1).getName());

        TextView txtEName = v.findViewById(R.id.txtEName);
        txtEName.setText("E - " + champ.getSpells().get(2).getName());

        TextView txtRName = v.findViewById(R.id.txtRName);
        txtRName.setText("R - " + champ.getSpells().get(3).getName());

        TextView txtPasive = v.findViewById(R.id.txtPasiveName);
        txtPasive.setText("Pasiva - " + champ.getPassive().getName());

        //Descripción Habilidades
        TextView txtQDesc = v.findViewById(R.id.txtQDesc);
        txtQDesc.setText(champ.getSpells().get(0).getDescription());

        TextView txtWDesc = v.findViewById(R.id.txtWDesc);
        txtWDesc.setText(champ.getSpells().get(1).getDescription());

        TextView txtEDesc = v.findViewById(R.id.txtEDesc);
        txtEDesc.setText(champ.getSpells().get(2).getDescription());

        TextView txtRDesc = v.findViewById(R.id.txtRDesc);
        txtRDesc.setText(champ.getSpells().get(3).getDescription());

        TextView txtPasiveDes = v.findViewById(R.id.txtPasiveDesc);
        txtPasiveDes.setText(champ.getPassive().getDescription());

        //Imagenes Habilidades


        ImageView imgQ = v.findViewById(R.id.imgQSpell);
        imgQ.setImageBitmap(getImg(champ.getSpells().get(0).getImage().getFull()));

        ImageView imgW = v.findViewById(R.id.imgWSpell);
        imgW.setImageBitmap(getImg(champ.getSpells().get(1).getImage().getFull()));

        ImageView imgE = v.findViewById(R.id.imgESpell);
        imgE.setImageBitmap(getImg(champ.getSpells().get(2).getImage().getFull()));

        ImageView imgR = v.findViewById(R.id.imgRSpell);
        imgR.setImageBitmap(getImg(champ.getSpells().get(3).getImage().getFull()));

        ImageView imgPasive = v.findViewById(R.id.imgPasive);
        imgPasive.setImageBitmap(getImgPassive(champ.getPassive().getImage().getFull()));

        return v;
    }

    private Bitmap getImgPassive(String imgName){
        final String url = "http://ddragon.leagueoflegends.com/cdn/"+ RiotApiHelper.getCurrentVersion() +"/img/passive/" + imgName;

        BitmapThread hilo = new BitmapThread(url);
        hilo.start();

        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return hilo.getBitmap();
    }

    private Bitmap getImg(String imgName){
        final String url = "http://ddragon.leagueoflegends.com/cdn/"+ RiotApiHelper.getCurrentVersion() +"/img/spell/" + imgName;

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
