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

public class EditPseudoController {

    private EditAccountActivity activity;

    public EditPseudoController(EditAccountActivity activity) {
        this.activity = activity;
    }

    /**
     * Inscrire un supporter
     * @param pseudo pseudo du supporter
     */
    public void onCreate(final String pseudo) {
        Call<ResponseBody> call = RestAlwaysData.get().editPseudo(pseudo, new SessionManagerPreferences(activity).getIdSupporter());
        call.enqueue(new Callback<ResponseBody>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Snackbar.make(activity.contextView, "Votre pseudo a bien été modifié", Snackbar.LENGTH_SHORT).show();
                    new SessionManagerPreferences(activity).updateSupporter(pseudo);

                } else {
                    Snackbar.make(activity.contextView, "Erreur lors du traitement", Snackbar.LENGTH_SHORT).show();
                }
                activity.lockFieldAndButtons(true);
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                activity.lockFieldAndButtons(true);
                Snackbar.make(activity.contextView, "Vérifiez votre connexion Internet", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
