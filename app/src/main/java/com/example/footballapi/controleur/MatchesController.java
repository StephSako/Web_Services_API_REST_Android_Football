package com.example.footballapi.controleur;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.footballapi.model.model_retrofit.team.Team;
import com.example.footballapi.model.model_recyclerview.matches.MatchesModel;
import com.example.footballapi.model.model_retrofit.restService.RestUser;
import com.example.footballapi.view.fragments.MatchesFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchesController {

    private MatchesFragment fragment;

    public MatchesController(MatchesFragment fragment) {
        this.fragment = fragment;
    }

    /**
     * Affiche la liste des matches d'une équipe
     * @param token
     */
    public void onCreate(final String token) {
        Call<Team> call = RestUser.get().matchesTeam(token, fragment.idTeam);
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

                        // On vérifie si le match a déjà été joué ou pas
                        if (team.getMatches().get(i).getStatus().equals("FINISHED"))
                            model.setScore(team.getMatches().get(i).getScore().getFullTime().getHomeTeam() + " - " + team.getMatches().get(i).getScore().getFullTime().getAwayTeam());
                        else{
                            String[] parts = team.getMatches().get(i).getUtcDate().split("T");
                            String date = parts[0]; // Day
                            String[] dateDay = date.split("-");
                            String day = dateDay[2];
                            String month = dateDay[1];
                            String year = dateDay[0];

                            model.setScore(day + "/" + month + "/" + year);
                        }

                        listFinal.add(model);
                    }
                    fragment.list = listFinal; // Appelée et affichée si le fragment existe déjà
                    fragment.showList(listFinal);
                } else {
                    Toast.makeText(fragment.getActivity(), "Le nombre d'appels a été dépassé", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Team> call, @NonNull Throwable t) {
                Toast.makeText(fragment.getActivity(), "Vérifiez votre connexion_activity Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
