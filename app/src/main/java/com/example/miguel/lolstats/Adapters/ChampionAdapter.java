package com.example.miguel.lolstats.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miguel.lolstats.R;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;
import java.util.ArrayList;

public class ChampionAdapter extends BaseAdapter {

    final Context context;
    ArrayList<Champion> champions;
    public ChampionAdapter (Context context, ArrayList<Champion> champions) {
        this.context = context;
        this.champions = champions;
    }

    @Override
    public int getCount() {
        return champions.size();
    }

    @Override
    public Object getItem(int position) {
        return champions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return champions.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.champion_list, null);
        }

        final Champion champion = champions.get(position);

        TextView txtName = v.findViewById(R.id.txtNombre);
        txtName.setText(champion.getName());

        TextView txtTitle = v.findViewById(R.id.txtTitle);
        txtTitle.setText(champion.getTitle());

        try{
            final ImageView imgChampion = v.findViewById(R.id.imgChampion);
            String uri = "@drawable/" + champion.getImage().getFull().replace(".png", "").toLowerCase();
            int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
            Drawable imagen = context.getDrawable(imageResource);
            imgChampion.setImageDrawable(imagen);
        }catch(Exception e){
            Toast.makeText(context, "Error no se pudo cargar una imagen..", Toast.LENGTH_SHORT).show();
        }

        return v;
    }

}
