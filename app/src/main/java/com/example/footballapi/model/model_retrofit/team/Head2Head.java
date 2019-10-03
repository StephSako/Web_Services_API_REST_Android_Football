package com.example.footballapi.model.model_retrofit.team;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Head2Head {

    public class HomeTeam {

        @SerializedName("wins")
        @Expose
        private int wins = -1;

        @SerializedName("draws")
        @Expose
        private int draws = -1;

        @SerializedName("losses")
        @Expose
        private int losses = -1;

        public int getWins() {
            return wins;
        }

        public int getDraws() {
            return draws;
        }

        public int getLosses() {
            return losses;
        }
    }

    @SerializedName("homeTeam")
    @Expose
    private HomeTeam homeTeam = new HomeTeam();

    @SerializedName("numberOfMatches")
    @Expose
    private int numberOfMatches = -1;

    @SerializedName("totalGoals")
    @Expose
    private int totalGoals = -1;

    public HomeTeam getHomeTeam() {
        return homeTeam;
    }

    public int getNumberOfMatches() {
        return numberOfMatches;
    }

    public int getTotalGoals() {
        return totalGoals;
    }
}
