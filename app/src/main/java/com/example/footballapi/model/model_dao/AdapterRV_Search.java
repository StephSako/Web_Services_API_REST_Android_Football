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
import android.widget.Button;
import android.widget.TextView;

import com.example.footballapi.R;
import com.example.footballapi.view.fragments.ClassementFragment;
import com.example.footballapi.view.activities.TeamActivity;

import java.util.List;

public class AdapterRV_Search extends RecyclerView.Adapter<AdapterRV_Search.ViewHolder> {

    public static final String CLE_DONNEES_ID_TEAM = "idTeam";
    public final static String CLE_DONNEES_ID_COMPET = "idCompet";

    private List<TeamDAO> listSearch;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvClubname;
        private Button btnCompetSearch;
        private Button btnTeamSearch;

        public View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            tvClubname = v.findViewById(R.id.nomClub_search);
            btnCompetSearch = v.findViewById(R.id.btnCompetSearch);
            btnTeamSearch = v.findViewById(R.id.btnTeamSearch);
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

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.tvClubname.setText(String.valueOf(listSearch.get(position).getClub_name()));

        holder.tvClubname.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, TeamActivity.class);
                intent.putExtra(CLE_DONNEES_ID_TEAM, listSearch.get(position).getIdTeam());
                context.startActivity(intent);
            }
        });

        holder.btnCompetSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, ClassementFragment.class);
                intent.putExtra(CLE_DONNEES_ID_COMPET, listSearch.get(position).getIdCompet());
                context.startActivity(intent);
            }
        });

        holder.btnTeamSearch.setOnClickListener(new OnClickListener() {
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