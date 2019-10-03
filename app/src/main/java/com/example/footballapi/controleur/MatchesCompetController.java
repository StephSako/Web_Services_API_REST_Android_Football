package com.example.footballapi.controleur;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.footballapi.model.model_recyclerview.matches.MatchesModel;
import com.example.footballapi.model.model_retrofit.competition.Classement;
import com.example.footballapi.model.model_retrofit.restService.football_data.RestFootballData;
import com.example.footballapi.view.fragments.MatchesFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchesCompetController {

    private MatchesFragment fragment;

    public MatchesCompetController(MatchesFragment fragment) {
        this.fragment = fragment;
    }

    /**
     * Affiche la liste des matches d'une compétition
     * @param token
     */
    public void onCreate(final String token) {
        Call<Classement> call = RestFootballData.get().matchesCompetition(token, fragment.id);
        call.enqueue(new Callback<Classement>() {
            @Override
            public void onResponse(@NonNull Call<Classement> call, @NonNull Response<Classement> response) {
                if (response.isSuccessful()) {
                    Classement classement = response.body();
                    assert classement != null;

                    List<MatchesModel> listFinal = new ArrayList<>();

                    for (int i = 0; i < classement.getMatches().size(); i++) {
                        MatchesModel model = new MatchesModel();
                        model.setMatchDay(String.valueOf(classement.getMatches().get(i).getMatchday()));
                        model.setHomeTeam(classement.getMatches().get(i).getHomeTeam().getName());
                        model.setAwayTeam(classement.getMatches().get(i).getAwayTeam().getName());
                        model.setWinner(classement.getMatches().get(i).getScore().getWinner());
                        model.setIdTeamAway(String.valueOf(classement.getMatches().get(i).getAwayTeam().getId()));
                        model.setIdTeamHome(String.valueOf(classement.getMatches().get(i).getHomeTeam().getId()));
                        model.setIdMatch(String.valueOf(classement.getMatches().get(i).getId()));

                        // On vérifie si le match a déjà été joué ou pas
                        if (classement.getMatches().get(i).getStatus().equals("FINISHED"))
                            model.setScore(classement.getMatches().get(i).getScore().getFullTime().getHomeTeam() + " - " + classement.getMatches().get(i).getScore().getFullTime().getAwayTeam());
                        else{
                            String[] parts = classement.getMatches().get(i).getUtcDate().split("T");
                            String date = parts[0]; // Day
                            String[] dateDay = date.split("-");
                            String day = dateDay[2];
                            String month = dateDay[1];

                            model.setScore(day + "/" + month);
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
            public void onFailure(@NonNull Call<Classement> call, @NonNull Throwable t) {
                Toast.makeText(fragment.getActivity(), "Vérifiez votre connexion_activity Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
