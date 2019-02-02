package com.example.footballapi.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.footballapi.R;
import com.example.footballapi.controleur.TeamController;
import com.example.footballapi.view.fragments.MatchesFragment;
import com.example.footballapi.view.fragments.SquadFragment;

public class TeamActivity extends AppCompatActivity implements View.OnClickListener {

    // Id de l'équipe
    public int idTeam = -1;

    public int getidTeam(){
        return this.idTeam;
    }

    Button btnSquad;
    Button btnMatches;
    public TextView tvTeamsColors;
    public TextView tvStade;
    public TextView tvActiveCompetitions;

    TeamController teamcontroller = new TeamController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_activity);

        btnSquad = findViewById(R.id.btnSquad);
        btnMatches = findViewById(R.id.btnMatches);

        btnSquad.setOnClickListener(this);
        btnMatches.setOnClickListener(this);

        tvTeamsColors = findViewById(R.id.tvTeamsColors);
        tvStade = findViewById(R.id.tvStade);
        tvActiveCompetitions = findViewById(R.id.tvActiveCompetitions);

        // On récupere l'id de l'équipe depuis l'activite mère
        Intent intent = getIntent();
        this.idTeam = intent.getIntExtra(ClassementActivity.CLE_DONNEES_ID_TEAM, 1);

        teamcontroller.afficheDetailsTeam(this.idTeam, this, getString(R.string.token));

        // On affiche le fragment de la liste de joueurs par défaut
        SquadFragment simpleFragment = SquadFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.add(R.id.idFragmentSquad_Match,
                simpleFragment).addToBackStack(null).commit();
    }

    public void onClick(View v) { // On remplace le fragment par celui géré par le bouton cliqué
        if (v.getId() == R.id.btnSquad) { // On affiche la liste des joueurs de l'équipe
            SquadFragment simpleFragment = SquadFragment.newInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager
                    .beginTransaction();
            fragmentTransaction.replace(R.id.idFragmentSquad_Match,
                    simpleFragment).addToBackStack(null).commit();
        }
        else if (v.getId() == R.id.btnMatches) { // On affiche la liste des matches de l'équipe
            MatchesFragment simpleFragment = MatchesFragment.newInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager
                    .beginTransaction();
            fragmentTransaction.replace(R.id.idFragmentSquad_Match,
                    simpleFragment).addToBackStack(null).commit();
        }
    }
}
