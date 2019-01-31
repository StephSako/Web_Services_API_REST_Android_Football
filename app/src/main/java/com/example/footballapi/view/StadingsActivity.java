package com.example.footballapi.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.footballapi.R;

public class StadingsActivity extends AppCompatActivity {

    int idCompet = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stadings_activity);

        // On récupere l'i de la competition depuis l'activite mère
        Intent intent = getIntent();
        idCompet = intent.getIntExtra(MainActivity.CLE_DONNEES_ID_COMPET, 1);
    }
}