package com.example.footballapi.view.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.footballapi.R;
import com.example.footballapi.model.model_viewpager.competition.Adapter_ViewPagerCompetition;
import com.google.android.material.tabs.TabLayout;

import java.util.HashMap;

public class CompetitionFragment extends Fragment {

    public int idCompet = -1;
    final static String CLE_DONNEES_ID_COMPET = "idCompet";

    @SuppressLint("UseSparseArrays")
    public static HashMap<Integer, String> teamsNameCrests = new HashMap<>();

    public static void setTeamsNameCrests(HashMap<Integer, String> teams){
        teamsNameCrests = teams;
    }

    public static String getTeamCrest(int team){
        return teamsNameCrests.get(team);
    }

    public CompetitionFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_competition, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) idCompet = bundle.getInt(CLE_DONNEES_ID_COMPET, -1);

        ViewPager viewPager = v.findViewById(R.id.pagerCompet);
        Adapter_ViewPagerCompetition myPagerAdapter = new Adapter_ViewPagerCompetition(getFragmentManager(), idCompet, "competition");
        viewPager.setAdapter(myPagerAdapter);
        TabLayout tabLayout = v.findViewById(R.id.tablayoutCompet);
        tabLayout.setupWithViewPager(viewPager);

        return v;
    }

}