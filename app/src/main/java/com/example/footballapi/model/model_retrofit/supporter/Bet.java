package com.example.footballapi.model.model_retrofit.supporter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Bet implements Serializable {

    public Bet(int idMatch, int idBet, int idWinner, int idSupporter) {
        this.idMatch = idMatch;
        this.idBet = idBet;
        this.idWinner = idWinner;
        this.idSupporter = idSupporter;
    }

    @SerializedName("idMatch")
    @Expose
    private int idMatch = -1;

    @SerializedName("idBet")
    @Expose
    private int idBet = -1;

    @SerializedName("idWinner")
    @Expose
    private int idWinner = -1;

    @SerializedName("idSupporter")
    @Expose
    private int idSupporter = -1;

    public int getIdMatch() {
        return idMatch;
    }

    public int getIdBet() {
        return idBet;
    }

    public int getIdWinner() {
        return idWinner;
    }

    public int getIdSupporter() {
        return idSupporter;
    }
}