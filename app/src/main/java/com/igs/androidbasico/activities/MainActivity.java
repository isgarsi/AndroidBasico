package com.igs.androidbasico.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.igs.androidbasico.R;
import com.igs.androidbasico.fragments.MainActivityFragment;

public class MainActivity extends AppCompatActivity implements MainActivityFragment.OnMainFragmentInteractionListener{

    private static final int ANIMATIONS = 0;
    private static final int FILES = 1;
    private static final int DB = 2;
    private static final int MATERIAL_DESIGN = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    @Override
    public void onItemSelected(int option) {
        Intent intent = null;

        switch (option){
            case ANIMATIONS:
                intent = new Intent(this,AnimationsActivity.class);
                break;
            case FILES:
                intent = new Intent(this,FilesActivity.class);
                break;
            case DB:
                intent = new Intent(this,DBActivity.class);
                break;
            case MATERIAL_DESIGN:
                intent = new Intent(this,FirstTestMD.class);
                break;
            default:
                break;
        }

        if(intent != null){
            startActivity(intent);
        }
    }
}
