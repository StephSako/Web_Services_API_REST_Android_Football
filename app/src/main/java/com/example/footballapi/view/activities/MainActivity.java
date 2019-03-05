package com.example.footballapi.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.footballapi.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Id de la compétition
    private int idCompet = -1;

    // Persistance courte
    private final static String PCidCompet = "idCompet";

    // Clef données transmissionyO
    final static String CLE_DONNEES_ID_COMPET = "idCompet";

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(PCidCompet, idCompet);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // On change le title de l'actionBar
        this.setTitle("Compétitions");

        // Récupérer les buttons
        // Boutons de l'écran
        ImageButton btnBundesliga = findViewById(R.id.btnBundesliga);
        ImageButton btnPrimeraDivision = findViewById(R.id.btnPrimeraDivision);
        ImageButton btnSerieA = findViewById(R.id.btnSerieA);
        ImageButton btnLigue1 = findViewById(R.id.btnLigue1);
        ImageButton btnPremierLeague = findViewById(R.id.btnPremierLeague);
        ImageButton btnLiguaNOS = findViewById(R.id.btnLiguaNOS);
        ImageButton btnNetherlands = findViewById(R.id.btnNetherlands);
        ImageButton btnBresil = findViewById(R.id.btnBresil);

        // Ecouteurs sur les boutons
        btnBundesliga.setOnClickListener(this);
        btnPrimeraDivision.setOnClickListener(this);
        btnSerieA.setOnClickListener(this);
        btnLigue1.setOnClickListener(this);
        btnPremierLeague.setOnClickListener(this);
        btnLiguaNOS.setOnClickListener(this);
        btnNetherlands.setOnClickListener(this);
        btnBresil.setOnClickListener(this);

        // Persistance courte
        if (savedInstanceState != null) {
            idCompet = savedInstanceState.getInt(PCidCompet);
        }
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnBundesliga) idCompet = 2002;
        else if (v.getId() == R.id.btnSerieA) idCompet = 2019;
        else if (v.getId() == R.id.btnPremierLeague) idCompet = 2021;
        else if (v.getId() == R.id.btnPrimeraDivision) idCompet = 2014;
        else if (v.getId() == R.id.btnLigue1) idCompet = 2015;
        else if (v.getId() == R.id.btnLiguaNOS) idCompet = 2017;
        else if (v.getId() == R.id.btnNetherlands) idCompet = 2003;
        else if (v.getId() == R.id.btnBresil) idCompet = 2013;

        Intent intent = new Intent(this, ClassementActivity.class);
        intent.putExtra(CLE_DONNEES_ID_COMPET, idCompet);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    // Affichage du menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    // Écouteur sur le menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // L’item sur lequel l’utilisateur a cliqué
        int id = item.getItemId();
        // Afficher le fragment des préférences
        if (id == R.id.pref) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            return true;
        }
        else if (id == R.id.credits) {
            Intent intent = new Intent(this, CreditsActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            return true;
        }
        else if (id == R.id.search) {
            Intent intent = new Intent(this, SearchTeamActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}