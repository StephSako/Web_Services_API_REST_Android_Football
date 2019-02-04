package com.example.footballapi.view.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.footballapi.R;
import com.example.footballapi.controleur.TeamController;
import com.example.footballapi.view.fragments.MatchesFragment;
import com.example.footballapi.view.fragments.SquadFragment;

public class TeamActivity extends AppCompatActivity implements View.OnClickListener {

    // Id de l'équipe
    private int idTeam = -1;

    public int getidTeam(){
        return this.idTeam;
    }

    private Button btnSquad;
    private Button btnMatches;
    private TextView tvWebSite;
    private TextView tvStade;
    private TextView tvActiveCompetitions;
    private ImageView logo_club;

    public TextView getTvActiveCompetitions() { return tvActiveCompetitions; }
    public TextView getTvWebSite() { return tvWebSite; }
    public TextView getTvStade() { return tvStade; }
    public ImageView getLogo_club() { return logo_club; }

    private TeamController teamcontroller = new TeamController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_activity);

        btnSquad = findViewById(R.id.btnSquad);
        btnMatches = findViewById(R.id.btnMatches);

        btnSquad.setOnClickListener(this);
        btnMatches.setOnClickListener(this);

        tvWebSite = findViewById(R.id.tvWebsite);
        tvStade = findViewById(R.id.tvStade);
        tvActiveCompetitions = findViewById(R.id.tvActiveCompetitions);

        logo_club = findViewById(R.id.logo_club);

        // On récupère l'id de l'équipe depuis l'activite mère
        Intent intent = getIntent();
        this.idTeam = intent.getIntExtra(ClassementActivity.CLE_DONNEES_ID_TEAM, 1);

        teamcontroller.afficheDetailsTeam(this.idTeam, this, getString(R.string.token));

        // On affiche le fragment de la liste de joueurs par défaut (on change la couleur du bouton
        btnMatches.setBackgroundResource(R.color.green);
        btnSquad.setBackgroundResource(R.color.grey_desactivated);

        MatchesFragment simpleFragment = MatchesFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.idFragmentSquad_Match, simpleFragment).addToBackStack(null).commit();
    }

    public void onClick(View v) { // On remplace le fragment par celui géré par le bouton cliqué
        if (v.getId() == R.id.btnSquad) { // On affiche la liste des joueurs de l'équipe

            btnMatches.setBackgroundResource(R.color.grey_desactivated);
            btnSquad.setBackgroundResource(R.color.green);

            SquadFragment simpleFragment = SquadFragment.newInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.idFragmentSquad_Match, simpleFragment).addToBackStack(null).commit();
        }
        else if (v.getId() == R.id.btnMatches) { // On affiche la liste des matches de l'équipe

            btnSquad.setBackgroundResource(R.color.grey_desactivated);
            btnMatches.setBackgroundResource(R.color.green);

            MatchesFragment simpleFragment = MatchesFragment.newInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.idFragmentSquad_Match, simpleFragment).addToBackStack(null).commit();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, ClassementActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
        finish();
    }
}
