package com.example.johnmcmillan.mobo2;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by johnmcmillan on 12/12/2016.
 */
//This class contains strings for the weather information for the map to display
public class WeatherActivity extends AppCompatActivity implements OnMapReadyCallback {
    WeatherButtonClass GlasWeather;
    WeatherButtonClass AberWeather;
    WeatherButtonClass EdinWeather;
    SharedPreferences sharedPref;
    private GoogleMap mMap;
    DatabaseInfo WeatherIcons;
    FragmentManager FmAbout;
    String ImgGlas,ImgEdin,ImgAber;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        android.support.v7.app.ActionBar ccActionBar=getSupportActionBar();
        if(ccActionBar !=null){
            ccActionBar.setDisplayShowHomeEnabled(true);
            ccActionBar.setDisplayHomeAsUpEnabled(true);
        }
        FmAbout=this.getFragmentManager();
        DataBaseMan WeatherDB=new DataBaseMan(this,"Weather.db",null,1);
        try {
            WeatherDB.dbCreate();
        } catch (IOException e) {
            e.printStackTrace();
        }

        GlasWeather = new WeatherButtonClass();
        AberWeather = new WeatherButtonClass();
        EdinWeather = new WeatherButtonClass();
        String GlasUrl    ="http://open.live.bbc.co.uk/weather/feeds/en/2648579/observations.rss";
        String AberUrl="http://open.live.bbc.co.uk/weather/feeds/en/2657832/observations.rss";
        String EdinUrl="http://open.live.bbc.co.uk/weather/feeds/en/2650225/observations.rss";

        WeatherAsyncRSSParser aysncGlasgow=new WeatherAsyncRSSParser(this,GlasUrl);
        WeatherAsyncRSSParser aysncAber=new WeatherAsyncRSSParser(this,AberUrl);
        WeatherAsyncRSSParser aysncEdin=new WeatherAsyncRSSParser(this,EdinUrl);
        try {
            GlasWeather=aysncGlasgow.execute("").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            AberWeather=aysncAber.execute("").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        try {
            EdinWeather=aysncEdin.execute("").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        WeatherIcons=WeatherDB.FindWeatherIcon(GlasWeather.GetWeatherType());
        if(WeatherIcons==null){
            Log.e("DataBAse","Failed");
        }
        ImgGlas =WeatherIcons.getWeatherImg();

        WeatherIcons=WeatherDB.FindWeatherIcon(EdinWeather.GetWeatherType());
        if(WeatherIcons==null){
            Log.e("DataBAse","Failed");
        }
        ImgEdin= WeatherIcons.getWeatherImg();
        WeatherIcons=WeatherDB.FindWeatherIcon(AberWeather.GetWeatherType());
        if(WeatherIcons==null){
            Log.e("DataBAse","Failed");
        }
        ImgAber= WeatherIcons.getWeatherImg();

        sharedPref= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
SetUpMap();

    }
    public void SetUpMap(){
        MapFragment WeatherMap=(MapFragment)getFragmentManager().findFragmentById(R.id.map);
        WeatherMap.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;
        switch (sharedPref.getInt("mc_TYPE",1)) {
            case 1:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case 2:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
            case 3:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            default:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        }


        LatLng aberdeen = new LatLng(57.149621, -2.094313);

mMap.addMarker(new MarkerOptions().position(aberdeen).title("Aberdeen"))
        .setIcon(BitmapDescriptorFactory.fromResource(getResources().getIdentifier(ImgAber,"drawable",getPackageName())));



        LatLng edinburgh = new LatLng(55.953390, -3.188334);

        mMap.addMarker(new MarkerOptions().position(edinburgh).title("Edinburgh"))
                .setIcon(BitmapDescriptorFactory.fromResource(getResources().getIdentifier(ImgEdin,"drawable",getPackageName())));

        LatLng glasgow = new LatLng(55.863004, -4.251360);
        mMap.addMarker(new MarkerOptions().position(glasgow).title("Glasgow"))
                .setIcon(BitmapDescriptorFactory.fromResource(getResources().getIdentifier(ImgGlas,"drawable",getPackageName())));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
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
            case R.id.Graph:
                startActivity(new Intent(this,DrawActivity.class));
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
