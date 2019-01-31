package com.example.footballapi.view;

import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import com.example.footballapi.R;
import com.example.footballapi.model.competition.Classement;
import com.example.footballapi.restService.RestUser;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassementFragment extends Fragment {

    // Elements du fragment
    ListView lvClassement;

    // ClassementControlleur
    //ClassementController classementController;

    ArrayList<Map<String, String>> data = new ArrayList<>();
    SimpleAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_list_classement, container, false);

        lvClassement = v.findViewById(R.id.lvClassement);

        Call<Classement> call = RestUser.get().competitions(getString(R.string.token), 2002);
        call.enqueue(new Callback<Classement>() {
            @Override
            public void onResponse(Call<Classement> call, Response<Classement> response) {
                if (response.isSuccessful()) {
                    Classement classement = response.body();
                    assert classement != null;

                    String[] columns = new String[] { "_id", "Position", "Club_name", "Diff", "Points" };

                    // Définition des données du tableau
                    // les lignes ci-dessous ont pour seul but de simuler
                    // un objet de type Cursor pour le passer au SimpleCursorAdapter.
                    // Si vos données sont issues d'une base SQLite,
                    // utilisez votre "cursor" au lieu du "matrixCursor"
                    MatrixCursor matrixCursor= new MatrixCursor(columns);
                    getActivity().startManagingCursor(matrixCursor);

                    // On remplit les lignes
                    for (int i = 1; i <= classement.getStandings().get(0).getTable().size(); i++){
                        String club_name = classement.getStandings().get(0).getTable().get(i-1).getTeam().getName();
                        int position = classement.getStandings().get(0).getTable().get(i-1).getPosition();
                        int points = classement.getStandings().get(0).getTable().get(i-1).getPoints();
                        int diff = classement.getStandings().get(0).getTable().get(i-1).getGoalDifference();
                        matrixCursor.addRow(new Object[] { 1, position, club_name, diff, points });
                    }

                    // on prendra les données des colonnes 1, 2, 3 et 4
                    String[] from = new String[] {"Position", "Club_name", "Diff", "Points"};

                    // ...pour les placer dans les TextView définis dans "row_classement.xml"
                    int[] to = new int[] { R.id.tvPosition, R.id.tvClubname, R.id.tvDiff, R.id.tvPoints};

                    // création de l'objet SimpleCursorAdapter...
                    SimpleCursorAdapter adapter = new SimpleCursorAdapter(getContext(), R.layout.row_classement, matrixCursor, from, to, 0);

                    // ...qui va remplir l'objet ListView
                    ListView lv = v.findViewById(R.id.lvClassement);
                    lv.setAdapter(adapter);

                    //Toast.makeText(getActivity(), "La competition est " + classement.getCompetition().getName(), Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(getActivity(), "Classement introuvable", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Classement> call, Throwable t) {
                //Toast.makeText(getActivity(), "Vérifiez votre connexion Internet", Toast.LENGTH_SHORT).show();
            }
        });

        // Inflate the layout for this fragment
        return v;
    }
}