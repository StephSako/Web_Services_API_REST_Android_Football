package com.example.footballapi.model.model_recyclerview.classement;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmadrosid.svgloader.SvgLoader;
import com.example.footballapi.R;
import com.example.footballapi.view.fragments.ClassementFragment;
import com.example.footballapi.view.activities.TeamActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterRV_Classement extends RecyclerView.Adapter<AdapterRV_Classement.ViewHolder> {

    public static final String CLE_DONNEES_ID_TEAM = "idTeam";

    private List<TeamModel> values;
    private ClassementFragment fragment;
    private boolean netaccess;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvPosition;
        private TextView tvClubname;
        private TextView tvDiff;
        private TextView tvPoints;
        private ImageView ivLogoClubClassement;

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

    public AdapterRV_Classement(List<TeamModel> myDataset, ClassementFragment fragment, boolean netaccess) {
        values = myDataset;
        this.fragment = fragment;
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

        String crest = values.get(position).getCrestURL();

        // Problèmes logos Ligue 1
        switch (values.get(position).getName()) {
            case "FC Nantes":
                crest = "https://upload.wikimedia.org/wikipedia/commons/5/5c/FC_Nantes_2019_logo.svg";
                break;
            case "Nîmes Olympique":
                crest = "https://upload.wikimedia.org/wikipedia/fr/f/f0/N%C3%AEmes_Olympique_logo_2018.svg";
                break;
            case "Toulouse FC":
                crest = "https://upload.wikimedia.org/wikipedia/fr/8/8b/Logo_Toulouse_FC_2018.svg";
                break;
            case "Stade Brestois 29":
                crest = "https://upload.wikimedia.org/wikipedia/fr/1/14/Logo_Stade_Brestois.svg";
                break;
            case "Amiens SC":
                crest = "https://upload.wikimedia.org/wikipedia/fr/e/ec/Logo_Amiens_SC_1998.svg";
                break;
            case "Stade de Reims":
                crest = "https://upload.wikimedia.org/wikipedia/fr/0/02/Logo_Stade_Reims_1999.svg";
                break;
            case "Lille OSC":
                crest = "https://upload.wikimedia.org/wikipedia/fr/6/62/Logo_LOSC_Lille_2018.svg";
                break;
        }

        if (!crest.equals("")) {
            switch (crest.substring(crest.length() - 3)){
                case "svg":
                    SvgLoader.pluck()
                            .with(this.fragment.getActivity())
                            .setPlaceHolder(R.drawable.ic_logo_foreground, R.drawable.ic_logo_foreground)
                            .load(crest, holder.ivLogoClubClassement)
                            .close();
                    break;
                case "gif":
                case "png":
                    // Display with androidgif
                    Picasso.get()
                            .load(crest)
                            .error(R.drawable.ic_logo_foreground)
                            .resize(50, 50)
                            .centerCrop()
                            .into(holder.ivLogoClubClassement);
                    break;
            }
        }

        // On active les listener en cas de activity_connexion à Internet, on les désactive sinon
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