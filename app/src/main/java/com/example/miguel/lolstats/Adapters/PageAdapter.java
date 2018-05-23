package com.example.miguel.lolstats.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.miguel.lolstats.Fragments.BuildFragment;
import com.example.miguel.lolstats.Fragments.InfoFragment;
import com.example.miguel.lolstats.Fragments.RateFragment;

public class PageAdapter extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public PageAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                 return new BuildFragment();
            case 1:
                return new RateFragment();
            case 2:
                return new InfoFragment();
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
