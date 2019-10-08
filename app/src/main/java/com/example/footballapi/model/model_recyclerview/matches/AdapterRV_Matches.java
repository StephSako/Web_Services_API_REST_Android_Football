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
import android.widget.TextView;

import com.example.footballapi.R;
import com.example.footballapi.view.activities.MatchActivity;

import java.util.List;

public class AdapterRV_Matches extends RecyclerView.Adapter<AdapterRV_Matches.ViewHolder> {

    public static final String CLE_DONNEES_ID_MATCH= "idMatch";

    private List<MatchesModel> values;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMatchday;
        TextView tvHomeTeam;
        TextView tvScore;
        TextView tvAwayTeam;

        public View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            tvMatchday = v.findViewById(R.id.tvMatchday);
            tvHomeTeam = v.findViewById(R.id.tvHomeTeam);
            tvScore = v.findViewById(R.id.tvScore);
            tvAwayTeam = v.findViewById(R.id.tvAwayTeam);
        }
    }

    public AdapterRV_Matches(List<MatchesModel> myDataset) {
        this.values = myDataset;
    }

    @NonNull
    @Override
    public AdapterRV_Matches.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_matches, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.tvMatchday.setText("J" + values.get(position).getMatchDay());
        holder.tvAwayTeam.setText(values.get(position).getAwayTeam());
        holder.tvScore.setText(values.get(position).getScore());
        holder.tvHomeTeam.setText(values.get(position).getHomeTeam());

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
                context.startActivity(intent);
            }
        });

        if (values.get(position).getStatus().equals("LIVE") || values.get(position).getStatus().equals("IN_PLAY"))
            holder.itemView.setBackgroundResource(R.drawable.live);
        else if (values.get(position).getStatus().equals("PAUSED") || values.get(position).getStatus().equals("SUSPENDED"))
            holder.itemView.setBackgroundResource(R.drawable.paused);
        else if (values.get(position).getStatus().equals("CANCELED"))
            holder.itemView.setBackgroundResource(R.drawable.canceled);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

}