package com.example.footballapi.view.fragments;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.footballapi.R;
import com.example.footballapi.controleur.SessionManagerPreferences;
import com.example.footballapi.controleur.TeamController;
import com.example.footballapi.model.model_viewpager.team.Adapter_ViewPagerTeam;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

@SuppressLint("Registered")
public class TeamFragment extends Fragment {

    private static final String CLE_DONNEES_ID_TEAM = "idTeam";

    // Variables qui seront transmises dans la vue Player
    public int idTeam = -1;
    public String nomClub = "";
    public String crestURLPlayer = "";

    public TextView tvWebSite;
    public TextView tvStade;
    public TextView tvActiveCompetitions;
    public TextView tvEntraineur;
    public ImageView logo_club;
    public View contextView;

    public boolean loadingPicsTeam;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_team, container, false);

        TeamController teamcontroller = new TeamController(this);

        // Récupérer les valeurs choisies
        // Préférences du switch pour afficher les logos
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
        // Préférences du switch pour afficher les logos
        this.loadingPicsTeam = sharedPref.getBoolean("logosTeam", true);

        this.tvWebSite = v.findViewById(R.id.tvWebsite);
        this.tvStade = v.findViewById(R.id.tvStade);
        this.tvActiveCompetitions = v.findViewById(R.id.tvActiveCompetitions);
        this.tvEntraineur = v.findViewById(R.id.tvEntraineur);
        this.contextView = v.findViewById(R.id.team_activity);

        logo_club = v.findViewById(R.id.logo_club);

        // On récupère l'id de l'équipe venant du classement
        Bundle bundle = this.getArguments();
        if (bundle != null) this.idTeam = bundle.getInt(CLE_DONNEES_ID_TEAM, -1);

        teamcontroller.onCreate(getString(R.string.token), this.idTeam);

        ViewPager viewPager = v.findViewById(R.id.pagerteam);
        Adapter_ViewPagerTeam myPagerAdapter = new Adapter_ViewPagerTeam(getFragmentManager(), this.idTeam, "team");
        viewPager.setAdapter(myPagerAdapter);
        TabLayout tabLayout = v.findViewById(R.id.tablayoutteam);
        tabLayout.setupWithViewPager(viewPager);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Préférences du switch pour afficher les logos
        this.loadingPicsTeam = new SessionManagerPreferences(Objects.requireNonNull(this.getContext())).logosDisplayed();
    }
}
