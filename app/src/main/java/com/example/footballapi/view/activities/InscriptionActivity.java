package com.example.footballapi.view.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.footballapi.R;
import com.example.footballapi.controleur.PlayerController;
import com.example.footballapi.model.model_recyclerview.squad.AdapterRV_Squad;

public class InscriptionActivity extends AppCompatActivity {

    public InscriptionActivity(){ }

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
        this.tvPlayerName = findViewById(R.id.tvPlayerName);
        this.tvBirthday = findViewById(R.id.tvBirthday);
        this.tvNationality = findViewById(R.id.tvNationality);
        this.tvPostePlayer = findViewById(R.id.tvPostePlayer);
        this.tvShirtNumberPlayer = findViewById(R.id.tvShirtNumberPlayer);

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
        // L’item sur lequel l’utilisateur a cliqué
        int id = item.getItemId();
        // Afficher le fragment des préférences
        if (id == R.id.pref) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            return true;
        }
        else if (id == R.id.credits) {
            Intent intent = new Intent(this, CreditsActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            return true;
        }
        else if (id == R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            return true;
        }
        else if (id == R.id.search) {
            Intent intent = new Intent(this, SearchTeamActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
