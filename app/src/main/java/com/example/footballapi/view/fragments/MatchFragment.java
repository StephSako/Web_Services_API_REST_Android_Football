package com.example.footballapi.view.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.footballapi.R;
import com.example.footballapi.controleur.BetController;
import com.example.footballapi.controleur.MatchController;
import com.example.footballapi.controleur.PourcentBetController;
import com.example.footballapi.controleur.SessionManagerPreferences;
import com.example.footballapi.model.model_dao.DataBase;

import java.util.Objects;

public class MatchFragment extends Fragment implements View.OnClickListener {

    private BetController betController;
    private PourcentBetController pourcentBetController;

    private int idMatch = -1;
    private int idHome = -1;
    private int idAway = -1;
    private String status = "";

    private static final String CLE_DONNEES_ID_MATCH= "idMatch";
    private static final String CLE_DONNEES_ID_HOME= "idHome";
    private static final String CLE_DONNEES_ID_AWAY= "idAway";
    private static final String CLE_DONNEES_STATUS= "status";

    public ImageView logo_club_home;
    public ImageView logo_club_away;
    public TextView tvVictoiresHome;
    public TextView tvVictoiresAway;
    public TextView tvPourcentHome;
    public TextView tvPourcentAway;
    public TextView tvDefaitesHome;
    public TextView tvDefaitesAway;
    public TextView tvNuls;
    public TextView tvTotaux;
    public TextView tvMatchDate;
    public TextView tvButsTotaux;
    public View contextView;
    public Button btnWinnerHome;
    public Button btnWinnerAway;
    private LinearLayout layoutBetButtons;
    private LinearLayout layoutCotes;
    public ProgressBar pbVictoriesHome;
    public ProgressBar pbVictoriesAway;
    public ProgressBar pbDefeatsHome;
    public ProgressBar pbDefeatsAway;
    public TextView tvGoalHomeHT;
    public TextView tvGoalAwayHT;
    public TextView tvGoalHomeFT;
    public TextView tvGoalAwayFT;
    public TextView tvVenue;
    public TextView tvNameHome;
    public TextView tvNameAway;
    public TextView tvNbParieurs;

