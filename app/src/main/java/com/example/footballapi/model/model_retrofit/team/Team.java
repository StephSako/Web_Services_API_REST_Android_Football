package com.example.footballapi.model.model_retrofit.team;

import com.example.footballapi.model.model_retrofit.competition.Classement;
import com.example.footballapi.model.model_retrofit.player.Player;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class Team {

    @SerializedName("id")
    @Expose
    private int id = -1;

    @SerializedName("activeCompetitions")
    @Expose
    private List<Classement.Competition> activeCompetitions = Collections.emptyList();

    @SerializedName("name")
    @Expose
    private String name = "";

    @SerializedName("crestUrl")
    @Expose
    private String crestUrl = "";

    @SerializedName("website")
    @Expose
    private String website = "";

    @SerializedName("venue")
    @Expose
    private String venue = "";

    @SerializedName("squad")
    @Expose
    private List<Player> squad  = Collections.emptyList();

    @SerializedName("matches")
    @Expose
    private List<Match> matches  = Collections.emptyList();

    public String getCrestUrl() {
        if (crestUrl != null)
                return crestUrl;
        else return "";
    }

    public int getId() {
        return id;
    }

    public List<Classement.Competition> getActiveCompetitions() {
        return activeCompetitions;
    }

    public String getName() {return name; }

    public String getWebSite() {
        return website;
    }

    public String getVenue() {
        return venue;
    }

    public List<Player> getSquad() {
        return squad;
    }

    public List<Match> getMatches() {
        return matches;
    }

}