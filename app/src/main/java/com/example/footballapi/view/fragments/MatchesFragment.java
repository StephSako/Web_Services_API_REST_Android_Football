package com.example.footballapi.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footballapi.R;
import com.example.footballapi.controleur.MatchesCompetController;
import com.example.footballapi.controleur.MatchesTeamController;
import com.example.footballapi.model.model_recyclerview.matches.AdapterRV_Matches;
import com.example.footballapi.model.model_recyclerview.matches.MatchesModel;

import java.util.List;
import java.util.Objects;

public class MatchesFragment extends Fragment {

    private int idForMatches = -1;
    private int positionDay = 0;
    private String type = "";
    private boolean isAlreadyCreated = false; // Ne pas recharger le controlleur au replace du fragment

    public void incrPositionDay(){
        this.positionDay++;
    }

    private RecyclerView rvMatches;
    public View contextView;

    private static final String KEY_ID = "idForMatches";
    private static final String KEY_TYPE = "typeMatches";

    public List<MatchesModel> list;

    public static MatchesFragment newInstance(int id, String type) {
        MatchesFragment frag = new MatchesFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_ID, id);
        args.putString(KEY_TYPE, type);
        frag.setArguments(args);
        return(frag);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_matches, container, false);
        rvMatches = v.findViewById(R.id.rvMatches);

        if(getArguments() != null){
            this.idForMatches = getArguments().getInt(KEY_ID, -1);
            this.type = getArguments().getString(KEY_TYPE, "");
        }

        if (this.type.equals("team")) new MatchesTeamController(this).onCreate(getString(R.string.token), this.idForMatches);
        else if (this.type.equals("competition")) {
            if (!isAlreadyCreated){
                new MatchesCompetController(this).onCreate(getString(R.string.token), this.idForMatches);
                isAlreadyCreated = true;
            }
            else showList(list); // On ne fais pas d'appel si le fragment existe déjà
        }

        return v;
    }

    public void showList(List<MatchesModel> list){
        if (list != null && list.size() > 0) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            rvMatches.setLayoutManager(layoutManager);
            RecyclerView.Adapter mAdapter = new AdapterRV_Matches(list, this);
            rvMatches.setAdapter(mAdapter);

            if ((positionDay-5) < 0) positionDay = 0;
            Objects.requireNonNull(rvMatches.getLayoutManager()).scrollToPosition(positionDay-5);
        }
    }
}