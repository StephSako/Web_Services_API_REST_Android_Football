package com.example.footballapi.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.footballapi.R;
import com.example.footballapi.controleur.PlayerController;
import com.example.footballapi.view.fragments.SquadFragment;

public class PlayerActivity extends AppCompatActivity {

    public PlayerActivity(){ }

    private PlayerController playercontroller = new PlayerController();

    private int idPlayer = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classement_activity);

        // On récupère l'id du joueur depuis le fragment père de la squad
        Intent intent = getIntent();
        this.idPlayer = intent.getIntExtra(SquadFragment.CLE_DONNEES_ID_PLAYER, 1);

        playercontroller.afficheDetailsJoueur(this.idPlayer, this, this, getString(R.string.token));
    }
}
