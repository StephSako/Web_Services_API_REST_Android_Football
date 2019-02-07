package com.example.footballapi.controleur;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.footballapi.model.competition.Classement;
import com.example.footballapi.restService.RestUser;
import com.example.footballapi.view.activities.SplashScreen;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataBaseController {

    public DataBaseController() { }

    /**
     * Met à jour le classement identifié dans la base de données locale pour la persistance longue
     */
    public void updateAllCompet(final int idCompet, final SplashScreen activity, String token) {
        Call<Classement> call = RestUser.get().competitions(token, idCompet);
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

                        activity.database.insertClassement(idTeam, idCompet, position, club_name, diff, points);
                    }
                } else {
                    Toast.makeText(activity, "Classement introuvable", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Classement> call, @NonNull Throwable t) {
                Toast.makeText(activity, "Classements non mis à jour\nVérifiez votre connexion", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
