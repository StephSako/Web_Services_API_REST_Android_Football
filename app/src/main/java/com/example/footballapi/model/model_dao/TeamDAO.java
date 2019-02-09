package com.example.footballapi.model.model_dao;

public class TeamDAO {

    private int idTeam;
    private String club_name;
    private String nomCompet;
    private int position;
    private int diff;
    private int points;

    TeamDAO(int idTeam, int position, String club_name, int diff, int points, String nomCompet) {
        this.idTeam = idTeam;
        this.club_name = club_name;
        this.nomCompet = nomCompet;
        this.position = position;
        this.diff = diff;
        this.points = points;
    }

    public int getIdTeam() {
        return idTeam;
    }
    public String getNomCompet() { return nomCompet; }

    public String getClub_name() {
        return club_name;
    }

    public int getPosition() {
        return position;
    }

    public int getDiff() {
        return diff;
    }

    public int getPoints() {
        return points;
    }
}
