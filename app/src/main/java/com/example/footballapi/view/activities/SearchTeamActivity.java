package com.example.footballapi.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SearchView;

import com.example.footballapi.R;

public class SearchTeamActivity extends AppCompatActivity {

    SearchView searchViewTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_team);

        searchViewTeam = findViewById(R.id.searchTeam);

    }
}
