package com.example.footballapi.model.model_recyclerview.squad;

public class SquadModel {

    private String playerName;
    private String playerShirtNumber;
    private String playerPosition;
    private String playerNationality;
    private String playerId;
    private String teamName;
    private String teamCrest;

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerShirtNumber() {
        return playerShirtNumber;
    }

    public void setPlayerShirtNumber(String playerShirtNumber) {
        this.playerShirtNumber = playerShirtNumber;
    }

    public String getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(String playerPosition) {
        this.playerPosition = playerPosition;
    }

    public String getPlayerNationality() {
        return playerNationality;
    }

    public void setPlayerNationality(String playerNationality) {
        this.playerNationality = playerNationality;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamCrest() {
        return teamCrest;
    }

    public void setTeamCrest(String teamCrest) {
        this.teamCrest = teamCrest;
    }
}
