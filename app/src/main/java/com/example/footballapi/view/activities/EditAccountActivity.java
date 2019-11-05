package com.example.footballapi.view.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.footballapi.R;
import com.example.footballapi.controleur.EditFavoriteTeamController;
import com.example.footballapi.controleur.EditPasswordController;
import com.example.footballapi.controleur.EditPseudoController;
import com.example.footballapi.controleur.SessionManagerPreferences;
import com.example.footballapi.model.model_dao.DataBase;
import com.example.footballapi.model.model_dao.TeamDAO;
import com.example.footballapi.model.model_session_manager.FavoriteTeam;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class EditAccountActivity extends AppCompatActivity implements View.OnClickListener{

    public EditAccountActivity(){ }

    private EditPseudoController editPseudoController;
    private EditPasswordController editPasswordController;
    private EditFavoriteTeamController editFavoriteTeamController;

    public EditText editEtPseudo;
    public EditText etPasswordOld;
    public EditText etPasswordNew;
    public EditText etPasswordNewVerif;
    public TextView tvTitreEdit;
    public ImageButton btnEditPseudo;
    public ImageButton btnEditTeam;
    public Button btnEditPassword;
    public Spinner editSpinnerFavoriteTeam;
    public int favoriteTeamId;
    public String favoriteTeamName;
    public View contextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        editPseudoController = new EditPseudoController(this);
        editPasswordController = new EditPasswordController(this);
        editFavoriteTeamController = new EditFavoriteTeamController(this);

        this.editEtPseudo = findViewById(R.id.editEtPseudo);
        this.editEtPseudo.setText(new SessionManagerPreferences(this).getSupporterName());

        this.etPasswordOld = findViewById(R.id.editEtPasswordOld);
        this.etPasswordNew = findViewById(R.id.editEtPasswordNew);
        this.etPasswordNewVerif = findViewById(R.id.editEtPasswordNewVerif);
        this.tvTitreEdit = findViewById(R.id.tvTitreEdit);
        this.btnEditPseudo = findViewById(R.id.btnEditPseudo);
        this.btnEditTeam = findViewById(R.id.btnEditTeam);
        this.btnEditPassword = findViewById(R.id.btnEditPassword);
        this.contextView = findViewById(R.id.edit_account_activity);

        this.tvTitreEdit.setTypeface(null, Typeface.BOLD);

        this.editSpinnerFavoriteTeam = findViewById(R.id.editFavoriteTeam);
        this.editSpinnerFavoriteTeam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                favoriteTeamId = ((FavoriteTeam) parent.getSelectedItem()).getIdTeam();
                favoriteTeamName = ((FavoriteTeam) parent.getSelectedItem()).getClub_name();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        btnEditPseudo.setOnClickListener(this);
        btnEditTeam.setOnClickListener(this);
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
                if (!this.etPasswordOld.getText().toString().equals(new SessionManagerPreferences(this).getPasswordSupporter())) {
                    Snackbar.make(this.contextView, "Le mot de passe actuel n'est pas celui renseigné", Snackbar.LENGTH_SHORT).show();
                } else {
                    if (!this.etPasswordNew.getText().toString().equals(this.etPasswordNewVerif.getText().toString())) {
                        Snackbar.make(this.contextView, "Les nouveaux mots de passe ne correspondent pas", Snackbar.LENGTH_SHORT).show();
                    } else {
                        editPasswordController.onCreate(this.etPasswordNew.getText().toString());
                        this.etPasswordOld.setText("");
                        this.etPasswordNew.setText("");
                        this.etPasswordNewVerif.setText("");
                    }
                }
            }
        }
        else if (v.getId() == R.id.btnEditPseudo){
            if (TextUtils.isEmpty(this.editEtPseudo.getText().toString())) {
                Snackbar.make(this.contextView, "Remplissez le champ du pseudo", Snackbar.LENGTH_SHORT).show();
            } else {
                this.editPseudoController.onCreate(String.valueOf(this.editEtPseudo.getText()));
            }
        }
        else if (v.getId() == R.id.btnEditTeam){
            this.editFavoriteTeamController.onCreate(this.favoriteTeamId, this.favoriteTeamName);
        }
    }

}
