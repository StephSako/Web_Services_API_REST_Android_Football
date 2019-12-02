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

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.footballapi.R;
import com.example.footballapi.controleur.TeamController;

import java.util.Objects;

@SuppressLint("Registered")
public class TeamFragment extends Fragment {

    // Variables qui seront transmises dans la vue Player
    public int idTeam = -1;
    public String nomClub = "";
    public String address = "";
    public String crestURLPlayer = "";

    public boolean loadingPicsTeam;
    private static final String KEY_ID = "idForMatches";

    public ImageView logo_club;
    public View contextView;
    public View v;

    public TeamFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_team, container, false);

        TeamController teamcontroller = new TeamController(this);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
        this.loadingPicsTeam = sharedPref.getBoolean("logosTeam", true);
        this.contextView = v.findViewById(R.id.team_fragment_info);
        this.logo_club = v.findViewById(R.id.logo_club);

        // On récupère l'id de l'équipe venant du classement
        Bundle bundle = this.getArguments();
        if (bundle != null) this.idTeam = bundle.getInt(KEY_ID, -1);

        teamcontroller.onCreate(getString(R.string.token), this.idTeam);

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
