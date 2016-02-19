package com.igs.androidbasico.helpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.igs.androidbasico.classes.Persona;

import java.util.ArrayList;

/**
 * Created by isgarsi on 09/07/2015.
 */
public class BDSQLiteHelper extends SQLiteOpenHelper {


    public BDSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Persona (id INTEGER, nombre VARCHAR(50), apellidos VARCHAR(100))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int lastVersion, int newVersion) {

        //Creamos una lista para recuperar las Personas
        ArrayList<Persona> personas = new ArrayList<Persona>();

        //Recupero todos los registros para no perder los datos
        Cursor c = db.rawQuery(" SELECT id, nombre, apellidos FROM Persona", null);
        if (c.moveToFirst()) {
            do {
                Persona p = new Persona(c.getInt(0), c.getString(1), c.getString(2));
                personas.add(p);
            } while(c.moveToNext());
        }

        //Se elimina la tabla
        db.execSQL("DROP TABLE IF EXISTS Persona");

        //Se crea la nueva versión
        db.execSQL("CREATE TABLE Persona (id INTEGER, nombre VARCHAR(50), apellidos VARCHAR(100), email VARCHAR(30))");

        //Inserto los datos recuperados
        for(Persona p : personas){
            db.execSQL("INSERT INTO persona (id, nombre, apellidos) " +
                    "VALUES (" + p.getId() + ", '" + p.getNombre() + "', '" + p.getApellidos() + "')");
        }
    }
}
