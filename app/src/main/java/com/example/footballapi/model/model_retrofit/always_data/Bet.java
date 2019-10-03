package com.example.footballapi.model.model_retrofit.always_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bet{

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