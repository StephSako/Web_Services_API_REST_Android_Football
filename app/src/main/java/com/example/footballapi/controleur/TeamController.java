package com.example.footballapi.controleur;

import androidx.annotation.NonNull;

import com.ahmadrosid.svgloader.SvgLoader;
import com.example.footballapi.R;
import com.example.footballapi.services.retrofit.football_data.RestFootballData;
import com.example.footballapi.model.model_retrofit.team.Team;
import com.example.footballapi.view.activities.TeamActivity;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamController {

    private TeamActivity activity;

    public TeamController(TeamActivity activity) {
        this.activity = activity;
    }

    /**
     * Affiche les détails d'une équipe
     * @param token token de connexion
     * */
    public void onCreate(final String token) {
        Call<Team> call = RestFootballData.get().teamsDetails(token, activity.idTeam);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(@NonNull Call<Team> call, @NonNull Response<Team> response) {
                if (response.isSuccessful()) {
                    Team team = response.body();
                    assert team != null;

                    activity.crestURLPlayer = team.getCrestUrl();

                    activity.nomClub = team.getName();

                    // On change le title de l'actionBar par le nom du club
                    Objects.requireNonNull(activity).setTitle(team.getName());

                    activity.tvWebSite.setText(team.getWebSite());
                    activity.tvStade.setText(team.getVenue());

                    StringBuilder activeCompetitions = new StringBuilder();

                    for (int i = 0; i < team.getActiveCompetitions().size(); i++){
                        if (i == team.getActiveCompetitions().size() - 1) activeCompetitions.append(team.getActiveCompetitions().get(i).getName());
                        else activeCompetitions.append(team.getActiveCompetitions().get(i).getName()).append(", ");
                    }

                    StringBuilder entraineur = new StringBuilder();
                    for (int i = 0; i < team.getSquad().size(); i++) {
                        if (team.getSquad().get(i).getRole().equals("COACH")) {
                            if (i == team.getSquad().size() - 1)
                                entraineur.append(team.getSquad().get(i).getName());
                            else
                                entraineur.append(team.getSquad().get(i).getName()).append("\n");
                        }
                    }

                    activity.tvActiveCompetitions.setText(activeCompetitions.toString());
                    activity.tvEntraineur.setText(entraineur.toString());

                    String crest = team.getCrestUrl();

                    switch (team.getName()) {
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
                        case "Getafe CF":
                            crest = "https://upload.wikimedia.org/wikipedia/fr/c/c8/Getafe_cf_logo.png";
                            break;
                        case "FC Famalicão":
                            crest = "https://upload.wikimedia.org/wikipedia/commons/4/4b/Futebol_Clube_de_Famalicao.png";
                            break;
                        case "Vitória SC":
                            crest = "https://upload.wikimedia.org/wikipedia/fr/2/29/Vitoria_Sport_Clube.png";
                            break;
                        case "Sporting Clube de Portugal":
                            crest = "https://upload.wikimedia.org/wikipedia/fr/7/7f/Sporting_Portugal.png";
                            break;
                        case "Rio Ave FC":
                            crest = "https://upload.wikimedia.org/wikipedia/fr/6/63/Rio_Ave_FC.svg";
                            break;
                        case "CD Santa Clara":
                            crest = "https://upload.wikimedia.org/wikipedia/en/3/37/C.D._Santa_Clara_logo.svg";
                            break;
                        case "CS Marítimo":
                            crest = "https://upload.wikimedia.org/wikipedia/fr/6/67/Club_Sport_Mar%C3%ADtimo.png";
                            break;
                        case "Vitória FC":
                            crest = "https://i.pinimg.com/originals/be/10/5d/be105d35b052035d32cae79d103d42c7.png";
                            break;
                        case "CD Aves":
                            crest = "https://upload.wikimedia.org/wikipedia/en/6/60/CD_Aves_logo.svg";
                            break;
                        case "Os Belenenses Futebol":
                            crest = "https://upload.wikimedia.org/wikipedia/ca/thumb/2/2a/Os_Belenenses.png/250px-Os_Belenenses.png";
                            break;
                        case "Sporting Clube de Braga":
                            crest = "https://upload.wikimedia.org/wikipedia/fr/d/d2/Sporting-Braga.png";
                            break;
                        case "CD Tondela":
                            crest = "https://topsoccerpicks.com/wp-content/uploads/2018/02/Tondela.png";
                            break;
                        case "Gil Vicente FC":
                            crest = "https://upload.wikimedia.org/wikipedia/fr/d/dc/Logo_Gil_Vicente_FC.png";
                            break;
                        case "Sparta Rotterdam":
                            crest = "https://upload.wikimedia.org/wikipedia/fr/5/53/Sparta_Rotterdam.png";
                            break;
                        case "FC Emmen":
                            crest = "https://upload.wikimedia.org/wikipedia/fr/8/83/FC_Emmen_logo.svg";
                            break;
                        case "ADO Den Haag":
                            crest = "https://www.logofootball.net/wp-content/uploads/Ado-Den-Haag-HD-Logo.png";
                            break;
                        case "CR Flamengo":
                            crest = "https://upload.wikimedia.org/wikipedia/commons/2/2e/Flamengo_braz_logo.svg";
                            break;
                        case "SE Palmeiras":
                            crest = "https://upload.wikimedia.org/wikipedia/commons/1/10/Palmeiras_logo.svg";
                            break;
                        case "Santos FC":
                            crest = "https://upload.wikimedia.org/wikipedia/commons/3/35/Santos_logo.svg";
                            break;
                        case "São Paulo FC":
                            crest = "https://upload.wikimedia.org/wikipedia/commons/6/6f/Brasao_do_Sao_Paulo_Futebol_Clube.svg";
                            break;
                        case "Grêmio FBPA":
                            crest = "https://upload.wikimedia.org/wikipedia/de/f/f4/Gremio_Porto_Alegre.svg";
                            break;
                        case "SC Internacional":
                            crest = "https://i.pinimg.com/originals/5d/ae/fd/5daefd5a1b62eceb9fb92dc536f5c34d.png";
                            break;
                        case "SC Corinthians Paulista":
                            crest = "https://upload.wikimedia.org/wikipedia/fr/a/a1/SC_Corinthians_Paulista.svg";
                            break;
                        case "CA Paranaense":
                            crest = "https://upload.wikimedia.org/wikipedia/en/d/dd/New_logo_of_club_Athletico_Paranaense.png";
                            break;
                        case "EC Bahia":
                            crest = "https://upload.wikimedia.org/wikipedia/en/thumb/b/b1/Esporte_Clube_Bahia_logo.png/200px-Esporte_Clube_Bahia_logo.png";
                            break;
                        case "Goiás EC":
                            crest = "https://upload.wikimedia.org/wikipedia/commons/7/7f/Goias_Esporte_Clube_logo.svg";
                            break;
                        case "CR Vasco da Gama":
                            crest = "https://upload.wikimedia.org/wikipedia/fr/1/1a/ClubDeRegatasVascoDaGama.svg";
                            break;
                        case "Fortaleza EC":
                            crest = "https://upload.wikimedia.org/wikipedia/commons/6/6e/Ecusson-fortaleza.png";
                            break;
                        case "CA Mineiro":
                            crest = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5f/Atletico_mineiro_galo.png/1200px-Atletico_mineiro_galo.png";
                            break;
                        case "Botafogo FR":
                            crest = "https://upload.wikimedia.org/wikipedia/commons/5/52/Botafogo_de_Futebol_e_Regatas_logo.svg";
                            break;
                        case "Ceará SC":
                            crest = "https://upload.wikimedia.org/wikipedia/commons/3/38/Cear%C3%A1_Sporting_Club_logo.svg";
                            break;
                        case "Fluminense FC":
                            crest = "https://upload.wikimedia.org/wikipedia/en/9/9e/Fluminense_fc_logo.svg";
                            break;
                        case "CS Alagoano":
                            crest = "http://www.leballonrond.fr/img/logos/equipas/3217_imgbank.png";
                            break;
                        case "Cruzeiro EC":
                            crest = "http://en.fodb.net/img/club/Brazil/100/Cruzeiro-EC.png";
                            break;
                        case "Chapecoense AF":
                            crest = "https://upload.wikimedia.org/wikipedia/fr/9/97/Associa%C3%A7%C3%A3o_Chapecoense_de_Futebol_%282016%29.png";
                            break;
                        case "Avaí FC":
                            crest = "https://upload.wikimedia.org/wikipedia/commons/f/fe/Avai_FC_%2805-E%29_-_SC.svg";
                    }

                    if (!crest.equals("") && activity.loadingPicsTeam) {
                        switch (crest.substring(crest.length() - 3)){
                            case "svg":
                                SvgLoader.pluck()
                                        .with(activity)
                                        .setPlaceHolder(R.drawable.ic_logo_foreground, R.drawable.ic_logo_foreground)
                                        .load(crest, activity.logo_club)
                                        .close();
                                break;
                            case "gif":
                            case "png":
                                Picasso.get()
                                        .load(crest)
                                        .error(R.drawable.ic_logo_foreground)
                                        .resize(50, 50)
                                        .centerCrop()
                                        .into(activity.logo_club);
                                break;
                        }
                    }

                    else activity.logo_club.setImageResource(R.drawable.ic_logo_foreground);

                } else {
                    Snackbar.make(activity.contextView, "Le nombre d'appels est dépassé", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Team> call, @NonNull Throwable t) {
                Snackbar.make(activity.contextView, "Vérifiez votre connexion Internet", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
