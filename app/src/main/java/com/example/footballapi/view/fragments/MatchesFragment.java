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

public class MatchesFragment extends Fragment {

    public static MatchesFragment newInstance() {
        return new MatchesFragment();
    }

    TeamController teamcontroller = new TeamController();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_matches, container, false);

        int idTeam = ((TeamActivity) getActivity()).getidTeam();

        teamcontroller.afficheMatchesTeams(idTeam, getContext(), getActivity(), getString(R.string.token), v);

        // Inflate the layout for this fragment
        return v;
    }
}