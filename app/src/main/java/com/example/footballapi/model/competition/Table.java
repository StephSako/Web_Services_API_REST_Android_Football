package com.example.footballapi.model.competition;

import com.example.footballapi.model.team.Team;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Table {

    @SerializedName("position")
    @Expose
    public int position;

    @SerializedName("team")
    @Expose
    public Team team;

    @SerializedName("playedGames")
    @Expose
    public int playedGames;

    @SerializedName("points")
    @Expose
    public int points;

    @SerializedName("goalsFor")
    @Expose
    public int goalsFor;

    @SerializedName("goalsAgainst")
    @Expose
    public int goalsAgainst;

    @SerializedName("goalDifference")
    @Expose
    public int goalDifference;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getPlayedGames() {
        return playedGames;
    }

    public void setPlayedGames(int playedGames) {
        this.playedGames = playedGames;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
