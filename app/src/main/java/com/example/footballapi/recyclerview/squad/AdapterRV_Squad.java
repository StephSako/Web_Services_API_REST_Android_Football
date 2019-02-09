package com.example.footballapi.recyclerview.squad;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.footballapi.R;
import com.example.footballapi.view.activities.PlayerActivity;
import com.example.footballapi.view.activities.TeamActivity;

import java.util.List;
import java.util.Objects;

public class AdapterRV_Squad extends RecyclerView.Adapter<AdapterRV_Squad.ViewHolder> {

    public static final String CLE_DONNEES_ID_PLAYER = "idPlayer";
    public static final String CLE_DONNEES_NOM_CLUB = "nomClub";
    public static final String CLE_DONNEES_CRUST_URL = "crestURL";


    private List<SquadModel> values;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
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

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterRV_Squad(List<SquadModel> myDataset) {
        values = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public AdapterRV_Squad.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_squad, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.tvPosition.setText(values.get(position).getPlayerPosition());
        holder.tvPlayerName.setText(values.get(position).getPlayerName());
        holder.tvNationality.setText(values.get(position).getPlayerNationality());
        holder.tvShirtNumber.setText(values.get(position).getPlayerShirtNumber());

        holder.tvPlayerName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, PlayerActivity.class);
                intent.putExtra(CLE_DONNEES_ID_PLAYER, Integer.parseInt(values.get(position).getPlayerId()));
                intent.putExtra(CLE_DONNEES_NOM_CLUB, ((TeamActivity) Objects.requireNonNull(context)).getnomClub());
                intent.putExtra(CLE_DONNEES_CRUST_URL, ((TeamActivity) Objects.requireNonNull(context)).getcrestURLPlayer());
                context.startActivity(intent);
            }
        });

        holder.tvPosition.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, PlayerActivity.class);
                intent.putExtra(CLE_DONNEES_ID_PLAYER, Integer.parseInt(values.get(position).getPlayerId()));
                intent.putExtra(CLE_DONNEES_NOM_CLUB, ((TeamActivity) Objects.requireNonNull(context)).getnomClub());
                intent.putExtra(CLE_DONNEES_CRUST_URL, ((TeamActivity) Objects.requireNonNull(context)).getcrestURLPlayer());
                context.startActivity(intent);
            }
        });

        holder.tvShirtNumber.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, PlayerActivity.class);
                intent.putExtra(CLE_DONNEES_ID_PLAYER, Integer.parseInt(values.get(position).getPlayerId()));
                intent.putExtra(CLE_DONNEES_NOM_CLUB, ((TeamActivity) Objects.requireNonNull(context)).getnomClub());
                intent.putExtra(CLE_DONNEES_CRUST_URL, ((TeamActivity) Objects.requireNonNull(context)).getcrestURLPlayer());
                context.startActivity(intent);
            }
        });

        holder.tvNationality.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, PlayerActivity.class);
                intent.putExtra(CLE_DONNEES_ID_PLAYER, Integer.parseInt(values.get(position).getPlayerId()));
                intent.putExtra(CLE_DONNEES_NOM_CLUB, ((TeamActivity) Objects.requireNonNull(context)).getnomClub());
                intent.putExtra(CLE_DONNEES_CRUST_URL, ((TeamActivity) Objects.requireNonNull(context)).getcrestURLPlayer());
                context.startActivity(intent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}