package com.example.footballapi.controleur;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ListView;
import android.widget.Toast;

import com.example.footballapi.model.player.Player;
import com.example.footballapi.restService.RestUser;
import com.example.footballapi.view.activities.ClassementActivity;
import com.example.footballapi.view.activities.PlayerActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayerController {

    public PlayerController() {   }

    /**
     * Affiche les détails d'un joueur
     */
    private ClassementActivity activity;

    public PlayerController(ClassementActivity activity) {
        this.activity = activity;
    }

    public void afficheDetailsJoueur(final int idPlayer, final Context context, final PlayerActivity activity, String token) {
        Call<Player> call = RestUser.get().players(token, idPlayer);
        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(@NonNull Call<Player> call, @NonNull Response<Player> response) {
                if (response.isSuccessful()) {
                    final Player player = response.body();
                    assert player != null;



                    // ...


                    Toast.makeText(activity, "ID joueur : " + idPlayer, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(activity, "Joueur introuvable", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Player> call, @NonNull Throwable t) {
                Toast.makeText(activity, "Vérifiez votre connexion Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
