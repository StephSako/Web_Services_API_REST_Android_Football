package com.example.footballapi.recyclerview.matches;

public class MatchesModel {

    private String matchDay;
    private String homeTeam;
    private String score;
    private String awayTeam;
    private String winner;

    public String getWinner() { return winner; }

    public void setWinner(String winner) { this.winner = winner; }

    public String getMatchDay() {
        return matchDay;
    }

    public void setMatchDay(String matchDay) {
        this.matchDay = matchDay;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }
}
