package com.example.footballapi.controleur;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.footballapi.model.team.Team;
import com.example.footballapi.restService.RestUser;
import com.example.footballapi.view.activities.ClassementActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamController {

    /**
     * Affiche la liste des joueurs
     */
    public void afficheListePlayersTeams(int idTeam, final Context context, final Activity activity, String token, final View v) {
        Call<Team> call = RestUser.get().teams("e0d9f47d73ae4a4a87eeb24935d8b2f8"/*Resources.getSystem().getString(R.string.token)*/, idTeam);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                if (response.isSuccessful()) {
                    final Team team = response.body();
                    assert team != null;

                    // On remplit le textview avec la liste des joueurs
                    String listePlayers = "";
                    for (int i = 0; i < team.getSquad().size()-1; i++){
                        listePlayers += team.getSquad().get(i).getName() + " - N° " + team.getSquad().get(i).getShirtNumber() + "\n";
                    }
                    //activity.tvGeneralActivity.setText(listePlayers);
                    Toast.makeText(activity, "L'équipe est " + team.getName(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(activity, "Equipe introuvable", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                Toast.makeText(activity, "Vérifiez votre connexion Internet", Toast.LENGTH_SHORT).show();
            }
        });





        /*
        Call<Classement> call = RestUser.get().competitions(token, idCompet);
        call.enqueue(new Callback<Classement>() {
            @Override
            public void onResponse(@NonNull Call<Classement> call, @NonNull Response<Classement> response) {
                if (response.isSuccessful()) {
                    Classement classement = response.body();
                    assert classement != null;

                    Objects.requireNonNull(activity).setTitle(classement.getCompetition().getName());

                    String[] columns = new String[] { "_id", "Position", "Club_name", "Diff", "Points" };

                    // Définition des données du tableau
                    SimpleCursorAdapter adapter;
                    try (MatrixCursor matrixCursor = new MatrixCursor(columns)) {
                        Objects.requireNonNull(activity).startManagingCursor(matrixCursor);

                        // On remplit les lignes (le classement d'id 0 repésente le classement total du championnat)
                        for (int i = 1; i <= classement.getStandings().get(0).getTable().size(); i++) {
                            String club_name = classement.getStandings().get(0).getTable().get(i - 1).getTeam().getName();
                            int position = classement.getStandings().get(0).getTable().get(i - 1).getPosition();
                            int points = classement.getStandings().get(0).getTable().get(i - 1).getPoints();
                            int diff = classement.getStandings().get(0).getTable().get(i - 1).getGoalDifference();
                            int idTeam = classement.getStandings().get(0).getTable().get(i - 1).getTeam().getId();
                            matrixCursor.addRow(new Object[]{idTeam, position, club_name, diff, points});
                        }

                        // on prendra les données des colonnes 1, 2, 3 et 4
                        String[] from = new String[]{"Position", "Club_name", "Diff", "Points"};

                        // ...pour les placer dans les TextView définis dans "row_classement.xml"
                        int[] to = new int[]{R.id.tvPosition, R.id.tvClubname, R.id.tvDiff, R.id.tvPoints};

                        // création de l'objet SimpleCursorAdapter...
                        adapter = new SimpleCursorAdapter(context, R.layout.row_classement, matrixCursor, from, to, 0);
                    }

                    // ...qui va remplir l'objet ListView
                    lvClassement.setAdapter(adapter);
                } else {
                    Toast.makeText(activity, "Compétition introuvable", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Classement> call, @NonNull Throwable t) {
                Toast.makeText(activity, "Vérifiez votre connexion Internet", Toast.LENGTH_SHORT).show();
            }
        });
         */







    }

    /**
     * Affiche les détails d'une équipe
     *
     * @param id id de l'équipe
     */
    public void afficheDetailsTeams(int id) {
        Call<Team> call = RestUser.get().teams("e0d9f47d73ae4a4a87eeb24935d8b2f8"/*Resources.getSystem().getString(R.string.token)*/, id);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                if (response.isSuccessful()) {
                    final Team team = response.body();
                    assert team != null;

                    // On remplit le textview avec les details de l'équipe
                    String details = "";
                    details += "Name : " + team.getName() + "\nCouleurs : " + team.getClubColors() + "\nStade : " + team.getVenue() + "\nCompétitions : \n";
                    for (int i = 0; i < team.getActiveCompetitions().size(); i++){
                        details += "            - " + team.getActiveCompetitions().get(i).getName() + "\n";
                    }
                    details += "\nEntraineur : ";
                    // Renvoyer le nom de l'entraineur
                    for (int i = 0; i < team.getSquad().size(); i++){
                        if (team.getSquad().get(i).getRole().equals("COACH")) {
                            details += team.getSquad().get(i).getName();
                            break;
                        }
                    }

                    //activity.tvGeneralActivity.setText(details);
                    Toast.makeText(activity, "L'équipe est " + team.getName(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(activity, "Equipe introuvable", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                Toast.makeText(activity, "Vérifiez votre connexion Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Affiche la liste des matches d'une équipe
     *
     * @param id id de l'équipe
     */
    public void afficheListeMatchesTeams(int id) {
        Call<Team> call = RestUser.get().matches("e0d9f47d73ae4a4a87eeb24935d8b2f8"/*Resources.getSystem().getString(R.string.token)*/, id);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                if (response.isSuccessful()) {
                    final Team team = response.body();
                    assert team != null;

                    // On remplit le textview avec la liste des matches de l'équipe
                    String listeMatches = "";
                    for (int i = 0; i < team.getMatches().size();i++){
                        if (team.getMatches().get(i).getScore().getWinner() != null) {
                            listeMatches += "J" + team.getMatches().get(i).getMatchday() + " - " + team.getMatches().get(i).getHomeTeam().getName() + " " + team.getMatches().get(i).getScore().getFullTime().getHomeTeam() + " - " + team.getMatches().get(i).getScore().getFullTime().getAwayTeam() + " " + team.getMatches().get(i).getAwayTeam().getName() + "\n";
                        }
                    }
                    //activity.tvGeneralActivity.setText(listeMatches);
                } else {
                    Toast.makeText(activity, "Matches d'équipe introuvables", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                Toast.makeText(activity, "Vérifiez votre connexion Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
