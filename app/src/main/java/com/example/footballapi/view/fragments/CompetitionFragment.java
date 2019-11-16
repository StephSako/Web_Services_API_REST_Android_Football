package com.example.footballapi.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.footballapi.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class CompetitionFragment extends Fragment {

    private int idCompet = -1;
    private final static String KEY_ID = "idForMatches";

    private ClassementFragment classementFragment;
    private MatchesFragment matchesFragment;

    public CompetitionFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_competition, container, false);

        if (this.getArguments() != null) idCompet = this.getArguments().getInt(KEY_ID, -1);

        BottomNavigationView navigation = v.findViewById(R.id.bottom_bar_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        this.classementFragment = ClassementFragment.newInstance(this.idCompet);
        Objects.requireNonNull(getFragmentManager()).beginTransaction().setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_left).replace(R.id.fragment_hoster_compet, this.classementFragment).commit();

        return v;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.classement_bottom_bar_nav:
                    Objects.requireNonNull(getFragmentManager()).beginTransaction().setCustomAnimations(R.anim.slide_from_left, R.anim.slide_to_right).replace(R.id.fragment_hoster_compet, classementFragment).commit();
                    return true;

                case R.id.matches_bottom_bar_nav:
                    if (matchesFragment == null)
                        matchesFragment = MatchesFragment.newInstance(idCompet, "competition");
                    Objects.requireNonNull(getFragmentManager()).beginTransaction().setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_left).replace(R.id.fragment_hoster_compet, matchesFragment).commit();
                    return true;
            }
            return false;
        }

    };

}