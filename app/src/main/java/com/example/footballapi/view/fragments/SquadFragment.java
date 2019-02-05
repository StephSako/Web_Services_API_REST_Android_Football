package com.example.footballapi.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.footballapi.R;
import com.example.footballapi.controleur.TeamController;
import com.example.footballapi.view.activities.PlayerActivity;
import com.example.footballapi.view.activities.TeamActivity;

import java.util.Objects;

public class SquadFragment extends Fragment {

    public ListView lvSquad;

    // Transmission de l'id du joueur cliqué et du nom du club
    public final static String CLE_DONNEES_ID_PLAYER = "idPlayer";
    public final static String CLE_DONNEES_NOM_CLUB = "nomClub";

    public static SquadFragment newInstance() {
        return new SquadFragment();
    }

    private TeamController teamcontroller = new TeamController();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_squad, container, false);

        lvSquad = v.findViewById(R.id.lvSquad);

        int idTeam = ((TeamActivity) Objects.requireNonNull(getActivity())).getidTeam();

        // Par defaut, on affiche l'équipe du club sélectionné
        teamcontroller.afficheListePlayersTeams(idTeam, getContext(), getActivity(), getString(R.string.token), v);

        // Gestion des clics sur les lignes sur la liste des joueurs
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View container, int position, long id) {
                // On affiche le joueur cliqué
                Intent intent = new Intent(getActivity(), PlayerActivity.class);
                intent.putExtra(CLE_DONNEES_ID_PLAYER, id);
                intent.putExtra(CLE_DONNEES_NOM_CLUB, ((TeamActivity) Objects.requireNonNull(getActivity())).getnomClub());
                startActivity(intent);
            }
        };

        // Utilisation avec notre listview
        lvSquad.setOnItemClickListener(itemClickListener);

        // Inflate the layout for this fragment
        return v;
    }
}