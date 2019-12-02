package com.example.footballapi.model.model_recyclerview.matches;

public class MatchesModel {

    private String matchDay;
    private String homeTeam;
    private String score;
    private String awayTeam;
    private String winner;
    private String idTeamHome;
    private String idTeamAway;
    private String idMatch;
    private String status;
    private String utcDate;

    String getIdTeamHome() { return idTeamHome; }

    public void setIdTeamHome(String idTeamHome) {
        this.idTeamHome = idTeamHome;
    }

    String getIdTeamAway() {
        return idTeamAway;
    }

    public void setIdTeamAway(String idTeamAway) {
        this.idTeamAway = idTeamAway;
    }

    String getWinner() { return winner; }

    public void setWinner(String winner) { this.winner = winner; }

    String getMatchDay() {
        return matchDay;
    }

    public void setMatchDay(String matchDay) {
        this.matchDay = matchDay;
    }

    String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    String getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(String idMatch) {
        this.idMatch = idMatch;
    }

    String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public void setUtcDate(String utcDate) {
        this.utcDate = utcDate;
    }

    public String getUtcDate() {
        return utcDate;
    }
}
