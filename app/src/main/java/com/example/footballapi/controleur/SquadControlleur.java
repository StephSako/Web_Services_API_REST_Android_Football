package com.example.footballapi.controleur;

import android.database.MatrixCursor;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.footballapi.R;
import com.example.footballapi.model.team.Team;
import com.example.footballapi.restService.RestUser;
import com.example.footballapi.view.fragments.SquadFragment;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SquadControlleur {

    private SquadFragment fragment;

    public SquadControlleur(SquadFragment fragment) {
        this.fragment = fragment;
    }

    /**
     * Affiche la liste des joueurs d'une équipe
     */
    public void onCreate(int idTeam, final String token, final View v) {
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
                        Objects.requireNonNull(fragment).startManagingCursor(matrixCursor);

                        // On remplit les lignes (le classement d'id 0 repésente le classement total du championnat)
                        for (int i = 0; i < team.getSquad().size(); i++) {

                            if (team.getSquad().get(i).getRole().equals("PLAYER")) {
                                String player_name = team.getSquad().get(i).getName();
                                String position = "";

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
                                String nationality = team.getSquad().get(i).getNationality();

                                String shirtNumber = "";
                                if (team.getSquad().get(i).getShirtNumber() != -1)
                                    shirtNumber = String.valueOf(team.getSquad().get(i).getShirtNumber());

                                int idPlayer = team.getSquad().get(i).getId();
                                matrixCursor.addRow(new Object[]{idPlayer, player_name, nationality, position, shirtNumber});
                            }
                        }

                        String[] from = new String[]{"PlayerName", "Nationality", "Position", "ShirtNumber"};
                        int[] to = new int[]{R.id.tvPlayerName, R.id.tvNationality, R.id.tvPosition, R.id.tvShirtNumber};
                        adapter = new SimpleCursorAdapter(context, R.layout.row_squad, matrixCursor, from, to, 0);
                    }

                    ListView lvSquad = v.findViewById(R.id.lvSquad);
                    lvSquad.setAdapter(adapter);
                } else {
                    Toast.makeText(activity, "Equipe introuvable", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Team> call, @NonNull Throwable t) {
                Toast.makeText(activity, "Vérifiez votre connexion Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
