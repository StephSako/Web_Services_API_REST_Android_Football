package com.example.footballapi.controleur;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.ahmadrosid.svgloader.SvgLoader;
import com.example.footballapi.R;
import com.example.footballapi.model.model_retrofit.restService.football_data.RestFootballData;
import com.example.footballapi.model.model_retrofit.team.OneMatch;
import com.example.footballapi.view.activities.MatchActivity;
import com.google.android.material.snackbar.Snackbar;

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
     * @param token token de connexion
     */
    public void onCreate(String token, int idMatch, final String crestHome, final String crestAway) {
        Call<OneMatch> call = RestFootballData.get().match(token, idMatch);
        call.enqueue(new Callback<OneMatch>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<OneMatch> call, @NonNull Response<OneMatch> response) {
                if (response.isSuccessful()) {
                    final OneMatch oneMatch = response.body();
                    assert oneMatch != null;

                    Objects.requireNonNull(activity).setTitle("Match");

                    if (!crestHome.equals(""))
                        SvgLoader.pluck()
                                .with(activity)
                                .setPlaceHolder(R.drawable.ic_logo_foreground, R.drawable.ic_logo_foreground)
                                .load(crestHome, activity.logo_club_home)
                                .close();
                    else activity.logo_club_home.setImageResource(R.drawable.ic_logo_foreground);

                    if (!crestAway.equals("") && activity.loadingPicsPlayer)
                        SvgLoader.pluck()
                                .with(activity)
                                .setPlaceHolder(R.drawable.ic_logo_foreground, R.drawable.ic_logo_foreground)
                                .load(crestAway, activity.logo_club_away)
                                .close();
                    else activity.logo_club_away.setImageResource(R.drawable.ic_logo_foreground);

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

                    activity.btnWinnerHome.setText(String.valueOf(oneMatch.getMatch().getHomeTeam().getName()));
                    activity.btnWinnerAway.setText(String.valueOf(oneMatch.getMatch().getAwayTeam().getName()));
                } else {
                    Snackbar.make(activity.contextView, "Le nombre d'appels a été dépassé", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<OneMatch> call, @NonNull Throwable t) {
                Snackbar.make(activity.contextView, "Vérifiez votre connexion Internet", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
