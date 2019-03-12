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

    public int idTeam;

    public RecyclerView rvMatches;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private boolean isAlreadyCreated = false; // Ne pas recharger le controlleur au démarrage
    public int resultOfSearch; // Gère les bugs d'enchainements de TeamActivity si la première est résultat d'une recherche

    public List<MatchesModel> list;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_matches, container, false);

        rvMatches = v.findViewById(R.id.rvMatches);

        Bundle bundle = this.getArguments();
        if (bundle != null) idTeam = bundle.getInt("idTeam", -1);

        if (!isAlreadyCreated){
            teamcontroller.onCreate(getString(R.string.token));
            isAlreadyCreated = true;
        }
        else{
            showList(list); // On ne fais pas d'appel REST si le fragment existe déjà
        }

        return v;
    }

    public void showList(List<MatchesModel> list){
        if (list != null && list.size() > 0) {
            // Define an adapter
            layoutManager = new LinearLayoutManager(getContext());
            rvMatches.setLayoutManager(layoutManager);
            mAdapter = new AdapterRV_Matches(list, this.idTeam, resultOfSearch);
            rvMatches.setAdapter(mAdapter);
        }
    }
}