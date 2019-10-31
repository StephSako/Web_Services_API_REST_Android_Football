package com.example.footballapi.model.model_recyclerview.matches;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmadrosid.svgloader.SvgLoader;
import com.example.footballapi.R;
import com.example.footballapi.model.model_dao.DataBase;
import com.example.footballapi.view.activities.MatchActivity;
import com.example.footballapi.view.fragments.MatchesFragment;
import com.squareup.picasso.Picasso;

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

        String crestHome = (new DataBase(fragment.getActivity()).findTeamCrest(Integer.valueOf(values.get(position).getIdTeamHome())) != null) ? new DataBase(fragment.getActivity()).findTeamCrest(Integer.valueOf(values.get(position).getIdTeamHome())) : "" ;
        String crestAway = (new DataBase(fragment.getActivity()).findTeamCrest(Integer.valueOf(values.get(position).getIdTeamAway())) != null) ? new DataBase(fragment.getActivity()).findTeamCrest(Integer.valueOf(values.get(position).getIdTeamAway())) : "" ;

        // Problèmes logos
        switch (values.get(position).getHomeTeam()) {
            case "FC Nantes":
                crestHome = "https://upload.wikimedia.org/wikipedia/commons/5/5c/FC_Nantes_2019_logo.svg";
                break;
            case "Nîmes Olympique":
                crestHome = "https://upload.wikimedia.org/wikipedia/fr/f/f0/N%C3%AEmes_Olympique_logo_2018.svg";
                break;
            case "Toulouse FC":
                crestHome = "https://upload.wikimedia.org/wikipedia/fr/8/8b/Logo_Toulouse_FC_2018.svg";
                break;
            case "Stade Brestois 29":
                crestHome = "https://upload.wikimedia.org/wikipedia/fr/1/14/Logo_Stade_Brestois.svg";
                break;
            case "Amiens SC":
                crestHome = "https://upload.wikimedia.org/wikipedia/fr/e/ec/Logo_Amiens_SC_1998.svg";
                break;
            case "Stade de Reims":
                crestHome = "https://upload.wikimedia.org/wikipedia/fr/0/02/Logo_Stade_Reims_1999.svg";
                break;
            case "Lille OSC":
                crestHome = "https://upload.wikimedia.org/wikipedia/fr/6/62/Logo_LOSC_Lille_2018.svg";
                break;
            case "Getafe CF":
                crestHome = "https://upload.wikimedia.org/wikipedia/fr/c/c8/Getafe_cf_logo.png";
                break;
            case "FC Famalicão":
                crestHome = "https://upload.wikimedia.org/wikipedia/commons/4/4b/Futebol_Clube_de_Famalicao.png";
                break;
            case "Vitória SC":
                crestHome = "https://upload.wikimedia.org/wikipedia/fr/2/29/Vitoria_Sport_Clube.png";
                break;
            case "Sporting Clube de Portugal":
                crestHome = "https://upload.wikimedia.org/wikipedia/fr/7/7f/Sporting_Portugal.png";
                break;
            case "Rio Ave FC":
                crestHome = "https://upload.wikimedia.org/wikipedia/fr/6/63/Rio_Ave_FC.svg";
                break;
            case "CD Santa Clara":
                crestHome = "https://upload.wikimedia.org/wikipedia/en/3/37/C.D._Santa_Clara_logo.svg";
                break;
            case "CS Marítimo":
                crestHome = "https://upload.wikimedia.org/wikipedia/fr/6/67/Club_Sport_Mar%C3%ADtimo.png";
                break;
            case "Vitória FC":
                crestHome = "https://i.pinimg.com/originals/be/10/5d/be105d35b052035d32cae79d103d42c7.png";
                break;
            case "CD Aves":
                crestHome = "https://upload.wikimedia.org/wikipedia/en/6/60/CD_Aves_logo.svg";
                break;
            case "Os Belenenses Futebol":
                crestHome = "https://upload.wikimedia.org/wikipedia/ca/thumb/2/2a/Os_Belenenses.png/250px-Os_Belenenses.png";
                break;
            case "Sporting Clube de Braga":
                crestHome = "https://upload.wikimedia.org/wikipedia/fr/d/d2/Sporting-Braga.png";
                break;
            case "CD Tondela":
                crestHome = "https://topsoccerpicks.com/wp-content/uploads/2018/02/Tondela.png";
                break;
            case "Gil Vicente FC":
                crestHome = "https://upload.wikimedia.org/wikipedia/fr/d/dc/Logo_Gil_Vicente_FC.png";
                break;
            case "Sparta Rotterdam":
                crestHome = "https://upload.wikimedia.org/wikipedia/fr/5/53/Sparta_Rotterdam.png";
                break;
            case "FC Emmen":
                crestHome = "https://upload.wikimedia.org/wikipedia/fr/8/83/FC_Emmen_logo.svg";
                break;
            case "ADO Den Haag":
                crestHome = "https://www.logofootball.net/wp-content/uploads/Ado-Den-Haag-HD-Logo.png";
                break;
            case "CR Flamengo":
                crestHome = "https://upload.wikimedia.org/wikipedia/commons/2/2e/Flamengo_braz_logo.svg";
                break;
            case "SE Palmeiras":
                crestHome = "https://upload.wikimedia.org/wikipedia/commons/1/10/Palmeiras_logo.svg";
                break;
            case "Santos FC":
                crestHome = "https://upload.wikimedia.org/wikipedia/commons/3/35/Santos_logo.svg";
                break;
            case "São Paulo FC":
                crestHome = "https://upload.wikimedia.org/wikipedia/commons/6/6f/Brasao_do_Sao_Paulo_Futebol_Clube.svg";
                break;
            case "Grêmio FBPA":
                crestHome = "https://upload.wikimedia.org/wikipedia/de/f/f4/Gremio_Porto_Alegre.svg";
                break;
            case "SC Internacional":
                crestHome = "https://i.pinimg.com/originals/5d/ae/fd/5daefd5a1b62eceb9fb92dc536f5c34d.png";
                break;
            case "SC Corinthians Paulista":
                crestHome = "https://upload.wikimedia.org/wikipedia/fr/a/a1/SC_Corinthians_Paulista.svg";
                break;
            case "CA Paranaense":
                crestHome = "https://upload.wikimedia.org/wikipedia/en/d/dd/New_logo_of_club_Athletico_Paranaense.png";
                break;
            case "EC Bahia":
                crestHome = "https://upload.wikimedia.org/wikipedia/en/thumb/b/b1/Esporte_Clube_Bahia_logo.png/200px-Esporte_Clube_Bahia_logo.png";
                break;
            case "Goiás EC":
                crestHome = "https://upload.wikimedia.org/wikipedia/commons/7/7f/Goias_Esporte_Clube_logo.svg";
                break;
            case "CR Vasco da Gama":
                crestHome = "https://upload.wikimedia.org/wikipedia/fr/1/1a/ClubDeRegatasVascoDaGama.svg";
                break;
            case "Fortaleza EC":
                crestHome = "https://upload.wikimedia.org/wikipedia/commons/6/6e/Ecusson-fortaleza.png";
                break;
            case "CA Mineiro":
                crestHome = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5f/Atletico_mineiro_galo.png/1200px-Atletico_mineiro_galo.png";
                break;
            case "Botafogo FR":
                crestHome = "https://upload.wikimedia.org/wikipedia/commons/5/52/Botafogo_de_Futebol_e_Regatas_logo.svg";
                break;
            case "Ceará SC":
                crestHome = "https://upload.wikimedia.org/wikipedia/commons/3/38/Cear%C3%A1_Sporting_Club_logo.svg";
                break;
            case "Fluminense FC":
                crestHome = "https://upload.wikimedia.org/wikipedia/en/9/9e/Fluminense_fc_logo.svg";
                break;
            case "CS Alagoano":
                crestHome = "http://www.leballonrond.fr/img/logos/equipas/3217_imgbank.png";
                break;
            case "Cruzeiro EC":
                crestHome = "http://en.fodb.net/img/club/Brazil/100/Cruzeiro-EC.png";
                break;
            case "Chapecoense AF":
                crestHome = "https://upload.wikimedia.org/wikipedia/fr/9/97/Associa%C3%A7%C3%A3o_Chapecoense_de_Futebol_%282016%29.png";
                break;
            case "Avaí FC":
                crestHome = "https://upload.wikimedia.org/wikipedia/commons/f/fe/Avai_FC_%2805-E%29_-_SC.svg";
        }

        switch (values.get(position).getAwayTeam()) {
            case "FC Nantes":
                crestAway = "https://upload.wikimedia.org/wikipedia/commons/5/5c/FC_Nantes_2019_logo.svg";
                break;
            case "Nîmes Olympique":
                crestAway = "https://upload.wikimedia.org/wikipedia/fr/f/f0/N%C3%AEmes_Olympique_logo_2018.svg";
                break;
            case "Toulouse FC":
                crestAway = "https://upload.wikimedia.org/wikipedia/fr/8/8b/Logo_Toulouse_FC_2018.svg";
                break;
            case "Stade Brestois 29":
                crestAway = "https://upload.wikimedia.org/wikipedia/fr/1/14/Logo_Stade_Brestois.svg";
                break;
            case "Amiens SC":
                crestAway = "https://upload.wikimedia.org/wikipedia/fr/e/ec/Logo_Amiens_SC_1998.svg";
                break;
            case "Stade de Reims":
                crestAway = "https://upload.wikimedia.org/wikipedia/fr/0/02/Logo_Stade_Reims_1999.svg";
                break;
            case "Lille OSC":
                crestAway = "https://upload.wikimedia.org/wikipedia/fr/6/62/Logo_LOSC_Lille_2018.svg";
                break;
            case "Getafe CF":
                crestAway = "https://upload.wikimedia.org/wikipedia/fr/c/c8/Getafe_cf_logo.png";
                break;
            case "FC Famalicão":
                crestAway = "https://upload.wikimedia.org/wikipedia/commons/4/4b/Futebol_Clube_de_Famalicao.png";
                break;
            case "Vitória SC":
                crestAway = "https://upload.wikimedia.org/wikipedia/fr/2/29/Vitoria_Sport_Clube.png";
                break;
            case "Sporting Clube de Portugal":
                crestAway = "https://upload.wikimedia.org/wikipedia/fr/7/7f/Sporting_Portugal.png";
                break;
            case "Rio Ave FC":
                crestAway = "https://upload.wikimedia.org/wikipedia/fr/6/63/Rio_Ave_FC.svg";
                break;
            case "CD Santa Clara":
                crestAway = "https://upload.wikimedia.org/wikipedia/en/3/37/C.D._Santa_Clara_logo.svg";
                break;
            case "CS Marítimo":
                crestAway = "https://upload.wikimedia.org/wikipedia/fr/6/67/Club_Sport_Mar%C3%ADtimo.png";
                break;
            case "Vitória FC":
                crestAway = "https://i.pinimg.com/originals/be/10/5d/be105d35b052035d32cae79d103d42c7.png";
                break;
            case "CD Aves":
                crestAway = "https://upload.wikimedia.org/wikipedia/en/6/60/CD_Aves_logo.svg";
                break;
            case "Os Belenenses Futebol":
                crestAway = "https://upload.wikimedia.org/wikipedia/ca/thumb/2/2a/Os_Belenenses.png/250px-Os_Belenenses.png";
                break;
            case "Sporting Clube de Braga":
                crestAway = "https://upload.wikimedia.org/wikipedia/fr/d/d2/Sporting-Braga.png";
                break;
            case "CD Tondela":
                crestAway = "https://topsoccerpicks.com/wp-content/uploads/2018/02/Tondela.png";
                break;
            case "Gil Vicente FC":
                crestAway = "https://upload.wikimedia.org/wikipedia/fr/d/dc/Logo_Gil_Vicente_FC.png";
                break;
            case "Sparta Rotterdam":
                crestAway = "https://upload.wikimedia.org/wikipedia/fr/5/53/Sparta_Rotterdam.png";
                break;
            case "FC Emmen":
                crestAway = "https://upload.wikimedia.org/wikipedia/fr/8/83/FC_Emmen_logo.svg";
                break;
            case "ADO Den Haag":
                crestAway = "https://www.logofootball.net/wp-content/uploads/Ado-Den-Haag-HD-Logo.png";
                break;
            case "CR Flamengo":
                crestAway = "https://upload.wikimedia.org/wikipedia/commons/2/2e/Flamengo_braz_logo.svg";
                break;
            case "SE Palmeiras":
                crestAway = "https://upload.wikimedia.org/wikipedia/commons/1/10/Palmeiras_logo.svg";
                break;
            case "Santos FC":
                crestAway = "https://upload.wikimedia.org/wikipedia/commons/3/35/Santos_logo.svg";
                break;
            case "São Paulo FC":
                crestAway = "https://upload.wikimedia.org/wikipedia/commons/6/6f/Brasao_do_Sao_Paulo_Futebol_Clube.svg";
                break;
            case "Grêmio FBPA":
                crestAway = "https://upload.wikimedia.org/wikipedia/de/f/f4/Gremio_Porto_Alegre.svg";
                break;
            case "SC Internacional":
                crestAway = "https://i.pinimg.com/originals/5d/ae/fd/5daefd5a1b62eceb9fb92dc536f5c34d.png";
                break;
            case "SC Corinthians Paulista":
                crestAway = "https://upload.wikimedia.org/wikipedia/fr/a/a1/SC_Corinthians_Paulista.svg";
                break;
            case "CA Paranaense":
                crestAway = "https://upload.wikimedia.org/wikipedia/en/d/dd/New_logo_of_club_Athletico_Paranaense.png";
                break;
            case "EC Bahia":
                crestAway = "https://upload.wikimedia.org/wikipedia/en/thumb/b/b1/Esporte_Clube_Bahia_logo.png/200px-Esporte_Clube_Bahia_logo.png";
                break;
            case "Goiás EC":
                crestAway = "https://upload.wikimedia.org/wikipedia/commons/7/7f/Goias_Esporte_Clube_logo.svg";
                break;
            case "CR Vasco da Gama":
                crestAway = "https://upload.wikimedia.org/wikipedia/fr/1/1a/ClubDeRegatasVascoDaGama.svg";
                break;
            case "Fortaleza EC":
                crestAway = "https://upload.wikimedia.org/wikipedia/commons/6/6e/Ecusson-fortaleza.png";
                break;
            case "CA Mineiro":
                crestAway = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5f/Atletico_mineiro_galo.png/1200px-Atletico_mineiro_galo.png";
                break;
            case "Botafogo FR":
                crestAway = "https://upload.wikimedia.org/wikipedia/commons/5/52/Botafogo_de_Futebol_e_Regatas_logo.svg";
                break;
            case "Ceará SC":
                crestAway = "https://upload.wikimedia.org/wikipedia/commons/3/38/Cear%C3%A1_Sporting_Club_logo.svg";
                break;
            case "Fluminense FC":
                crestAway = "https://upload.wikimedia.org/wikipedia/en/9/9e/Fluminense_fc_logo.svg";
                break;
            case "CS Alagoano":
                crestAway = "http://www.leballonrond.fr/img/logos/equipas/3217_imgbank.png";
                break;
            case "Cruzeiro EC":
                crestAway = "http://en.fodb.net/img/club/Brazil/100/Cruzeiro-EC.png";
                break;
            case "Chapecoense AF":
                crestAway = "https://upload.wikimedia.org/wikipedia/fr/9/97/Associa%C3%A7%C3%A3o_Chapecoense_de_Futebol_%282016%29.png";
                break;
            case "Avaí FC":
                crestAway = "https://upload.wikimedia.org/wikipedia/commons/f/fe/Avai_FC_%2805-E%29_-_SC.svg";
        }

        if (!crestHome.equals("")) {
            switch (crestHome.substring(crestHome.length() - 3)){
                case "svg":
                    SvgLoader.pluck()
                            .with(this.fragment.getActivity())
                            .setPlaceHolder(R.drawable.ic_logo_foreground, R.drawable.ic_logo_foreground)
                            .load(crestHome, holder.ivLogoClubHome)
                            .close();
                    break;
                case "gif":
                case "png":
                    Picasso.get()
                            .load(crestHome)
                            .error(R.drawable.ic_logo_foreground)
                            .resize(50, 50)
                            .centerCrop()
                            .into(holder.ivLogoClubHome);
                    break;
            }
        }

        if (!crestAway.equals("")) {

            switch (crestAway.substring(crestAway.length() - 3)){
                case "svg":
                    SvgLoader.pluck()
                            .with(this.fragment.getActivity())
                            .setPlaceHolder(R.drawable.ic_logo_foreground, R.drawable.ic_logo_foreground)
                            .load(crestAway, holder.ivLogoClubAway)
                            .close();
                    break;
                case "gif":

                    break;
                case "png":
                    Picasso.get()
                            .load(crestAway)
                            .error(R.drawable.ic_logo_foreground)
                            .resize(50, 50)
                            .centerCrop()
                            .into(holder.ivLogoClubAway);
                    break;
            }
        }

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

        switch (values.get(position).getStatus()) {
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