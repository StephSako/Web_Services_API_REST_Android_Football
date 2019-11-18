package com.example.footballapi.model.model_recyclerview.squad;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footballapi.R;
import com.example.footballapi.view.fragments.PlayerFragment;
import com.example.footballapi.view.fragments.SquadFragment;

import java.util.List;

public class AdapterRV_Squad extends RecyclerView.Adapter<AdapterRV_Squad.ViewHolder> {

    private static final String CLE_DONNEES_ID_PLAYER = "idPlayer";
    private static final String CLE_DONNEES_NOM_CLUB = "nomClub";
    private static final String CLE_DONNEES_CRUST_URL = "crestTeam";

    private List<SquadModel> values;
    private SquadFragment fragment;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPlayerName;
        TextView tvNationality;
        TextView tvPosition;

        public View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            tvPlayerName = v.findViewById(R.id.tvPlayerName);
            tvNationality = v.findViewById(R.id.tvNationality);
            tvPosition = v.findViewById(R.id.tvPosition);
        }
    }

    public AdapterRV_Squad(List<SquadModel> myDataset, SquadFragment fragment) {
        this.values = myDataset;
        this.fragment = fragment;
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

        holder.itemView.setBackgroundResource(R.drawable.terminated);

        holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment playerFragment = new PlayerFragment();
                Bundle bundle = new Bundle();
                bundle.putInt(CLE_DONNEES_ID_PLAYER, Integer.parseInt(values.get(position).getPlayerId()));
                bundle.putString(CLE_DONNEES_NOM_CLUB, values.get(position).getTeamName());
                bundle.putString(CLE_DONNEES_CRUST_URL, values.get(position).getTeamCrest());
                playerFragment.setArguments(bundle);

                AppCompatActivity activity = (AppCompatActivity) fragment.getActivity();
                if (activity != null) activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_left).replace(R.id.fragment_hoster, playerFragment).addToBackStack(null).commit();
            }
        });
    }
    @Override
    public int getItemCount() {
        return values.size();
    }

}