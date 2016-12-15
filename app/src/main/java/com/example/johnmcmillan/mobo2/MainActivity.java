package com.example.johnmcmillan.mobo2;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    Button newsButton, weatherButton;
    SaveData SDPref;
    SharedPreferences mySharPref;


FragmentManager FmAbout;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Create action bar
        android.support.v7.app.ActionBar ccActionBar=getSupportActionBar();
        if(ccActionBar !=null){
            ccActionBar.setDisplayShowHomeEnabled(true);
        }
        newsButton = (Button) findViewById(R.id.newsButton);
        newsButton.setOnClickListener(this);

        weatherButton = (Button) findViewById(R.id.weatherButton);
        weatherButton.setOnClickListener(this);


        mySharPref = PreferenceManager.getDefaultSharedPreferences(this);
        SDPref = new SaveData(mySharPref);
        SDPref.setDefaultPrefs();;
        FmAbout=this.getFragmentManager();

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






    @Override
    public void onClick(View view) {

        if (view==newsButton)
        {
            Log.e("ButtonTest","News");
            startActivity(new Intent(this,NewsActivity.class));
        }

        if (view==weatherButton)
        {
            startActivity(new Intent(this,WeatherActivity.class));
        }


    }


    /**
     * Created by johnmcmillan on 22/11/2016.
     */




}

