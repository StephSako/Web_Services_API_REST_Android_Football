package com.example.footballapi.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.footballapi.R;
import com.example.footballapi.controleur.SquadController;
import com.example.footballapi.controleur.TeamController;
import com.example.footballapi.recyclerview.classement.AdapterRV_Classement;
import com.example.footballapi.recyclerview.classement.TeamModel;
import com.example.footballapi.recyclerview.squad.AdapterRV_Squad;
import com.example.footballapi.recyclerview.squad.SquadModel;
import com.example.footballapi.view.activities.PlayerActivity;
import com.example.footballapi.view.activities.TeamActivity;

import java.util.List;
import java.util.Objects;

public class SquadFragment extends Fragment {

    // Transmission de l'id du joueur cliqué et du nom du club
    public final static String CLE_DONNEES_ID_PLAYER = "idPlayer";
    public final static String CLE_DONNEES_NOM_CLUB = "nomClub";
    public final static String CLE_DONNEES_CRUST_URL = "crestURL";

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
        squadcontroller.onCreate(idTeam, getString(R.string.token));

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