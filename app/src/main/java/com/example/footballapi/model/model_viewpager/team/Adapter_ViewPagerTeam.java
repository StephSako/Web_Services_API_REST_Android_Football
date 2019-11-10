package com.example.footballapi.model.model_viewpager.team;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.footballapi.view.fragments.MapFragment;
import com.example.footballapi.view.fragments.MatchesFragment;
import com.example.footballapi.view.fragments.SquadFragment;

public class Adapter_ViewPagerTeam extends FragmentStatePagerAdapter {

    private int id;
    private String type;
    private String address;
    private String teamName;

    public Adapter_ViewPagerTeam(FragmentManager fm, int id, String type, String teamName, String address) {
        super(fm);
        this.id = id;
        this.type = type;
        this.teamName = teamName;
        this.address = address;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return(MatchesFragment.newInstance(this.id, this.type));
            case 1: return(SquadFragment.newInstance(this.id));
            case 2: return(MapFragment.newInstance(this.teamName, this.address));
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Matches";
            case 1: return "Équipe";
            case 2: return "Maps";
            default: return null;
        }
    }
}
