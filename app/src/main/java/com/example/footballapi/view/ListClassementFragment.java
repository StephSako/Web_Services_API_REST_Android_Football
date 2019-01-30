package com.example.footballapi.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.footballapi.R;
import com.example.footballapi.controleur.ClassementController;
import com.example.footballapi.view.interfaces.ListClassementFragmentInterface;

/**
 * Fragment listant les événements
 */
public class ListClassementFragment extends Fragment implements ListClassementFragmentInterface {

    // Controleur du fragment
    ClassementController classementcontroller;

    // Element du fragment
    ListView lvRow;
    TextView tvCompetition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle   savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_liste_classement, container, false);
        lvRow = view.findViewById(R.id.lvClassement);
        tvCompetition = view.findViewById(R.id.tvCompetition);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        showStadings(view); // On affiche le classement
    }

    @Override
    public void showStadings(View view) {
        this.classementcontroller.afficheListeTeamsCompetition(2002);
    }
}