package com.example.footballapi.model.model_viewpager.competition;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.footballapi.view.fragments.ClassementFragment;
import com.example.footballapi.view.fragments.MatchesFragment;

public class Adapter_ViewPagerCompetition extends FragmentStatePagerAdapter {

    private int id;
    private String type;

    public Adapter_ViewPagerCompetition(FragmentManager fm, int id, String type) {
        super(fm);
        this.id = id;
        this.type = type;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return(ClassementFragment.newInstance(this.id));
            case 1: return(MatchesFragment.newInstance(this.id, this.type));
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
            case 0: return "Classement";
            case 1: return "Matches";
            default: return null;
        }
    }
}