package com.example.johnmcmillan.mobo2;

import android.util.Log;

/**
 * Created by johnmcmillan on 21/11/2016.
 */

//This class gets and sets the title and description for the weather items within the news feed.
public class WeatherButtonClass {

    private String itemTitleWeather;
    private String itemDescWeather;
    private String itemLinkWeather;

    public String getItemTitleWeather()
    {
        return this.itemTitleWeather;
    }

    public void setItemTitleWeather(String sItemTitleWeather)
    {
        this.itemTitleWeather = sItemTitleWeather;
    }

    public String getItemDescWeather()
    {
        return this.itemDescWeather;
    }

    public void setItemDescWeather(String sItemDescWeather)
    {
        this.itemDescWeather = sItemDescWeather;
    }

    public String getItemLinkWeather()
    {
        return this.itemLinkWeather;
    }

    public void setItemLinkWeather(String sItemLinkWeather)
    {
        this.itemLinkWeather = sItemLinkWeather;
    }
    public String GetWeatherType(){
        String temp=this.itemTitleWeather;
        temp = temp.substring(temp.indexOf(": ") + 1);
        temp = temp.substring(1, temp.indexOf(","));
        Log.e("SubSting",temp);
        return temp;
    }
    public String GetWeathertemp(){
        String temp=this.itemTitleWeather;
        temp = temp.substring(temp.indexOf(",") + 1);
        temp = temp.substring(1, temp.indexOf("°C"));
        Log.e("SubSting",temp);
        return temp;
    }

    //Constructor

    public WeatherButtonClass()
    {
        this.itemTitleWeather = "";
        this.itemDescWeather = "";
        this.itemLinkWeather = "";
    }

    @Override
    public String toString()
    {
        String weatherRSSData;
        weatherRSSData = "WeatherButtonClass [itemTitleWeather=" + itemTitleWeather;
        weatherRSSData = ", itemDescWeather=" + itemDescWeather;
        weatherRSSData = ",itemLinkWeather=" + itemLinkWeather +"]";
        return weatherRSSData;

    }


}
