package com.example.footballapi.controleur;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.ahmadrosid.svgloader.SvgLoader;
import com.example.footballapi.R;
import com.example.footballapi.model.model_retrofit.retrofit.football_data.RestFootballData;
import com.example.footballapi.model.model_retrofit.team.Team;
import com.example.footballapi.view.fragments.TeamFragment;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamController {

    private TeamFragment fragment;

    public TeamController(TeamFragment fragment) {
        this.fragment = fragment;
    }

    /**
     * Affiche les détails d'une équipe
     * @param token token de connexion
     * */
    public void onCreate(final String token, int idTeam) {
        Call<Team> call = RestFootballData.get().teamsDetails(token, idTeam);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(@NonNull Call<Team> call, @NonNull Response<Team> response) {
                if (response.isSuccessful()) {
                    Team team = response.body();
                    assert team != null;

                    fragment.crestURLPlayer = team.getCrestUrl();

                    fragment.nomClub = team.getName();

                    // On change le title de l'actionBar par le nom du club
                    Objects.requireNonNull(fragment.getActivity()).setTitle(team.getName());

                    fragment.tvWebSite.setText(team.getWebSite());
                    fragment.tvStade.setText(team.getVenue());

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

                    fragment.tvActiveCompetitions.setText(activeCompetitions.toString());
                    fragment.tvEntraineur.setText(entraineur.toString());

                    String crest = (new CrestGenerator().crestGenerator(team.getName()).equals("")) ?team.getCrestUrl() : new CrestGenerator().crestGenerator(team.getName());

                    if (crest.length() >= 4 && fragment.loadingPicsTeam) {
                        switch (crest.substring(crest.length() - 3)){
                            case "svg":
                                SvgLoader.pluck()
                                        .with(fragment.getActivity())
                                        .load(crest, fragment.logo_club)
                                        .close();
                                break;
                            case "gif":
                            case "png":
                                Picasso.get()
                                        .load(crest)
                                        .resize(50, 50)
                                        .centerCrop()
                                        .into(fragment.logo_club);
                                break;
                        }
                    } else {
                        fragment.logo_club.setImageResource(R.drawable.ic_logo_foreground);
                    }

                    String[] colorsClub = team.getClubColors().split(" / ");
                    int[] colors = {Color.parseColor(colorsClub[0].toLowerCase()), Color.parseColor(colorsClub[1].toLowerCase())};
                    GradientDrawable gradient = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors);
                    ActionBar bar = ((AppCompatActivity) fragment.getActivity()).getSupportActionBar();
                    Objects.requireNonNull(bar).setBackgroundDrawable(gradient);

                } else {
                    Snackbar.make(fragment.contextView, "Le nombre d'appels est dépassé", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Team> call, @NonNull Throwable t) {
                Snackbar.make(fragment.contextView, "Vérifiez votre connexion Internet", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
