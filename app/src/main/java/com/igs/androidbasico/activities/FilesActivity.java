package com.igs.androidbasico.activities;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.igs.androidbasico.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by isgarsi on 19/2/16.
 */
public class FilesActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String INTERNAL_FILENAME = "pruebaI.txt";
    private static final String EXTERNAL_FILENAME = "pruebaE.txt";


    private EditText text;
    private Button butFileResources;
    private Button butSaveInternal;
    private Button butReadInternal;
    private Button butSaveSD;
    private Button butReadSD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);

        text = (EditText) findViewById(R.id.text);
        butFileResources = (Button) findViewById(R.id.btn_app_storage);
        butSaveInternal = (Button) findViewById(R.id.btn_save_internal_storage);
        butReadInternal = (Button) findViewById(R.id.btn_read_internal_storage);
        butSaveSD = (Button) findViewById(R.id.btn_save_sd);
        butReadSD = (Button) findViewById(R.id.btn_read_sd);

        butFileResources.setOnClickListener(this);
        butSaveInternal.setOnClickListener(this);
        butReadInternal.setOnClickListener(this);
        butSaveSD.setOnClickListener(this);
        butReadSD.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_app_storage:
                InputStream is = getResources().openRawResource(R.raw.holamundo);
                BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
                try {
                    String textRead = buffer.readLine();
                    Toast.makeText(this, textRead, Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(this, "Error leyendo fichero raw", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_read_internal_storage:
                File rFile = new File(getFilesDir(), INTERNAL_FILENAME);
                try {
                    BufferedReader bReader = new BufferedReader(new FileReader(rFile));
                    String textRead = bReader.readLine();
                    Toast.makeText(this, textRead, Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(this, "Error leyendo fichero de memoria interna", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_save_internal_storage:
                File wFile = new File(getFilesDir(), INTERNAL_FILENAME);
                try {
                    FileWriter out = new FileWriter(wFile);
                    out.write(text.getText().toString());
                    out.close();
                } catch (IOException e) {
                    Toast.makeText(this, "Error escribiendo fichero en memoria interna", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_read_sd:
                if (isExternalStorageReadable()) {
                    File rFileE = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), EXTERNAL_FILENAME);
//                    File rFileE = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), EXTERNAL_FILENAME);
                    try {
                        BufferedReader bReader = new BufferedReader(new FileReader(rFileE));
                        String textRead = bReader.readLine();
                        Toast.makeText(this, textRead, Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        Toast.makeText(this, "Error leyendo fichero de memoria externa", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btn_save_sd:
                if (isExternalStorageWritable()) {
                    File wFileE = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), EXTERNAL_FILENAME);
//                    File wFileE = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), EXTERNAL_FILENAME);
                    try {
                        FileWriter out = new FileWriter(wFileE);
                        out.write(text.getText().toString());
                        out.close();
                    } catch (IOException e) {
                        Toast.makeText(this, "Error escribiendo fichero en memoria externa", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}