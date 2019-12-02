package com.example.footballapi.controleur;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.example.footballapi.model.model_retrofit.retrofit.always_data.RestAlwaysData;
import com.example.footballapi.model.model_retrofit.supporter.PourcentBet;
import com.example.footballapi.view.fragments.MatchFragment;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PourcentBetController {

    private MatchFragment fragment;

    public PourcentBetController(MatchFragment fragment) {
        this.fragment = fragment;
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

                    fragment.tvPourcentHome.setText(pourcentbets.getPourcentHome() + "%");
                    fragment.tvPourcentAway.setText(pourcentbets.getPourcentAway() + "%");

                    if (pourcentbets.getNbParieurs() == 1) fragment.tvNbParieurs.setText(pourcentbets.getNbParieurs() + " parieur pour ce match");
                    else if (pourcentbets.getNbParieurs() > 0) fragment.tvNbParieurs.setText(pourcentbets.getNbParieurs() + " parieurs pour ce match");
                    else fragment.tvNbParieurs.setText("Aucun pari pour ce match");
                }
            }

            @Override
            public void onFailure(@NonNull Call<PourcentBet> call, @NonNull Throwable t) {
                Snackbar.make(fragment.contextView, "Vérifiez votre connexion Internet", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
