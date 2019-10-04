package com.example.footballapi.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.footballapi.R;
import com.example.footballapi.controleur.ConnexionController;

public class InscriptionActivity extends AppCompatActivity implements View.OnClickListener{

    public InscriptionActivity(){ }

    private InscriptionController inscriptionController;

    public EditText etPseudo;
    public EditText etPassword;
    public Button btnConnexion;
    public Button btnInscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion_activity);
        connexionController = new ConnexionController(this);

        this.etPseudo = findViewById(R.id.etPseudo);
        this.etPassword = findViewById(R.id.etPassword);
        //this.etFavoriteTeam = findViewById(R.id.tvClubname);
        this.btnConnexion = findViewById(R.id.btnConnexion);
        this.btnInscription = findViewById(R.id.btnInscription);

        btnConnexion.setOnClickListener(this);
        btnInscription.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnConnexion){
            connexionController.onCreate(this.etPseudo.getText().toString(), this.etPassword.getText().toString());
        }
        else if (v.getId() == R.id.btnInscription){
            Intent intent = new Intent(this, InscriptionActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        }
    }

}
