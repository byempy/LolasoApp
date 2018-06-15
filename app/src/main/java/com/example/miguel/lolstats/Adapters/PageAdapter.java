package com.example.miguel.lolstats.Adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.miguel.lolstats.Fragments.BuildFragment;
import com.example.miguel.lolstats.Fragments.InfoFragment;
import com.example.miguel.lolstats.Fragments.RateFragment;

import net.rithms.riot.api.endpoints.static_data.dto.Champion;

public class PageAdapter extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;
    Champion champion;
    InfoFragment ifrag;
    RateFragment rfrag;
    BuildFragment bfrag;

    //Constructor to the class
    public PageAdapter(FragmentManager fm, int tabCount, Champion champion) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
        this.champion = champion;
        ifrag = new InfoFragment();
        rfrag = new RateFragment();
        bfrag = new BuildFragment();
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        Bundle bl = new Bundle();
        bl.putSerializable("champion", champion);
        switch (position) {
            case 0:
                bfrag.setArguments(bl);
                 return bfrag;
            case 1:
                rfrag.setArguments(bl);
                return rfrag;
            case 2:
                ifrag.setArguments(bl);
                return ifrag;
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}
