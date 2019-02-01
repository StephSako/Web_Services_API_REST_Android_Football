package com.example.footballapi.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.footballapi.R;
import com.example.footballapi.controleur.ClassementController;
import com.example.footballapi.view.activities.StadingsActivity;

public class ClassementFragment extends Fragment {

    public static ClassementFragment newInstance() {
        return new ClassementFragment();
    }

    ClassementController classementcontroller = new ClassementController();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_list_classement, container, false);

        int idCompet = ((StadingsActivity) getActivity()).getidCompet();

        classementcontroller.afficheListeTeamsCompetition(idCompet, getContext(), getActivity(), getString(R.string.token), v);

        // Inflate the layout for this fragment
        return v;
    }
}