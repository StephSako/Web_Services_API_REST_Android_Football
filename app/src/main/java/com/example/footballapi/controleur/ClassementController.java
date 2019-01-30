package com.example.footballapi.controleur;

import android.view.View;
import android.widget.Toast;

import com.example.footballapi.R;
import com.example.footballapi.model.competition.Classement;
import com.example.footballapi.model.competition.Table;
import com.example.footballapi.restService.RestUser;
import com.example.footballapi.view.ListClassementFragment;
import com.example.footballapi.view.MainActivity;

import java.util.List;

import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassementController {

    private MainActivity activity;

    public ClassementController(MainActivity activity) {
        this.activity = activity;
    }

    /**
     * Affiche le classement d'une compétition
     *
     * @param id id de la competition
     */
    public void afficheListeTeamsCompetition(int id, final List<Table> tabClassement, final View view) {
        Call<Classement> call = RestUser.get().competitions("e0d9f47d73ae4a4a87eeb24935d8b2f8"/*Resources.getSystem().getString(R.string.token)*/, id);
        call.enqueue(new Callback<Classement>() {
            @Override
            public void onResponse(Call<Classement> call, Response<Classement> response) {
                if (response.isSuccessful()) {
                    final Classement classement = response.body();
                    assert classement != null;

                    // Nom les colonnes du tableau
                    final String[] TABLE_HEADERS = {""/*Position*/, ""/*Logo*/, ""/*Club*/, "Diff", "Pts"};

                    tabClassement.clear();
                    tabClassement.addAll(classement.getStandings().get(0).getTable());
                    SortableTableView tableView = view.findViewById(R.id.stvStadings);
                    // Tableau de 4 colonnes
                    tableView.setColumnCount(4);
                    // Adaptateur pour l'affichage du contenu des cases
                    tableView.setDataAdapter(new ListClassementFragment.EventTableAdaptater(getActivity(), tabClassement));
                    // Header simple
                    tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(getActivity(), TABLE_HEADERS));
                    // Réaction au click
                    tableView.addDataClickListener(new ListClassementFragment.EventClickListener());

                    Toast.makeText(activity, "La competition est " + classement.getCompetition().getName(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(activity, "Classement introuvable", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Classement> call, Throwable t) {
                Toast.makeText(activity, "Vérifiez votre connexion Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
