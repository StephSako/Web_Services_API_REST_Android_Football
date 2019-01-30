package com.example.footballapi.view;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.footballapi.R;
import com.example.footballapi.controleur.PlayerController;
import com.example.footballapi.controleur.TeamController;

public class MainActivity extends AppCompatActivity {

    // Elements de la vue de l'activité
    public TextView tvGeneralActivity;

    private PlayerController playercontroller;
    private TeamController teamcontroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //récupérer le textview
        tvGeneralActivity = findViewById(R.id.tvGeneralActivity);
        tvGeneralActivity.setText("");

        playercontroller = new PlayerController(this);
        teamcontroller = new TeamController(this);

        //affichage du fragment principal
        getFragmentManager().beginTransaction()
                .replace(R.id.fragmentMainFragment, new ListFragment(), "ListFragment")
                .commit();
    }

    /*public void showPlayer() {
        this.playercontroller.afficheDetailsJoueur(138); // Marco Reus = 138
    }

    public void showSquad() {
        this.teamcontroller.afficheListePlayersTeams(4); // Dortmund = 4
    }

    public void showTeamDetails() {
        this.teamcontroller.afficheDetailsTeams(4); // Dortmund = 4
    }

    public void showTeamMatches() {
        this.teamcontroller.afficheListeMatchesTeams(4); // Dortmund = 4
    }*/
}