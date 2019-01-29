package com.example.footballapi.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.footballapi.R;
import com.example.footballapi.controleur.ClassementController;
import com.example.footballapi.controleur.PlayerController;
import com.example.footballapi.controleur.TeamController;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MainActivityInterface {

    // Elements de la vue de l'activité
    public Button btnPrintJoueur;
    public Button btnPrintStadings;
    public Button btnPrintDetailsTeam;
    public Button btnPrintListePlayersTeam;
    public Button btnPrintMatchesTeam;
    public TextView tvStandings;

    private PlayerController playercontroller;
    private TeamController teamcontroller;
    private ClassementController classementcontroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_player);

        //récupérer le bouton et la listView
        btnPrintJoueur = findViewById(R.id.btnPrintJoueur);
        btnPrintStadings= findViewById(R.id.btnPrintStadings);
        btnPrintDetailsTeam= findViewById(R.id.btnPrintDetailsTeam);
        btnPrintListePlayersTeam= findViewById(R.id.btnPrintListePlayersTeam);
        btnPrintMatchesTeam= findViewById(R.id.btnPrintMatchesTeam);
        tvStandings = findViewById(R.id.tvStandings);
        tvStandings.setText("");

        //écouteurs sur le bouton
        btnPrintJoueur.setOnClickListener(this);
        btnPrintStadings.setOnClickListener(this);
        btnPrintDetailsTeam.setOnClickListener(this);
        btnPrintListePlayersTeam.setOnClickListener(this);
        btnPrintMatchesTeam.setOnClickListener(this);

        playercontroller = new PlayerController(this);
        teamcontroller = new TeamController(this);
        classementcontroller = new ClassementController(this);
    }

    public void onClick(View v) {
        tvStandings.setText("");
        if (v.getId() == R.id.btnPrintJoueur) {
            showPlayer();
            //afficheDetailsJoueur(Integer.parseInt(tvJoueur.getText().toString()));
        }
        else if (v.getId() == R.id.btnPrintStadings) showStadings();
        else if (v.getId() == R.id.btnPrintListePlayersTeam) showSquad();
        else if (v.getId() == R.id.btnPrintMatchesTeam) showTeamMatches();
        else if (v.getId() == R.id.btnPrintDetailsTeam) showTeamDetails();
    }

    public void showPlayer() {
        this.playercontroller.afficheDetailsJoueur(138); // Marco Reus = 138
    }

    public void showSquad() {
        this.teamcontroller.afficheListePlayersTeams(4); // Dortmund = 4
    }

    public void showTeamDetails() {
        this.teamcontroller.afficheDetailsTeams(4); // Dortmund = 4
    }

    public void showStadings() {
        this.classementcontroller.afficheListeTeamsCompetition(2002); // Bundesliga = 2002
    }

    public void showTeamMatches() {
        this.teamcontroller.afficheListeMatchesTeams(4); // Dortmund = 4
    }
}