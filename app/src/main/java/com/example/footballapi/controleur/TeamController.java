package com.example.footballapi.controleur;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ahmadrosid.svgloader.SvgLoader;
import com.example.footballapi.R;
import com.example.footballapi.model.model_retrofit.retrofit.football_data.RestFootballData;
import com.example.footballapi.model.model_retrofit.team.Team;
import com.example.footballapi.model.model_viewpager.team.Adapter_ViewPagerTeam;
import com.example.footballapi.view.fragments.TeamFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
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
                    fragment.address = team.getAddress();

                    Objects.requireNonNull(fragment.getActivity()).setTitle(team.getName());

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

                    // For unknow colors
                    for (int i=0; i < colorsClub.length; i++) {
                        colorsClub[i] = colorsClub[i].replaceAll("Navy Blue", "Navy");
                        colorsClub[i] = colorsClub[i].replaceAll("Claret", "#722F37");
                        colorsClub[i] = colorsClub[i].replaceAll("Sky Blue", "#87CEEB");
                        colorsClub[i] = colorsClub[i].replaceAll("Royal Blue", "#4169E1");
                        colorsClub[i] = colorsClub[i].replaceAll("Gold", "#FFD700");
                    }

                    int[] colors = {Color.parseColor(colorsClub[0].toLowerCase()), Color.parseColor(colorsClub[1].toLowerCase())};
                    GradientDrawable gradient = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors);
                    ActionBar bar = ((AppCompatActivity) fragment.getActivity()).getSupportActionBar();
                    Objects.requireNonNull(bar).setBackgroundDrawable(gradient);

                    ViewPager viewPager = fragment.v.findViewById(R.id.pagerteam);
                    Adapter_ViewPagerTeam myPagerAdapter = new Adapter_ViewPagerTeam(fragment.getChildFragmentManager(), team.getId(), "team", team.getVenue(), team.getAddress(), team.getWebSite());
                    viewPager.setAdapter(myPagerAdapter);
                    TabLayout tabLayout = fragment.v.findViewById(R.id.tablayoutteam);
                    tabLayout.setupWithViewPager(viewPager);
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
