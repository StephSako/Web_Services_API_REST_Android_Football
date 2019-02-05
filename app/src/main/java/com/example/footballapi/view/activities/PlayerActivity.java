package com.example.footballapi.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.footballapi.R;
import com.example.footballapi.controleur.PlayerController;
import com.example.footballapi.view.fragments.SquadFragment;

public class PlayerActivity extends AppCompatActivity {

    public PlayerActivity(){ }

    private PlayerController playercontroller = new PlayerController();

    public int idPlayer = -1;
    public String nomClub = "";

    public ImageView logo_club_player;
    /*public TextView tvClubPlayer;*/
    public TextView tvPlayerName;
    public TextView tvBirthday;
    public TextView tvNationality;
    public TextView tvPostePlayer;
    public TextView tvShirtNumberPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classement_activity);

        this.logo_club_player = findViewById(R.id.ivLogoClubPlayer);
        /*this.tvClubPlayer = */
        this.tvPlayerName = findViewById(R.id.tvPlayerName);
        this.tvBirthday = findViewById(R.id.tvBirthday);
        this.tvNationality = findViewById(R.id.tvNationality);
        this.tvPostePlayer = findViewById(R.id.tvPostePlayer);
        this.tvShirtNumberPlayer = findViewById(R.id.tvShirtNumberPlayer);

        // On récupère l'id du joueur depuis le fragment père de la squad
        Intent intent = getIntent();
        this.idPlayer = intent.getIntExtra(SquadFragment.CLE_DONNEES_ID_PLAYER, 1);
        this.nomClub = intent.getStringExtra(SquadFragment.CLE_DONNEES_NOM_CLUB);

        playercontroller.afficheDetailsJoueur(this.idPlayer, this, this, getString(R.string.token));
    }
}
