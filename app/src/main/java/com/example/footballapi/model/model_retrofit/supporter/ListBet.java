package com.example.footballapi.model.model_retrofit.supporter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class ListBet implements Serializable {

    @SerializedName("tab_bets")
    @Expose
    private List<Bet> tab_bets  = Collections.emptyList();

    public List<Bet> getTab_bets() {
        return tab_bets;
    }
}