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

public class MatchesFragment extends Fragment {

    private int idForMatches = -1;
    private String type = "";

    private RecyclerView rvMatches;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public View contextView;

    private static final String KEY_ID = "idTeam";
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
        /*else {
            Intent intent = getIntent();
            this.idForMatches = new SessionManagerPreferences(Objects.requireNonNull(this.getContext())).getFavoriteTeamIdSupporter();
            this.type = "team";
        }*/

        if (this.type.equals("team")) new MatchesTeamController(this).onCreate(getString(R.string.token), this.idForMatches);
        else if (this.type.equals("competition")) new MatchesCompetController(this).onCreate(getString(R.string.token), this.idForMatches);

        return v;
    }

    public void showList(List<MatchesModel> list){
        if (list != null && list.size() > 0) {
            layoutManager = new LinearLayoutManager(getContext());
            rvMatches.setLayoutManager(layoutManager);
            mAdapter = new AdapterRV_Matches(list, this);
            rvMatches.setAdapter(mAdapter);
        }
    }
}