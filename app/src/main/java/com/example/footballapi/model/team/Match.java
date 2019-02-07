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

            public void setHomeTeam(int homeTeam) {
                this.homeTeam = homeTeam;
            }

            public int getAwayTeam() {
                return awayTeam;
            }

            public void setAwayTeam(int awayTeam) {
                this.awayTeam = awayTeam;
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

        public void setFullTime(FullTime fullTime) {
            this.fullTime = fullTime;
        }

        public String getWinner() {
            return winner;
        }

        public void setWinner(String winner) {
            this.winner = winner;
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

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getMatchday() {
        return matchday;
    }

    public void setMatchday(int matchday) {
        this.matchday = matchday;
    }
}
