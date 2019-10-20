package com.example.footballapi.model.model_retrofit.team;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PourcentBet {

    @SerializedName("pourcentHome")
    @Expose
    private float pourcentHome = -1;

    @SerializedName("pourcentAway")
    @Expose
    private float pourcentAway = -1;

    public float getPourcentHome() {
        return pourcentHome;
    }

    public float getPourcentAway() {
        return pourcentAway;
    }
}
