package com.example.miguel.lolstats.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.miguel.lolstats.Adapters.BuildAdapter;
import com.example.miguel.lolstats.ApisHelper.ChampionggApiHelper;
import com.example.miguel.lolstats.Models.Campeon;
import com.example.miguel.lolstats.R;

import net.rithms.riot.api.endpoints.static_data.dto.Champion;

import java.util.ArrayList;

public class BuildFragment extends Fragment {


    public BuildFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Champion champ = (Champion)getArguments().get("champion");

        ChampionggApiHelper api = new ChampionggApiHelper();
        ArrayList<Campeon> campeon = api.getCampeon(champ.getId());
        View v =  inflater.inflate(R.layout.fragment_build, container, false);

        ListView lstRates = v.findViewById(R.id.lstBuild);
        BuildAdapter rAdapter = new BuildAdapter(getContext(), campeon);
        lstRates.setAdapter(rAdapter);

        return v;
    }
}
