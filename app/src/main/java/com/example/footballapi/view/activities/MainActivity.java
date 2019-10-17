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

    private DrawerLayout dl;
    private NavigationView nv;
    private ActionBarDrawerToggle t;
    private TextView tvSupporterName;
    private TextView tvSupporterFavoriteTeam;

    final static String CLE_DONNEES_ID_COMPET = "idCompet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dl = findViewById(R.id.drawer_layout);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Fragment fragment = new CompetitionFragment(); // Fragment displayed by default
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putInt(CLE_DONNEES_ID_COMPET, 2002);
        fragment.setArguments(bundle);
        ft.replace(R.id.fragment_hoster, fragment);
        ft.commit();

        nv = findViewById(R.id.nav_view);//Mise en place du NavigationDrawer
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                Fragment fragment = new CompetitionFragment();
                int idCompet = -1;
                switch(id)
                {
                    case R.id.itemBundesliga:
                        idCompet = 2002;
                        break;
                    case R.id.itemEredivisie:
                        idCompet = 2003;
                        break;
                    case R.id.itemLigaBresil:
                        idCompet = 2013;
                        break;
                    case R.id.itemLigaEspagne:
                        idCompet = 2014;
                        break;
                    case R.id.itemLigaNOS:
                        idCompet = 2017;
                        break;
                    case R.id.itemLigue1:
                        idCompet = 2015;
                        break;
                    case R.id.itemPremierLeague:
                        idCompet = 2021;
                        break;
                    case R.id.itemSerieA:
                        idCompet = 2019;
                        break;

                    case R.id.logout:
                        logout();
                        break;
                    case R.id.pref:

                        break;
                    case R.id.credits:

                        break;
                    default:
                        return true;
                }

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt(CLE_DONNEES_ID_COMPET, idCompet);
                fragment.setArguments(bundle);
                ft.replace(R.id.fragment_hoster, fragment);
                ft.commit();

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

                return true;
            }
        });

        View v = nv.inflateHeaderView(R.layout.nav_header);
        this.tvSupporterName = v.findViewById(R.id.tvSupporterName);
        this.tvSupporterFavoriteTeam = v.findViewById(R.id.tvSupporterFavoriteTeam);

        this.tvSupporterName.setText(new SessionManagerPreferences(this).getSupporterName());
        this.tvSupporterFavoriteTeam.setText(new SessionManagerPreferences(this).getFavoriteTeamNameSupporter());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { // Displaying Drawer
        if(t.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    private void logout(){
        new SessionManagerPreferences(this).logout();

        Intent intent = new Intent(this, ConnexionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}