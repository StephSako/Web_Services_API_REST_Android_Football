package com.example.footballapi.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.footballapi.R;
import com.example.footballapi.controleur.MatchesController;
import com.example.footballapi.recyclerview.matches.AdapterRV_Matches;
import com.example.footballapi.recyclerview.matches.MatchesModel;
import com.example.footballapi.view.activities.TeamActivity;

import java.util.List;
import java.util.Objects;

public class MatchesFragment extends Fragment {

    public static MatchesFragment newInstance() {
        return new MatchesFragment();
    }

    private MatchesController teamcontroller = new MatchesController(this);

    public RecyclerView rvMatches;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_matches, container, false);

        rvMatches = v.findViewById(R.id.rvMatches);

        int idTeam = ((TeamActivity) Objects.requireNonNull(getActivity())).getidTeam();

        teamcontroller.onCreate(idTeam, getString(R.string.token));

        // Inflate the layout for this fragment
        return v;
    }

    public void showList(List<MatchesModel> list){
        // Define an adapter
        layoutManager = new LinearLayoutManager(getContext());
        rvMatches.setLayoutManager(layoutManager);
        mAdapter = new AdapterRV_Matches(list);
        rvMatches.setAdapter(mAdapter);
    }
}