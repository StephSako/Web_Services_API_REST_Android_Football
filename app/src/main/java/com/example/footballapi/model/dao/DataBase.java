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
        String sqlCreate = "create table CLASSEMENTS ("
                            + "idCompet integer primary key,"
                            + "nomCompet text not null,"
                            + "diff integer not null,"
                            + "points integer not null)";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String sqlUpdate = "drop table CLASSEMENTS";
        db.execSQL(sqlUpdate);
        this.onCreate(db);
    }

    public void insertClassement(){

    }
}
