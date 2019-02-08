package com.example.footballapi.recyclerview.model;

public class TeamModel {

    private String position;
    private String name;
    private String diff;
    private String points;
    private String idTeam;

    public String getIdTeam() { return idTeam; }

    public void setIdTeam(String idTeam) {
        this.idTeam = idTeam;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
