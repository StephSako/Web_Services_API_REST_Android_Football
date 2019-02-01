package com.example.footballapi.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.footballapi.R;
import com.example.footballapi.controleur.ClassementController;

public class ClassementActivity extends AppCompatActivity {

    public int idCompet = -1;
    public final static String PCidCompet = "idCompet";
    ListView lvClassement;

    ClassementController classementcontroller = new ClassementController();

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(PCidCompet, idCompet);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classement_activity);

        lvClassement = findViewById(R.id.lvClassement);

        // On récupere l'id de la competition depuis l'activite mère
        Intent intent = getIntent();
        idCompet = intent.getIntExtra(MainActivity.CLE_DONNEES_ID_COMPET, 1);

        classementcontroller.afficheListeTeamsCompetition(idCompet, this, this, getString(R.string.token), lvClassement);
    }
}