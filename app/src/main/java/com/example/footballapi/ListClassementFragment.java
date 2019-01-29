package com.example.footballapi;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.footballapi.model.team.Team;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.TableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

/**
 * Fragment listant les événements
 */
public class ListClassementFragment extends Fragment {

    // Liste d'événements
    List<Team> classement;
    // Nom les colonnes du tableau
    private static final String[] TABLE_HEADERS = {""/*Position*/, ""/*Logo*/, ""/*Club*/, "Diff", "Pts"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialisation de la liste d'événements
        classement = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (isHidden()) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .show(tvStadings.this)
                    .commit();
        }
        return inflater.inflate(R.layout.fragment_list_classement, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        classement.clear();
        classement.addAll(XXXXXXXXXXXXXXXX);
        SortableTableView tableView = view.findViewById(R.id.tvStandings);
        // Tableau de 4 colonnes
        tableView.setColumnCount(4);
        // Adaptateur pour l'affichage du contenu des cases
        tableView.setDataAdapter(new EventTableAdaptater(getActivity(), classement));
        // Header simple
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(getActivity(), TABLE_HEADERS));
        // Réaction au click
        tableView.addDataClickListener(new EventClickListener());
        // Comparateurs permettant de trier les colonnes
        tableView.setColumnComparator(0, EventComparator.getEventNameComparator());
        tableView.setColumnComparator(1, EventComparator.getEventTypeComparator());
        tableView.setColumnComparator(2, EventComparator.getEventDateComparator());
        tableView.setColumnComparator(3, EventComparator.getEventLocComparator());
    }

    /**
     * Adapter l'événement au tableau
     */
    private class EventTableAdaptater extends TableDataAdapter<Team> {

        EventTableAdaptater(Context context, List<Team> data) {
            super(context, data);
        }

        @Override
        public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
            Team team = getRowData(rowIndex);
            View renderedView = null;

            switch (columnIndex) {
                case 0:
                    TextView tvName = new TextView(getContext());
                    tvName.setText(team.getPosition());
                    tvName.setGravity(Gravity.CENTER);
                    renderedView = tvName;
                    break;
                case 1:
                    TextView tvType = new TextView(getContext());
                    tvType.setText(team.getCrestUrl());
                    tvType.setGravity(Gravity.CENTER);
                    renderedView = tvType;
                    break;
                case 2:
                    TextView tvDate = new TextView(getContext());
                    tvDate.setText(DateFormat.getDateTimeInstance().format(team.getDate()));
                    tvDate.setGravity(Gravity.CENTER);
                    renderedView = tvDate;
                    break;
                case 3:
                    TextView tvCoord = new TextView(getContext());
                    tvCoord.setText(team.getCoordonnées().toString());
                    tvCoord.setGravity(Gravity.CENTER);
                    renderedView = tvCoord;
                    break;
                case 4:
                    TextView tvCoord = new TextView(getContext());
                    tvCoord.setText(team.getCoordonnées().toString());
                    tvCoord.setGravity(Gravity.CENTER);
                    renderedView = tvCoord;
                    break;
            }

            return renderedView;
        }

    }

    /**
     * Click listener du tableau
     */
    private class EventClickListener implements TableDataClickListener<EventModel> {
        @Override
        public void onDataClicked(int rowIndex, EventModel event) {
            Bundle args = new Bundle();
            args.putParcelable("event", events.get(rowIndex));
            Event EventFrag = new Event();
            EventFrag.setArguments(args);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out,
                            android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.fragment_container, EventFrag)
                    .addToBackStack(null)
                    .commit();
        }
    }