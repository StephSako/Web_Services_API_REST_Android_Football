package com.example.footballapi.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.footballapi.R;
import com.example.footballapi.controleur.InscriptionController;

public class InscriptionActivity extends AppCompatActivity implements View.OnClickListener{

    public InscriptionActivity(){ }

    private InscriptionController inscriptionController;

    public EditText etPseudo;
    public EditText etPassword;
    public EditText etPasswordVerif;
    public Button btnConnexion;
    public Button btnInscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription_activity);
        inscriptionController = new InscriptionController(this);

        this.etPseudo = findViewById(R.id.etPseudo);
        this.etPassword = findViewById(R.id.etPassword);
        this.etPasswordVerif = findViewById(R.id.etPasswordVerif);
        //this.etFavoriteTeam = findViewById(R.id.tvClubname);
        this.btnConnexion = findViewById(R.id.btnConnexion);
        this.btnInscription = findViewById(R.id.btnInscription);

        btnConnexion.setOnClickListener(this);
        btnInscription.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnInscription) {
            if (this.etPassword.getText().toString().matches("") && this.etPasswordVerif.getText().toString().matches("") && this.etPseudo.getText().toString().matches("")){
                Toast.makeText(this, "Remplissez tous les champs", Toast.LENGTH_SHORT).show();
            }
            else{
                if (!this.etPassword.getText().toString().equals(this.etPasswordVerif.getText().toString())){
                    Toast.makeText(this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
                }else{
                    inscriptionController.onCreate(this.etPseudo.getText().toString(), this.etPassword.getText().toString());
                }
            }
        }
        else if (v.getId() == R.id.btnConnexion){
            Intent intent = new Intent(this, ConnexionActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        }
    }

}
