package com.example.footballapi.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.footballapi.R;
import com.example.footballapi.view.fragments.SquadFragment;

public class TeamActivity extends AppCompatActivity {

    // Id de la compétition
    public int idTeam = -1;

    //méthode permettant de récupérer le numéro de table
    public int getidTeam(){
        return this.idTeam;
    }

    /*Button btnSquad;
    Button btnMatches;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_activity);

        /*btnSquad = findViewById(R.id.btnSquad);
        btnMatches = findViewById(R.id.btnMatches);

        btnSquad.setOnClickListener(this);
        btnMatches.setOnClickListener(this);*/

        // On récupere l'id de l'équipe depuis l'activite mère
        Intent intent = getIntent();
        this.idTeam = intent.getIntExtra(ClassementActivity.CLE_DONNEES_ID_TEAM, 1);

        // On affiche le fragment du classement de la compétition choisie
        SquadFragment simpleFragment = SquadFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.add(R.id.idFragmentSquad_Match,
                simpleFragment).addToBackStack(null).commit();
    }

    /*public void onClick(View v) {
        if (v.getId() == R.id.btnSquad) { // On affiche la liste des joueurs de l'équipe

        }
        else if (v.getId() == R.id.btnMatches) {

        }
    }*/
}
