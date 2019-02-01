package com.example.footballapi.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.footballapi.R;
import com.example.footballapi.view.fragments.SquadFragment;

public class TeamActivity  extends AppCompatActivity {

    public int idTeam = -1;

    public int getidTeam(){
        return this.idTeam;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classement_activity);

        // On récupere l'id de la competition depuis l'activite mère
        Intent intent = getIntent();
        this.idTeam = intent.getIntExtra(MainActivity.CLE_DONNEES_ID_COMPET, 1);

        Toast.makeText(getApplicationContext(), "Compétition introuvable", Toast.LENGTH_SHORT).show();

        // On affiche le fragment du classement de la compétition choisie
        SquadFragment simpleFragment = SquadFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.add(R.id.idFragmentSquad_Match,
                simpleFragment).addToBackStack(null).commit();
    }

    // CAS DU CLICK SUR LE BOUTON MATCHES OU SQUAD
    // => CHANGEMENT DE FRAGMENT
}
