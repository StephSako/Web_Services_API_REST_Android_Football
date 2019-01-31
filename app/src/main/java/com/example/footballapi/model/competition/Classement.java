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

    public Classement(Competition competition, List<Standing> standings) {
        this.competition = competition;
        this.standings = standings;
    }

    public Competition getCompetition() {
        return competition;
    }

    public List<Standing> getStandings() {
        return standings;
    }

}