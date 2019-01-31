package com.example.footballapi.model.competition;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Standing {

    @SerializedName("stage")
    @Expose
    private String stage;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("table")
    @Expose
    private List<Table> table;

    public void setStage(String stage) {
        this.stage = stage;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Table> getTable() {
        return table;
    }

    public void setTable(List<Table> table) {
        this.table = table;
    }
}
