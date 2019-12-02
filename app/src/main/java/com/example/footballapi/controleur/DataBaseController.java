package com.example.footballapi.controleur;

import androidx.annotation.NonNull;

import com.example.footballapi.model.model_retrofit.competition.Classement;
import com.example.footballapi.model.model_retrofit.retrofit.football_data.RestFootballData;
import com.example.footballapi.view.activities.SplashScreen;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataBaseController {

    private SplashScreen activity;

    public DataBaseController(SplashScreen activity) {
        this.activity = activity;
    }

    /**
     * Met à jour le classement identifié dans la base de données locale pour la persistance longue
     * @param idCompet id de la compétition à mettre à jour
     * @param token token de connexion
     */
    public void updateAllCompet(final int idCompet, String token) {
        Call<Classement> call = RestFootballData.get().competitions(token, idCompet);
        call.enqueue(new Callback<Classement>() {
            @Override
            public void onResponse(@NonNull Call<Classement> call, @NonNull Response<Classement> response) {
                if (response.isSuccessful()) {
                    Classement classement = response.body();
                    assert classement != null;

                    for (int i = 1; i <= classement.getStandings().get(0).getTable().size(); i++) {
                        String club_name = classement.getStandings().get(0).getTable().get(i - 1).getTeam().getName();
                        int position = classement.getStandings().get(0).getTable().get(i - 1).getPosition();
                        int points = classement.getStandings().get(0).getTable().get(i - 1).getPoints();
                        int diff = classement.getStandings().get(0).getTable().get(i - 1).getGoalDifference();
                        int idTeam = classement.getStandings().get(0).getTable().get(i - 1).getTeam().getId();
                        String crest = (classement.getStandings().get(0).getTable().get(i - 1).getTeam().getCrestUrl() != null) ? classement.getStandings().get(0).getTable().get(i - 1).getTeam().getCrestUrl() : "" ;

                        activity.getDataBase().insertClassement(idTeam, idCompet, classement.getCompetition().getName(), position, club_name, diff, points, crest);
                    }
                } else {
                    Snackbar.make(activity.contextView, "Classement introuvable", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Classement> call, @NonNull Throwable t) {
                Snackbar.make(activity.contextView, "Erreur", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
