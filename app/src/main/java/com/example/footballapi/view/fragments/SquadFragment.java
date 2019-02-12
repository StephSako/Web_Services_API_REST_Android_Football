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

    private boolean isAlreadyCreated = false; // Ne pas recharger le controlleur au démarrage

    private SquadController squadcontroller = new SquadController(this);

    public List<SquadModel> list;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_squad, container, false);

        rvSquad = v.findViewById(R.id.rvSquad);

        this.idTeam = ((TeamActivity) Objects.requireNonNull(getActivity())).idTeam;
        this.crestURLPlayer = ((TeamActivity) Objects.requireNonNull(getActivity())).crestURLPlayer;

        if(!isAlreadyCreated){
            squadcontroller.onCreate(getString(R.string.token));
            isAlreadyCreated = true;
        }
        else{
            showList(list); // On ne fais pas d'appel REST si le fragment existe déjà
        }

        return v;
    }

    public void showList(List<SquadModel> list){
        if (list != null && list.size() > 0) {
            layoutManager = new LinearLayoutManager(getContext());
            rvSquad.setLayoutManager(layoutManager);
            mAdapter = new AdapterRV_Squad(list);
            rvSquad.setAdapter(mAdapter);
        }
    }
}