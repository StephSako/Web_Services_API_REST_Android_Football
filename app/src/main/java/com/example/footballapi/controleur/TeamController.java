package com.example.footballapi.controleur;

import android.app.Activity;
import android.content.Context;
import android.database.MatrixCursor;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.footballapi.R;
import com.example.footballapi.model.team.Team;
import com.example.footballapi.restService.RestUser;
import com.example.footballapi.view.activities.TeamActivity;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamController {

    public TeamController() {   }

    /**
     * Affiche les détails d'une équipe
     * */
    public void afficheDetailsTeam(int idTeam, final TeamActivity activity, final String token) {
        Call<Team> call = RestUser.get().teamsDetails(token, idTeam);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(@NonNull Call<Team> call, @NonNull Response<Team> response) {
                if (response.isSuccessful()) {
                    Team team = response.body();
                    assert team != null;

                    Objects.requireNonNull(activity).setTitle(team.getName());

                    activity.tvTeamsColors.setText(team.getClubColors());
                    activity.tvStade.setText(team.getVenue());

                    StringBuilder activeCompetitions = new StringBuilder();
                    for (int i = 0; i < team.getActiveCompetitions().size(); i++){
                        if (i == team.getActiveCompetitions().size() - 1) activeCompetitions.append(team.getActiveCompetitions().get(i).getName());
                        else activeCompetitions.append(team.getActiveCompetitions().get(i).getName()).append(", ");
                    }
                    activity.tvActiveCompetitions.setText(activeCompetitions.toString());

                } else {
                    Toast.makeText(activity, "Compétition introuvable", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Team> call, @NonNull Throwable t) {
                Toast.makeText(activity, "Vérifiez votre connexion Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Affiche la liste des joueurs d'une équipe
     */
    public void afficheListePlayersTeams(int idTeam, final Context context, final Activity activity, final String token, final View v) {
        Call<Team> call = RestUser.get().teamSquad(token, idTeam);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(@NonNull Call<Team> call, @NonNull Response<Team> response) {
                if (response.isSuccessful()) {
                    Team team = response.body();
                    assert team != null;

                    String[] columns = new String[] { "_id", "PlayerName", "Nationality", "Position", "ShirtNumber" };

                    // Définition des données du tableau
                    SimpleCursorAdapter adapter;
                    try (MatrixCursor matrixCursor = new MatrixCursor(columns)) {
                        Objects.requireNonNull(activity).startManagingCursor(matrixCursor);

                        // On remplit les lignes (le classement d'id 0 repésente le classement total du championnat)
                        for (int i = 0; i < team.getSquad().size(); i++) {
                            String player_name = team.getSquad().get(i).getName();

                            String position = "";
                            if (team.getSquad().get(i).getPosition() != null) {
                                switch (team.getSquad().get(i).getPosition()) {
                                    case "Goalkeeper":
                                        position = "Gardien";
                                        break;
                                    case "Defender":
                                        position = "Défenseur";
                                        break;
                                    case "Midfielder":
                                        position = "Milieu";
                                        break;
                                    case "Attacker":
                                        position = "Attaquant";
                                        break;
                                }
                            }
                            else position = "Entraîneur";

                            String nationality = team.getSquad().get(i).getNationality();

                            String shirtNumber = "";
                            if (team.getSquad().get(i).getShirtNumber() != 0)
                                shirtNumber = String.valueOf(team.getSquad().get(i).getShirtNumber());

                            int idPlayer = team.getSquad().get(i).getId();
                            matrixCursor.addRow(new Object[]{idPlayer, player_name, nationality, position, shirtNumber});
                        }

                        // on prendra les données des colonnes 1, 2, 3 et 4
                        String[] from = new String[]{"PlayerName", "Nationality", "Position", "ShirtNumber"};

                        // ...pour les placer dans les TextView définis dans "row_classement.xml"
                        int[] to = new int[]{R.id.tvPosition, R.id.tvClubname, R.id.tvDiff, R.id.tvPoints};

                        // création de l'objet SimpleCursorAdapter...
                        adapter = new SimpleCursorAdapter(context, R.layout.row_classement, matrixCursor, from, to, 0);
                    }

                    // ...qui va remplir l'objet ListView
                    ListView lvSquad = v.findViewById(R.id.lvSquad);
                    lvSquad.setAdapter(adapter);

                    // Gestion des clics sur les lignes
                    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View container, int position, long id) {
                            Toast.makeText(activity, "L'id player est " + id + " dans l'API", Toast.LENGTH_SHORT).show();
                        }
                    };

                    // Utilisation avec notre listview
                    lvSquad.setOnItemClickListener(itemClickListener);
                } else {
                    Toast.makeText(activity, "Compétition introuvable", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Team> call, @NonNull Throwable t) {
                Toast.makeText(activity, "Vérifiez votre connexion Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