    public MatchFragment(){}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_match, container, false);

        MatchController matchController = new MatchController(this);
        betController = new BetController(this);
        pourcentBetController = new PourcentBetController(this);

        this.logo_club_home = v.findViewById(R.id.ivLogoClubHome);
        this.logo_club_away = v.findViewById(R.id.ivLogoClubAway);
        this.tvVictoiresHome = v.findViewById(R.id.tvVictoiresHome);
        this.tvVictoiresAway = v.findViewById(R.id.tvVictoiresAway);
        this.tvDefaitesHome = v.findViewById(R.id.tvDefaitesHome);
        this.tvDefaitesAway = v.findViewById(R.id.tvDefaitesAway);
        this.tvNuls = v.findViewById(R.id.tvNuls);
        this.tvTotaux = v.findViewById(R.id.tvMatchesTotaux);
        this.tvMatchDate = v.findViewById(R.id.tvMatchDate);
        this.tvButsTotaux = v.findViewById(R.id.tvButsTotaux);
        this.btnWinnerHome = v.findViewById(R.id.btnWinnerHome);
        this.btnWinnerAway = v.findViewById(R.id.btnWinnerAway);
        this.contextView = v.findViewById(R.id.match_fragment);
        this.tvPourcentAway = v.findViewById(R.id.tvPourcentAway);
        this.tvPourcentHome = v.findViewById(R.id.tvPourcentHome);
        this.tvGoalHomeHT = v.findViewById(R.id.tvGoalHomeHT);
        this.tvGoalAwayHT = v.findViewById(R.id.tvGoalAwayHT);
        this.tvGoalHomeFT = v.findViewById(R.id.tvGoalHomeFT);
        this.tvGoalAwayFT = v.findViewById(R.id.tvGoalAwayFT);
        this.tvVenue = v.findViewById(R.id.tvVenue);
        this.layoutBetButtons = v.findViewById(R.id.layoutBetButtons);
        this.layoutCotes = v.findViewById(R.id.layoutCotes);
        this.tvNameHome = v.findViewById(R.id.tvNameHome);
        this.tvNameAway = v.findViewById(R.id.tvNameAway);
        this.tvNbParieurs = v.findViewById(R.id.tvNbParieurs);

        this.pbVictoriesHome = v.findViewById(R.id.pbVictoriesHome);
        this.pbVictoriesHome.setRotation(180);
        this.pbVictoriesAway = v.findViewById(R.id.pbVictoriesAway);
        this.pbVictoriesHome.getProgressDrawable().setColorFilter(Color.rgb(70,149,22), android.graphics.PorterDuff.Mode.SRC_IN);
        this.pbVictoriesAway.getProgressDrawable().setColorFilter(Color.rgb(70,149,22), android.graphics.PorterDuff.Mode.SRC_IN);

        this.pbDefeatsHome = v.findViewById(R.id.pbDefeatsHome);
        this.pbDefeatsHome.setRotation(180);
        this.pbDefeatsAway = v.findViewById(R.id.pbDefeatsAway);
        this.pbDefeatsHome.getProgressDrawable().setColorFilter(Color.rgb(202,44,30), android.graphics.PorterDuff.Mode.SRC_IN);
        this.pbDefeatsAway.getProgressDrawable().setColorFilter(Color.rgb(202,44,30), android.graphics.PorterDuff.Mode.SRC_IN);

        this.btnWinnerHome.setOnClickListener(this);
        this.btnWinnerAway.setOnClickListener(this);

        if (this.getArguments() != null) {
            this.idMatch = this.getArguments().getInt(CLE_DONNEES_ID_MATCH, -1);
            this.idHome = this.getArguments().getInt(CLE_DONNEES_ID_HOME, -1);
            this.idAway = this.getArguments().getInt(CLE_DONNEES_ID_AWAY, -1);
            this.status = this.getArguments().getString(CLE_DONNEES_STATUS, "");
        }

        this.tvMatchDate.setTypeface(null, Typeface.BOLD);
        this.tvVenue.setTypeface(null, Typeface.BOLD);
        this.tvPourcentHome.setTypeface(null, Typeface.BOLD);
        this.tvPourcentAway.setTypeface(null, Typeface.BOLD);
        this.tvGoalAwayFT.setTypeface(null, Typeface.BOLD);
        this.tvGoalAwayHT.setTypeface(null, Typeface.BOLD);
        this.tvGoalHomeFT.setTypeface(null, Typeface.BOLD);
        this.tvGoalHomeHT.setTypeface(null, Typeface.BOLD);
        this.tvNbParieurs.setTypeface(null, Typeface.BOLD);


        // On cache les botuons de paris si le match est déjà joué, annulé, ... ou déjà parié par l'utilisateur actif
        assert this.status != null;
        if (this.status.equals("LIVE") || this.status.equals("IN_PLAY") || this.status.equals("FINISHED") || this.status.equals("PAUSED") || this.status.equals("SUSPENDED") || new SessionManagerPreferences(Objects.requireNonNull(this.getActivity())).isBet(this.idMatch) != -1)
            this.layoutBetButtons.setVisibility(LinearLayout.GONE);

        // On cache les côtes si le match n'a pas déjà été parié par l'utilisateur actif
        if (new SessionManagerPreferences(Objects.requireNonNull(this.getActivity())).isBet(this.idMatch) == -1){
            this.layoutCotes.setVisibility(LinearLayout.GONE);
        }

        matchController.onCreate(getString(R.string.token), this.idMatch, new DataBase(this.getActivity()).findTeamCrest(this.idHome), new DataBase(this.getActivity()).findTeamCrest(this.idAway));
        pourcentBetController.onCreate(this.idMatch, this.idHome, this.idAway);

        return v;
    }

    public void onClick(View v) {
        this.layoutBetButtons.setVisibility(LinearLayout.GONE);
        this.layoutCotes.setVisibility(LinearLayout.VISIBLE);
        if (v.getId() == R.id.btnWinnerHome) betController.onCreate(idMatch, new SessionManagerPreferences(Objects.requireNonNull(this.getActivity())).getIdSupporter(), idHome);
        else if (v.getId() == R.id.btnWinnerAway) betController.onCreate(idMatch, new SessionManagerPreferences(Objects.requireNonNull(this.getActivity())).getIdSupporter(), idAway);
        pourcentBetController.onCreate(this.idMatch, this.idHome, this.idAway);
    }
}
