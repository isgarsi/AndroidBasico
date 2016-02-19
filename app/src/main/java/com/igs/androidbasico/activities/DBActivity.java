package com.igs.androidbasico.activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.igs.androidbasico.R;
import com.igs.androidbasico.helpers.BDSQLiteHelper;

/**
 * Created by isgarsi  on 09/07/2015.
 */
public class DBActivity extends AppCompatActivity {

    private TextView txtPersona1;
    private TextView txtPersona2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        BDSQLiteHelper sqliteHelper = new BDSQLiteHelper(this, "MiBD", null, 1);

        //Abrimos la base de datos en modo escritura
        SQLiteDatabase db = sqliteHelper.getWritableDatabase();

        //Si no ocurrio ningun error abriendo la bd
        if (db != null) {
            //Insertamos 2 personas para probar
            for (int i = 1; i < 3; i++) {
                db.execSQL("INSERT INTO Persona (id, nombre, apellidos) " +
                        "VALUES (" + i + ", 'Nombre " + i + "', 'Apellido " + i + "')");
            }
        }


        txtPersona1 = (TextView) findViewById(R.id.nombre_persona1);
        txtPersona2 = (TextView) findViewById(R.id.nombre_persona2);

        //Realizamos select
        Cursor c = db.rawQuery(" SELECT nombre, apellidos FROM Persona", null);
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            int numPersona = 1;
            do {
                String nombre = c.getString(0);
                String apellidos = c.getString(1);
                if (numPersona == 1) {
                    txtPersona1.setText(nombre + " " + apellidos);
                } else {
                    txtPersona2.setText(nombre + " " + apellidos);
                }

                numPersona++;
            } while (c.moveToNext());
        }

        //Cerramos la base de datos
        db.close();
    }
}
