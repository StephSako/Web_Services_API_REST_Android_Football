package com.example.footballapi.model.team;

import com.example.footballapi.model.competition.Competition;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Match {

    public class Score {

        public class FullTime {

            @SerializedName("homeTeam")
            @Expose
            private int homeTeam;

            @SerializedName("awayTeam")
            @Expose
            private int awayTeam;

            public int getHomeTeam() {
                return homeTeam;
            }

            public int getAwayTeam() {
                return awayTeam;
            }

        }

        @SerializedName("fullTime")
        @Expose
        private FullTime fullTime;

        @SerializedName("winner")
        @Expose
        private String winner;

        public FullTime getFullTime() {
            return fullTime;
        }

        public String getWinner() {
            return winner;
        }
    }

    @SerializedName("competition")
    @Expose
    private Competition competition;

    @SerializedName("score")
    @Expose
    private Score score;

    @SerializedName("homeTeam")
    @Expose
    private Team homeTeam;

    @SerializedName("awayTeam")
    @Expose
    private Team awayTeam;

    @SerializedName("matchday")
    @Expose
    private int matchday;

    public Competition getCompetition() {
        return competition;
    }

    public Score getScore() {
        return score;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public int getMatchday() {
        return matchday;
    }

}
