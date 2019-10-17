package com.example.footballapi.controleur;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.example.footballapi.model.model_retrofit.restService.always_data.RestAlwaysData;
import com.example.footballapi.model.model_retrofit.supporter.ListBet;
import com.example.footballapi.services.SessionManagerPreferences;
import com.example.footballapi.view.activities.MatchActivity;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BetController {

    private MatchActivity activity;

    public BetController(MatchActivity activity) {
        this.activity = activity;
    }

    /**
     * Parier sur un match
     * @param idMatch id du match
     * @param idSupporter id du supporter dans les SharedPreferences
     * @param idWinner id de l'équipe gagnante
     */
    public void onCreate(final int idMatch, int idSupporter, final int idWinner) {
        Call<ListBet> call = RestAlwaysData.get().bet(idMatch, idSupporter, idWinner);
        call.enqueue(new Callback<ListBet>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<ListBet> call, @NonNull Response<ListBet> response) {
                if (response.isSuccessful()) {
                    final ListBet bets = response.body();
                    assert bets != null;

                    // Mettre à jour les paris dans les SharedPreferences
                    SessionManagerPreferences.getSettings(activity.getApplicationContext()).updateBets(bets.getTab_bets());
                    Snackbar.make(activity.contextView, "Paris effectué", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ListBet> call, @NonNull Throwable t) {
                Snackbar.make(activity.contextView, "Vérifiez votre connexion Internet", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
