package com.example.footballapi.controleur;

import android.widget.Toast;

import com.example.footballapi.model.competition.Classement;
import com.example.footballapi.restService.RestUser;
import com.example.footballapi.view.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassementController {

    private MainActivity activity;

    public ClassementController(MainActivity activity) {
        this.activity = activity;
    }

    /**
     * Affiche le classement d'une compétition
     *
     * @param id id de la competition
     */
    public void afficheListeTeamsCompetition(int id) {
        Call<Classement> call = RestUser.get().competitions("e0d9f47d73ae4a4a87eeb24935d8b2f8"/*Resources.getSystem().getString(R.string.token)*/, id);
        call.enqueue(new Callback<Classement>() {
            @Override
            public void onResponse(Call<Classement> call, Response<Classement> response) {
                if (response.isSuccessful()) {
                    final Classement classement = response.body();
                    assert classement != null;

                    // On remplit le textview avec le classement des équipes
                    String standing = "";
                    for (int i = 1; i <= classement.getStandings().get(0).getTable().size(); i++){
                        standing += "" + i + " - " + classement.getStandings().get(0).getTable().get(i-1).getTeam().getName() + "\n";
                    }
                    activity.tvStandings.setText(standing);
                    Toast.makeText(activity, "La competition est " + classement.getCompetition().getName(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(activity, "Classement introuvable", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Classement> call, Throwable t) {
                Toast.makeText(activity, "Vérifiez votre connexion Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
