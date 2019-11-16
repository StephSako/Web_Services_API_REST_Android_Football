package com.example.footballapi.controleur;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.example.footballapi.model.model_retrofit.retrofit.always_data.RestAlwaysData;
import com.example.footballapi.view.fragments.EditAccountFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPasswordController {

    private EditAccountFragment fragment;

    public EditPasswordController(EditAccountFragment fragment) {
        this.fragment = fragment;
    }

    /**
     * Inscrire un supporter
     * @param password pseudo du supporter
     */
    public void onCreate(final String password) {
        Call<ResponseBody> call = RestAlwaysData.get().editPassword(password, new SessionManagerPreferences(Objects.requireNonNull(fragment.getContext())).getIdSupporter());
        call.enqueue(new Callback<ResponseBody>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Snackbar.make(fragment.contextView, "Votre mot de passe a bien été modifié", Snackbar.LENGTH_SHORT).show();
                    new SessionManagerPreferences(fragment.getContext()).updatePasswordSupporter(password);
                } else {
                    Snackbar.make(fragment.contextView, "Erreur lors du traitement", Snackbar.LENGTH_SHORT).show();
                }
                fragment.lockFieldAndButtons(true);
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                fragment.lockFieldAndButtons(true);
                Snackbar.make(fragment.contextView, "Vérifiez votre connexion Internet", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
