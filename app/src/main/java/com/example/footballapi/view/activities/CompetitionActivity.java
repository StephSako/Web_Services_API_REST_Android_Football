package com.example.footballapi.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.footballapi.R;
import com.example.footballapi.model.model_viewpager.competition.Adapter_ViewPagerCompetition;

public class CompetitionActivity extends AppCompatActivity {

    public int idCompet = -1;
    private static final String KEY_COMPET = "idCompet";

    public CompetitionActivity() { }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.competition_activity);

        // On récupère l'id de la competition depuis l'activite mère
        Intent intent = getIntent();
        if ((this.idCompet = intent.getIntExtra(KEY_COMPET, -1)) == -1)
            this.idCompet = intent.getIntExtra(KEY_COMPET, -1);

        ViewPager viewPager = findViewById(R.id.pagerCompet);
        Adapter_ViewPagerCompetition myPagerAdapter = new Adapter_ViewPagerCompetition(getSupportFragmentManager(), idCompet);
        viewPager.setAdapter(myPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tablayoutCompet);
        tabLayout.setupWithViewPager(viewPager);
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
}