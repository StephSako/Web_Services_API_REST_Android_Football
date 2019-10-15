package com.example.footballapi.view.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.footballapi.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private NavigationView nv;

    final static String CLE_DONNEES_ID_COMPET = "idCompet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dl = findViewById(R.id.drawer_layout);

        Fragment fragment = new CompetitionFragment(); // Fragment displayed by default
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putInt(CLE_DONNEES_ID_COMPET, 2002);
        fragment.setArguments(bundle);
        ft.replace(R.id.fragment_hoster, fragment);
        ft.commit();

        nv = findViewById(R.id.nav_view);//Mise en place du NavigationDrawer
        nv.getHeaderView(0).setBackgroundColor(Color.parseColor("#FF69B4"));
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                Fragment fragment = new CompetitionFragment();
                int idCompet;
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
                    default:
                        return true;
                }

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt(CLE_DONNEES_ID_COMPET, idCompet);
                fragment.setArguments(bundle);
                ft.replace(R.id.fragment_hoster, fragment);
                ft.commit();

                DrawerLayout drawer = findViewById(R.id.drawer_layout);//Fin de la mise en place du Drawer, et
                drawer.closeDrawer(GravityCompat.START);//activation du Drawer

                return true;
            }
        });
    }
}