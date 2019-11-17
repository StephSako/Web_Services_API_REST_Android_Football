package com.example.footballapi.controleur;

import androidx.annotation.NonNull;

import com.example.footballapi.model.model_recyclerview.matches.MatchesModel;
import com.example.footballapi.model.model_retrofit.team.Team;
import com.example.footballapi.model.model_retrofit.retrofit.football_data.RestFootballData;
import com.example.footballapi.view.fragments.MatchesFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchesTeamController {

    private MatchesFragment fragment;

    public MatchesTeamController(MatchesFragment fragment) {
        this.fragment = fragment;
    }

    /**
     * Affiche la liste des matches d'une équipe
     * @param token token de connexion
     */
    public void onCreate(final String token, int idTeam) {
        Call<Team> call = RestFootballData.get().matchesTeam(token, idTeam);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(@NonNull Call<Team> call, @NonNull Response<Team> response) {
                if (response.isSuccessful()) {
                    Team team = response.body();
                    assert team != null;

                    List<MatchesModel> listFinal = new ArrayList<>();

                    for (int i = 0; i < team.getMatches().size(); i++) {
                        MatchesModel model = new MatchesModel();
                        model.setMatchDay(String.valueOf(team.getMatches().get(i).getMatchday()));
                        model.setHomeTeam(team.getMatches().get(i).getHomeTeam().getName());
                        model.setAwayTeam(team.getMatches().get(i).getAwayTeam().getName());
                        model.setWinner(team.getMatches().get(i).getScore().getWinner());
                        model.setIdTeamAway(String.valueOf(team.getMatches().get(i).getAwayTeam().getId()));
                        model.setIdTeamHome(String.valueOf(team.getMatches().get(i).getHomeTeam().getId()));
                        model.setIdMatch(String.valueOf(team.getMatches().get(i).getId()));
                        model.setStatus(String.valueOf(team.getMatches().get(i).getStatus()));
                        model.setUtcDate(String.valueOf(team.getMatches().get(i).getUtcDate()));

                        String date = team.getMatches().get(i).getUtcDate().split("T")[0]; // Day
                        String[] dateDay = date.split("-");

                        if(team.getMatches().get(i).getStatus().equals("FINISHED")) fragment.incrPositionDay();

                        // On vérifie si le match a déjà été joué ou pas
                        if (team.getMatches().get(i).getStatus().equals("FINISHED"))
                            model.setScore(team.getMatches().get(i).getScore().getFullTime().getHomeTeam() + " - " + team.getMatches().get(i).getScore().getFullTime().getAwayTeam());
                        else model.setScore(dateDay[2] + "/" + dateDay[1]);

                        listFinal.add(model);
                    }
                    fragment.showList(listFinal);
                } else {
                    Snackbar.make(Objects.requireNonNull(fragment.getView()), "Le nombre d'appels a été dépassé", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Team> call, @NonNull Throwable t) {
                Snackbar.make(Objects.requireNonNull(fragment.getView()), "Vérifiez votre connexion Internet", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
