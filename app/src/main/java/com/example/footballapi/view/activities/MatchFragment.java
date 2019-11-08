package com.example.footballapi.view.activities;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import com.example.footballapi.model.model_recyclerview.matches.AdapterRV_Matches;

import java.util.Objects;

public class MatchFragment extends Fragment implements View.OnClickListener {

    private BetController betController;
    private PourcentBetController pourcentBetController;

    public int idMatch = -1;
    public int idHome = -1;
    public int idAway = -1;
    public String status = "";

    public static final String CLE_DONNEES_ID_MATCH= "idMatch";
    public static final String CLE_DONNEES_ID_HOME= "idHome";
    public static final String CLE_DONNEES_ID_AWAY= "idAway";
    public static final String CLE_DONNEES_STATUS= "status";

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

    public boolean loadingPicsPlayer;

    public MatchFragment(){}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_match, container, false);

        MatchController matchController = new MatchController(this);
        betController = new BetController(this);
        pourcentBetController = new PourcentBetController(this);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
        this.loadingPicsPlayer = sharedPref.getBoolean("logosPlayer", true);

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

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            this.idMatch = bundle.getInt(CLE_DONNEES_ID_MATCH, -1);
            this.idHome = bundle.getInt(AdapterRV_Matches.CLE_DONNEES_ID_HOME, -1);
            this.idAway = bundle.getInt(AdapterRV_Matches.CLE_DONNEES_ID_AWAY, -1);
            this.status = bundle.getString(AdapterRV_Matches.CLE_DONNEES_STATUS, "");
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

        assert this.status != null;
        if (this.status.equals("LIVE") || this.status.equals("IN_PLAY") || this.status.equals("FINISHED") || this.status.equals("PAUSED") || this.status.equals("SUSPENDED") || new SessionManagerPreferences(Objects.requireNonNull(this.getActivity())).isBet(this.idMatch) != -1)
            this.layoutBetButtons.setVisibility(LinearLayout.GONE);

        // On bloque les boutons selon les paris existants
        if (new SessionManagerPreferences(Objects.requireNonNull(this.getActivity())).isBet(this.idMatch) != -1){
            if (new SessionManagerPreferences(this.getActivity()).isBet(this.idMatch) == idHome){
                this.btnWinnerHome.setEnabled(false);
                this.btnWinnerAway.setEnabled(true);
            }else if (new SessionManagerPreferences(this.getActivity()).isBet(this.idMatch) == idAway){
                this.btnWinnerHome.setEnabled(true);
                this.btnWinnerAway.setEnabled(false);
            }
        }

        matchController.onCreate(getString(R.string.token), this.idMatch, new DataBase(this.getActivity()).findTeamCrest(this.idHome), new DataBase(this.getActivity()).findTeamCrest(this.idAway));
        pourcentBetController.onCreate(this.idMatch, this.idHome, this.idAway);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.loadingPicsPlayer = new SessionManagerPreferences(Objects.requireNonNull(this.getActivity())).logosPlayerDisplayed();
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnWinnerHome){
            this.layoutBetButtons.setVisibility(LinearLayout.GONE);
            betController.onCreate(idMatch, new SessionManagerPreferences(Objects.requireNonNull(this.getActivity())).getIdSupporter(), idHome);
            pourcentBetController.onCreate(this.idMatch, this.idHome, this.idAway);
        }
        else if (v.getId() == R.id.btnWinnerAway){
            this.layoutBetButtons.setVisibility(LinearLayout.GONE);
            betController.onCreate(idMatch, new SessionManagerPreferences(Objects.requireNonNull(this.getActivity())).getIdSupporter(), idAway);
            pourcentBetController.onCreate(this.idMatch, this.idHome, this.idAway);
        }
    }
}
