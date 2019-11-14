package com.example.footballapi.model.model_dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footballapi.R;
import com.example.footballapi.view.activities.MainActivity;
import com.example.footballapi.view.fragments.TeamFragment;

import java.util.List;

public class AdapterRV_Search extends RecyclerView.Adapter<AdapterRV_Search.ViewHolder> {

    private static final String CLE_DONNEES_ID_TEAM = "idTeam";
    private final static String CLE_DONNEES_ID_COMPET = "idCompet";

    private List<TeamDAO> listSearch;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Button btnCompetSearch;
        private Button btnTeamSearch;

        public View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            btnCompetSearch = v.findViewById(R.id.btnCompetSearch);
            btnTeamSearch = v.findViewById(R.id.btnTeamSearch);
        }
    }

    public AdapterRV_Search(List<TeamDAO> myDataset) {
        listSearch = myDataset;
    }

    @NonNull
    @Override
    public AdapterRV_Search.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_search, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.btnCompetSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra(CLE_DONNEES_ID_COMPET, listSearch.get(position).getIdCompet());
                context.startActivity(intent);
            }
        });

        holder.btnTeamSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, TeamFragment.class);
                intent.putExtra(CLE_DONNEES_ID_TEAM, listSearch.get(position).getIdTeam());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listSearch.size();
    }

}