package com.example.footballapi.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.footballapi.R;
import com.example.footballapi.controleur.ClassementController;

public class ClassementActivity extends AppCompatActivity {

    public ListView lvClassement;

    // Transmission de l'id de l'équipe cliquée
    final static String CLE_DONNEES_ID_TEAM = "idTeam";

    int idCompet = -1;

    private ClassementController classementcontroller = new ClassementController();

    // Sauvegarder l'id de la compétition lors du onBackPressed
    /*@Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("idCompet", this.idCompet);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        this.idCompet = savedInstanceState.getInt("idCompet");
    }*/

    public ClassementActivity() { }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classement_activity);

        lvClassement = findViewById(R.id.lvClassement);

        // On récupère l'id de la competition depuis l'activite mère
        Intent intent = getIntent();
        this.idCompet = intent.getIntExtra(MainActivity.CLE_DONNEES_ID_COMPET, 1);

        classementcontroller.afficheListeTeamsCompetition(idCompet, this, this, getString(R.string.token));

        // Gestion des clics sur les lignes
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View container, int position, long id) {
                // On affiche l'équipe cliquée
                Intent intent = new Intent(getApplicationContext(), TeamActivity.class);
                intent.putExtra(CLE_DONNEES_ID_TEAM, (int) id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        };

        // Utilisation avec notre listview
        lvClassement.setOnItemClickListener(itemClickListener);
    }

    // Appuie sur le bouton Précédent du portable
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}