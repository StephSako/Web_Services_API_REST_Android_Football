package com.example.footballapi.controleur;

import androidx.annotation.NonNull;

import com.ahmadrosid.svgloader.SvgLoader;
import com.example.footballapi.R;
import com.example.footballapi.services.retrofit.football_data.RestFootballData;
import com.example.footballapi.model.model_retrofit.team.Team;
import com.example.footballapi.view.activities.TeamActivity;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamController {

    private TeamActivity activity;

    public TeamController(TeamActivity activity) {
        this.activity = activity;
    }

    /**
     * Affiche les détails d'une équipe
     * @param token token de connexion
     * */
    public void onCreate(final String token) {
        Call<Team> call = RestFootballData.get().teamsDetails(token, activity.idTeam);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(@NonNull Call<Team> call, @NonNull Response<Team> response) {
                if (response.isSuccessful()) {
                    Team team = response.body();
                    assert team != null;

                    activity.crestURLPlayer = team.getCrestUrl();

                    activity.nomClub = team.getName();

                    // On change le title de l'actionBar par le nom du club
                    Objects.requireNonNull(activity).setTitle(team.getName());

                    activity.tvWebSite.setText(team.getWebSite());
                    activity.tvStade.setText(team.getVenue());

                    StringBuilder activeCompetitions = new StringBuilder();

                    for (int i = 0; i < team.getActiveCompetitions().size(); i++){
                        if (i == team.getActiveCompetitions().size() - 1) activeCompetitions.append(team.getActiveCompetitions().get(i).getName());
                        else activeCompetitions.append(team.getActiveCompetitions().get(i).getName()).append(", ");
                    }

                    StringBuilder entraineur = new StringBuilder();
                    for (int i = 0; i < team.getSquad().size(); i++) {
                        if (team.getSquad().get(i).getRole().equals("COACH")) {
                            if (i == team.getSquad().size() - 1)
                                entraineur.append(team.getSquad().get(i).getName());
                            else
                                entraineur.append(team.getSquad().get(i).getName()).append("\n");
                        }
                    }

                    activity.tvActiveCompetitions.setText(activeCompetitions.toString());
                    activity.tvEntraineur.setText(entraineur.toString());

                    if (!team.getCrestUrl().equals("") && activity.loadingPicsTeam) {
                        SvgLoader.pluck()
                                .with(activity)
                                .setPlaceHolder(R.drawable.ic_logo_foreground, R.drawable.ic_logo_foreground)
                                .load(team.getCrestUrl(), activity.logo_club)
                                .close();
                    }

                    else activity.logo_club.setImageResource(R.drawable.ic_logo_foreground);

                } else {
                    Snackbar.make(activity.contextView, "Le nombre d'appels est dépassé", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Team> call, @NonNull Throwable t) {
                Snackbar.make(activity.contextView, "Vérifiez votre connexion Internet", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
