package com.example.footballapi.model.model_retrofit.competition;

import com.example.footballapi.model.model_retrofit.team.Team;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class Classement {

    public static class Competition {

        @SerializedName("name")
        @Expose
        private String name = "";

        public String getName() {
            return name;
        }
    }

    public class Standing {

        public class Table {

            @SerializedName("position")
            @Expose
            private int position = -1;

            @SerializedName("team")
            @Expose
            private Team team = new Team();

            @SerializedName("points")
            @Expose
            private int points = -1;

            @SerializedName("goalDifference")
            @Expose
            private int goalDifference = -1;

            public Team getTeam() {
                return team;
            }

            public int getPoints() {
                return points;
            }

            public int getGoalDifference() {
                return goalDifference;
            }

            public int getPosition() {
                return position;
            }

        }

        @SerializedName("table")
        @Expose
        private List<Table> table = Collections.emptyList();

        public List<Table> getTable() {
            return table;
        }
    }

    @SerializedName("competition")
    @Expose
    private Competition competition = new Competition();

    @SerializedName("standings")
    @Expose
    private List<Standing> standings = Collections.emptyList();

    public Competition getCompetition() {
        return competition;
    }

    public List<Standing> getStandings() {
        return standings;
    }

}