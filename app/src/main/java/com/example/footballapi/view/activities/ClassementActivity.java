package com.example.footballapi.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.footballapi.R;
import com.example.footballapi.controleur.ClassementController;
import com.example.footballapi.model.model_dao.AdapterRV_Search;
import com.example.footballapi.model.model_recyclerview.classement.AdapterRV_Classement;
import com.example.footballapi.model.model_recyclerview.classement.TeamModel;
import com.example.footballapi.model.model_recyclerview.matches.AdapterRV_Matches;

import java.util.List;

public class ClassementActivity extends AppCompatActivity {

    private RecyclerView rvClassement;

    public int idCompet = -1;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private ClassementController classementcontroller;

    public ClassementActivity() { }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classement_activity);
        classementcontroller = new ClassementController(this);

        rvClassement = findViewById(R.id.rvClassement);

        // On récupère l'id de la competition depuis l'activite mère
        Intent intent = getIntent();
        if ((this.idCompet = intent.getIntExtra(AdapterRV_Search.CLE_DONNEES_ID_COMPET, -1)) == -1)
            this.idCompet = intent.getIntExtra(MainActivity.CLE_DONNEES_ID_COMPET, -1);

        classementcontroller.onCreate(getString(R.string.token));

    }

    // Appuie sur le bouton Précédent du portable
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    // Affichage du menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
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
        else if (id == R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
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

    public void showList(List<TeamModel> list, boolean netaccess){
        // Define an adapter
        layoutManager = new LinearLayoutManager(this);
        rvClassement.setLayoutManager(layoutManager);
        mAdapter = new AdapterRV_Classement(list, this, netaccess);
        rvClassement.setAdapter(mAdapter);
    }
}