package com.example.alumnospr.marketapp;

import android.provider.BaseColumns;

public class EstructuraBD {

    private EstructuraBD() {}

    public static final String TABLE_NAME = "pedidos";
    public static final String COLUMNA1= "id";
    public static final String COLUMNA2= "nombre";
    public static final String COLUMNA3= "descripcion";
    public static final String COLUMNA4= "cantidad";
    public static final String COLUMNA5= "precio";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + EstructuraBD.TABLE_NAME + " (" +
                    EstructuraBD.COLUMNA1 + " INTEGER PRIMARY KEY," +
                    EstructuraBD.COLUMNA2 + " TEXT," +
                    EstructuraBD.COLUMNA3 + " TEXT," +
                    EstructuraBD.COLUMNA4 + " TEXT," +
                    EstructuraBD.COLUMNA5 + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + EstructuraBD.TABLE_NAME;
}
