package com.example.footballapi.model.model_retrofit.supporter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class Supporter {

    @SerializedName("idSupporter")
    @Expose
    private int idSupporter = -1;

    @SerializedName("pseudo")
    @Expose
    private String pseudo = "";

    @SerializedName("favoriteTeam")
    @Expose
    private int favoriteTeam = -1;

    @SerializedName("favoriteTeamName")
    @Expose
    private String favoriteTeamName = "";

    @SerializedName("password")
    @Expose
    private String password = "";

    @SerializedName("tab_bets")
    @Expose
    private List<Bet> tab_bets  = Collections.emptyList();

    public int getIdSupporter() {
        return idSupporter;
    }

    public String getPseudo() {
        return pseudo;
    }

    public int getFavoriteTeam() {
        return favoriteTeam;
    }

    public String getFavoriteTeamName() {
        return favoriteTeamName;
    }

    public String getPassword() {
        return password;
    }

    public List<Bet> getTab_bets() {
        return tab_bets;
    }
}