package com.example.footballapi.view.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.footballapi.R;
import com.example.footballapi.controleur.BetController;
import com.example.footballapi.controleur.MatchController;
import com.example.footballapi.controleur.PourcentBetController;
import com.example.footballapi.model.model_dao.DataBase;
import com.example.footballapi.model.model_recyclerview.matches.AdapterRV_Matches;
import com.example.footballapi.services.SessionManagerPreferences;

public class MatchActivity extends AppCompatActivity implements View.OnClickListener {

    private BetController betController;
    private PourcentBetController pourcentBetController;

    public int idMatch = -1;
    public int idHome = -1;
    public int idAway = -1;
    public String status = "";

    public ImageView logo_club_home;
    public ImageView logo_club_away;
    public TextView tvVictoiresHome;
    public TextView tvVictoiresAway;
    public TextView tvPourcentHome;
    public TextView tvPourcentAway;
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
    public ProgressBar pbVictoriesHome;
    public ProgressBar pbVictoriesAway;
    public ProgressBar pbDefeatsHome;
    public ProgressBar pbDefeatsAway;
    public TextView tvGoalHomeHT;
    public TextView tvGoalAwayHT;
    public TextView tvGoalHomeFT;
    public TextView tvGoalAwayFT;
    public TextView tvVenue;
    public TextView tvNameHome;
    public TextView tvNameAway;
    public TextView tvNbParieurs;

    public boolean loadingPicsPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        MatchController matchController = new MatchController(this);
        betController = new BetController(this);
        pourcentBetController = new PourcentBetController(this);

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
        this.tvPourcentAway = findViewById(R.id.tvPourcentAway);
        this.tvPourcentHome = findViewById(R.id.tvPourcentHome);
        this.tvGoalHomeHT = findViewById(R.id.tvGoalHomeHT);
        this.tvGoalAwayHT = findViewById(R.id.tvGoalAwayHT);
        this.tvGoalHomeFT = findViewById(R.id.tvGoalHomeFT);
        this.tvGoalAwayFT = findViewById(R.id.tvGoalAwayFT);
        this.tvVenue = findViewById(R.id.tvVenue);
        this.layoutBetButtons = findViewById(R.id.layoutBetButtons);
        this.tvNameHome = findViewById(R.id.tvNameHome);
        this.tvNameAway = findViewById(R.id.tvNameAway);
        this.tvNbParieurs = findViewById(R.id.tvNbParieurs);

        this.pbVictoriesHome = findViewById(R.id.pbVictoriesHome);
        this.pbVictoriesHome.setRotation(180);
        this.pbVictoriesAway = findViewById(R.id.pbVictoriesAway);
        this.pbVictoriesHome.getProgressDrawable().setColorFilter(Color.rgb(70,149,22), android.graphics.PorterDuff.Mode.SRC_IN);
        this.pbVictoriesAway.getProgressDrawable().setColorFilter(Color.rgb(70,149,22), android.graphics.PorterDuff.Mode.SRC_IN);

        this.pbDefeatsHome = findViewById(R.id.pbDefeatsHome);
        this.pbDefeatsHome.setRotation(180);
        this.pbDefeatsAway = findViewById(R.id.pbDefeatsAway);
        this.pbDefeatsHome.getProgressDrawable().setColorFilter(Color.rgb(202,44,30), android.graphics.PorterDuff.Mode.SRC_IN);
        this.pbDefeatsAway.getProgressDrawable().setColorFilter(Color.rgb(202,44,30), android.graphics.PorterDuff.Mode.SRC_IN);

        this.btnWinnerHome.setOnClickListener(this);
        this.btnWinnerAway.setOnClickListener(this);

        Intent intent = getIntent();
        this.idMatch = intent.getIntExtra(AdapterRV_Matches.CLE_DONNEES_ID_MATCH, -1);
        this.idHome = intent.getIntExtra(AdapterRV_Matches.CLE_DONNEES_ID_HOME, -1);
        this.idAway = intent.getIntExtra(AdapterRV_Matches.CLE_DONNEES_ID_AWAY, -1);
        this.status = intent.getStringExtra(AdapterRV_Matches.CLE_DONNEES_STATUS);

        this.tvMatchDate.setTypeface(null, Typeface.BOLD);
        this.tvVenue.setTypeface(null, Typeface.BOLD);
        this.tvPourcentHome.setTypeface(null, Typeface.BOLD);
        this.tvPourcentAway.setTypeface(null, Typeface.BOLD);
        this.tvGoalAwayFT.setTypeface(null, Typeface.BOLD);
        this.tvGoalAwayHT.setTypeface(null, Typeface.BOLD);
        this.tvGoalHomeFT.setTypeface(null, Typeface.BOLD);
        this.tvGoalHomeHT.setTypeface(null, Typeface.BOLD);
        this.tvNbParieurs.setTypeface(null, Typeface.BOLD);

        assert this.status != null;
        if (this.status.equals("LIVE") || this.status.equals("IN_PLAY") || this.status.equals("FINISHED") || this.status.equals("PAUSED") || this.status.equals("SUSPENDED") || new SessionManagerPreferences(this).isBet(this.idMatch) != -1)
            this.layoutBetButtons.setVisibility(LinearLayout.GONE);

        // On bloque les boutons selon les paris existants
        if (new SessionManagerPreferences(this).isBet(this.idMatch) != -1){
            if (new SessionManagerPreferences(this).isBet(this.idMatch) == idHome){
                this.btnWinnerHome.setEnabled(false);
                this.btnWinnerAway.setEnabled(true);
            }else if (new SessionManagerPreferences(this).isBet(this.idMatch) == idAway){
                this.btnWinnerHome.setEnabled(true);
                this.btnWinnerAway.setEnabled(false);
            }
        }

        matchController.onCreate(getString(R.string.token), this.idMatch, new DataBase(this).findTeamCrest(this.idHome), new DataBase(this).findTeamCrest(this.idAway));
        pourcentBetController.onCreate(this.idMatch, this.idHome, this.idAway);
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

    public void onClick(View v) {
        if (v.getId() == R.id.btnWinnerHome){
            this.layoutBetButtons.setVisibility(LinearLayout.GONE);
            betController.onCreate(idMatch, new SessionManagerPreferences(this).getIdSupporter(), idHome);
            pourcentBetController.onCreate(this.idMatch, this.idHome, this.idAway);
        }
        else if (v.getId() == R.id.btnWinnerAway){
            this.layoutBetButtons.setVisibility(LinearLayout.GONE);
            betController.onCreate(idMatch, new SessionManagerPreferences(this).getIdSupporter(), idAway);
            pourcentBetController.onCreate(this.idMatch, this.idHome, this.idAway);
        }
    }
}
