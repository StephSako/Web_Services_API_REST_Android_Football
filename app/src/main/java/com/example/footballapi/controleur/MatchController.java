package com.example.footballapi.controleur;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.footballapi.R;
import com.example.footballapi.model.model_retrofit.restService.football_data.RestFootballData;
import com.example.footballapi.model.model_retrofit.team.OneMatch;
import com.example.footballapi.view.activities.MatchActivity;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchController {

    private MatchActivity activity;

    public MatchController(MatchActivity activity) {
        this.activity = activity;
    }

    /**
     * Affiche un match spécifique
     * @param token
     */
    public void onCreate(String token) {
        Call<OneMatch> call = RestFootballData.get().match(token, activity.idMatch);
        call.enqueue(new Callback<OneMatch>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<OneMatch> call, @NonNull Response<OneMatch> response) {
                if (response.isSuccessful()) {
                    final OneMatch oneMatch = response.body();
                    assert oneMatch != null;

                    Objects.requireNonNull(activity).setTitle("Match");

                    activity.logo_club_away.setImageResource(R.drawable.ic_logo_foreground);
                    activity.logo_club_home.setImageResource(R.drawable.ic_logo_foreground);

                    String[] parts = oneMatch.getMatch().getUtcDate().split("T");
                    String date = parts[0]; // Day
                    String[] dateDay = date.split("-");
                    String day = dateDay[2];
                    String month = dateDay[1];
                    activity.tvMatchDate.setText(day + "/" + month);

                    activity.tvNuls.setText(String.valueOf(oneMatch.getHead2head().getHomeTeam().getDraws()));
                    activity.tvButsTotaux.setText(String.valueOf(oneMatch.getHead2head().getTotalGoals()));

                    activity.tvVictoiresHome.setText(String.valueOf(oneMatch.getHead2head().getHomeTeam().getWins()));
                    activity.tvDefaitesHome.setText(String.valueOf(oneMatch.getHead2head().getHomeTeam().getLosses()));

                    activity.tvVictoiresAway.setText(String.valueOf((oneMatch.getHead2head().getNumberOfMatches())-(oneMatch.getHead2head().getHomeTeam().getWins() + oneMatch.getHead2head().getHomeTeam().getDraws())));
                    activity.tvDefaitesAway.setText(String.valueOf((oneMatch.getHead2head().getNumberOfMatches())-(oneMatch.getHead2head().getHomeTeam().getLosses() + oneMatch.getHead2head().getHomeTeam().getDraws())));

                    activity.tvTotaux.setText(String.valueOf(oneMatch.getHead2head().getNumberOfMatches()));
                } else {
                    Toast.makeText(activity, "Le nombre d'appels a été dépassé", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<OneMatch> call, @NonNull Throwable t) {
                Toast.makeText(activity, "Vérifiez votre connexion_activity Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
