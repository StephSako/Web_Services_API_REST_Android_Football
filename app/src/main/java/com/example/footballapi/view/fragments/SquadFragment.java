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
import com.example.footballapi.controleur.SquadController;
import com.example.footballapi.model.model_recyclerview.squad.AdapterRV_Squad;
import com.example.footballapi.model.model_recyclerview.squad.SquadModel;
import com.example.footballapi.view.activities.TeamActivity;

import java.util.List;
import java.util.Objects;

public class SquadFragment extends Fragment {

    public int idTeam = -1;
    public String crestURLPlayer = "";

    public RecyclerView rvSquad;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public static SquadFragment newInstance() {
        return new SquadFragment();
    }

    private SquadController squadcontroller = new SquadController(this);

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_squad, container, false);

        rvSquad = v.findViewById(R.id.rvSquad);

        this.idTeam = ((TeamActivity) Objects.requireNonNull(getActivity())).getidTeam();
        this.crestURLPlayer = ((TeamActivity) Objects.requireNonNull(getActivity())).getcrestURLPlayer();

        // Par defaut, on affiche l'équipe du club sélectionné
        squadcontroller.onCreate(this.idTeam, getString(R.string.token));

        // Inflate the layout for this fragment
        return v;
    }

    public void showList(List<SquadModel> list){
        // Define an adapter
        layoutManager = new LinearLayoutManager(getContext());
        rvSquad.setLayoutManager(layoutManager);
        mAdapter = new AdapterRV_Squad(list);
        rvSquad.setAdapter(mAdapter);
    }
}