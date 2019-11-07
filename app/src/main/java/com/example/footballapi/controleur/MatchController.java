package com.example.footballapi.controleur;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.ahmadrosid.svgloader.SvgLoader;
import com.example.footballapi.R;
import com.example.footballapi.model.model_retrofit.retrofit.football_data.RestFootballData;
import com.example.footballapi.model.model_retrofit.team.OneMatch;
import com.example.footballapi.view.activities.MatchActivity;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

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
     * @param crestAway crest team away
     * @param crestHome crest team home
     * @param idMatch id du match
     * @param token token de connexion
     *
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

                    String crestHomeFinal = (new CrestGenerator().crestGenerator(oneMatch.getMatch().getHomeTeam().getName()).equals("")) ? crestHome : new CrestGenerator().crestGenerator(oneMatch.getMatch().getHomeTeam().getName());
                    String crestAwayFinal = (new CrestGenerator().crestGenerator(oneMatch.getMatch().getAwayTeam().getName()).equals("")) ? crestAway : new CrestGenerator().crestGenerator(oneMatch.getMatch().getAwayTeam().getName());

                    if (crestHomeFinal.length() >= 4) {
                        switch (crestHomeFinal.substring(crestHomeFinal.length() - 3)) {
                            case "svg":
                                SvgLoader.pluck()
                                        .with(activity)
                                        .load(crestHomeFinal, activity.logo_club_home)
                                        .close();
                                break;
                            case "gif":
                            case "png":
                                // Display with androidgif
                                Picasso.get()
                                        .load(crestHomeFinal)
                                        .resize(50, 50)
                                        .centerCrop()
                                        .into(activity.logo_club_home);
                                break;
                        }
                    }else {
                        activity.logo_club_home.setImageResource(R.drawable.ic_logo_foreground);
                    }

                    if (crestAwayFinal.length() >= 4) {
                        switch (crestAwayFinal.substring(crestAwayFinal.length() - 3)){
                            case "svg":
                                SvgLoader.pluck()
                                        .with(activity)
                                        .load(crestAwayFinal, activity.logo_club_away)
                                        .close();
                                break;
                            case "gif":
                            case "png":
                                // Display with androidgif
                                Picasso.get()
                                        .load(crestAwayFinal)
                                        .resize(50, 50)
                                        .centerCrop()
                                        .into(activity.logo_club_away);
                                break;
                        }
                    } else {
                        activity.logo_club_away.setImageResource(R.drawable.ic_logo_foreground);
                    }

                    String[] parts = oneMatch.getMatch().getUtcDate().split("T");
                    String date = parts[0]; // Day
                    String[] dateDay = date.split("-");
                    String day = dateDay[2];
                    String month = dateDay[1];
                    activity.tvMatchDate.setText(day + "/" + month);

                    if (oneMatch.getHead2head() != null){
                        activity.tvNuls.setText(String.valueOf(oneMatch.getHead2head().getHomeTeam().getDraws()));
                        activity.tvButsTotaux.setText(String.valueOf(oneMatch.getHead2head().getTotalGoals()));

                        activity.tvVictoiresHome.setText(String.valueOf(oneMatch.getHead2head().getHomeTeam().getWins()));
                        activity.tvDefaitesHome.setText(String.valueOf(oneMatch.getHead2head().getHomeTeam().getLosses()));

                        activity.tvVictoiresAway.setText(String.valueOf(oneMatch.getHead2head().getAwayTeam().getWins()));
                        activity.tvDefaitesAway.setText(String.valueOf(oneMatch.getHead2head().getAwayTeam().getLosses()));

                        activity.tvTotaux.setText(String.valueOf(oneMatch.getHead2head().getNumberOfMatches()));

                        activity.pbVictoriesHome.setMax(oneMatch.getHead2head().getHomeTeam().getWins() + oneMatch.getHead2head().getAwayTeam().getWins());
                        activity.pbVictoriesAway.setMax(oneMatch.getHead2head().getHomeTeam().getWins() + oneMatch.getHead2head().getAwayTeam().getWins());
                        activity.pbVictoriesHome.setProgress(oneMatch.getHead2head().getHomeTeam().getWins());
                        activity.pbVictoriesAway.setProgress(oneMatch.getHead2head().getAwayTeam().getWins());

                        activity.pbDefeatsHome.setMax(oneMatch.getHead2head().getHomeTeam().getLosses() + oneMatch.getHead2head().getAwayTeam().getLosses());
                        activity.pbDefeatsAway.setMax(oneMatch.getHead2head().getHomeTeam().getLosses() + oneMatch.getHead2head().getAwayTeam().getLosses());
                        activity.pbDefeatsHome.setProgress(oneMatch.getHead2head().getHomeTeam().getLosses());
                        activity.pbDefeatsAway.setProgress(oneMatch.getHead2head().getAwayTeam().getLosses());
                    }

                    activity.btnWinnerHome.setText(String.valueOf(oneMatch.getMatch().getHomeTeam().getName()));
                    activity.btnWinnerAway.setText(String.valueOf(oneMatch.getMatch().getAwayTeam().getName()));

                    activity.tvVenue.setText(oneMatch.getMatch().getVenue());

                    if(oneMatch.getMatch().getStatus().equals("FINISHED")) {
                        activity.tvGoalHomeFT.setText(String.valueOf(oneMatch.getMatch().getScore().getFullTime().getHomeTeam()));
                        activity.tvGoalAwayFT.setText(String.valueOf(oneMatch.getMatch().getScore().getFullTime().getAwayTeam()));
                        activity.tvGoalHomeHT.setText(String.valueOf(oneMatch.getMatch().getScore().getHalfTime().getHomeTeam()));
                        activity.tvGoalAwayHT.setText(String.valueOf(oneMatch.getMatch().getScore().getHalfTime().getAwayTeam()));
                    }
                    else{
                        activity.tvGoalHomeFT.setText("-");
                        activity.tvGoalAwayFT.setText("-");
                        activity.tvGoalHomeHT.setText("-");
                        activity.tvGoalAwayHT.setText("-");
                    }

                    activity.tvNameHome.setText(String.valueOf(oneMatch.getMatch().getHomeTeam().getName()));
                    activity.tvNameAway.setText(String.valueOf(oneMatch.getMatch().getAwayTeam().getName()));
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
