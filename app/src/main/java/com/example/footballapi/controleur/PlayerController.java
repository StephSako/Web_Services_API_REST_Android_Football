package com.example.footballapi.controleur;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.ahmadrosid.svgloader.SvgLoader;
import com.example.footballapi.R;
import com.example.footballapi.model.model_retrofit.player.Player;
import com.example.footballapi.model.model_retrofit.restService.football_data.RestFootballData;
import com.example.footballapi.view.activities.PlayerActivity;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayerController {

    private PlayerActivity activity;

    public PlayerController(PlayerActivity activity) {
        this.activity = activity;
    }

    /**
     * Affiche les détails d'un joueur
     * @param token token de connexion
     */
    public void onCreate(String token) {
        Call<Player> call = RestFootballData.get().players(token, activity.idPlayer);
        call.enqueue(new Callback<Player>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<Player> call, @NonNull Response<Player> response) {
                if (response.isSuccessful()) {
                    final Player player = response.body();
                    assert player != null;

                    // On change le title de l'actionBar par le nom du joueur
                    Objects.requireNonNull(activity).setTitle(player.getName());

                    if (!activity.crestURLPlayer.equals("") && activity.loadingPicsPlayer)
                        SvgLoader.pluck()
                                .with(activity)
                                .setPlaceHolder(R.drawable.ic_logo_foreground, R.drawable.ic_logo_foreground)
                                .load(activity.crestURLPlayer, activity.logo_club_player)
                                .close();
                    else activity.logo_club_player.setImageResource(R.drawable.ic_logo_foreground);

                    String[] dateBirthDay = player.getDateOfBirth().split("-");
                    String day = dateBirthDay[2];
                    String month = dateBirthDay[1];
                    String year = dateBirthDay[0];

                    String birthday = day + "/" + month + "/" + year;
                    activity.tvBirthday.setText(birthday);

                    activity.tvClubPlayer.setText(activity.nomClub);
                    activity.tvNationality.setText(player.getNationality());
                    activity.tvPlayerName.setText(player.getName());

                    if (player.getShirtNumber() != -1)
                        activity.tvShirtNumberPlayer.setText(String.valueOf(player.getShirtNumber()));

                    if (player.getPosition() != null) {
                        switch (player.getPosition()) {
                            case "Goalkeeper":
                                activity.tvPostePlayer.setText("Gardien");
                                break;
                            case "Defender":
                                activity.tvPostePlayer.setText("Défenseur");
                                break;
                            case "Midfielder":
                                activity.tvPostePlayer.setText("Milieu");
                                break;
                            case "Attacker":
                                activity.tvPostePlayer.setText("Attaquant");
                                break;
                        }
                    }
                    else activity.tvPostePlayer.setText("");

                } else {
                    Snackbar.make(activity.contextView, "Le nombre d'appels a été dépassé", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Player> call, @NonNull Throwable t) {
                Snackbar.make(activity.contextView, "Vérifiez votre connexion Internet", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
