package com.example.johnmcmillan.mobo2;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

/**
 * Created by johnmcmillan on 13/12/2016.
 */

public class SettingsActivity extends AppCompatActivity {
    SaveData SDPref;
    SharedPreferences MySharedPred;
    FragmentManager FmAbout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);
        android.support.v7.app.ActionBar ccActionBar=getSupportActionBar();
        if(ccActionBar !=null){
            ccActionBar.setDisplayShowHomeEnabled(true);
            ccActionBar.setDisplayHomeAsUpEnabled(true);
        }
        MySharedPred = PreferenceManager.getDefaultSharedPreferences(this);
        SDPref = new SaveData(MySharedPred);
        SDPref.setDefaultPrefs();

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
    public void SaveRadio(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();


        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.RBnor:
                if (checked)
                    SDPref.savePreferences("mc_TYPE", 1);
                break;
            case R.id.RBter:
                if (checked)
                    SDPref.savePreferences("mc_TYPE", 2);
                break;
            case R.id.RBsat:
                if (checked)
                    SDPref.savePreferences("mc_TYPE", 3);
                break;
            case R.id.RBHybrid:
                if (checked)
                    SDPref.savePreferences("mc_TYPE", 4);
                break;
        }
    }
}
