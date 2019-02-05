package com.example.footballapi.controleur;

import android.annotation.SuppressLint;
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
    public void afficheDetailsJoueur(final int idPlayer, final PlayerActivity activity, String token) {
        Call<Player> call = RestUser.get().players(token, idPlayer);
        call.enqueue(new Callback<Player>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<Player> call, @NonNull Response<Player> response) {
                if (response.isSuccessful()) {
                    final Player player = response.body();
                    assert player != null;

                    activity.tvBirthday.setText(player.getDateOfBirth());
                    activity.tvClubPlayer.setText(activity.nomClub);
                    activity.tvNationality.setText(player.getNationality());
                    activity.tvPlayerName.setText(player.getName());
                    activity.tvShirtNumberPlayer.setText(player.getShirtNumber());

                    switch (player.getPosition()) {
                        case "Goalkeeper":
                            activity.tvShirtNumberPlayer.setText("Gardien");
                            break;
                        case "Defender":
                            activity.tvShirtNumberPlayer.setText("Défenseur");
                            break;
                        case "Midfielder":
                            activity.tvShirtNumberPlayer.setText("Milieu");
                            break;
                        case "Attacker":
                            activity.tvShirtNumberPlayer.setText("Attaquant");
                            break;
                    }
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
