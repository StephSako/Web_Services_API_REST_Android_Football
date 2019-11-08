package com.example.footballapi.controleur;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.footballapi.model.model_retrofit.supporter.Bet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

/**
 * Gestionnaire de persistance longue
 */
public class SessionManagerPreferences {

    @SuppressLint("StaticFieldLeak")
    private static SessionManagerPreferences instance = null;

    private SharedPreferences sharedPreferences ;
    private SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    public SessionManagerPreferences(Context context){
        this.sharedPreferences = context.getSharedPreferences("LOGIN_SETTINGS", MODE_PRIVATE);
        this.editor = this.sharedPreferences.edit();
    }

    public synchronized static SessionManagerPreferences getSettings(Context context) {
        if (instance == null){
            instance = new SessionManagerPreferences(context);
        }
        return instance;
    }

    public void sign_in(int idSupporter, String pseudo, String password, int favoriteTeam, String favoriteTeamName, List<Bet> bets){
        this.editor.putInt("idSupporter", idSupporter);
        this.editor.putString("pseudo", pseudo);
        this.editor.putString("password", password);
        this.editor.putInt("favoriteTeam", favoriteTeam);
        this.editor.putString("favoriteTeamName", favoriteTeamName);

        String betsSerialized = new Gson().toJson(bets);
        this.editor.putString("bets", betsSerialized);

        this.editor.apply();
        this.editor.commit();
    }

    public void updateSupporter(String pseudo){
        this.editor.putString("pseudo", pseudo);

        this.editor.apply();
        this.editor.commit();
    }

    public void updateFavoriteTeamSupporter(int favoriteTeam, String favoriteTeamName){
        this.editor.putInt("favoriteTeam", favoriteTeam);
        this.editor.putString("favoriteTeamName", favoriteTeamName);

        this.editor.apply();
        this.editor.commit();
    }

    public void updatePasswordSupporter(String password){
        this.editor.putString("password", password);

        this.editor.apply();
        this.editor.commit();
    }

    public void logout() {
        this.editor.clear();
        this.editor.commit();
    }

    private HashMap<String, String> getSupporter(){
        HashMap<String, String> supporter = new HashMap<>();
        supporter.put("idSupporter", String.valueOf(this.sharedPreferences.getInt("idSupporter", -1)));
        supporter.put("pseudo", this.sharedPreferences.getString("pseudo", ""));
        supporter.put("password", this.sharedPreferences.getString("password", ""));
        supporter.put("favoriteTeam", String.valueOf(this.sharedPreferences.getInt("favoriteTeam", -1)));
        supporter.put("bets", this.sharedPreferences.getString("bets", ""));
        supporter.put("favoriteTeamName", this.sharedPreferences.getString("favoriteTeamName", ""));

        return supporter;
    }

    public int getIdSupporter(){
        return this.sharedPreferences.getInt("idSupporter", -1);
    }

    public String getPasswordSupporter(){
        return this.sharedPreferences.getString("password", "");
    }

    public String getSupporterName(){
        return this.sharedPreferences.getString("pseudo", "");
    }

    public String getFavoriteTeamNameSupporter(){
        return this.sharedPreferences.getString("favoriteTeamName", "");
    }

    public int getFavoriteTeamIdSupporter(){
        return this.sharedPreferences.getInt("favoriteTeam", -1);
    }

    public boolean isConnected(){
        return (!Objects.equals(this.getSupporter().get("idSupporter"), "-1"));
    }

    public boolean logosTeamDisplayed(){
        return this.sharedPreferences.getBoolean("logosTeam", true);
    }

    public boolean logosPlayerDisplayed(){
        return this.sharedPreferences.getBoolean("logosPlayer", true);
    }

    private List<Bet> getBets(){
        return new Gson().fromJson(this.sharedPreferences.getString("bets", ""), new TypeToken<List<Bet>>() {}.getType());
    }

    public void updateBets(List<Bet> bets){
        String betsSerialized = new Gson().toJson(bets);
        this.editor.putString("bets", betsSerialized);

        this.editor.apply();
        this.editor.commit();
    }

    public int isBet(int idMatch){
        List<Bet> bets =  getBets();
        for(Bet bet : bets){
            if (bet.getIdMatch() == idMatch && bet.getIdSupporter() == this.sharedPreferences.getInt("idSupporter", -1)){
                return bet.getIdWinner();
            }
        }
        return -1;
    }
}