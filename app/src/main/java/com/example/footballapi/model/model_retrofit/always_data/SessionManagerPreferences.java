package com.example.footballapi.model.model_retrofit.always_data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;

/**
 * Gestionnaire de persistance longue
 */
public class SessionManagerPreferences {

    @SuppressLint("StaticFieldLeak")
    private static SessionManagerPreferences instance = null;

    private SharedPreferences sharedPreferences ;
    private SharedPreferences.Editor editor;

    private SessionManagerPreferences(Context context){
        this.sharedPreferences = context.getSharedPreferences("LOGIN_SETTINGS", MODE_PRIVATE);
        this.editor = this.sharedPreferences.edit();
    }

    public synchronized static SessionManagerPreferences getSettings(Context context) {
        if (instance == null){
            instance = new SessionManagerPreferences(context);
        }
        return instance;
    }

    public void sign_in(int idSupporter, String pseudo, String password, int favoriteTeam){
        this.editor.putInt("idSupporter", idSupporter);
        this.editor.putString("pseudo", pseudo);
        this.editor.putString("password", password);
        this.editor.putInt("favoriteTeam", favoriteTeam);
        this.editor.commit();
    }

    public void logout() {
        this.editor.clear();
        this.editor.commit();
    }

    public HashMap getSupporter(){
        HashMap<String, String> supporter = new HashMap<>();
        supporter.put("idSupporter", String.valueOf(this.sharedPreferences.getInt("idSupporter", -1)));
        supporter.put("pseudo", this.sharedPreferences.getString("pseudo", ""));
        supporter.put("password", this.sharedPreferences.getString("password", ""));
        supporter.put("favoriteTeam", String.valueOf(this.sharedPreferences.getInt("idSupporter", -1)));
        return supporter;
    }
}