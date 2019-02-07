package com.example.footballapi.model.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Classement.db";
    private static final int DATABASE_VERSION = 2;

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
                            + "points integer not null)";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String sqlUpdate = "drop table EQUIPES";
        db.execSQL(sqlUpdate);
        this.onCreate(db);
    }

    public void insertClassement(int idTeam, int idCompet, String nomCompet, int position, String nomTeam, int diff, int points){
        nomTeam = nomTeam.replace("'", "''");

        // Si l'équipe existe déjà, on met à jour, sinon on l'insère
        String sqlInsert = "insert or replace into EQUIPES (idTeam, idCompet, nomCompet, position, nomTeam, diff, points) values ("
                            + idTeam + ", " + idCompet + ", '" + nomCompet + "', " + position + ", '" + nomTeam + "', " + diff + ", " + points + ")";
        this.getWritableDatabase().execSQL(sqlInsert);
    }

    // Méthode permettant de récupérer le classement d'une compétition dont l'id est passé en paramètre
    public List<TeamDAO> findClassementById(int idCompet){
        List<TeamDAO> classement = new ArrayList<>();
        String sqlSearchClassment = "select idTeam, position, nomTeam, diff, points, nomCompet from EQUIPES where idCompet = '"
                                + idCompet + "' order by position";

        // Résultat du SELECT
        @SuppressLint("Recycle") Cursor cursor = this.getReadableDatabase().rawQuery(sqlSearchClassment, null);

        // On parcours le curseur
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            TeamDAO teamDAO = new TeamDAO(cursor.getInt(0),cursor.getInt(1),
                    cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5));
            classement.add(teamDAO);
            cursor.moveToNext();
        }

        return classement;
    }
}
