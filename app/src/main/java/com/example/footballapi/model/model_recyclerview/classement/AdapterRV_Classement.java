package com.example.footballapi.model.model_recyclerview.classement;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmadrosid.svgloader.SvgLoader;
import com.example.footballapi.R;
import com.example.footballapi.view.activities.ClassementActivity;
import com.example.footballapi.view.activities.TeamActivity;

import java.util.List;

public class AdapterRV_Classement extends RecyclerView.Adapter<AdapterRV_Classement.ViewHolder> {

    public static final String CLE_DONNEES_ID_TEAM = "idTeam";

    private List<TeamModel> values;
    private ClassementActivity activity;
    private boolean netaccess;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvPosition;
        private TextView tvClubname;
        private TextView tvDiff;
        private TextView tvPoints;
        private ImageView ivLogoClubClassement;

        ImageView getivLogoClubClassement(){ return this.ivLogoClubClassement;}

        public View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            tvPosition = v.findViewById(R.id.tvPosition);
            tvClubname = v.findViewById(R.id.tvClubname);
            tvDiff = v.findViewById(R.id.tvDiff);
            tvPoints = v.findViewById(R.id.tvPoints);
            ivLogoClubClassement = v.findViewById(R.id.ivLogoClubClassement);
        }
    }

    public AdapterRV_Classement(List<TeamModel> myDataset, ClassementActivity activity, boolean netaccess) {
        values = myDataset;
        this.activity = activity;
        this.netaccess = netaccess;
    }

    @NonNull
    @Override
    public AdapterRV_Classement.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_classement, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.tvPosition.setText(values.get(position).getPosition());
        holder.tvClubname.setText(values.get(position).getName());
        holder.tvDiff.setText(values.get(position).getDiff());
        holder.tvPoints.setText(values.get(position).getPoints());

        // On affiche l'image SVG pour afficher le logo du club
        if (values.get(position).getCrestURL() != null) {
            SvgLoader.pluck()
                    .with(this.activity)
                    .setPlaceHolder(R.drawable.ic_logo_foreground, R.drawable.ic_logo_foreground)
                    .load(values.get(position).getCrestURL(), holder.getivLogoClubClassement())
                    .close();
        }

        // On active les listener en cas de connexion à Internet, on les désactive sinon
        if(netaccess) {
            holder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, TeamActivity.class);
                    intent.putExtra(CLE_DONNEES_ID_TEAM, Integer.parseInt(values.get(position).getIdTeam()));
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

}