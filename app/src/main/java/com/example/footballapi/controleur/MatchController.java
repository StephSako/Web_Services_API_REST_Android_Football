package com.example.footballapi.controleur;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.ahmadrosid.svgloader.SvgLoader;
import com.example.footballapi.R;
import com.example.footballapi.model.model_retrofit.retrofit.football_data.RestFootballData;
import com.example.footballapi.model.model_retrofit.team.OneMatch;
import com.example.footballapi.view.fragments.MatchFragment;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchController {

    private MatchFragment fragment;

    public MatchController(MatchFragment fragment) {
        this.fragment = fragment;
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

                    Objects.requireNonNull(fragment.getActivity()).setTitle("Match");

                    String crestHomeFinal = (new CrestGenerator().crestGenerator(oneMatch.getMatch().getHomeTeam().getName()).equals("")) ? crestHome : new CrestGenerator().crestGenerator(oneMatch.getMatch().getHomeTeam().getName());
                    String crestAwayFinal = (new CrestGenerator().crestGenerator(oneMatch.getMatch().getAwayTeam().getName()).equals("")) ? crestAway : new CrestGenerator().crestGenerator(oneMatch.getMatch().getAwayTeam().getName());

                    if (crestHomeFinal.length() >= 4) {
                        switch (crestHomeFinal.substring(crestHomeFinal.length() - 3)) {
                            case "svg":
                                SvgLoader.pluck()
                                        .with(fragment.getActivity())
                                        .load(crestHomeFinal, fragment.logo_club_home)
                                        .close();
                                break;
                            case "gif":
                            case "png":
                                // Display with androidgif
                                Picasso.get()
                                        .load(crestHomeFinal)
                                        .resize(50, 50)
                                        .centerCrop()
                                        .into(fragment.logo_club_home);
                                break;
                        }
                    }else {
                        fragment.logo_club_home.setImageResource(R.drawable.ic_logo_foreground);
                    }

                    if (crestAwayFinal.length() >= 4) {
                        switch (crestAwayFinal.substring(crestAwayFinal.length() - 3)){
                            case "svg":
                                SvgLoader.pluck()
                                        .with(fragment.getActivity())
                                        .load(crestAwayFinal, fragment.logo_club_away)
                                        .close();
                                break;
                            case "gif":
                            case "png":
                                // Display with androidgif
                                Picasso.get()
                                        .load(crestAwayFinal)
                                        .resize(50, 50)
                                        .centerCrop()
                                        .into(fragment.logo_club_away);
                                break;
                        }
                    } else {
                        fragment.logo_club_away.setImageResource(R.drawable.ic_logo_foreground);
                    }

                    String[] parts = oneMatch.getMatch().getUtcDate().split("T");
                    String date = parts[0]; // Day
                    String[] dateDay = date.split("-");
                    String day = dateDay[2];
                    String month = dateDay[1];
                    fragment.tvMatchDate.setText(day + "/" + month);

                    if (oneMatch.getHead2head() != null){
                        fragment.tvNuls.setText(String.valueOf(oneMatch.getHead2head().getHomeTeam().getDraws()));
                        fragment.tvButsTotaux.setText(String.valueOf(oneMatch.getHead2head().getTotalGoals()));

                        fragment.tvVictoiresHome.setText(String.valueOf(oneMatch.getHead2head().getHomeTeam().getWins()));
                        fragment.tvDefaitesHome.setText(String.valueOf(oneMatch.getHead2head().getHomeTeam().getLosses()));

                        fragment.tvVictoiresAway.setText(String.valueOf(oneMatch.getHead2head().getAwayTeam().getWins()));
                        fragment.tvDefaitesAway.setText(String.valueOf(oneMatch.getHead2head().getAwayTeam().getLosses()));

                        fragment.tvTotaux.setText(String.valueOf(oneMatch.getHead2head().getNumberOfMatches()));

                        fragment.pbVictoriesHome.setMax(oneMatch.getHead2head().getHomeTeam().getWins() + oneMatch.getHead2head().getAwayTeam().getWins());
                        fragment.pbVictoriesAway.setMax(oneMatch.getHead2head().getHomeTeam().getWins() + oneMatch.getHead2head().getAwayTeam().getWins());
                        fragment.pbVictoriesHome.setProgress(oneMatch.getHead2head().getHomeTeam().getWins());
                        fragment.pbVictoriesAway.setProgress(oneMatch.getHead2head().getAwayTeam().getWins());

                        fragment.pbDefeatsHome.setMax(oneMatch.getHead2head().getHomeTeam().getLosses() + oneMatch.getHead2head().getAwayTeam().getLosses());
                        fragment.pbDefeatsAway.setMax(oneMatch.getHead2head().getHomeTeam().getLosses() + oneMatch.getHead2head().getAwayTeam().getLosses());
                        fragment.pbDefeatsHome.setProgress(oneMatch.getHead2head().getHomeTeam().getLosses());
                        fragment.pbDefeatsAway.setProgress(oneMatch.getHead2head().getAwayTeam().getLosses());
                    }

                    fragment.btnWinnerHome.setText(String.valueOf(oneMatch.getMatch().getHomeTeam().getName()));
                    fragment.btnWinnerAway.setText(String.valueOf(oneMatch.getMatch().getAwayTeam().getName()));

                    fragment.tvVenue.setText(oneMatch.getMatch().getVenue());

                    if(oneMatch.getMatch().getStatus().equals("FINISHED")) {
                        fragment.tvGoalHomeFT.setText(String.valueOf(oneMatch.getMatch().getScore().getFullTime().getHomeTeam()));
                        fragment.tvGoalAwayFT.setText(String.valueOf(oneMatch.getMatch().getScore().getFullTime().getAwayTeam()));
                        fragment.tvGoalHomeHT.setText(String.valueOf(oneMatch.getMatch().getScore().getHalfTime().getHomeTeam()));
                        fragment.tvGoalAwayHT.setText(String.valueOf(oneMatch.getMatch().getScore().getHalfTime().getAwayTeam()));
                    }
                    else{
                        fragment.tvGoalHomeFT.setText("-");
                        fragment.tvGoalAwayFT.setText("-");
                        fragment.tvGoalHomeHT.setText("-");
                        fragment.tvGoalAwayHT.setText("-");
                    }

                    fragment.tvNameHome.setText(String.valueOf(oneMatch.getMatch().getHomeTeam().getName()));
                    fragment.tvNameAway.setText(String.valueOf(oneMatch.getMatch().getAwayTeam().getName()));
                } else {
                    Snackbar.make(fragment.contextView, "Le nombre d'appels a été dépassé", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<OneMatch> call, @NonNull Throwable t) {
                Snackbar.make(fragment.contextView, "Vérifiez votre connexion Internet", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
