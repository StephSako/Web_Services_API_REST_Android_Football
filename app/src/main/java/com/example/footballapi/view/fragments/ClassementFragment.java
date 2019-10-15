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
import com.example.footballapi.controleur.ClassementController;
import com.example.footballapi.model.model_recyclerview.classement.AdapterRV_Classement;
import com.example.footballapi.model.model_recyclerview.classement.TeamModel;

import java.util.HashMap;
import java.util.List;

public class ClassementFragment extends Fragment {

    public int idCompet = -1;

    private static final String KEY_COMPET = "idCompet";

    private RecyclerView rvClassement;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private ClassementController classementcontroller = new ClassementController(this);

    public static ClassementFragment newInstance(int id) {
        ClassementFragment frag = new ClassementFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_COMPET, id);
        frag.setArguments(args);
        return(frag);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_classement, container, false);
        rvClassement = v.findViewById(R.id.rvClassement);

        if(getArguments() != null) this.idCompet = getArguments().getInt(KEY_COMPET, -1);
        classementcontroller.onCreate(getString(R.string.token));

        return v;
    }

    public void showList(List<TeamModel> list, boolean netaccess, HashMap<Integer, String> teamNameCrest){
        if (teamNameCrest != null) CompetitionFragment.setTeamsNameCrests(teamNameCrest);

        layoutManager = new LinearLayoutManager(getContext());
        rvClassement.setLayoutManager(layoutManager);
        mAdapter = new AdapterRV_Classement(list, this, netaccess);
        rvClassement.setAdapter(mAdapter);
    }
}