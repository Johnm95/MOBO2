package com.example.johnmcmillan.mobo2;

import java.io.Serializable;

/**
 * Created by johnmcmillan on 13/12/2016.
 */

public class DatabaseInfo implements Serializable {

//This class gets and sets the weatherID, the weatherImages
// *********************************************
// Declare variables etc.
// *********************************************


    private String WeatherID;
    private String WeatherImg;




    private static final long serialVersionUID = 0L;
// *********************************************
// Declare getters and setters etc.
// *********************************************
    public String getWeatherID() {
        return WeatherID;
    }

    public void setWeatherID(String WeatherID) {
        this.WeatherID = WeatherID;
    }

    public String getWeatherImg() {
        return WeatherImg;
    }

    public void setWeatherImg(String WeatherImg) {
        this.WeatherImg = WeatherImg;
    }
}


