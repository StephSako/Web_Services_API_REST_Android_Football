package com.example.footballapi.model.model_recyclerview.squad;

public class SquadModel {

    private String playerName;
    private String playerPosition;
    private String playerNationality;
    private String playerId;
    private String teamName;
    private String teamCrest;

    String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    String getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(String playerPosition) {
        this.playerPosition = playerPosition;
    }

    String getPlayerNationality() {
        return playerNationality;
    }

    public void setPlayerNationality(String playerNationality) {
        this.playerNationality = playerNationality;
    }

    String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    String getTeamCrest() {
        return teamCrest;
    }

    public void setTeamCrest(String teamCrest) {
        this.teamCrest = teamCrest;
    }
}
