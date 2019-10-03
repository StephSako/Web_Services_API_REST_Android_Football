package com.example.footballapi.view.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.footballapi.R;
import com.example.footballapi.controleur.PlayerController;
import com.example.footballapi.model.model_recyclerview.squad.AdapterRV_Squad;

public class ConnexionActivity extends AppCompatActivity {

    public ConnexionActivity(){ }

    private PlayerController playercontroller;

    public EditText etPseudo;
    public EditText etPassword;
    public Button btnConnexion;
    public Button btnInscription;

    public boolean loadingPicsPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_activity);
        playercontroller = new PlayerController(this);

        // Récupérer les valeurs choisies
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        // Préférences du switch pour afficher les logos
        this.loadingPicsPlayer = sharedPref.getBoolean("logosPlayer", true);

        this.logo_club_player = findViewById(R.id.ivLogoClubPlayer);
        this.tvClubPlayer = findViewById(R.id.tvClubname);

        playercontroller.onCreate(getString(R.string.token));
    }


}
