package com.example.footballapi.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Classement.db";
    private static final int DATABASE_VERSION = 1;

    public DataBase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        String sqlCreate = "create table EQUIPES ("
                            + "idTeam integer primary key,"
                            + "idCompet integer not null,"
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

    public void insertClassement(int idTeam, int idCompet, int position, String nomTeam, int diff, int points){
        nomTeam = nomTeam.replace("'", "''");

        // Si l'équipe existe déjà, on met à jour, sinon on l'insère
        String sqlInsert = "insert or replace into EQUIPES (idTeam, idCompet, nomTeam, diff, points) values ("
                            + idTeam + ", " + idCompet + ", '" + position + ", " + nomTeam + "', " + diff + ", " + points + ")";
        this.getWritableDatabase().execSQL(sqlInsert);
    }
}
