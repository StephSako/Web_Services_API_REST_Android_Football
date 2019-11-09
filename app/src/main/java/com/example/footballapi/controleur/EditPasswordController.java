package com.example.footballapi.controleur;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.example.footballapi.model.model_retrofit.retrofit.always_data.RestAlwaysData;
import com.example.footballapi.view.activities.EditAccountActivity;
import com.google.android.material.snackbar.Snackbar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPasswordController {

    private EditAccountActivity activity;

    public EditPasswordController(EditAccountActivity activity) {
        this.activity = activity;
    }

    /**
     * Inscrire un supporter
     * @param password pseudo du supporter
     */
    public void onCreate(final String password) {
        Call<ResponseBody> call = RestAlwaysData.get().editPassword(password, new SessionManagerPreferences(activity).getIdSupporter());
        call.enqueue(new Callback<ResponseBody>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Snackbar.make(activity.contextView, "Votre mot de passe a bien été modifié", Snackbar.LENGTH_SHORT).show();
                    new SessionManagerPreferences(activity).updatePasswordSupporter(password);
                    activity.lockFieldAndButtons(true);

                } else {
                    Snackbar.make(activity.contextView, "Erreur lors du traitement", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Snackbar.make(activity.contextView, "Vérifiez votre connexion Internet", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
