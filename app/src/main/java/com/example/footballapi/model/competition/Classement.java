package com.example.footballapi.model.competition;

import com.example.footballapi.model.team.Team;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Classement {

    public class Competition {

        @SerializedName("name")
        @Expose
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class Standing {

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

        @SerializedName("stage")
        @Expose
        private String stage;

        @SerializedName("type")
        @Expose
        private String type;

        @SerializedName("table")
        @Expose
        private List<Table> table;

        public List<Table> getTable() {
            return table;
        }
    }

    @SerializedName("stage")
    @Expose
    private String stage;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("table")
    @Expose
    private List<Standing.Table> table;

    public List<Standing.Table> getTable() {
        return table;
    }


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