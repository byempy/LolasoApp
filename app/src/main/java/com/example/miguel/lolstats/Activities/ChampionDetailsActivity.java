package com.example.miguel.lolstats.Activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.miguel.lolstats.Adapters.PageAdapter;
import com.example.miguel.lolstats.R;

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

        ImageView imgSplash = findViewById(R.id.imgSplash);

        String uri = "@drawable/" + champion.getImage().getFull().replace(".png", "").toLowerCase() + "0";
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable imagen = getDrawable(imageResource);
        imgSplash.setImageDrawable(imagen);

        final ViewPager viewPager = findViewById(R.id.pager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), champion);
        viewPager.setAdapter(pageAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
