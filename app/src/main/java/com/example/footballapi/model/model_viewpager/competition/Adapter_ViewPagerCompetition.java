package com.example.footballapi.model.model_viewpager.competition;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.footballapi.view.fragments.ClassementFragment;
import com.example.footballapi.view.fragments.MatchesFragment;

public class Adapter_ViewPagerCompetition extends FragmentStatePagerAdapter {

    private int id;

    public Adapter_ViewPagerCompetition(FragmentManager fm, int id) {
        super(fm);
        this.id = id;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return(ClassementFragment.newInstance(this.id));
            case 1: return(MatchesFragment.newInstance(this.id));
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
