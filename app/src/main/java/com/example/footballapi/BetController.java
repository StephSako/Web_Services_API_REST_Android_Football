package com.example.footballapi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.footballapi.model.model_retrofit.restService.always_data.RestAlwaysData;
import com.example.footballapi.model.model_retrofit.supporter.Bet;
import com.example.footballapi.model.model_session_manager.SessionManagerPreferences;
import com.example.footballapi.view.activities.MainActivity;
import com.example.footballapi.view.activities.MatchActivity;

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
    public void onCreate(int idMatch, int idSupporter, int idWinner) {
        Call<Bet> call = RestAlwaysData.get().bet(idMatch, idSupporter, idWinner);
        call.enqueue(new Callback<Bet>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<Bet> call, @NonNull Response<Bet> response) {
                if (response.isSuccessful()) {
                    final Bet bet = response.body();
                    assert bet != null;

                    // Mettre à jour les paris dans les SharedPreferences
                    SessionManagerPreferences.getSettings(activity.getApplicationContext()).sign_in(supporter.getIdSupporter(), supporter.getPseudo(), supporter.getPassword(), supporter.getFavoriteTeam(), supporter.getTab_bets());
                    Intent intent = new Intent(activity, MainActivity.class);
                    activity.startActivity(intent);
                    activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Bet> call, @NonNull Throwable t) {
                Toast.makeText(activity, "Vérifiez votre connexion_activity Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
