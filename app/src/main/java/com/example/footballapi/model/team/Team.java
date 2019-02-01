package com.example.footballapi.model.team;

import com.example.footballapi.model.competition.Classement;
import com.example.footballapi.model.player.Player;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Team {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("activeCompetitions")
    @Expose
    private List<Classement.Competition> activeCompetitions;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("crestUrl")
    @Expose
    private String crestUrl;

    @SerializedName("clubColors")
    @Expose
    private String clubColors;

    @SerializedName("venue")
    @Expose
    private String venue;

    @SerializedName("squad")
    @Expose
    private List<Player> squad;

    @SerializedName("matches")
    @Expose
    private List<Match> matches;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Classement.Competition> getActiveCompetitions() {
        return activeCompetitions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClubColors() {
        return clubColors;
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