package com.example.footballapi.controleur;

import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.example.footballapi.model.competition.Classement;
import com.example.footballapi.restService.RestUser;
import com.example.footballapi.view.MainActivity;

import java.io.InputStream;
import java.net.URL;

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

                    // Nom les colonnes du tableau
                    final String[] TABLE_HEADERS = {""/*Position*/, ""/*Logo*/, ""/*Club*/, "Diff", "Pts"};
                    final String[] club_names= new String[] {};

                    for (int i = 0; i < classement.getStandings().get(0).getTable().size(); i++){
                        club_names[i] = classement.getStandings().get(0).getTable().get(i).getTeam().getName();
                    }

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

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "srcImage");
            return d;
        } catch (Exception e) {
            return null;
        }
    }
}
