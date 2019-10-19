package com.example.footballapi.model.model_recyclerview.matches;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmadrosid.svgloader.SvgLoader;
import com.example.footballapi.R;
import com.example.footballapi.model.model_dao.DataBase;
import com.example.footballapi.view.activities.MatchActivity;
import com.example.footballapi.view.fragments.MatchesFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterRV_Matches extends RecyclerView.Adapter<AdapterRV_Matches.ViewHolder> {

    public static final String CLE_DONNEES_ID_MATCH= "idMatch";
    public static final String CLE_DONNEES_ID_HOME= "idHome";
    public static final String CLE_DONNEES_ID_AWAY= "idAway";
    public static final String CLE_DONNEES_STATUS= "status";

    private List<MatchesModel> values;
    private MatchesFragment fragment;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMatchday;
        TextView tvHomeTeam;
        TextView tvScore;
        TextView tvAwayTeam;
        private ImageView ivLogoClubHome;
        private ImageView ivLogoClubAway;

        public View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            tvMatchday = v.findViewById(R.id.tvMatchday);
            tvHomeTeam = v.findViewById(R.id.tvHomeTeam);
            tvScore = v.findViewById(R.id.tvScore);
            tvAwayTeam = v.findViewById(R.id.tvAwayTeam);
            ivLogoClubHome = v.findViewById(R.id.ivLogoHome);
            ivLogoClubAway = v.findViewById(R.id.ivLogoAway);
        }
    }

    public AdapterRV_Matches(List<MatchesModel> myDataset, MatchesFragment fragment) {
        this.values = myDataset;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public AdapterRV_Matches.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_matches, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.tvMatchday.setText("J" + values.get(position).getMatchDay());
        holder.tvAwayTeam.setText(values.get(position).getAwayTeam());
        holder.tvScore.setText(values.get(position).getScore());
        holder.tvHomeTeam.setText(values.get(position).getHomeTeam());



        String crestHome = (new DataBase(fragment.getActivity()).findTeamCrest(Integer.valueOf(values.get(position).getIdTeamHome())) != null) ? new DataBase(fragment.getActivity()).findTeamCrest(Integer.valueOf(values.get(position).getIdTeamHome())) : "" ;
        String crestAway = (new DataBase(fragment.getActivity()).findTeamCrest(Integer.valueOf(values.get(position).getIdTeamAway())) != null) ? new DataBase(fragment.getActivity()).findTeamCrest(Integer.valueOf(values.get(position).getIdTeamAway())) : "" ;

        // Problèmes logos Ligue 1
        switch (values.get(position).getHomeTeam()) {
            case "FC Nantes":
                crestHome = "https://upload.wikimedia.org/wikipedia/commons/5/5c/FC_Nantes_2019_logo.svg";
                break;
            case "Nîmes Olympique":
                crestHome = "https://upload.wikimedia.org/wikipedia/fr/f/f0/N%C3%AEmes_Olympique_logo_2018.svg";
                break;
            case "Toulouse FC":
                crestHome = "https://upload.wikimedia.org/wikipedia/fr/8/8b/Logo_Toulouse_FC_2018.svg";
                break;
            case "Stade Brestois 29":
                crestHome = "https://upload.wikimedia.org/wikipedia/fr/1/14/Logo_Stade_Brestois.svg";
                break;
            case "Amiens SC":
                crestHome = "https://upload.wikimedia.org/wikipedia/fr/e/ec/Logo_Amiens_SC_1998.svg";
                break;
            case "Stade de Reims":
                crestHome = "https://upload.wikimedia.org/wikipedia/fr/0/02/Logo_Stade_Reims_1999.svg";
                break;
            case "Lille OSC":
                crestHome = "https://upload.wikimedia.org/wikipedia/fr/6/62/Logo_LOSC_Lille_2018.svg";
                break;
        }

        switch (values.get(position).getAwayTeam()) {
            case "FC Nantes":
                crestAway = "https://upload.wikimedia.org/wikipedia/commons/5/5c/FC_Nantes_2019_logo.svg";
                break;
            case "Nîmes Olympique":
                crestAway = "https://upload.wikimedia.org/wikipedia/fr/f/f0/N%C3%AEmes_Olympique_logo_2018.svg";
                break;
            case "Toulouse FC":
                crestAway = "https://upload.wikimedia.org/wikipedia/fr/8/8b/Logo_Toulouse_FC_2018.svg";
                break;
            case "Stade Brestois 29":
                crestAway = "https://upload.wikimedia.org/wikipedia/fr/1/14/Logo_Stade_Brestois.svg";
                break;
            case "Amiens SC":
                crestAway = "https://upload.wikimedia.org/wikipedia/fr/e/ec/Logo_Amiens_SC_1998.svg";
                break;
            case "Stade de Reims":
                crestAway = "https://upload.wikimedia.org/wikipedia/fr/0/02/Logo_Stade_Reims_1999.svg";
                break;
            case "Lille OSC":
                crestAway = "https://upload.wikimedia.org/wikipedia/fr/6/62/Logo_LOSC_Lille_2018.svg";
                break;
        }

        if (!crestHome.equals("")) {
            switch (crestHome.substring(crestHome.length() - 3)){
                case "svg":
                    SvgLoader.pluck()
                            .with(this.fragment.getActivity())
                            .setPlaceHolder(R.drawable.ic_logo_foreground, R.drawable.ic_logo_foreground)
                            .load(crestHome, holder.ivLogoClubHome)
                            .close();
                    break;
                case "gif":
                case "png":
                    Picasso.get()
                            .load(crestHome)
                            .error(R.drawable.ic_logo_foreground)
                            .resize(50, 50)
                            .centerCrop()
                            .into(holder.ivLogoClubHome);
                    break;
            }
        }

        if (!crestAway.equals("")) {

            switch (crestAway.substring(crestAway.length() - 3)){
                case "svg":
                    SvgLoader.pluck()
                            .with(this.fragment.getActivity())
                            .setPlaceHolder(R.drawable.ic_logo_foreground, R.drawable.ic_logo_foreground)
                            .load(crestAway, holder.ivLogoClubAway)
                            .close();
                    break;
                case "gif":

                    break;
                case "png":
                    Picasso.get()
                            .load(crestAway)
                            .error(R.drawable.ic_logo_foreground)
                            .resize(50, 50)
                            .centerCrop()
                            .into(holder.ivLogoClubAway);
                    break;
            }
        }

        if (values.get(position).getWinner() != null) {
            switch (values.get(position).getWinner()) {
                case "HOME_TEAM":
                    holder.tvHomeTeam.setTypeface(null, Typeface.BOLD);
                    holder.tvAwayTeam.setTypeface(null, Typeface.NORMAL);
                    holder.tvHomeTeam.setTextColor(Color.BLACK);
                    holder.tvAwayTeam.setTextColor(Color.BLACK);
                    break;
                case "AWAY_TEAM":
                    holder.tvHomeTeam.setTypeface(null, Typeface.NORMAL);
                    holder.tvAwayTeam.setTypeface(null, Typeface.BOLD);
                    holder.tvHomeTeam.setTextColor(Color.BLACK);
                    holder.tvAwayTeam.setTextColor(Color.BLACK);
                    break;
                case "DRAW":  // Match nul
                    holder.tvHomeTeam.setTypeface(null, Typeface.NORMAL);
                    holder.tvAwayTeam.setTypeface(null, Typeface.NORMAL);
                    holder.tvHomeTeam.setTextColor(Color.GRAY);
                    holder.tvAwayTeam.setTextColor(Color.GRAY);
                    break;
            }
        }
        else{
            holder.tvHomeTeam.setTypeface(null, Typeface.NORMAL);
            holder.tvAwayTeam.setTypeface(null, Typeface.NORMAL);
            holder.tvHomeTeam.setTextColor(Color.BLACK);
            holder.tvAwayTeam.setTextColor(Color.BLACK);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, MatchActivity.class);
                intent.putExtra(CLE_DONNEES_ID_MATCH, Integer.parseInt(values.get(position).getIdMatch()));
                intent.putExtra(CLE_DONNEES_ID_HOME, Integer.parseInt(values.get(position).getIdTeamHome()));
                intent.putExtra(CLE_DONNEES_ID_AWAY, Integer.parseInt(values.get(position).getIdTeamAway()));
                intent.putExtra(CLE_DONNEES_STATUS, values.get(position).getStatus());
                context.startActivity(intent);
            }
        });

        switch (values.get(position).getStatus()) {
            case "LIVE":
            case "IN_PLAY":
                holder.itemView.setBackgroundResource(R.drawable.live);
                break;
            case "PAUSED":
            case "SUSPENDED":
                holder.itemView.setBackgroundResource(R.drawable.paused);
                break;
            case "CANCELED":
                holder.itemView.setBackgroundResource(R.drawable.canceled);
                break;
            case "FINISHED":
                holder.itemView.setBackgroundResource(R.drawable.terminated);
                break;
            case "SCHEDULED":
                holder.itemView.setBackgroundResource(R.drawable.scheduled);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

}