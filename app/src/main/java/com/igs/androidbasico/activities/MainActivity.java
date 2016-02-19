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
            default:
                break;
        }

        if(intent != null){
            startActivity(intent);
        }
    }
}
