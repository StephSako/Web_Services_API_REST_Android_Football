package com.example.footballapi.model.model_dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Classement.db";
    private static final int DATABASE_VERSION = 3;

    public DataBase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        String sqlCreate = "create table EQUIPES ("
                            + "idTeam integer primary key,"
                            + "idCompet integer not null,"
                            + "nomCompet text not null,"
                            + "position integer not null,"
                            + "nomTeam text not null,"
                            + "diff integer not null,"
                            + "points integer not null,"
                            + "crest text)";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String sqlUpdate = "drop table EQUIPES";
        db.execSQL(sqlUpdate);
        this.onCreate(db);
    }

    public void insertClassement(int idTeam, int idCompet, String nomCompet, int position, String nomTeam, int diff, int points, String crest){
        nomTeam = nomTeam.replace("'", "''");

        // Si l'équipe existe déjà, on met à jour, sinon on l'insère
        String sqlInsert = "insert or replace into EQUIPES (idTeam, idCompet, nomCompet, position, nomTeam, diff, points, crest) values ("
                            + idTeam + ", " + idCompet + ", '" + nomCompet + "', " + position + ", '" + nomTeam + "', " + diff + ", " + points + ", '" + crest + "')";
        this.getWritableDatabase().execSQL(sqlInsert);
    }

    // Méthode permettant de récupérer le classement d'une compétition dont l'id est passé en paramètre
    public List<TeamDAO> findClassementById(int idCompet){
        List<TeamDAO> classement = new ArrayList<>();
        String sqlSearchClassment = "select idTeam, position, nomTeam, diff, points, nomCompet, crest from EQUIPES where idCompet = '"
                                + idCompet + "' order by position";

        // Résultat du SELECT
        @SuppressLint("Recycle") Cursor cursor = this.getReadableDatabase().rawQuery(sqlSearchClassment, null);

        // On parcours le curseur
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            TeamDAO teamDAO = new TeamDAO(cursor.getInt(0),cursor.getInt(1),
                    cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5), cursor.getString(6));
            classement.add(teamDAO);
            cursor.moveToNext();
        }

        return classement;
    }

    // Méthode permettant de récupérer les équipes correspondates à ce qu'a saisi l'utilisateur dans la searchView
    public List<TeamDAO> findTeamByName(String nomClub){
        List<TeamDAO> classement = new ArrayList<>();
        String sqlSearchClassment = "SELECT idTeam, idCompet, nomTeam, crest FROM EQUIPES where nomTeam LIKE '%" + nomClub + "%' order by nomTeam";

        // Résultat du SELECT
        @SuppressLint("Recycle") Cursor cursor = this.getReadableDatabase().rawQuery(sqlSearchClassment, null);

        // On parcours le curseur
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            TeamDAO teamDAO = new TeamDAO(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3));
            classement.add(teamDAO);
            cursor.moveToNext();
        }

        return classement;
    }

    // Méthode permettant de récupérer toutes les équipes
    public List<TeamDAO> findAllTeams(){
        List<TeamDAO> teams = new ArrayList<>();
        String sqlSearchTeams = "select idTeam, idCompet, nomTeam, crest from EQUIPES order by nomTeam";

        // Résultat du SELECT
        @SuppressLint("Recycle") Cursor cursor = this.getReadableDatabase().rawQuery(sqlSearchTeams, null);

        // On parcours le curseur
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            TeamDAO teamDAO = new TeamDAO(cursor.getInt(0),cursor.getInt(1), cursor.getString(2), cursor.getString(3));
            teams.add(teamDAO);
            cursor.moveToNext();
        }

        return teams;
    }

    // Méthode permettant de récupérer le crest d'une équipe passée en paramètres
    public String findTeamCrest(int idTeam){
        String crest = "";
        String sqlSearchTeams = "select crest from EQUIPES where idTeam = " + idTeam +" ";

        // Résultat du SELECT
        @SuppressLint("Recycle") Cursor cursor = this.getReadableDatabase().rawQuery(sqlSearchTeams, null);

        // On parcours le curseur
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            crest = cursor.getString(0);
            cursor.moveToNext();
        }

        return crest;
    }
}
