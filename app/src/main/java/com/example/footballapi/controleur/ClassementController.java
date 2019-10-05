package com.example.footballapi.controleur;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.footballapi.model.model_retrofit.competition.Classement;
import com.example.footballapi.model.model_dao.DataBase;
import com.example.footballapi.model.model_dao.TeamDAO;
import com.example.footballapi.model.model_recyclerview.classement.TeamModel;
import com.example.footballapi.model.model_retrofit.restService.football_data.RestFootballData;
import com.example.footballapi.view.fragments.ClassementFragment;

import java.util.ArrayList;
import java.util.List;

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
     * @param token
     */
    public void onCreate(String token) {
        Call<Classement> call = RestFootballData.get().competitions(token, fragment.idCompet);
        call.enqueue(new Callback<Classement>() {

            @Override
            public void onResponse(@NonNull Call<Classement> call, @NonNull Response<Classement> response) {
                if (response.isSuccessful()) {
                    Classement classement = response.body();
                    assert classement != null;

                    List<TeamModel> listFinal = new ArrayList<>();

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
                    }

                    fragment.showList(listFinal, true);
                } else {
                    Toast.makeText(fragment.getContext(), "Le nombre d'appels a été dépassé", Toast.LENGTH_SHORT).show();
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

                    // booléen qui active ou désactive les écouteurs sur les item de la recyclerview en cas de connexion_activity oun non à internet
                    fragment.showList(listFinal, false);
                }
                Toast.makeText(fragment.getContext(), "Classement non mis à jour.\nVérifiez votre connexion.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
