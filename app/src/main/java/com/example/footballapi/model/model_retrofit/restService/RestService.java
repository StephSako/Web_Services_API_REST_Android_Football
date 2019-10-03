package com.example.footballapi.model.model_retrofit.restService;

import com.example.footballapi.model.model_retrofit.competition.Classement;
import com.example.footballapi.model.model_retrofit.player.Player;
import com.example.footballapi.model.model_retrofit.team.OneMatch;
import com.example.footballapi.model.model_retrofit.team.Team;
import com.example.footballapi.model.model_retrofit.always_data.Supporter;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Liste des différents appels REST
 */

public interface RestService {

    /** V1 **/

    // Afficher un joueur en particulier
    @GET("players/{id}/")
    Call<Player> players(@Header("X-Auth-Token") String token, @Path("id") int id);

    // Afficher la liste des joueurs d'une equipe
    @GET("teams/{id}/")
    Call<Team> teamSquad(@Header("X-Auth-Token") String token, @Path("id") int id);

    // Afficher les détails d'une equipe
    @GET("teams/{id}/")
    Call<Team> teamsDetails(@Header("X-Auth-Token") String token, @Path("id") int id);

    // Afficher la classement d'une competition
    @GET("competitions/{id}/standings")
    Call<Classement> competitions(@Header("X-Auth-Token") String token, @Path("id") int id);

    // Afficher les matches d'une équipe
    @GET("teams/{id}/matches")
    Call<Team> matchesTeam(@Header("X-Auth-Token") String token, @Path("id") int id);



    /** V2 **/

    // Afficher les matches d'une compétition
    @GET("competitions/{id}/matches")
    Call<Classement> matchesCompetition(@Header("X-Auth-Token") String token, @Path("id") int id);

    // Afficher un match en particulier
    @GET("matches/{id}")
    Call<OneMatch> match(@Header("X-Auth-Token") String token, @Path("id") int id);

    @FormUrlEncoded
    @POST("supporter/sign_up.php")
    Call<Supporter> inscription(@Field("pseudo") String pseudo, @Field("password") String mdpasse, @Field("favoriteTeam") int favoriteTeam);

    @FormUrlEncoded
    @POST("user/sign_in.php")
    Call<Supporter> connexion(@Field("pseudo") String pseudo, @Field("password") String password);

}