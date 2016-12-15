package com.example.johnmcmillan.mobo2;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.net.MalformedURLException;

/**
 * Created by johnmcmillan on 22/11/2016.
 */




public class WeatherAsyncRSSParser extends AsyncTask<String,Integer,WeatherButtonClass>{

    private Context weatherAppContext;
    private String weatherUrlRSSToParse;

    public WeatherAsyncRSSParser(Context weatherCurrentAppContext, String weatherUrlRSS)

    {
        weatherAppContext = weatherCurrentAppContext;
        weatherUrlRSSToParse = weatherUrlRSS;
    }

    //@Override
    protected void onPreExecute()
    {
        Toast.makeText(weatherAppContext,"Parsing started!", Toast.LENGTH_SHORT).show();
    }

    //@Override
    protected WeatherButtonClass doInBackground(String...params)
    {
        WeatherButtonClass weatherParsedData;
        RSSWeatherParser weatherRssParser = new RSSWeatherParser();
        try {
            weatherRssParser.parseRSSDataWeather(weatherUrlRSSToParse);
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }

        weatherParsedData = weatherRssParser.getWeatherDataItem();

        return weatherParsedData;
    }

    //@Override
    protected void onPostExecute(WeatherButtonClass weatherResult)
    {
        Toast.makeText(weatherAppContext,"Parsing finished!", Toast.LENGTH_SHORT).show();
    }
}
