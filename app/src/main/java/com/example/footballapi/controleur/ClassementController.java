package com.example.footballapi.controleur;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.example.footballapi.model.model_dao.DataBase;
import com.example.footballapi.model.model_dao.TeamDAO;
import com.example.footballapi.model.model_recyclerview.classement.TeamModel;
import com.example.footballapi.model.model_retrofit.competition.Classement;
import com.example.footballapi.services.retrofit.football_data.RestFootballData;
import com.example.footballapi.view.fragments.ClassementFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassementController {

    private ClassementFragment fragment;

    public ClassementController(ClassementFragment fragment) {
        this.fragment = fragment;
    }

    /**
     * Affiche le classement d'une compétition
     * @param token token de connexion
     */
    public void onCreate(String token, int idCompet) {
        Call<Classement> call = RestFootballData.get().competitions(token, idCompet);
        call.enqueue(new Callback<Classement>() {

            @Override
            public void onResponse(@NonNull Call<Classement> call, @NonNull Response<Classement> response) {
                if (response.isSuccessful()) {
                    Classement classement = response.body();
                    assert classement != null;

                    Objects.requireNonNull(fragment.getActivity()).setTitle(classement.getCompetition().getName());

                    List<TeamModel> listFinal = new ArrayList<>();
                    @SuppressLint("UseSparseArrays") HashMap<Integer, String> teamsNameCrests = new HashMap<>();

                    // On remplit les lignes (le classement d'id 0 représente le classement total du championnat)
                    for (int i = 1; i <= classement.getStandings().get(0).getTable().size(); i++) {
                        TeamModel model = new TeamModel();
                        model.setPosition(String.valueOf(classement.getStandings().get(0).getTable().get(i - 1).getPosition()));
                        model.setName(classement.getStandings().get(0).getTable().get(i - 1).getTeam().getName());
                        model.setDiff(String.valueOf(classement.getStandings().get(0).getTable().get(i - 1).getGoalDifference()));
                        model.setPoints(String.valueOf(classement.getStandings().get(0).getTable().get(i - 1).getPoints()));
                        model.setIdTeam(String.valueOf(classement.getStandings().get(0).getTable().get(i - 1).getTeam().getId()));
                        model.setCrestURL(String.valueOf(classement.getStandings().get(0).getTable().get(i - 1).getTeam().getCrestUrl()));
                        listFinal.add(model);

                        teamsNameCrests.put(classement.getStandings().get(0).getTable().get(i - 1).getTeam().getId(), classement.getStandings().get(0).getTable().get(i - 1).getTeam().getCrestUrl());
                    }

                    fragment.showList(listFinal, true);
                } else {
                    Snackbar.make(Objects.requireNonNull(fragment.getView()), "Le nombre d'appels a été dépassé", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Classement> call, @NonNull Throwable t) {

                // On affiche le classement récupéré depuis la base de données locale en mode hors ligne
                DataBase database = new DataBase(fragment.getContext());
                List<TeamDAO> classementDAO = database.findClassementById(fragment.idCompet);

                if (classementDAO.size() > 0) { // Si la BD locale n'a jamais été initialisée

                    List<TeamModel> listFinal = new ArrayList<>();

                    // On remplit les lignes (le classement d'id 0 représente le classement total du championnat)
                    for (int i = 0; i < classementDAO.size(); i++) {
                        TeamModel model = new TeamModel();

                        model.setPosition(String.valueOf(classementDAO.get(i).getPosition()));
                        model.setName(classementDAO.get(i).getClub_name());
                        model.setDiff(String.valueOf(classementDAO.get(i).getDiff()));
                        model.setPoints(String.valueOf(classementDAO.get(i).getPoints()));
                        model.setIdTeam(String.valueOf(classementDAO.get(i).getDiff()));
                        listFinal.add(model);
                    }

                    // booléen qui active ou désactive les écouteurs sur les item de la recyclerview en cas de activity_connexion oun non à internet
                    fragment.showList(listFinal, false);
                }
                Snackbar.make(Objects.requireNonNull(fragment.getView()), "Vérifiez votre connexion Internet", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
