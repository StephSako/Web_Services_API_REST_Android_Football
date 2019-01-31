package com.example.footballapi.model.competition;

import com.example.footballapi.model.team.Team;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Table {

    @SerializedName("position")
    @Expose
    private int position;

    @SerializedName("team")
    @Expose
    private Team team;

    @SerializedName("playedGames")
    @Expose
    private int playedGames;

    @SerializedName("points")
    @Expose
    private int points;

    @SerializedName("goalsFor")
    @Expose
    private int goalsFor;

    @SerializedName("goalsAgainst")
    @Expose
    private int goalsAgainst;

    @SerializedName("goalDifference")
    @Expose
    private int goalDifference;

    public Team getTeam() {
        return team;
    }

    public int getPlayedGames() {
        return playedGames;
    }

    public int getPoints() {
        return points;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public int getPosition() {
        return position;
    }

}
