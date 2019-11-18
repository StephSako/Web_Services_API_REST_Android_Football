package com.example.footballapi.view.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.footballapi.R;
import com.example.footballapi.controleur.EditFavoriteTeamController;
import com.example.footballapi.controleur.EditPasswordController;
import com.example.footballapi.controleur.EditPseudoController;
import com.example.footballapi.controleur.SessionManagerPreferences;
import com.example.footballapi.model.model_dao.DataBase;
import com.example.footballapi.model.model_dao.TeamDAO;
import com.example.footballapi.model.model_session_manager.FavoriteTeam;
import com.example.footballapi.view.activities.MainActivity;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EditAccountFragment extends Fragment implements View.OnClickListener{

    public EditAccountFragment(){ }

    private EditPseudoController editPseudoController;
    private EditPasswordController editPasswordController;
    private EditFavoriteTeamController editFavoriteTeamController;

    private EditText editEtPseudo;
    private EditText etPasswordOld;
    private EditText etPasswordNew;
    private EditText etPasswordNewVerif;
    private ImageButton btnEditPseudo;
    private ImageButton btnEditTeam;
    private Button btnEditPassword;
    private Spinner editSpinnerFavoriteTeam;
    private int favoriteTeamId;
    private String favoriteTeamName;
    public View contextView;
    public View v;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_edit_account, container, false);

        Objects.requireNonNull(this.getActivity()).setTitle("Modifier le compte");

        editPseudoController = new EditPseudoController(this);
        editPasswordController = new EditPasswordController(this);
        editFavoriteTeamController = new EditFavoriteTeamController(this);

        this.editEtPseudo = v.findViewById(R.id.editEtPseudo);
        this.editEtPseudo.setText(new SessionManagerPreferences(Objects.requireNonNull(this.getContext())).getSupporterName());

        this.etPasswordOld = v.findViewById(R.id.editEtPasswordOld);
        this.etPasswordNew = v.findViewById(R.id.editEtPasswordNew);
        this.etPasswordNewVerif = v.findViewById(R.id.editEtPasswordNewVerif);
        this.btnEditPseudo = v.findViewById(R.id.btnEditPseudo);
        this.btnEditTeam = v.findViewById(R.id.btnEditTeam);
        this.btnEditPassword = v.findViewById(R.id.btnEditPassword);
        this.contextView = v.findViewById(R.id.edit_account_activity);

        this.editSpinnerFavoriteTeam = v.findViewById(R.id.editFavoriteTeam);
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

        DataBase database = new DataBase(this.getContext());
        List<TeamDAO> teams = database.findAllTeams();
        ArrayList<FavoriteTeam> teamsList = new ArrayList<>();

        for (int i = 0; i < teams.size(); i++) {
            teamsList.add(new FavoriteTeam(teams.get(i).getIdTeam(), teams.get(i).getClub_name(), teams.get(i).getIdCompet()));
        }

        ArrayAdapter adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, teamsList);
        this.editSpinnerFavoriteTeam.setAdapter(adapter);

        return v;
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnEditPassword) {
            if (TextUtils.isEmpty(this.etPasswordOld.getText().toString()) && TextUtils.isEmpty(this.etPasswordNew.getText().toString()) && TextUtils.isEmpty(this.etPasswordNew.getText().toString())){
                Snackbar.make(this.contextView, "Remplissez tous les champs", Snackbar.LENGTH_SHORT).show();
            }
            else{
                if (!this.etPasswordOld.getText().toString().equals(new SessionManagerPreferences(Objects.requireNonNull(this.getContext())).getPasswordSupporter())) {
                    Snackbar.make(this.contextView, "Le mot de passe actuel n'est pas celui renseigné", Snackbar.LENGTH_SHORT).show();
                } else {
                    if (!this.etPasswordNew.getText().toString().equals(this.etPasswordNewVerif.getText().toString())) {
                        Snackbar.make(this.contextView, "Les nouveaux mots de passe ne correspondent pas", Snackbar.LENGTH_SHORT).show();
                    } else {
                        lockFieldAndButtons(false);
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
                ((MainActivity) Objects.requireNonNull(getActivity())).updateNavHeader();
                lockFieldAndButtons(false);
                this.editPseudoController.onCreate(String.valueOf(this.editEtPseudo.getText()));
            }
        }
        else if (v.getId() == R.id.btnEditTeam){
            ((MainActivity) Objects.requireNonNull(getActivity())).updateNavHeader();
            lockFieldAndButtons(false);
            this.editFavoriteTeamController.onCreate(this.favoriteTeamId, this.favoriteTeamName);
        }
    }

    public void lockFieldAndButtons(boolean enabled){
        this.editSpinnerFavoriteTeam.setEnabled(enabled);
        this.editEtPseudo.setEnabled(enabled);
        this.etPasswordNewVerif.setEnabled(enabled);
        this.etPasswordNew.setEnabled(enabled);
        this.etPasswordOld.setEnabled(enabled);
        this.btnEditPassword.setEnabled(enabled);
        this.btnEditPseudo.setEnabled(enabled);
        this.btnEditTeam.setEnabled(enabled);
    }

}
