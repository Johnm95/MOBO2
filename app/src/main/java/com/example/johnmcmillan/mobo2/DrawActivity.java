package com.example.johnmcmillan.mobo2;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by johnmcmillan on 13/12/2016.
 */

public class DrawActivity  extends AppCompatActivity
{


    FragmentManager FmAbout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw); // app main UI screen

        android.support.v7.app.ActionBar ccActionBar=getSupportActionBar();
        if(ccActionBar !=null){
            ccActionBar.setDisplayShowHomeEnabled(true);
            ccActionBar.setDisplayHomeAsUpEnabled(true);
        }

        FmAbout=this.getFragmentManager();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(new DrawView(this));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case  android.R.id.home:
                onBackPressed();
                return true;
            case R.id.About:
                DialogFragment AboutDlg= new AboutDialogue();
                AboutDlg.show(FmAbout,"menu");
                return true;
            // Lab 7 code goes here
            case R.id.Settings: // Lab 7
                startActivity(new Intent(this,SettingsActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
