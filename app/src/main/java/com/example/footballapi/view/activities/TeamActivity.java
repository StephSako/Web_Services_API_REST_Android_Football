package com.example.footballapi.view.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.footballapi.R;
import com.example.footballapi.controleur.TeamController;
import com.example.footballapi.model.model_recyclerview.classement.AdapterRV_Classement;
import com.example.footballapi.model.model_viewpager.team.Adapter_ViewPagerTeam;

@SuppressLint("Registered")
public class TeamActivity extends AppCompatActivity {

    // Variables qui seront transmises dans la vue Player
    public int idTeam = -1;
    public String nomClub = "";
    public String crestURLPlayer = "";

    public TextView tvWebSite;
    public TextView tvStade;
    public TextView tvActiveCompetitions;
    public TextView tvEntraineur;
    public ImageView logo_club;
    public View contextView;

    public boolean loadingPicsTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_team);
        TeamController teamcontroller = new TeamController(this);

        // Récupérer les valeurs choisies
        // Préférences du switch pour afficher les logos
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        // Préférences du switch pour afficher les logos
        this.loadingPicsTeam = sharedPref.getBoolean("logosTeam", true);

        this.tvWebSite = findViewById(R.id.tvWebsite);
        this.tvStade = findViewById(R.id.tvStade);
        this.tvActiveCompetitions = findViewById(R.id.tvActiveCompetitions);
        this.tvEntraineur = findViewById(R.id.tvEntraineur);
        this.contextView = findViewById(R.id.team_activity);

        logo_club = findViewById(R.id.logo_club);

        // On récupère l'id de l'équipe venant du le classement
        Intent intent = getIntent();
        this.idTeam = intent.getIntExtra(AdapterRV_Classement.CLE_DONNEES_ID_TEAM, -1);

        teamcontroller.onCreate(getString(R.string.token));

        ViewPager viewPager = findViewById(R.id.pagerteam);
        Adapter_ViewPagerTeam myPagerAdapter = new Adapter_ViewPagerTeam(getSupportFragmentManager(), this.idTeam, "team");
        viewPager.setAdapter(myPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tablayoutteam);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Récupérer les valeurs choisies
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        // Préférences du switch pour afficher les logos
        this.loadingPicsTeam = sharedPref.getBoolean("logosTeam", true);
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
        if (item.getItemId() == R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            return true;
        }
        else if (item.getItemId() == R.id.search) {
            Intent intent = new Intent(this, SearchTeamActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
