package com.example.footballapi.model.team;

import com.example.footballapi.model.competition.Competition;
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
    private List<Competition> activeCompetitions;

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

    public List<Competition> getActiveCompetitions() {
        return activeCompetitions;
    }

    public void setActiveCompetitions(List<Competition> activeCompetitions) {
        this.activeCompetitions = activeCompetitions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCrestUrl() {
        return crestUrl;
    }

    public void setCrestUrl(String crestUrl) {
        this.crestUrl = crestUrl;
    }

    public String getClubColors() {
        return clubColors;
    }

    public void setClubColors(String clubColors) {
        this.clubColors = clubColors;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public List<Player> getSquad() {
        return squad;
    }

    public void setSquad(List<Player> squad) {
        this.squad = squad;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}