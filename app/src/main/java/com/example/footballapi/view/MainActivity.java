package com.example.footballapi.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.footballapi.R;
import com.example.footballapi.model.competition.Classement;
import com.example.footballapi.model.player.Player;
import com.example.footballapi.model.team.Team;
import com.example.footballapi.restService.RestUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Elements de la vue de l'activité
    private Button btnPrintJoueur;
    private Button btnPrintStadings;
    private Button btnPrintDetailsTeam;
    private Button btnPrintListePlayersTeam;
    private Button btnPrintMatchesTeam;
    private TextView tvStandings;

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
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnPrintJoueur) {
            tvStandings.setText("");
            afficheDetailsJoueur(138); // Marco Reus = 138
            //afficheDetailsJoueur(Integer.parseInt(tvJoueur.getText().toString()));
        }
        else if (v.getId() == R.id.btnPrintStadings) {
            tvStandings.setText("");
            afficheListeTeamsCompetition(2002); // Bundesliga = 2002
        }
        else if (v.getId() == R.id.btnPrintListePlayersTeam) {
            tvStandings.setText("");
            afficheListePlayersTeams(4); // Dortmund = 4
        }
        else if (v.getId() == R.id.btnPrintMatchesTeam) {
            tvStandings.setText("");
            afficheListeMatchesTeams(4); // Dortmund = 4
        }
        else if (v.getId() == R.id.btnPrintDetailsTeam) {
            tvStandings.setText("");
            afficheDetailsTeams(4); // Dortmund = 4
        }
    }

    /**
     * Affiche les détails d'un joueur
     *
     * @param id id du joueur
     */
    private void afficheDetailsJoueur(int id) {
        Call<Player> call = RestUser.get().players(getString(R.string.token), id);
        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                if (response.isSuccessful()) {
                    final Player player = response.body();
                    assert player != null;
                    tvStandings.setText("Nom : " + player.getName() + "\nNationalité : " + player.getNationality() + "\nPosition : " + player.getPosition() + "\nNuméro : " + player.getShirtNumber());

                    Toast.makeText(MainActivity.this, "Le joueur est " + player.getName(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Joueur introuvable", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Vérifiez votre connexion Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Affiche le classement d'une compétition
     *
     * @param id id de la competition
     */
    private void afficheListeTeamsCompetition(int id) {
        Call<Classement> call = RestUser.get().competitions(getString(R.string.token), id);
        call.enqueue(new Callback<Classement>() {
            @Override
            public void onResponse(Call<Classement> call, Response<Classement> response) {
                if (response.isSuccessful()) {
                    final Classement classement = response.body();
                    assert classement != null;

                    // On remplit le textview avec le classement des équipes
                    String standing = "";
                    for (int i = 1; i <= classement.getStandings().get(0).getTable().size(); i++){
                        standing += "" + i + " - " + classement.getStandings().get(0).getTable().get(i-1).getTeam().getName() + "\n";
                    }
                    tvStandings.setText(standing);
                    Toast.makeText(MainActivity.this, "La competition est " + classement.getCompetition().getName(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Classement introuvable", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Classement> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Vérifiez votre connexion Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Affiche la liste des joueurs
     *
     * @param id id de l'équipe
     */
    private void afficheListePlayersTeams(int id) {
        Call<Team> call = RestUser.get().teams(getString(R.string.token), id);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                if (response.isSuccessful()) {
                    final Team team = response.body();
                    assert team != null;

                    // On remplit le textview avec la liste des joueurs
                    String listePlayers = "";
                    for (int i = 0; i < team.getSquad().size()-1; i++){
                        listePlayers += team.getSquad().get(i).getName() + " - N° " + team.getSquad().get(i).getShirtNumber() + "\n";
                    }
                    tvStandings.setText(listePlayers);
                    Toast.makeText(MainActivity.this, "L'équipe est " + team.getName(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Equipe introuvable", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Vérifiez votre connexion Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Affiche les détails d'une équipe
     *
     * @param id id de l'équipe
     */
    private void afficheDetailsTeams(int id) {
        Call<Team> call = RestUser.get().teams(getString(R.string.token), id);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                if (response.isSuccessful()) {
                    final Team team = response.body();
                    assert team != null;

                    // On remplit le textview avec les details de l'équipe
                    String details = "";
                    details += "Name : " + team.getName() + "\nCouleurs : " + team.getClubColors() + "\nStade : " + team.getVenue() + "\nCompétitions : \n";
                    for (int i = 0; i < team.getActiveCompetitions().size(); i++){
                        details += "            - " + team.getActiveCompetitions().get(i).getName() + "\n";
                    }
                    details += "\nEntraineur : ";
                    // Renvoyer le nom de l'entraineur
                    for (int i = 0; i < team.getSquad().size(); i++){
                        if (team.getSquad().get(i).getRole().equals("COACH")) {
                            details += team.getSquad().get(i).getName();
                            break;
                        }
                    }

                    tvStandings.setText(details);
                    Toast.makeText(MainActivity.this, "L'équipe est " + team.getName(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Equipe introuvable", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Vérifiez votre connexion Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Affiche la liste des matches d'une équipe
     *
     * @param id id de l'équipe
     */
    private void afficheListeMatchesTeams(int id) {
        Call<Team> call = RestUser.get().matches(getString(R.string.token), id);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                if (response.isSuccessful()) {
                    final Team team = response.body();
                    assert team != null;

                    // On remplit le textview avec la liste des matches de l'équipe
                    String listeMatches = "";
                    for (int i = 0; i < team.getMatches().size();i++){
                        if (team.getMatches().get(i).getScore().getWinner() != null) {
                            listeMatches += "J" + team.getMatches().get(i).getMatchday() + " - " + team.getMatches().get(i).getHomeTeam().getName() + " " + team.getMatches().get(i).getScore().getFullTime().getHomeTeam() + " - " + team.getMatches().get(i).getScore().getFullTime().getAwayTeam() + " " + team.getMatches().get(i).getAwayTeam().getName() + "\n";
                        }
                    }
                    tvStandings.setText(listeMatches);
                } else {
                    Toast.makeText(MainActivity.this, "Matches d'équipe introuvables", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Vérifiez votre connexion Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}