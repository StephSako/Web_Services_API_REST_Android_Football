package com.example.footballapi.model.model_viewpager.team;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.footballapi.view.fragments.MatchesFragment;
import com.example.footballapi.view.fragments.SquadFragment;

public class Adapter_ViewPagerTeam extends FragmentStatePagerAdapter {

    private int id;
    private String type;

    public Adapter_ViewPagerTeam(FragmentManager fm, int id, String type) {
        super(fm);
        this.id = id;
        this.type = type;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return(MatchesFragment.newInstance(this.id, this.type));
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
