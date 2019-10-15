package com.example.footballapi.view.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.footballapi.R;
import com.example.footballapi.controleur.PlayerController;
import com.example.footballapi.model.model_recyclerview.squad.AdapterRV_Squad;

public class PlayerActivity extends AppCompatActivity {

    public PlayerActivity(){ }

    private PlayerController playercontroller;

    public int idPlayer = -1;
    public String nomClub = "";
    public String crestURLPlayer = "";

    public ImageView logo_club_player;
    public TextView tvClubPlayer;
    public TextView tvPlayerName;
    public TextView tvBirthday;
    public TextView tvNationality;
    public TextView tvPostePlayer;
    public TextView tvShirtNumberPlayer;
    public View contextView;

    public boolean loadingPicsPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        playercontroller = new PlayerController(this);

        // Récupérer les valeurs choisies
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        // Préférences du switch pour afficher les logos
        this.loadingPicsPlayer = sharedPref.getBoolean("logosPlayer", true);

        this.logo_club_player = findViewById(R.id.ivLogoClubPlayer);
        this.tvClubPlayer = findViewById(R.id.tvClubname);
        this.tvPlayerName = findViewById(R.id.tvPlayerName);
        this.tvBirthday = findViewById(R.id.tvBirthday);
        this.tvNationality = findViewById(R.id.tvNationality);
        this.tvPostePlayer = findViewById(R.id.tvPostePlayer);
        this.tvShirtNumberPlayer = findViewById(R.id.tvShirtNumberPlayer);
        this.contextView = findViewById(R.id.player_activity);

        // On récupère l'id du joueur depuis le fragment père de la squad
        Intent intent = getIntent();
        this.idPlayer = intent.getIntExtra(AdapterRV_Squad.CLE_DONNEES_ID_PLAYER, 1);
        this.nomClub = intent.getStringExtra(AdapterRV_Squad.CLE_DONNEES_NOM_CLUB);
        this.crestURLPlayer = intent.getStringExtra(AdapterRV_Squad.CLE_DONNEES_CRUST_URL);

        playercontroller.onCreate(getString(R.string.token));
    }

    @Override
    public void onResume() {
        super.onResume();
        // Récupérer les valeurs choisies
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        // Préférences du switch pour afficher les logos
        this.loadingPicsPlayer = sharedPref.getBoolean("logosPlayer", true);
    }

    // Affichage du menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    // Écouteur sur le menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
