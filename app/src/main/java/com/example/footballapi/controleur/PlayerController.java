package com.example.footballapi.controleur;

import android.widget.Toast;

import com.example.footballapi.model.player.Player;
import com.example.footballapi.restService.RestUser;
import com.example.footballapi.view.StadingsActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayerController {
    /**
     * Affiche les détails d'un joueur
     *
     * @param id id du joueur
     */

    private StadingsActivity activity;

    public PlayerController(StadingsActivity activity) {
        this.activity = activity;
    }

    public void afficheDetailsJoueur(int id) {
        Call<Player> call = RestUser.get().players("e0d9f47d73ae4a4a87eeb24935d8b2f8"/*Resources.getSystem().getString(R.string.token)*/, id);
        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                if (response.isSuccessful()) {
                    final Player player = response.body();
                    assert player != null;
                    activity.tvGeneralActivity.setText("Nom : " + player.getName() + "\nNationalité : " + player.getNationality() + "\nPosition : " + player.getPosition() + "\nNuméro : " + player.getShirtNumber());

                    Toast.makeText(activity, "Le joueur est " + player.getName(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(activity, "Joueur introuvable", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                Toast.makeText(activity, "Vérifiez votre connexion Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
