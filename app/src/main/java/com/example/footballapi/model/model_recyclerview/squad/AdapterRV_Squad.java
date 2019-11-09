package com.example.footballapi.model.model_recyclerview.squad;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footballapi.R;
import com.example.footballapi.view.activities.PlayerActivity;

import java.util.List;

public class AdapterRV_Squad extends RecyclerView.Adapter<AdapterRV_Squad.ViewHolder> {

    public static final String CLE_DONNEES_ID_PLAYER = "idPlayer";
    public static final String CLE_DONNEES_NOM_CLUB = "nomClub";
    public static final String CLE_DONNEES_CRUST_URL = "crestTeam";


    private List<SquadModel> values;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPlayerName;
        TextView tvShirtNumber;
        TextView tvNationality;
        TextView tvPosition;

        public View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            tvPlayerName = v.findViewById(R.id.tvPlayerName);
            tvShirtNumber = v.findViewById(R.id.tvShirtNumber);
            tvNationality = v.findViewById(R.id.tvNationality);
            tvPosition = v.findViewById(R.id.tvPosition);
        }
    }

    public AdapterRV_Squad(List<SquadModel> myDataset) {
        values = myDataset;
    }

    @NonNull
    @Override
    public AdapterRV_Squad.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_squad, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.tvPosition.setText(values.get(position).getPlayerPosition());
        holder.tvPlayerName.setText(values.get(position).getPlayerName());
        holder.tvNationality.setText(values.get(position).getPlayerNationality());
        holder.tvShirtNumber.setText(values.get(position).getPlayerShirtNumber());

        holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, PlayerActivity.class);
                intent.putExtra(CLE_DONNEES_ID_PLAYER, Integer.parseInt(values.get(position).getPlayerId()));
                intent.putExtra(CLE_DONNEES_NOM_CLUB, values.get(position).getTeamName());
                intent.putExtra(CLE_DONNEES_CRUST_URL, values.get(position).getTeamCrest());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return values.size();
    }

}