package com.unidad04.parcialunidad4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        BaseDeDatos.execSQL("create table MD_Clientes(" +
                "ID_Cliente int primary key," +
                "sNombreCliente text," +
                "sApellidosCliente text," +
                "sDireccionCliente text," +
                "sCiudadCliente text)");

        BaseDeDatos.execSQL("create table MD_Vehiculos(" +
                "ID_Vehiculo int primary key," +
                "sMarca text," +
                "sModelo text)");

        BaseDeDatos.execSQL("create table MD_ClienteVehiculo(" +
                "ID_Cliente int primary key," +
                "ID_Vehiculo int," +
                "sMatricula text," +
                "iKilometros text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
