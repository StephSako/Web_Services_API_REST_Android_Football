package com.example.footballapi.model.model_retrofit.team;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Match {

    public class Score {

        public class FullTime {

            @SerializedName("homeTeam")
            @Expose
            private int ScorehomeTeam = -1;

            @SerializedName("awayTeam")
            @Expose
            private int ScoreawayTeam = -1;

            public int getHomeTeam() {
                return ScorehomeTeam;
            }

            public int getAwayTeam() {
                return ScoreawayTeam;
            }

        }

        public class HalfTime {

            @SerializedName("homeTeam")
            @Expose
            private int ScorehomeTeam = -1;

            @SerializedName("awayTeam")
            @Expose
            private int ScoreawayTeam = -1;

            public int getHomeTeam() {
                return ScorehomeTeam;
            }

            public int getAwayTeam() {
                return ScoreawayTeam;
            }

        }

        @SerializedName("halfTime")
        @Expose
        private HalfTime halfTime = new HalfTime();

        @SerializedName("fullTime")
        @Expose
        private FullTime fullTime = new FullTime();

        public FullTime getFullTime() {
            return fullTime;
        }

        public HalfTime getHalfTime() {
            return halfTime;
        }

        @SerializedName("winner")
        @Expose
        private String winner = "";

        public String getWinner() {
            return winner;
        }
    }

    public class AwayTeam{

        @SerializedName("name")
        @Expose
        private String name = "";

        public String getName() { return name; }

        @SerializedName("id")
        @Expose
        private int id = -1;

        public int getId() { return id; }
    }

    public class HomeTeam{

        @SerializedName("name")
        @Expose
        private String name = "";

        public String getName() { return name; }

        @SerializedName("id")
        @Expose
        private int id = -1;

        public int getId() { return id; }
    }

    @SerializedName("score")
    @Expose
    private Score score = new Score();

    @SerializedName("homeTeam")
    @Expose
    private HomeTeam homeTeam = new HomeTeam();

    @SerializedName("awayTeam")
    @Expose
    private AwayTeam awayTeam = new AwayTeam();

    @SerializedName("matchday")
    @Expose
    private int matchday = -1;

    @SerializedName("id")
    @Expose
    private int id = -1;

    @SerializedName("status")
    @Expose
    private String status = "";

    @SerializedName("utcDate")
    @Expose
    private String utcDate = "";

    public String getUtcDate() { return utcDate; }

    public String getStatus() { return status; }

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
