package com.example.footballapi.controleur;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.ahmadrosid.svgloader.SvgLoader;
import com.example.footballapi.R;
import com.example.footballapi.model.model_retrofit.player.Player;
import com.example.footballapi.model.model_retrofit.restService.RestUser;
import com.example.footballapi.model.model_retrofit.team.OneMatch;
import com.example.footballapi.view.activities.MatchActivity;
import com.example.footballapi.view.activities.PlayerActivity;

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
        Call<OneMatch> call = RestUser.get().match(token, activity.idMatch);
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

                    String[] dateMatch = oneMatch.getMatch().getUtcDate().split("-");
                    String day = dateMatch[2];
                    String month = dateMatch[1];
                    activity.tvMatchDate.setText(day + "/" + month);

                    activity.tvNuls.setText(oneMatch.getHead2head().getHomeTeam().getDraws());
                    activity.tvButsTotaux.setText(oneMatch.getHead2head().getTotalGoals());

                    activity.tvVictoiresHome.setText(oneMatch.getHead2head().getHomeTeam().getWins());
                    activity.tvDefaitesHome.setText(oneMatch.getHead2head().getHomeTeam().getLosses());

                    activity.tvVictoiresAway.setText((oneMatch.getHead2head().getNumberOfMatches())-(oneMatch.getHead2head().getHomeTeam().getLosses()));
                    activity.tvDefaitesAway.setText((oneMatch.getHead2head().getNumberOfMatches())-(oneMatch.getHead2head().getHomeTeam().getWins()));

                    activity.tvTotaux.setText(oneMatch.getHead2head().getNumberOfMatches());
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
