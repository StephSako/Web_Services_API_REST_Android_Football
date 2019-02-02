package com.example.footballapi.model.team;

import com.example.footballapi.model.competition.Classement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Match {

    public class Score {

        public class FullTime {

            @SerializedName("homeTeam")
            @Expose
            private int ScorehomeTeam;

            @SerializedName("awayTeam")
            @Expose
            private int ScoreawayTeam;

            public int getHomeTeam() {
                return ScorehomeTeam;
            }

            public int getAwayTeam() {
                return ScoreawayTeam;
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

    public class AwayTeam{
        @SerializedName("id")
        @Expose
        private int id;

        @SerializedName("name")
        @Expose
        private String name;

        public int getId() { return id; }

        public String getName() { return name; }
    }

    public class HomeTeam{
        @SerializedName("id")
        @Expose
        private int id;

        @SerializedName("name")
        @Expose
        private String name;

        public int getId() { return id; }

        public String getName() { return name; }
    }

    @SerializedName("competition")
    @Expose
    private Classement.Competition competition;

    @SerializedName("score")
    @Expose
    private Score score;

    @SerializedName("homeTeam")
    @Expose
    private HomeTeam homeTeam;

    @SerializedName("awayTeam")
    @Expose
    private AwayTeam awayTeam;

    @SerializedName("matchday")
    @Expose
    private int matchday;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("utcDate")
    @Expose
    private String utcDate;

    public String getUtcDate() { return utcDate; }

    public String getStatus() { return status; }

    public Classement.Competition getCompetition() {
        return competition;
    }

    public Score getScore() {
        return score;
    }

    public HomeTeam getHomeTeam() {
        return homeTeam;
    }

    public AwayTeam getAwayTeam() {
        return awayTeam;
    }

    public int getMatchday() {
        return matchday;
    }

    public int getId() { return id; }
}
