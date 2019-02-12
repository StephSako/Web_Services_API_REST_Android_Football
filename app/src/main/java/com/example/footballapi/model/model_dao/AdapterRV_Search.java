package com.example.footballapi.model.model_dao;

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
import com.example.footballapi.view.activities.SearchTeamActivity;
import com.example.footballapi.view.activities.TeamActivity;

import java.util.List;

public class AdapterRV_Search extends RecyclerView.Adapter<AdapterRV_Search.ViewHolder> {

    public static final String CLE_DONNEES_ID_TEAM = "idTeam";

    private List<TeamDAO> listSearch;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView tvPosition;
        private TextView tvClubname;
        private TextView tvPoints;

        public View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            tvPosition = v.findViewById(R.id.tvPosition);
            tvClubname = v.findViewById(R.id.tvClubname);
            tvPoints = v.findViewById(R.id.tvPoints);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterRV_Search(List<TeamDAO> myDataset) {
        listSearch = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public AdapterRV_Search.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_search, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.tvPosition.setText(listSearch.get(position).getPosition());
        holder.tvClubname.setText(listSearch.get(position).getClub_name());
        holder.tvPoints.setText(listSearch.get(position).getPoints());


        holder.tvClubname.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, TeamActivity.class);
                intent.putExtra(CLE_DONNEES_ID_TEAM, listSearch.get(position).getPosition());
                context.startActivity(intent);
            }
        });

        holder.tvPoints.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, TeamActivity.class);
                intent.putExtra(CLE_DONNEES_ID_TEAM, listSearch.get(position).getIdTeam());
                context.startActivity(intent);
            }
        });

        holder.tvPosition.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, TeamActivity.class);
                intent.putExtra(CLE_DONNEES_ID_TEAM, listSearch.get(position).getIdTeam());
                context.startActivity(intent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listSearch.size();
    }

}