package com.example.footballapi.view.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.footballapi.R;
import com.example.footballapi.controleur.EditAccountController;
import com.example.footballapi.controleur.EditPasswordController;
import com.example.footballapi.controleur.SessionManagerPreferences;
import com.example.footballapi.model.model_dao.DataBase;
import com.example.footballapi.model.model_dao.TeamDAO;
import com.example.footballapi.model.model_session_manager.FavoriteTeam;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class EditAccountActivity extends AppCompatActivity implements View.OnClickListener{

    public EditAccountActivity(){ }

    private EditAccountController editAccountController;
    private EditPasswordController editPasswordController;

    public EditText editEtPseudo;
    public EditText etPasswordOld;
    public EditText etPasswordNew;
    public EditText etPasswordNewVerif;
    public TextView tvTitreEdit;
    public Button btnEditAccount;
    public Button btnEditPassword;
    public Spinner editSpinnerFavoriteTeam;
    public int favoriteTeamId;
    public String favoriteTeamName;
    public View contextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        editAccountController = new EditAccountController(this);
        editPasswordController = new EditPasswordController(this);

        this.editEtPseudo = findViewById(R.id.editEtPseudo);
        String a = new SessionManagerPreferences(this).getSupporterName();
        this.editEtPseudo.setText(new SessionManagerPreferences(this).getSupporterName());

        this.etPasswordOld = findViewById(R.id.editEtPasswordOld);
        this.etPasswordNew = findViewById(R.id.editEtPasswordNew);
        this.etPasswordNewVerif = findViewById(R.id.editEtPasswordNewVerif);
        this.tvTitreEdit = findViewById(R.id.tvTitreEdit);
        this.btnEditAccount = findViewById(R.id.btnConnexion);
        this.btnEditPassword = findViewById(R.id.btnInscription);
        this.contextView = findViewById(R.id.inscription_activity);

        this.tvTitreEdit.setTypeface(null, Typeface.BOLD);

        this.editSpinnerFavoriteTeam = findViewById(R.id.favoriteTeam);
        this.editSpinnerFavoriteTeam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                favoriteTeamId = ((FavoriteTeam) parent.getSelectedItem()).getIdTeam();
                favoriteTeamName = ((FavoriteTeam) parent.getSelectedItem()).getClub_name();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        btnEditAccount.setOnClickListener(this);
        btnEditPassword.setOnClickListener(this);

        DataBase database = new DataBase(this);
        List<TeamDAO> teams = database.findAllTeams();
        ArrayList<FavoriteTeam> teamsList = new ArrayList<>();

        for (int i = 0; i < teams.size(); i++) {
            teamsList.add(new FavoriteTeam(teams.get(i).getIdTeam(), teams.get(i).getClub_name(), teams.get(i).getIdCompet()));
        }

        ArrayAdapter<FavoriteTeam> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, teamsList);
        this.editSpinnerFavoriteTeam.setAdapter(adapter);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnEditPassword) {
            if (TextUtils.isEmpty(this.etPasswordOld.getText().toString()) && TextUtils.isEmpty(this.etPasswordNew.getText().toString()) && TextUtils.isEmpty(this.etPasswordNew.getText().toString())){
                Snackbar.make(this.contextView, "Remplissez tous les champs", Snackbar.LENGTH_SHORT).show();
            }
            else{
                if (!this.etPasswordNew.getText().toString().equals(this.etPasswordNewVerif.getText().toString())){
                    Snackbar.make(this.contextView, "Les nouveaux mots de passe ne correspondent pas", Snackbar.LENGTH_SHORT).show();
                }else{
                    editPasswordController.onCreate(this.etPasswordNew.getText().toString());
                }
            }
        }
        else if (v.getId() == R.id.btnEditAccount){
            if (!TextUtils.isEmpty(this.etPasswordOld.getText().toString())) {
                this.editAccountController.onCreate(String.valueOf(this.editEtPseudo.getText()), this.favoriteTeamId, this.favoriteTeamName);
            } else {
                Snackbar.make(this.contextView, "Remplissez le champ du pseudo", Snackbar.LENGTH_SHORT).show();
            }
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
