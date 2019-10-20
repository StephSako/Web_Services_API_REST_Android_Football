package com.example.footballapi.controleur;

import androidx.annotation.NonNull;

import com.example.footballapi.model.model_recyclerview.squad.SquadModel;
import com.example.footballapi.services.retrofit.football_data.RestFootballData;
import com.example.footballapi.model.model_retrofit.team.Team;
import com.example.footballapi.view.fragments.SquadFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SquadController {

    private SquadFragment fragment;

    public SquadController(SquadFragment fragment) {
        this.fragment = fragment;
    }

    /**
     * Affiche la liste des joueurs d'une équipe
     * @param token token de connexion
     */
    public void onCreate(final String token) {
        Call<Team> call = RestFootballData.get().teamSquad(token, fragment.idTeam);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(@NonNull Call<Team> call, @NonNull Response<Team> response) {
                if (response.isSuccessful()) {
                    Team team = response.body();
                    assert team != null;

                    List<SquadModel> listFinal = new ArrayList<>();

                    for (int i = 0; i < team.getSquad().size(); i++) {
                        if (team.getSquad().size() > 0) {
                            if (team.getSquad().get(i).getRole().equals("PLAYER")) {
                                SquadModel model = new SquadModel();
                                model.setPlayerName(String.valueOf(team.getSquad().get(i).getName()));

                                if (team.getSquad().get(i).getPosition() != null) {
                                    switch (team.getSquad().get(i).getPosition()) {
                                        case "Goalkeeper":
                                            model.setPlayerPosition("Gardien");
                                            break;
                                        case "Defender":
                                            model.setPlayerPosition("Défenseur");
                                            break;
                                        case "Midfielder":
                                            model.setPlayerPosition("Milieu");
                                            break;
                                        case "Attacker":
                                            model.setPlayerPosition("Attaquant");
                                            break;
                                    }
                                }
                                else model.setPlayerPosition("");

                                model.setPlayerNationality(team.getSquad().get(i).getNationality());
                                model.setPlayerId(String.valueOf(team.getSquad().get(i).getId()));

                                if (team.getSquad().get(i).getShirtNumber() != -1)
                                    model.setPlayerShirtNumber(String.valueOf(team.getSquad().get(i).getShirtNumber()));
                                else model.setPlayerShirtNumber("");

                                fragment.list = listFinal;
                                listFinal.add(model);
                            }
                        }
                    }

                    fragment.showList(listFinal);
                } else {
                    Snackbar.make(Objects.requireNonNull(fragment.getView()), "Le nombre d'appels a été dépassé", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Team> call, @NonNull Throwable t) {
                Snackbar.make(Objects.requireNonNull(fragment.getView()), "Vérifiez votre connexion Internet", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
