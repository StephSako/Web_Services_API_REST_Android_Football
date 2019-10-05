package com.example.footballapi.model.model_session_manager;

public class FavoriteTeam {

    private int idTeam;
    private String club_name;
    private int idCompet;

    public FavoriteTeam(int idTeam, String club_name, int idCompet) {
        this.idTeam = idTeam;
        this.club_name = club_name;
        this.idCompet = idCompet;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public String getClub_name() {
        return club_name;
    }

    public int getIdCompet() {
        return idCompet;
    }

    @Override
    public String toString() {
        return this.club_name;
    }
}
