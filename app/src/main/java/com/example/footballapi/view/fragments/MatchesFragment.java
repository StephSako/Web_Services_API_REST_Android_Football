package com.example.footballapi.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.footballapi.R;
import com.example.footballapi.controleur.MatchesController;
import com.example.footballapi.model.model_recyclerview.matches.AdapterRV_Matches;
import com.example.footballapi.model.model_recyclerview.matches.MatchesModel;
import com.example.footballapi.view.activities.TeamActivity;

import java.util.List;
import java.util.Objects;

public class MatchesFragment extends Fragment {

    public static MatchesFragment newInstance() {
        return new MatchesFragment();
    }

    private MatchesController teamcontroller = new MatchesController(this);

    public int idTeam = -1;

    public RecyclerView rvMatches;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_matches, container, false);

        rvMatches = v.findViewById(R.id.rvMatches);

        idTeam = ((TeamActivity) Objects.requireNonNull(getActivity())).getidTeam();

        teamcontroller.onCreate(getString(R.string.token));

        // Inflate the layout for this fragment
        return v;
    }

    public void showList(List<MatchesModel> list){
        if (list.size() > 0) {
            // Define an adapter
            layoutManager = new LinearLayoutManager(getContext());
            rvMatches.setLayoutManager(layoutManager);
            mAdapter = new AdapterRV_Matches(list);
            rvMatches.setAdapter(mAdapter);
        }
        else Toast.makeText(getActivity(), "La saison est terminée.", Toast.LENGTH_SHORT).show();
    }
}