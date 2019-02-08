package com.example.footballapi.controleur;

import android.app.Activity;
import android.content.Context;
import android.database.MatrixCursor;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.footballapi.R;
import com.example.footballapi.model.team.Team;
import com.example.footballapi.restService.RestUser;
import com.example.footballapi.view.fragments.MatchesFragment;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchesController {

    private MatchesFragment fragment;

    public MatchesController(MatchesFragment fragment) {
        this.fragment = fragment;
    }

    /**
     * Affiche la liste des matches d'une équipe
     */
    public void onCreate(int idTeam, final Context context, final Activity activity, final String token, final View v) {
        Call<Team> call = RestUser.get().matches(token, idTeam);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(@NonNull Call<Team> call, @NonNull Response<Team> response) {
                if (response.isSuccessful()) {
                    Team team = response.body();
                    assert team != null;

                    String[] columns = new String[] { "_id", "MatchDay", "HomeTeam", "Score", "AwayTeam" };

                    // Définition des données du tableau
                    SimpleCursorAdapter adapter;
                    try (MatrixCursor matrixCursor = new MatrixCursor(columns)) {
                        Objects.requireNonNull(activity).startManagingCursor(matrixCursor);

                        // On remplit les lignes de matches
                        for (int i = 0; i < team.getMatches().size(); i++) {
                            int MatchDay = team.getMatches().get(i).getMatchday();
                            String HomeTeam = team.getMatches().get(i).getHomeTeam().getName();
                            String AwayTeam = team.getMatches().get(i).getAwayTeam().getName();

                            // On vérifie si le match a déjà été joué ou pas
                            String Score;
                            if (team.getMatches().get(i).getStatus().equals("FINISHED"))
                                Score = team.getMatches().get(i).getScore().getFullTime().getHomeTeam() + " - " + team.getMatches().get(i).getScore().getFullTime().getAwayTeam();
                            else{
                                String[] parts = team.getMatches().get(i).getUtcDate().split("T");
                                String date = parts[0]; // Day
                                String[] dateDay = date.split("-");
                                String day = dateDay[2];
                                String month = dateDay[1];
                                String year = dateDay[0];

                                Score = day + "/" + month + "/" + year;
                            }

                            int id = team.getMatches().get(i).getId();
                            matrixCursor.addRow(new Object[]{id, MatchDay, HomeTeam, Score, AwayTeam});
                        }

                        String[] from = new String[]{ "MatchDay", "HomeTeam", "Score", "AwayTeam"};
                        int[] to = new int[]{R.id.tvMatchday, R.id.tvHomeTeam, R.id.tvScore, R.id.tvAwayTeam};
                        adapter = new SimpleCursorAdapter(context, R.layout.row_matches, matrixCursor, from, to, 0);
                    }
                    ListView lvMatches = v.findViewById(R.id.lvMatches);
                    lvMatches.setAdapter(adapter);
                } else {
                    Toast.makeText(activity, "Match introuvable", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Team> call, @NonNull Throwable t) {
                Toast.makeText(activity, "Vérifiez votre connexion Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
