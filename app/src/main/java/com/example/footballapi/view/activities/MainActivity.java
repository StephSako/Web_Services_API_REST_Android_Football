package com.example.footballapi.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.ahmadrosid.svgloader.SvgLoader;
import com.example.footballapi.R;
import com.example.footballapi.controleur.CrestGenerator;
import com.example.footballapi.controleur.SessionManagerPreferences;
import com.example.footballapi.model.model_dao.DataBase;
import com.example.footballapi.view.fragments.CompetitionFragment;
import com.example.footballapi.view.fragments.CreditsFragment;
import com.example.footballapi.view.fragments.EditAccountFragment;
import com.example.footballapi.view.fragments.MatchesFragment;
import com.example.footballapi.view.fragments.TeamFragment;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener{

    private ActionBarDrawerToggle actionBarDrawerToggle;

    private static final String CLE_DONNEES_ID_TEAM = "idTeam";
    private static final String CLE_DONNEES_ID_COMPET = "idCompet";
    private static final String KEY_TYPE = "typeMatches";

    private TextView tvSupporterName;
    private TextView tvSupporterFavoriteTeam;
    private ImageView ivFavoriteTeam;
    DrawerLayout dl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView nv = findViewById(R.id.nav_view);
        View v = nv.inflateHeaderView(R.layout.nav_header);
        this.dl = findViewById(R.id.drawer_layout);
        this.actionBarDrawerToggle = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);
        this.dl.addDrawerListener(this.actionBarDrawerToggle);
        this.actionBarDrawerToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        this.tvSupporterName = v.findViewById(R.id.tvSupporterName);
        this.tvSupporterFavoriteTeam = v.findViewById(R.id.tvSupporterFavoriteTeam);
        this.tvSupporterName.setText(new SessionManagerPreferences(this).getSupporterName());
        this.tvSupporterFavoriteTeam.setText(new SessionManagerPreferences(this).getFavoriteTeamNameSupporter());
        this.ivFavoriteTeam = v.findViewById(R.id.ivFavoriteTeam);

        ivFavoriteTeam.setOnClickListener(this);
        nv.setNavigationItemSelectedListener(this);

        String crestBD = (new DataBase(this).findTeamCrest(new SessionManagerPreferences(this).getFavoriteTeamIdSupporter()) != null) ? new DataBase(this).findTeamCrest(new SessionManagerPreferences(this).getFavoriteTeamIdSupporter()) : "";
        String crest = (new CrestGenerator().crestGenerator(new SessionManagerPreferences(this).getFavoriteTeamNameSupporter()).equals("")) ? crestBD : new CrestGenerator().crestGenerator(new SessionManagerPreferences(this).getFavoriteTeamNameSupporter());
        switch (crest.substring(crest.length() - 3)) {
            case "svg":
                SvgLoader.pluck()
                        .with(this)
                        .setPlaceHolder(R.drawable.ic_logo_foreground, R.drawable.ic_logo_foreground)
                        .load(crest, this.ivFavoriteTeam)
                        .close();
                break;
            case "gif":
            case "png":
                Picasso.get()
                        .load(crest)
                        .error(R.drawable.ic_logo_foreground)
                        .resize(50, 50)
                        .centerCrop()
                        .into(this.ivFavoriteTeam);
                break;
        }

        if (getIntent().getExtras() != null) {
            if (Objects.requireNonNull(getIntent().getExtras()).containsKey(CLE_DONNEES_ID_TEAM)) {
                Fragment teamFragment = new TeamFragment();
                Bundle bundle = new Bundle();
                bundle.putInt(CLE_DONNEES_ID_TEAM, getIntent().getIntExtra(CLE_DONNEES_ID_TEAM, -1));
                teamFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_hoster, teamFragment).addToBackStack(null).commit();
            } else if (Objects.requireNonNull(getIntent().getExtras()).containsKey(CLE_DONNEES_ID_COMPET)) {
                Fragment fragment = new CompetitionFragment();
                Bundle bundle = new Bundle();
                bundle.putInt(CLE_DONNEES_ID_COMPET, getIntent().getIntExtra(CLE_DONNEES_ID_COMPET, -1));
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_hoster, fragment).addToBackStack(null).commit();
            }
        } else {
            // Fragment des matches de l'équipe favorite affichée par défaut
            Fragment fragment = new MatchesFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(CLE_DONNEES_ID_TEAM, new SessionManagerPreferences(this).getFavoriteTeamIdSupporter());
            bundle.putString(KEY_TYPE, "team");
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_hoster, fragment).addToBackStack(null).commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = new CompetitionFragment();
        Bundle bundle = new Bundle();
        switch (item.getItemId()) {
            case R.id.itemBundesliga:
                bundle.putInt(CLE_DONNEES_ID_COMPET, 2002);
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_hoster, fragment).commit();
                break;
            case R.id.itemEredivisie:
                bundle.putInt(CLE_DONNEES_ID_COMPET, 2003);
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_hoster, fragment).commit();
                break;
            case R.id.itemLigaBresil:
                bundle.putInt(CLE_DONNEES_ID_COMPET, 2013);
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_hoster, fragment).commit();
                break;
            case R.id.itemLigaEspagne:
                bundle.putInt(CLE_DONNEES_ID_COMPET, 2014);
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_hoster, fragment).commit();
                break;
            case R.id.itemLigaNOS:
                bundle.putInt(CLE_DONNEES_ID_COMPET, 2017);
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_hoster, fragment).commit();
                break;
            case R.id.itemLigue1:
                bundle.putInt(CLE_DONNEES_ID_COMPET, 2015);
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_hoster, fragment).commit();
                break;
            case R.id.itemPremierLeague:
                bundle.putInt(CLE_DONNEES_ID_COMPET, 2021);
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_hoster, fragment).commit();
                break;
            case R.id.itemSerieA:
                bundle.putInt(CLE_DONNEES_ID_COMPET, 2019);
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_hoster, fragment).commit();
                break;
            case R.id.logout:
                logout();
                break;
            case R.id.pref:
                launch_item_class(SettingsActivity.class);
                break;
            case R.id.credits:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_hoster, new CreditsFragment()).commit();
                break;
            case R.id.itemSearch:
                launch_item_class(SearchTeamActivity.class);
                break;
            case R.id.edit:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_hoster, new EditAccountFragment()).commit();
                break;
            default:
                return true;
        }

        this.dl.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { // Displaying Drawer
        if (this.actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        new SessionManagerPreferences(this).logout();

        Intent intent = new Intent(this, ConnexionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    private void launch_item_class(Class class_) {
        Intent intent = new Intent(this, class_);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.tvSupporterName.setText(new SessionManagerPreferences(this).getSupporterName());
        this.tvSupporterFavoriteTeam.setText(new SessionManagerPreferences(this).getFavoriteTeamNameSupporter());

        String crestBD = (new DataBase(this).findTeamCrest(new SessionManagerPreferences(this).getFavoriteTeamIdSupporter()) != null) ? new DataBase(this).findTeamCrest(new SessionManagerPreferences(this).getFavoriteTeamIdSupporter()) : "" ;
        String crest = (new CrestGenerator().crestGenerator(new SessionManagerPreferences(this).getFavoriteTeamNameSupporter()).equals("")) ? crestBD : new CrestGenerator().crestGenerator(new SessionManagerPreferences(this).getFavoriteTeamNameSupporter());

        if (!crest.equals("")) {
            switch (crest.substring(crest.length() - 3)) {
                case "svg":
                    SvgLoader.pluck()
                            .with(this)
                            .setPlaceHolder(R.drawable.ic_logo_foreground, R.drawable.ic_logo_foreground)
                            .load(crest, this.ivFavoriteTeam)
                            .close();
                    break;
                case "gif":
                case "png":
                    Picasso.get()
                            .load(crest)
                            .error(R.drawable.ic_logo_foreground)
                            .resize(50, 50)
                            .centerCrop()
                            .into(this.ivFavoriteTeam);
                    break;
            }
        }
    }

    public void onClick(View v) {
        if (v.getId() == R.id.ivFavoriteTeam) {
            this.dl.closeDrawer(GravityCompat.START);
            Fragment teamFragment = new TeamFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(CLE_DONNEES_ID_TEAM, new SessionManagerPreferences(this).getFavoriteTeamIdSupporter());
            teamFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_hoster, teamFragment).commit();
        }
    }
}