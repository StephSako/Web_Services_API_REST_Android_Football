package com.example.footballapi.controleur;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.example.footballapi.model.model_retrofit.supporter.PourcentBet;
import com.example.footballapi.services.retrofit.always_data.RestAlwaysData;
import com.example.footballapi.view.activities.MatchActivity;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PourcentBetController {

    private MatchActivity activity;

    public PourcentBetController(MatchActivity activity) {
        this.activity = activity;
    }

    /**
     * Parier sur un match
     * @param idMatch id du match
     * @param idHome id de l'équipe home
     * @param idAway id de l'équipe away
     */
    public void onCreate(int idMatch, int idHome, int idAway) {
        Call<PourcentBet> call = RestAlwaysData.get().pourcent(idMatch, idHome, idAway);
        call.enqueue(new Callback<PourcentBet>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<PourcentBet> call, @NonNull Response<PourcentBet> response) {
                if (response.isSuccessful()) {
                    final PourcentBet pourcentbets = response.body();
                    assert pourcentbets != null;

                    activity.tvPourcentHome.setText(pourcentbets.getPourcentHome() + "%");
                    activity.tvPourcentAway.setText(pourcentbets.getPourcentAway() + "%");

                    if (pourcentbets.getNbParieurs() > 0) activity.tvNbParieurs.setText(pourcentbets.getNbParieurs() + " parieurs pour ce match");
                    else if (pourcentbets.getNbParieurs() == 1) activity.tvNbParieurs.setText(pourcentbets.getNbParieurs() + " parieur pour ce match");
                    else activity.tvNbParieurs.setText("Aucun pari pour ce match");
                }
            }

            @Override
            public void onFailure(@NonNull Call<PourcentBet> call, @NonNull Throwable t) {
                Snackbar.make(activity.contextView, "Vérifiez votre connexion Internet", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
