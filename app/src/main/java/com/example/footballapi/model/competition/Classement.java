package com.example.footballapi.model.competition;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Classement {

    @SerializedName("competition")
    @Expose
    private Competition competition;

    @SerializedName("standings")
    @Expose
    private List<Standing> standings;

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public List<Standing> getStandings() {
        return standings;
    }

    public void setStandings(List<Standing> standings) {
        this.standings = standings;
    }
}