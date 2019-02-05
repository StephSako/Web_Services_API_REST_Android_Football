package com.example.footballapi.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.footballapi.R;
import com.example.footballapi.controleur.TeamController;
import com.example.footballapi.view.fragments.MatchesFragment;
import com.example.footballapi.view.fragments.SquadFragment;

public class TeamActivity extends AppCompatActivity implements View.OnClickListener {

    // Id de l'équipe
    private int idTeam = -1;
    public String nomClub = "";

    public int getidTeam(){
        return this.idTeam;
    }
    public String getnomClub(){
        return this.nomClub;
    }

    public Button btnSquad;
    public Button btnMatches;
    public TextView tvWebSite;
    public TextView tvStade;
    public TextView tvActiveCompetitions;
    public TextView tvEntraineur;
    public ImageView logo_club;

    private TeamController teamcontroller = new TeamController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_activity);

        this.btnSquad = findViewById(R.id.btnSquad);
        this.btnMatches = findViewById(R.id.btnMatches);

        this.btnSquad.setOnClickListener(this);
        this.btnMatches.setOnClickListener(this);

        this.tvWebSite = findViewById(R.id.tvWebsite);
        this.tvStade = findViewById(R.id.tvStade);
        this.tvActiveCompetitions = findViewById(R.id.tvActiveCompetitions);
        this.tvEntraineur = findViewById(R.id.tvEntraineur);

        logo_club = findViewById(R.id.logo_club);

        // On récupère l'id de l'équipe depuis l'activite mère
        Intent intent = getIntent();
        this.idTeam = intent.getIntExtra(ClassementActivity.CLE_DONNEES_ID_TEAM, 1);

        teamcontroller.afficheDetailsTeam(this.idTeam, this, getString(R.string.token));

        // On affiche le fragment de la liste de joueurs par défaut (on change la couleur du bouton
        btnMatches.setBackgroundResource(R.color.green);
        btnSquad.setBackgroundResource(R.color.grey_desactivated);

        MatchesFragment simpleFragment = MatchesFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.idFragmentSquad_Match, simpleFragment, "MATCHES").addToBackStack("MATCHES").commit();
    }

    public void onClick(View v) { // On remplace le fragment par celui géré par le bouton cliqué
        if (v.getId() == R.id.btnSquad) { // On affiche la liste des joueurs de l'équipe

            btnMatches.setBackgroundResource(R.color.grey_desactivated);
            btnSquad.setBackgroundResource(R.color.green);

            SquadFragment simpleFragment = SquadFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.idFragmentSquad_Match, simpleFragment, "MATCHES").addToBackStack("MATCHES").commit();
        }
        else if (v.getId() == R.id.btnMatches) { // On affiche la liste des matches de l'équipe

            btnSquad.setBackgroundResource(R.color.grey_desactivated);
            btnMatches.setBackgroundResource(R.color.green);

            MatchesFragment simpleFragment = MatchesFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.idFragmentSquad_Match, simpleFragment).addToBackStack(null).commit();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // On supprime le fragment pour éviter un appel REST inutile
        /*for (Fragment fragment:getSupportFragmentManager().getFragments()) {
            if (fragment!=null) {
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }
        }*/

        Intent i = new Intent(this, ClassementActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
        finish();
    }
}
