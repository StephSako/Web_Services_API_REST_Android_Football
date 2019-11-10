package com.example.footballapi.view.fragments;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.footballapi.R;
import com.example.footballapi.controleur.TeamController;
import com.example.footballapi.model.model_viewpager.team.Adapter_ViewPagerTeam;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

@SuppressLint("Registered")
public class TeamFragment extends Fragment {

    // Variables qui seront transmises dans la vue Player
    public int idTeam = -1;
    public String nomClub = "";
    public String address = "";
    public String crestURLPlayer = "";

    public TextView tvWebSite;
    public TextView tvStade;
    public TextView tvActiveCompetitions;
    public TextView tvEntraineur;
    public ImageView logo_club;
    public View contextView;

    public boolean loadingPicsTeam;

    private static final String KEY_ID = "idTeam";

    public TeamFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_team, container, false);

        TeamController teamcontroller = new TeamController(this);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
        this.loadingPicsTeam = sharedPref.getBoolean("logosTeam", true);

        this.tvWebSite = v.findViewById(R.id.tvWebsite);
        this.tvStade = v.findViewById(R.id.tvStade);
        this.tvActiveCompetitions = v.findViewById(R.id.tvActiveCompetitions);
        this.tvEntraineur = v.findViewById(R.id.tvEntraineur);
        this.contextView = v.findViewById(R.id.team_fragment);

        logo_club = v.findViewById(R.id.logo_club);

        // On récupère l'id de l'équipe venant du classement
        Bundle bundle = this.getArguments();
        if (bundle != null) this.idTeam = bundle.getInt(KEY_ID, -1);

        teamcontroller.onCreate(getString(R.string.token), this.idTeam);

        ViewPager viewPager = v.findViewById(R.id.pagerteam);
        Adapter_ViewPagerTeam myPagerAdapter = new Adapter_ViewPagerTeam(getFragmentManager(), this.idTeam, "team", nomClub, address);
        viewPager.setAdapter(myPagerAdapter);
        TabLayout tabLayout = v.findViewById(R.id.tablayoutteam);
        tabLayout.setupWithViewPager(viewPager);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
        this.loadingPicsTeam = sharedPref.getBoolean("logosTeam", true);
    }

    @Override
    public void onStop() {
        super.onStop();
        ActionBar bar = ((AppCompatActivity) Objects.requireNonNull(this.getActivity())).getSupportActionBar();
        Objects.requireNonNull(bar).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorClair)));
    }
}
