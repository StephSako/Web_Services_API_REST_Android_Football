package com.example.footballapi.view.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.footballapi.R;
import com.example.footballapi.controleur.BetController;
import com.example.footballapi.controleur.MatchController;
import com.example.footballapi.model.model_recyclerview.matches.AdapterRV_Matches;
import com.example.footballapi.model.model_session_manager.SessionManagerPreferences;

public class MatchActivity extends AppCompatActivity implements View.OnClickListener {

    private BetController betController;

    public int idMatch = -1;
    public int idHome = -1;
    public int idAway = -1;
    public String status = "";

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
    public View contextView;
    public Button btnWinnerHome;
    public Button btnWinnerAway;
    public LinearLayout layoutBetButtons;

    public boolean loadingPicsPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_activity);

        MatchController matchController = new MatchController(this);
        betController = new BetController(this);

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
        this.btnWinnerHome = findViewById(R.id.btnWinnerHome);
        this.btnWinnerAway = findViewById(R.id.btnWinnerAway);
        this.contextView = findViewById(R.id.match_activity);
        this.layoutBetButtons = findViewById(R.id.layoutBetButtons);

        this.btnWinnerHome.setOnClickListener(this);
        this.btnWinnerAway.setOnClickListener(this);

        Intent intent = getIntent();
        this.idMatch = intent.getIntExtra(AdapterRV_Matches.CLE_DONNEES_ID_MATCH, -1);
        this.idHome = intent.getIntExtra(AdapterRV_Matches.CLE_DONNEES_ID_HOME, -1);
        this.idAway = intent.getIntExtra(AdapterRV_Matches.CLE_DONNEES_ID_AWAY, -1);
        this.status = intent.getStringExtra(AdapterRV_Matches.CLE_DONNEES_STATUS);

        assert this.status != null;
        if (this.status.equals("LIVE") || this.status.equals("IN_PLAY") || this.status.equals("FINISHED") || this.status.equals("PAUSED") || this.status.equals("SUSPENDED"))
            this.layoutBetButtons.setVisibility(LinearLayout.GONE);

        // On bloque les boutons selon les paris existants
        if (new SessionManagerPreferences(this).isBet(this.idMatch) != -1){
            if (new SessionManagerPreferences(this).isBet(this.idMatch) == idHome){
                this.btnWinnerHome.setBackgroundColor(Color.WHITE);
                this.btnWinnerHome.setEnabled(false);
                this.btnWinnerAway.setEnabled(true);
            }else if (new SessionManagerPreferences(this).isBet(this.idMatch) == idAway){
                this.btnWinnerHome.setEnabled(true);
                this.btnWinnerAway.setEnabled(false);
                this.btnWinnerAway.setBackgroundColor(Color.WHITE);
            }
        }

        matchController.onCreate(getString(R.string.token), this.idMatch, CompetitionActivity.getTeamCrest(this.idHome), CompetitionActivity.getTeamCrest(this.idAway));
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

    public void onClick(View v) {
        if (v.getId() == R.id.btnWinnerHome){
            this.btnWinnerHome.setEnabled(false);
            this.btnWinnerAway.setEnabled(true);
            betController.onCreate(idMatch, new SessionManagerPreferences(this).getIdSupporter(), idHome);
        }
        else if (v.getId() == R.id.btnWinnerAway){
            this.btnWinnerAway.setEnabled(false);
            this.btnWinnerHome.setEnabled(true);
            betController.onCreate(idMatch, new SessionManagerPreferences(this).getIdSupporter(), idAway);
        }
    }
}
