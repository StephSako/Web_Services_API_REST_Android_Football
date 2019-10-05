package com.example.footballapi.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.footballapi.R;
import com.example.footballapi.controleur.InscriptionController;
import com.example.footballapi.model.model_dao.DataBase;
import com.example.footballapi.model.model_dao.TeamDAO;
import com.example.footballapi.model.model_session_manager.FavoriteTeam;

import java.util.ArrayList;
import java.util.List;

public class InscriptionActivity extends AppCompatActivity implements View.OnClickListener{

    public InscriptionActivity(){ }

    private InscriptionController inscriptionController;

    public EditText etPseudo;
    public EditText etPassword;
    public EditText etPasswordVerif;
    public Button btnConnexion;
    public Button btnInscription;
    public Spinner spinnerFavoriteTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription_activity);
        inscriptionController = new InscriptionController(this);

        this.etPseudo = findViewById(R.id.etPseudo);
        this.etPassword = findViewById(R.id.etPassword);
        this.etPasswordVerif = findViewById(R.id.etPasswordVerif);
        this.btnConnexion = findViewById(R.id.btnConnexion);
        this.btnInscription = findViewById(R.id.btnInscription);
        this.spinnerFavoriteTeam = findViewById(R.id.favoriteTeam);

        btnConnexion.setOnClickListener(this);
        btnInscription.setOnClickListener(this);

        DataBase database = new DataBase(this);
        List<TeamDAO> teams = database.findAllTeams();
        ArrayList<FavoriteTeam> teamsList = new ArrayList<>();

        for (int i = 0; i < teams.size(); i++) {
            teamsList.add(new FavoriteTeam(teams.get(i).getIdTeam(), teams.get(i).getClub_name(), teams.get(i).getIdCompet()));
        }

        ArrayAdapter<FavoriteTeam> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, teamsList);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        this.spinnerFavoriteTeam.setAdapter(adapter);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnInscription) {
            if (!TextUtils.isEmpty(this.etPassword.getText().toString()) && !TextUtils.isEmpty(this.etPseudo.getText().toString()) && !TextUtils.isEmpty(this.etPasswordVerif.getText().toString())){
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
