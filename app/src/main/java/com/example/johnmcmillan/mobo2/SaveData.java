package com.example.johnmcmillan.mobo2;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by johnmcmillan on 13/12/2016.
 */

public class SaveData extends Activity {


    // *********************************************
    // Declare variables etc.
    // *********************************************
    SharedPreferences SharedPrefs;




    private int Maptype;






    // *********************************************
    // Declare getters and setters etc.
    // *********************************************


    private void setMaptype(int Maptype)
    {
        this.Maptype = Maptype;
    }


    public int getMaptype()
    {
        return Maptype;
    }






// **************************************************
// Declare constructor.
// **************************************************


    public SaveData(SharedPreferences SDPrefs){
        setMaptype(1);



        try {
            this.SharedPrefs = SDPrefs;
        }
        catch (Exception e)
        {
            Log.e("n","Pref Manager is NULL" );
        }
        setDefaultPrefs();
    }


    public void savePreferences(String key, boolean value) {
        SharedPreferences.Editor editor = SharedPrefs.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }


    public void savePreferences(String key, String value) {
        SharedPreferences.Editor editor = SharedPrefs.edit();
        editor.putString(key, value);
        editor.commit();
    }


    public void savePreferences(String key, int value) {
        SharedPreferences.Editor editor = SharedPrefs.edit();
        editor.putInt(key, value);
        editor.commit();
    }


    public void setDefaultPrefs(){
        savePreferences("mc_TYPE", 1);
    }




}







