package com.example.footballapi.model.model_viewpager.team;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.footballapi.view.fragments.MatchesFragment;
import com.example.footballapi.view.fragments.SquadFragment;

public class Adapter_ViewPagerTeam extends FragmentStatePagerAdapter {

    private int id;
    private String crestURL;

    public Adapter_ViewPagerTeam(FragmentManager fm, int id, String crestURL) {
        super(fm);
        this.id = id;
        this.crestURL = crestURL;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return(MatchesFragment.newInstance(this.id));
            case 1: return(SquadFragment.newInstance(this.id));
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Matches";
            case 1: return "Équipe";
            default: return null;
        }
    }
}