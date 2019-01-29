package com.example.footballapi.controleur;

import android.widget.Toast;

import com.example.footballapi.model.team.Team;
import com.example.footballapi.restService.RestUser;
import com.example.footballapi.view.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamController {

    private MainActivity activity;

    public TeamController(MainActivity activity) {
        this.activity = activity;
    }

    /**
     * Affiche la liste des joueurs
     *
     * @param id id de l'équipe
     */
    public void afficheListePlayersTeams(int id) {
        Call<Team> call = RestUser.get().teams("e0d9f47d73ae4a4a87eeb24935d8b2f8"/*Resources.getSystem().getString(R.string.token)*/, id);
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
                    activity.tvStandings.setText(listePlayers);
                    Toast.makeText(activity, "L'équipe est " + team.getName(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(activity, "Equipe introuvable", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                Toast.makeText(activity, "Vérifiez votre connexion Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Affiche les détails d'une équipe
     *
     * @param id id de l'équipe
     */
    public void afficheDetailsTeams(int id) {
        Call<Team> call = RestUser.get().teams("e0d9f47d73ae4a4a87eeb24935d8b2f8"/*Resources.getSystem().getString(R.string.token)*/, id);
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

                    activity.tvStandings.setText(details);
                    Toast.makeText(activity, "L'équipe est " + team.getName(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(activity, "Equipe introuvable", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                Toast.makeText(activity, "Vérifiez votre connexion Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Affiche la liste des matches d'une équipe
     *
     * @param id id de l'équipe
     */
    public void afficheListeMatchesTeams(int id) {
        Call<Team> call = RestUser.get().matches("e0d9f47d73ae4a4a87eeb24935d8b2f8"/*Resources.getSystem().getString(R.string.token)*/, id);
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
                    activity.tvStandings.setText(listeMatches);
                } else {
                    Toast.makeText(activity, "Matches d'équipe introuvables", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                Toast.makeText(activity, "Vérifiez votre connexion Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
