package com.example.footballapi.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.footballapi.R;

public class MainActivity extends AppCompatActivity {

    // Elements de la vue de l'activité
    public TextView tvGeneralActivity;

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //récupérer le textview
        tvGeneralActivity = findViewById(R.id.tvGeneralActivity);
        tvGeneralActivity.setText("");

        //affichage du fragment principal
        getFragmentManager().beginTransaction().replace(R.id.idFragmentClassement, new ListFragment(), "ClassementFragment").commit();
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }
}