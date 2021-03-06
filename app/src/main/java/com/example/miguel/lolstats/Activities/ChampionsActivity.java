package com.example.miguel.lolstats.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miguel.lolstats.Adapters.ChampionAdapter;
import com.example.miguel.lolstats.R;
import com.example.miguel.lolstats.ApisHelper.RiotApiHelper;

import net.rithms.riot.api.endpoints.static_data.dto.Champion;


import java.util.ArrayList;

public class ChampionsActivity extends AppCompatActivity {

    private ListView lstChampions;
    private TextView filter;
    private ChampionAdapter championAdapter;
    private ArrayList<Champion> championDataFilter = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        championDataFilter.addAll(RiotApiHelper.championsData);

        if(RiotApiHelper.championsData.size()==0){
            Toast.makeText(getApplicationContext(),"API Rate Limit exceeded.. Retry later", Toast.LENGTH_LONG).show();
            finish();
        }

        filter = findViewById(R.id.txtFilter);
        filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterList();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        lstChampions = findViewById(R.id.lstChampions);
        lstChampions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ChampionDetailsActivity.class);
                intent.putExtra("champion", championDataFilter.get(position));
                startActivity(intent);
            }
        });
        championAdapter = new ChampionAdapter(this, championDataFilter);
        lstChampions.setAdapter(championAdapter);
    }

    private void filterList(){
        championDataFilter.clear();
        for(Champion c: RiotApiHelper.championsData){
            if(c.getName().toLowerCase().contains(filter.getText().toString().toLowerCase())){
                championDataFilter.add(c);
            }
        }

        if(filter.getText().toString().equals("")){
            championDataFilter.addAll(RiotApiHelper.championsData);
        }

        championAdapter.notifyDataSetChanged();
    }

}
