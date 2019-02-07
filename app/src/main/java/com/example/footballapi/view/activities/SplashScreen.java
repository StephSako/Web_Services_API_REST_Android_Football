package com.example.footballapi.view.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.footballapi.R;
import com.example.footballapi.controleur.DataBaseController;
import com.example.footballapi.model.dao.DataBase;

public class SplashScreen extends Activity {

    private final int[] tabIdCompet = {2002, 2019, 2021, 2014, 2015, 2017, 2003, 2013};
    private DataBase database;
    private DataBaseController databaseupdatercompet = new DataBaseController();

    public DataBase getDataBase(){ return database; }

    public SplashScreen() { }

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        int SPLASH_TIME_OUT = 2050;
        ImageView logo = findViewById(R.id.ivSplashscreen);

        this.database = new DataBase(this);

        /* On met à jour l'intégralité de la table EQUIPES de la base de données locales pour la persistance longue
        avec le classement des 8 championnats car les classements sont dynamiques */

        for (int i = 0; i < tabIdCompet.length; i++){
            databaseupdatercompet.updateAllCompet(tabIdCompet[i],this, getString(R.string.token));
        }

        new Handler().postDelayed(new Runnable(){

            @Override
            public void run(){

                // On lance la première activité du choix des compétitions
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.splashscreen_animation);
        logo.startAnimation(myanim);
    }
}
