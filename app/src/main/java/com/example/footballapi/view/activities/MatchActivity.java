package com.example.footballapi.view.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.footballapi.R;
import com.example.footballapi.controleur.MatchController;
import com.example.footballapi.model.model_recyclerview.matches.AdapterRV_Matches;

public class MatchActivity extends AppCompatActivity {

    private MatchController matchController;

    public int idMatch = -1;

    public ImageView logo_club_home;
    public ImageView logo_club_away;
    public TextView tvVictoiresHome;
    public TextView tvVictoiresAway;
    public TextView tvDefaitesHome;
    public TextView tvDefaitesAway;
    public TextView tvNuls;
    public TextView tvTotaux;
    public TextView tvMatchDate;
    public TextView tvButsTotaux;

    public boolean loadingPicsPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_activity);
        matchController = new MatchController(this);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        this.loadingPicsPlayer = sharedPref.getBoolean("logosPlayer", true);

        this.logo_club_home = findViewById(R.id.ivLogoClubHome);
        this.logo_club_away = findViewById(R.id.ivLogoClubAway);
        this.tvVictoiresHome = findViewById(R.id.tvVictoiresHome);
        this.tvVictoiresAway = findViewById(R.id.tvVictoiresAway);
        this.tvDefaitesHome = findViewById(R.id.tvDefaitesHome);
        this.tvDefaitesAway = findViewById(R.id.tvDefaitesAway);
        this.tvNuls = findViewById(R.id.tvNuls);
        this.tvTotaux = findViewById(R.id.tvMatchesTotaux);
        this.tvMatchDate = findViewById(R.id.tvMatchDate);
        this.tvButsTotaux = findViewById(R.id.tvButsTotaux);

        Intent intent = getIntent();
        this.idMatch = intent.getIntExtra(AdapterRV_Matches.CLE_DONNEES_ID_MATCH, -1);

        matchController.onCreate(getString(R.string.token));
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
