package com.example.footballapi.model.model_session_manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.footballapi.model.model_retrofit.always_data.Bet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

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

    public void sign_in(int idSupporter, String pseudo, String password, int favoriteTeam, List<Bet> bets){
        this.editor.putInt("idSupporter", idSupporter);
        this.editor.putString("pseudo", pseudo);
        this.editor.putString("password", password);
        this.editor.putInt("favoriteTeam", favoriteTeam);
        /*this.editor.putInt("favoriteTeam", favoriteTeam);

        // Serializer les paris
        Gson gson = new Gson();
        Collections.sort(bets, new Comparator<Bet>() {
            @Override
            public int compare(Bet bet, Bet t1) {
                return 0;
            }

            @Override
            public Comparator<Bet> reversed() {
                return null;
            }

            @Override
            public Comparator<Bet> thenComparing(Comparator<? super Bet> other) {
                return null;
            }

            @Override
            public <U> Comparator<Bet> thenComparing(Function<? super Bet, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
                return null;
            }

            @Override
            public <U extends Comparable<? super U>> Comparator<Bet> thenComparing(Function<? super Bet, ? extends U> keyExtractor) {
                return null;
            }

            @Override
            public Comparator<Bet> thenComparingInt(ToIntFunction<? super Bet> keyExtractor) {
                return null;
            }

            @Override
            public Comparator<Bet> thenComparingLong(ToLongFunction<? super Bet> keyExtractor) {
                return null;
            }

            @Override
            public Comparator<Bet> thenComparingDouble(ToDoubleFunction<? super Bet> keyExtractor) {
                return null;
            }
        });
        String json = gson.toJson(bets);
        editor.putString("bets", json);*/

        editor.apply();
        this.editor.commit();
    }

    public void logout() {
        this.editor.clear();
        this.editor.commit();
    }

    public HashMap<String, String> getSupporter(){
        HashMap<String, String> supporter = new HashMap<>();
        supporter.put("idSupporter", String.valueOf(this.sharedPreferences.getInt("idSupporter", -1)));
        supporter.put("pseudo", this.sharedPreferences.getString("pseudo", ""));
        supporter.put("password", this.sharedPreferences.getString("password", ""));
        supporter.put("favoriteTeam", String.valueOf(this.sharedPreferences.getInt("favoriteTeam", -1)));

        // Deserializer les paris
        /*Gson gson = new Gson();
        String json = this.sharedPreferences.getString("bets", "");
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> list = gson.fromJson(json, type);
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });*/
        return supporter;
    }
}