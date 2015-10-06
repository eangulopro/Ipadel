package com.example.enriqueangulomendoza.ipadel;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by enriqueangulomendoza on 3/10/15.
 */
public class DataBaseSQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Ipadel.db";
    private static final int DATABASE_VERSION = 1;


    public DataBaseSQLiteHelper (Context context){

        super (context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db){

        //Creación de la tabla PAISES
        db.execSQL(DataBaseScript.CREATE_PAISES_SCRIPT);
        //Creación de la tabla COMUNIDADES
        db.execSQL(DataBaseScript.CREATE_COMUNIDADES_SCRIPT);
        //Creación de la tabla PROVINCIAS
        db.execSQL(DataBaseScript.CREATE_PROVINCIAS_SCRIPT);
        //Creación de la tabla POBLACIONES
        db.execSQL(DataBaseScript.CREATE_POBLACIONES_SCRIPT);
        //Creación de la tabla CONTACTOS
        db.execSQL(DataBaseScript.CREATE_CONTACTOS_SCRIPT);
        //Creación de la tabla CLUBS
        db.execSQL(DataBaseScript.CREATE_CLUBS_SCRIPT);
        //Creación de la tabla PISTAS
        db.execSQL(DataBaseScript.CREATE_PISTAS_SCRIPT);

        //Creación de la tabla CLUBS_AUX
        db.execSQL(DataBaseScript.CREATE_CLUBS_AUX_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva){

    }

}

