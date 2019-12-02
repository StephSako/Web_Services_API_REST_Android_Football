package com.example.footballapi.model.model_dao;

public class TeamDAO {

    private int idTeam;
    private String club_name;
    private String nomCompet;
    private String crest;
    private int position;
    private int diff;
    private int points;
    private int idCompet;

    TeamDAO(int idTeam, int position, String club_name, int diff, int points, String nomCompet, String crest) {
        this.idTeam = idTeam;
        this.club_name = club_name;
        this.nomCompet = nomCompet;
        this.position = position;
        this.diff = diff;
        this.points = points;
        this.crest = crest;
    }

    TeamDAO(int idTeam, int idCompet, String club_name, String crest) {
        this.idCompet = idCompet;
        this.idTeam = idTeam;
        this.club_name = club_name;
        this.crest = crest;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }

    public void setClub_name(String club_name) {
        this.club_name = club_name;
    }

    public void setNomCompet(String nomCompet) {
        this.nomCompet = nomCompet;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getIdCompet() {
        return idCompet;
    }

    public void setIdCompet(int idCompet) {
        this.idCompet = idCompet;
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

    public String getCrest() {
        return crest;
    }

    public void setCrest(String crest) {
        this.crest = crest;
    }
}
