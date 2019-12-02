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
import com.example.footballapi.controleur.ClassementController;
import com.example.footballapi.model.model_recyclerview.classement.AdapterRV_Classement;
import com.example.footballapi.model.model_recyclerview.classement.TeamModel;

import java.util.List;

public class ClassementFragment extends Fragment {

    public int idCompet = -1;
    private static final String KEY_ID = "idForMatches";

    private RecyclerView rvClassement;

    public static ClassementFragment newInstance(int id) {
        ClassementFragment frag = new ClassementFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_ID, id);
        frag.setArguments(args);
        return(frag);
    }

    private ClassementController classementcontroller = new ClassementController(this);

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_classement, container, false);
        rvClassement = v.findViewById(R.id.rvClassement);

        if(getArguments() != null) this.idCompet = getArguments().getInt(KEY_ID, -1);
        classementcontroller.onCreate(getString(R.string.token), this.idCompet);

        return v;
    }

    public void showList(List<TeamModel> list, boolean netaccess){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvClassement.setLayoutManager(layoutManager);
        RecyclerView.Adapter mAdapter = new AdapterRV_Classement(list, this, netaccess);
        rvClassement.setAdapter(mAdapter);
    }
}