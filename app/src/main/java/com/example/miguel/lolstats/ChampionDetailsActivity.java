package com.example.miguel.lolstats;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import net.rithms.riot.api.endpoints.static_data.dto.Champion;

public class ChampionDetailsActivity extends AppCompatActivity {

    private Champion champion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champion_details);
        champion = (Champion) getIntent().getExtras().get("champion");

        TextView txtDName = findViewById(R.id.txtDName);
        txtDName.setText(champion.getName());

        TextView txtDTitle = findViewById(R.id.txtDTitle);
        txtDTitle.setText(champion.getTitle());

        TextView txtLore = findViewById(R.id.txtLore);
        txtLore.setText(champion.getLore().replace("\n", ""));

        ImageView imgSplash = findViewById(R.id.imgSplash);

        String uri = "@drawable/" + champion.getImage().getFull().replace(".png", "").toLowerCase() + "0";
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable imagen = getDrawable(imageResource);
        imgSplash.setImageDrawable(imagen);

    }
}
