package com.example.footballapi.controleur;

import android.content.Context;
import android.database.MatrixCursor;
import android.support.annotation.NonNull;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.footballapi.R;
import com.example.footballapi.model.competition.Classement;
import com.example.footballapi.restService.RestUser;
import com.example.footballapi.view.activities.ClassementActivity;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassementController {

    public ClassementController() { }

    /**
     * Affiche le classement d'une compétition
     */
    public void afficheListeTeamsCompetition(final int idCompet, final Context context, final ClassementActivity activity, String token) {
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

                        // On remplit les lignes (le classement d'id 0 représente le classement total du championnat)
                        for (int i = 1; i <= classement.getStandings().get(0).getTable().size(); i++) {
                            String club_name = classement.getStandings().get(0).getTable().get(i - 1).getTeam().getName();
                            int position = classement.getStandings().get(0).getTable().get(i - 1).getPosition();
                            int points = classement.getStandings().get(0).getTable().get(i - 1).getPoints();
                            int diff = classement.getStandings().get(0).getTable().get(i - 1).getGoalDifference();
                            int idTeam = classement.getStandings().get(0).getTable().get(i - 1).getTeam().getId();

                            matrixCursor.addRow(new Object[]{idTeam, position, club_name, diff, points});
                        }

                        ListView lv = activity.lvClassement;

                        // on prendra les données des colonnes 1, 2, 3 et 4
                        String[] from = new String[]{"Position", "Club_name", "Diff", "Points"};

                        // ...pour les placer dans les TextView définis dans "row_classement.xml"
                        int[] to = new int[]{R.id.tvPosition, R.id.tvClubname, R.id.tvDiff, R.id.tvPoints};

                        // création de l'objet SimpleCursorAdapter...
                        adapter = new SimpleCursorAdapter(context, R.layout.row_classement, matrixCursor, from, to, 0);
                    }

                    // ...qui va remplir l'objet ListView
                    activity.lvClassement.setAdapter(adapter);
                } else {
                    Toast.makeText(activity, "Compétition introuvable", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Classement> call, @NonNull Throwable t) {
                Toast.makeText(activity, "Vérifiez votre connexion Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
