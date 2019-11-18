package com.example.footballapi.view.fragments;

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

import com.example.footballapi.R;
import com.example.footballapi.controleur.PlayerController;

public class PlayerFragment extends Fragment {

    public PlayerFragment(){ }

    public int idPlayer = -1;
    public String nomClub = "";
    public String crestURLPlayer = "";

    private static final String CLE_DONNEES_ID_PLAYER = "idPlayer";
    private static final String CLE_DONNEES_NOM_CLUB = "nomClub";
    private static final String CLE_DONNEES_CRUST_URL = "crestTeam";

    public ImageView logo_club_player;
    public TextView tvClubPlayer;
    public TextView tvPlayerName;
    public TextView tvBirthday;
    public TextView tvNationality;
    public TextView tvPostePlayer;
    public View contextView;
    public View v;

    public boolean loadingPicsPlayer;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_player, container, false);
        PlayerController playercontroller = new PlayerController(this);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
        this.loadingPicsPlayer = sharedPref.getBoolean("logosPlayer", true);

        this.logo_club_player = v.findViewById(R.id.ivLogoClubPlayer);
        this.tvClubPlayer = v.findViewById(R.id.tvClubname);
        this.tvPlayerName = v.findViewById(R.id.tvPlayerName);
        this.tvBirthday = v.findViewById(R.id.tvBirthday);
        this.tvNationality = v.findViewById(R.id.tvNationality);
        this.tvPostePlayer = v.findViewById(R.id.tvPostePlayer);
        this.contextView = v.findViewById(R.id.player_activity);

        Bundle bundle = this.getArguments();
        if (bundle != null){
            this.idPlayer = bundle.getInt(CLE_DONNEES_ID_PLAYER, 1);
            this.nomClub = bundle.getString(CLE_DONNEES_NOM_CLUB);
            this.crestURLPlayer = bundle.getString(CLE_DONNEES_CRUST_URL);
        }

        playercontroller.onCreate(getString(R.string.token));

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Récupérer les valeurs choisies
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
        // Préférences du switch pour afficher les logos
        this.loadingPicsPlayer = sharedPref.getBoolean("logosPlayer", true);
    }
}
