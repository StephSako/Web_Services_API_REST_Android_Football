package com.example.footballapi.controleur;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.ahmadrosid.svgloader.SvgLoader;
import com.example.footballapi.R;
import com.example.footballapi.model.model_retrofit.player.Player;
import com.example.footballapi.model.model_retrofit.retrofit.football_data.RestFootballData;
import com.example.footballapi.view.fragments.PlayerFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayerController {

    private PlayerFragment fragment;

    public PlayerController(PlayerFragment fragment) {
        this.fragment = fragment;
    }

    /**
     * Affiche les détails d'un joueur
     * @param token token de connexion
     */
    public void onCreate(String token) {
        Call<Player> call = RestFootballData.get().players(token, fragment.idPlayer);
        call.enqueue(new Callback<Player>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<Player> call, @NonNull Response<Player> response) {
                if (response.isSuccessful()) {
                    final Player player = response.body();
                    assert player != null;

                    // On change le title de l'actionBar par le nom du joueur
                    Objects.requireNonNull(fragment.getActivity()).setTitle(player.getName());

                    if (!fragment.crestURLPlayer.equals("") && fragment.loadingPicsPlayer)
                        SvgLoader.pluck()
                                .with(fragment.getActivity())
                                .setPlaceHolder(R.drawable.ic_logo_foreground, R.drawable.ic_logo_foreground)
                                .load(fragment.crestURLPlayer, fragment.logo_club_player)
                                .close();
                    else fragment.logo_club_player.setImageResource(R.drawable.ic_logo_foreground);

                    String[] dateBirthDay = player.getDateOfBirth().split("-");
                    String day = dateBirthDay[2];
                    String month = dateBirthDay[1];
                    String year = dateBirthDay[0];

                    String birthday = day + "/" + month + "/" + year;
                    fragment.tvBirthday.setText(birthday);

                    fragment.tvClubPlayer.setText(fragment.nomClub);
                    fragment.tvNationality.setText(player.getNationality());
                    fragment.tvPlayerName.setText(player.getName());

                    if (player.getPosition() != null) {
                        switch (player.getPosition()) {
                            case "Goalkeeper":
                                fragment.tvPostePlayer.setText("Gardien");
                                break;
                            case "Defender":
                                fragment.tvPostePlayer.setText("Défenseur");
                                break;
                            case "Midfielder":
                                fragment.tvPostePlayer.setText("Milieu");
                                break;
                            case "Attacker":
                                fragment.tvPostePlayer.setText("Attaquant");
                                break;
                        }
                    }
                    else {
                        fragment.tvPostePlayer.setText("Entraineur");
                    }

                } else {
                    Snackbar.make(fragment.contextView, "Le nombre d'appels a été dépassé", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Player> call, @NonNull Throwable t) {
                Snackbar.make(fragment.contextView, "Vérifiez votre connexion Internet", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
