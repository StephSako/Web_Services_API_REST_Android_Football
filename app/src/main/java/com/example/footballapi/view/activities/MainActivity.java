package com.example.footballapi.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.footballapi.R;
import com.example.footballapi.services.SessionManagerPreferences;
import com.example.footballapi.view.fragments.CompetitionFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActionBarDrawerToggle t;

    final static String CLE_DONNEES_ID_COMPET = "idCompet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout dl = findViewById(R.id.drawer_layout);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // On récupère l'id de la competition depuis l'activite mère
        Intent intent = getIntent();
        int idCompet;
        if ((idCompet = intent.getIntExtra(CLE_DONNEES_ID_COMPET, -1)) == -1)
            idCompet = 2002;

        Fragment fragment = new CompetitionFragment(); // Fragment displayed by default
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putInt(CLE_DONNEES_ID_COMPET, idCompet);
        fragment.setArguments(bundle);
        ft.replace(R.id.fragment_hoster, fragment);
        ft.commit();

        NavigationView nv = findViewById(R.id.nav_view);//Mise en place du NavigationDrawer

        View v = nv.inflateHeaderView(R.layout.nav_header);
        TextView tvSupporterName = v.findViewById(R.id.tvSupporterName);
        TextView tvSupporterFavoriteTeam = v.findViewById(R.id.tvSupporterFavoriteTeam);

        tvSupporterName.setText(new SessionManagerPreferences(this).getSupporterName());
        tvSupporterFavoriteTeam.setText(new SessionManagerPreferences(this).getFavoriteTeamNameSupporter());

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                Fragment fragment = new CompetitionFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                switch(id)
                {
                    case R.id.itemBundesliga:
                        bundle.putInt(CLE_DONNEES_ID_COMPET, 2002);
                        fragment.setArguments(bundle);
                        ft.replace(R.id.fragment_hoster, fragment);
                        ft.commit();
                        break;
                    case R.id.itemEredivisie:
                        bundle.putInt(CLE_DONNEES_ID_COMPET, 2003);
                        fragment.setArguments(bundle);
                        ft.replace(R.id.fragment_hoster, fragment);
                        ft.commit();
                        break;
                    case R.id.itemLigaBresil:
                        bundle.putInt(CLE_DONNEES_ID_COMPET, 2013);
                        fragment.setArguments(bundle);
                        ft.replace(R.id.fragment_hoster, fragment);
                        ft.commit();
                        break;
                    case R.id.itemLigaEspagne:
                        bundle.putInt(CLE_DONNEES_ID_COMPET, 2014);
                        fragment.setArguments(bundle);
                        ft.replace(R.id.fragment_hoster, fragment);
                        ft.commit();
                        break;
                    case R.id.itemLigaNOS:
                        bundle.putInt(CLE_DONNEES_ID_COMPET, 2017);
                        fragment.setArguments(bundle);
                        ft.replace(R.id.fragment_hoster, fragment);
                        ft.commit();
                        break;
                    case R.id.itemLigue1:
                        bundle.putInt(CLE_DONNEES_ID_COMPET, 2015);
                        fragment.setArguments(bundle);
                        ft.replace(R.id.fragment_hoster, fragment);
                        ft.commit();
                        break;
                    case R.id.itemPremierLeague:
                        bundle.putInt(CLE_DONNEES_ID_COMPET, 2021);
                        fragment.setArguments(bundle);
                        ft.replace(R.id.fragment_hoster, fragment);
                        ft.commit();
                        break;
                    case R.id.itemSerieA:
                        bundle.putInt(CLE_DONNEES_ID_COMPET, 2019);
                        fragment.setArguments(bundle);
                        ft.replace(R.id.fragment_hoster, fragment);
                        ft.commit();
                        break;

                    case R.id.logout:
                        logout();
                        break;
                    case R.id.pref:
                        launch_item_class(SettingsActivity.class);
                        break;
                    case R.id.credits:
                        launch_item_class(CreditsActivity.class);
                        break;
                    case R.id.itemSearch:
                        launch_item_class(SearchTeamActivity.class);
                        break;
                    default:
                        return true;
                }

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { // Displaying Drawer
        if(t.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    private void logout(){
        new SessionManagerPreferences(this).logout();

        Intent intent = new Intent(this, ConnexionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    private void launch_item_class(Class class_){
        Intent intent = new Intent(this, class_);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}