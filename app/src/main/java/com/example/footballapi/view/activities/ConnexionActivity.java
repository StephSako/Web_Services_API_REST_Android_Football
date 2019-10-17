package com.example.footballapi.view.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.footballapi.R;
import com.example.footballapi.controleur.ConnexionController;
import com.google.android.material.snackbar.Snackbar;

public class ConnexionActivity extends AppCompatActivity implements View.OnClickListener{

    public ConnexionActivity(){ }

    private ConnexionController connexionController;

    public EditText etPseudo;
    public EditText etPassword;
    public Button btnConnexion;
    public Button btnInscription;
    @SuppressLint("StaticFieldLeak")
    public static View contextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        connexionController = new ConnexionController(this);

        this.etPseudo = findViewById(R.id.etPseudo);
        this.etPassword = findViewById(R.id.etPassword);
        this.btnConnexion = findViewById(R.id.btnConnexion);
        this.btnInscription = findViewById(R.id.btnInscription);
        contextView = findViewById(R.id.connexion_activity);

        btnConnexion.setOnClickListener(this);
        btnInscription.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnConnexion){
            if (!TextUtils.isEmpty(this.etPassword.getText().toString()) && !TextUtils.isEmpty(this.etPseudo.getText().toString())){
                connexionController.onCreate(this.etPseudo.getText().toString(), this.etPassword.getText().toString());
            }
            else {
                Snackbar.make(contextView, "Remplissez tous les champs", Snackbar.LENGTH_SHORT).show();
            }
        }
        else if (v.getId() == R.id.btnInscription){
            Intent intent = new Intent(this, InscriptionActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        }
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

}
