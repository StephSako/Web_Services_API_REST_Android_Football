package com.example.footballapi.view;

import android.app.usage.UsageEvents.Event;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.footballapi.R;
import com.example.footballapi.controleur.ClassementController;
import com.example.footballapi.model.competition.Classement;
import com.example.footballapi.model.competition.Table;
import com.example.footballapi.model.team.Team;

import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;
import de.codecrafters.tableview.listeners.TableDataClickListener;

/**
 * Fragment listant les événements
 */
public class ListClassementFragment extends Fragment implements ListClassementFragmentInterface {

    // Liste d'événements
    List<Table> tabClassement;

    // Controleur du fragment
    ClassementController classementcontroller = new ClassementController(getActivity().this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialisation de la liste d'événements
        tabClassement = new ArrayList<>();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        showStadings(view);
    }

    /**
     * Adapter l'événement au tableau
     */
    public class EventTableAdaptater extends TableDataAdapter<Team> {

        EventTableAdaptater(Context context, List<Team> data) {
            super(context, data);
        }

        @Override
        public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
            Classement classement = XXXXXXXXXXXXX
            View renderedView = null;

            switch (columnIndex) {
                case 0:
                    TextView tvPosition = new TextView(getContext());
                    tvPosition.setText(columnIndex+1);
                    tvPosition.setGravity(Gravity.CENTER);
                    renderedView = tvPosition;
                    break;
                case 1:
                    ImageView ivLogo = new ImageView(getContext());
                    ivLogo.setXXXXXXXXXXXXXX(classement.getCrestUrl());
                    renderedView = ivLogo;
                    break;
                case 2:
                    TextView tvNameClub = new TextView(getContext());
                    tvNameClub.setText(classement.getStandings().get(0).getTable().get(columnIndex).getTeam().getName());
                    tvNameClub.setGravity(Gravity.CENTER);
                    renderedView = tvNameClub;
                    break;
                case 3:
                    TextView tvDiff = new TextView(getContext());
                    tvDiff.setText(classement.getStandings().get(0).getTable().get(columnIndex).getGoalDifference());
                    tvDiff.setGravity(Gravity.CENTER);
                    renderedView = tvDiff;
                    break;
                case 4:
                    TextView tvPoints = new TextView(getContext());
                    tvPoints.setText(classement.getStandings().get(0).getTable().get(columnIndex).getPoints());
                    tvPoints.setGravity(Gravity.CENTER);
                    renderedView = tvPoints;
                    break;
            }

            return renderedView;
        }
    }

    public void showStadings(View view) {
        this.classementcontroller.afficheListeTeamsCompetition(2002, tabClassement, view); // Bundesliga = 2002
    }

    /**
     * Click listener du tableau
     */
    public class EventClickListener implements TableDataClickListener<Table> {
        @Override
        public void onDataClicked(int rowIndex, Table event) {
            Bundle args = new Bundle();
            args.putParcelable("event", (Parcelable) tabClassement.get(rowIndex));
            Table TableFrag = new Table();
            TableFrag.setArguments(args);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out,
                            android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.fragmentMainFragment, TableFrag)
                    .addToBackStack(null)
                    .commit();
        }
    }