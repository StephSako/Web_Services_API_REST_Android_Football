package com.example.footballapi.view.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.footballapi.R;
import com.example.footballapi.controleur.SquadController;
import com.example.footballapi.model.model_recyclerview.squad.AdapterRV_Squad;
import com.example.footballapi.model.model_recyclerview.squad.SquadModel;

import java.util.List;

public class SquadFragment extends Fragment {

    public int idTeam = -1;

    private RecyclerView rvSquad;

    private static final String KEY_ID = "idTeam";

    private SquadController squadcontroller = new SquadController(this);

    public List<SquadModel> list;

    public static SquadFragment newInstance(int id) {
        SquadFragment frag = new SquadFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_ID, id);
        frag.setArguments(args);
        return(frag);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_squad, container, false);
        rvSquad = v.findViewById(R.id.rvSquad);

        if(getArguments() != null) this.idTeam = getArguments().getInt(KEY_ID, -1);

        squadcontroller.onCreate(getString(R.string.token));

        return v;
    }

    public void showList(List<SquadModel> list){
        if (list != null && list.size() > 0) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            rvSquad.setLayoutManager(layoutManager);
            RecyclerView.Adapter mAdapter = new AdapterRV_Squad(list);
            rvSquad.setAdapter(mAdapter);
        }
    }
}