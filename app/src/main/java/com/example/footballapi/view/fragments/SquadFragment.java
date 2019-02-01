package com.example.footballapi.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.footballapi.R;
import com.example.footballapi.controleur.TeamController;
import com.example.footballapi.view.activities.TeamActivity;

public class SquadFragment extends Fragment {

    public static SquadFragment newInstance() {
        return new SquadFragment();
    }

    TeamController teamcontroller = new TeamController();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_squad, container, false);

        int idCompet = ((TeamActivity) getActivity()).getidTeam();

        // Par defaut, on affiche l'équipe du club sélectionné
        teamcontroller.afficheListePlayersTeams(idCompet, getContext(), getActivity(), getString(R.string.token), v);

        // Inflate the layout for this fragment
        return v;
    }
}