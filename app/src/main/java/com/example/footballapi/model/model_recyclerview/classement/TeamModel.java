package com.example.footballapi.model.model_recyclerview.classement;

public class TeamModel {

    private String position;
    private String name;
    private String diff;
    private String points;
    private String idTeam;
    private String crestURL;

    public String getCrestURL() { return crestURL; }

    public void setCrestURL(String crestURL) { this.crestURL = crestURL; }

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
