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
import com.example.footballapi.controleur.ClassementController;
import com.example.footballapi.model.model_recyclerview.classement.AdapterRV_Classement;
import com.example.footballapi.model.model_recyclerview.classement.TeamModel;

import java.util.List;

public class ClassementFragment extends Fragment {

    public int idCompet = -1;

    private static final String KEY_COMPET = "id_compet";

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

    public void showList(List<TeamModel> list, boolean netaccess){
        layoutManager = new LinearLayoutManager(getContext());
        rvClassement.setLayoutManager(layoutManager);
        mAdapter = new AdapterRV_Classement(list, this, netaccess);
        rvClassement.setAdapter(mAdapter);
    }
}