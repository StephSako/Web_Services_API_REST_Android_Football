package com.example.footballapi.model.model_retrofit.team;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OneMatch {

    @SerializedName("head2head")
    @Expose
    private Head2Head head2head = new Head2Head();

    @SerializedName("match")
    @Expose
    private Match match = new Match();

    public Head2Head getHead2head() {
        return head2head;
    }

    public Match getMatch() {
        return match;
    }
}