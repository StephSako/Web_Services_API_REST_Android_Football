package com.example.footballapi.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.footballapi.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Boutons de l'écran
    ImageButton btnBundesliga;
    ImageButton btnPrimeraDivision;
    ImageButton btnSerieA;
    ImageButton btnLigue1;
    ImageButton btnPremierLeague;
    ImageButton btnLiguaNOS;
    ImageButton btnNetherlands;
    ImageButton btnBresil;

    // Id de la compétition
    public int idCompet = -1;

    //méthode permettant de récupérer le numéro de table
    public int getidCompet(){
        return this.idCompet;
    }

    // Persistance courte
    public final static String PCidCompet = "idCompet";

    // Clef données transmission
    final static String CLE_DONNEES_ID_COMPET = "idCompet";

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(PCidCompet, idCompet);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // Récupérer les buttons
        btnBundesliga = findViewById(R.id.btnBundesliga);
        btnPrimeraDivision = findViewById(R.id.btnPrimeraDivision);
        btnSerieA = findViewById(R.id.btnSerieA);
        btnLigue1 = findViewById(R.id.btnLigue1);
        btnPremierLeague = findViewById(R.id.btnPremierLeague);
        btnLiguaNOS = findViewById(R.id.btnLiguaNOS);
        btnNetherlands = findViewById(R.id.btnNetherlands);
        btnBresil = findViewById(R.id.btnBresil);

        // Ecouteurs sur les boutons
        btnBundesliga.setOnClickListener(this);
        btnPrimeraDivision.setOnClickListener(this);
        btnSerieA.setOnClickListener(this);
        btnLigue1.setOnClickListener(this);
        btnPremierLeague.setOnClickListener(this);
        btnLiguaNOS.setOnClickListener(this);
        btnNetherlands.setOnClickListener(this);
        btnBresil.setOnClickListener(this);

        // Persistance courte
        if (savedInstanceState != null) {
            idCompet = savedInstanceState.getInt(PCidCompet);
        }
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnBundesliga) idCompet = 2002;
        else if (v.getId() == R.id.btnSerieA) idCompet = 2019;
        else if (v.getId() == R.id.btnPremierLeague) idCompet = 2021;
        else if (v.getId() == R.id.btnPrimeraDivision) idCompet = 2014;
        else if (v.getId() == R.id.btnLigue1) idCompet = 2015;
        else if (v.getId() == R.id.btnLiguaNOS) idCompet = 2017;
        else if (v.getId() == R.id.btnNetherlands) idCompet = 2003;
        else if (v.getId() == R.id.btnBresil) idCompet = 2013;

        Intent intent = new Intent(this, ClassementActivity.class);
        intent.putExtra(CLE_DONNEES_ID_COMPET, idCompet);
        startActivity(intent);
    }
}