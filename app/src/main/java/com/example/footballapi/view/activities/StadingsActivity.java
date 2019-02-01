package com.example.footballapi.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.footballapi.R;
import com.example.footballapi.view.fragments.ClassementFragment;

public class StadingsActivity extends AppCompatActivity {

    public int idCompet = -1;
    public final static String PCidCompet = "idCompet";

    public int getidCompet(){
        return this.idCompet;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(PCidCompet, idCompet);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stadings_activity);

        // On récupere l'id de la competition depuis l'activite mère
        Intent intent = getIntent();
        idCompet = intent.getIntExtra(MainActivity.CLE_DONNEES_ID_COMPET, 1);

        // On affiche le fragment du classement de la compétition choisie
        ClassementFragment simpleFragment = ClassementFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.add(R.id.idFragmentClassement,
                simpleFragment).addToBackStack(null).commit();
    }
}