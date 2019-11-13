package com.example.footballapi.model.model_viewpager.team;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.footballapi.view.fragments.MapFragment;
import com.example.footballapi.view.fragments.MatchesFragment;
import com.example.footballapi.view.fragments.SquadFragment;
import com.example.footballapi.view.fragments.TeamWebFragment;

public class Adapter_ViewPagerTeam extends FragmentStatePagerAdapter {

    private int id;
    private String type;
    private String address;
    private String teamName;
    private String teamURL;

    public Adapter_ViewPagerTeam(FragmentManager fm, int id, String type, String teamName, String address, String teamURL) {
        super(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.id = id;
        this.type = type;
        this.teamName = teamName;
        this.address = address;
        this.teamURL = teamURL;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return MatchesFragment.newInstance(this.id, this.type);
            case 1: return SquadFragment.newInstance(this.id);
            case 2: return MapFragment.newInstance(this.teamName, this.address);
            case 3: return TeamWebFragment.newInstance(this.teamURL);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Matches";
            case 1: return "Équipe";
            case 2: return "Maps";
            case 3: return "Web";
            default: return null;
        }
    }
}
