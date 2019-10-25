package com.example.footballapi.services.retrofit.always_data;

import com.example.footballapi.model.model_retrofit.supporter.ListBet;
import com.example.footballapi.model.model_retrofit.supporter.Supporter;
import com.example.footballapi.model.model_retrofit.supporter.PourcentBet;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Liste des différents appels REST
 */

public interface RestService {

    /** V2 **/

    @FormUrlEncoded
    @POST("supporter/sign_up.php")
    Call<Supporter> inscription(@Field("pseudo") String pseudo, @Field("password") String password, @Field("favoriteTeam") int favoriteTeam, @Field("favoriteTeamName") String favoriteTeamName);

    @FormUrlEncoded
    @POST("supporter/sign_in.php")
    Call<Supporter> connexion(@Field("pseudo") String pseudo, @Field("password") String password);

    @FormUrlEncoded
    @POST("bet/bet.php")
    Call<ListBet> bet(@Field("idMatch") int idMatch, @Field("idSupporter") int idSupporter, @Field("idWinner") int idWinner);

    @FormUrlEncoded
    @POST("bet/pourcent.php")
    Call<PourcentBet> pourcent(@Field("idMatch") int idMatch, @Field("idHome") int idHome, @Field("idAway") int idAway);

}