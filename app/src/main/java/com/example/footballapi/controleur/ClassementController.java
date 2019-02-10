package com.example.footballapi.controleur;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.footballapi.model.model_retrofit.competition.Classement;
import com.example.footballapi.model.model_dao.DataBase;
import com.example.footballapi.model.model_dao.TeamDAO;
import com.example.footballapi.model.model_recyclerview.classement.TeamModel;
import com.example.footballapi.model.model_retrofit.restService.RestUser;
import com.example.footballapi.view.activities.ClassementActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassementController {

    private ClassementActivity activity;

    public ClassementController(ClassementActivity activity) {
        this.activity = activity;
    }

    /**
     * Affiche le classement d'une compétition
     * @param token
     */
    public void onCreate(String token) {
        Call<Classement> call = RestUser.get().competitions(token, activity.idCompet);
        call.enqueue(new Callback<Classement>() {

            @Override
            public void onResponse(@NonNull Call<Classement> call, @NonNull Response<Classement> response) {
                if (response.isSuccessful()) {
                    Classement classement = response.body();
                    assert classement != null;
                    Objects.requireNonNull(activity).setTitle(classement.getCompetition().getName());

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

                    activity.showList(listFinal, true);
                } else {
                    Toast.makeText(activity, "Compétition introuvable", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Classement> call, @NonNull Throwable t) {

                // On affiche le classement récupéré depuis la base de données locale en mode hors ligne
                DataBase database = new DataBase(activity);
                List<TeamDAO> classementDAO = database.findClassementById(activity.idCompet);

                if (classementDAO.size() > 0) { // Si la BD locale n'a jamais été initialisée

                    Objects.requireNonNull(activity).setTitle(classementDAO.get(0).getNomCompet() + " - [Hors ligne]");

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

                    // booléen qui active ou désactive les écouteurs sur les item de la recyclerview en cas de connexion oun non à internet
                    activity.showList(listFinal, false);
                }
                Toast.makeText(activity, "Classement non mis à jour.\nVérifiez votre connexion.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
