package com.example.footballapi.model.model_recyclerview.matches;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmadrosid.svgloader.SvgLoader;
import com.example.footballapi.R;
import com.example.footballapi.view.activities.CompetitionActivity;
import com.example.footballapi.view.activities.MatchActivity;
import com.example.footballapi.view.fragments.MatchesFragment;

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

        ImageView ivLogoClubHome(){ return this.ivLogoClubHome;}
        ImageView ivLogoClubAway(){ return this.ivLogoClubAway;}

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

        SvgLoader.pluck()
                .with(this.fragment.getActivity())
                .setPlaceHolder(R.drawable.ic_logo_foreground, R.drawable.ic_logo_foreground)
                .load(CompetitionActivity.getTeamCrest(Integer.valueOf(values.get(position).getIdTeamHome())), holder.ivLogoClubHome())
                .close();

        SvgLoader.pluck()
                .with(this.fragment.getActivity())
                .setPlaceHolder(R.drawable.ic_logo_foreground, R.drawable.ic_logo_foreground)
                .load(CompetitionActivity.getTeamCrest(Integer.valueOf(values.get(position).getIdTeamAway())), holder.ivLogoClubAway())
                .close();

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

        String a = values.get(position).getStatus();
        switch (a) {
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