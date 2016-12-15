package com.example.johnmcmillan.mobo2;

import android.renderscript.ScriptGroup;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by johnmcmillan on 22/11/2016.
 */

public class RSSWeatherParser {

    private WeatherButtonClass weatherDataItem;

    public void setWeatherDataItem(String sItemDataWeather)
    {
        weatherDataItem.setItemTitleWeather(sItemDataWeather);
        weatherDataItem.setItemDescWeather(sItemDataWeather);
        weatherDataItem.setItemLinkWeather(sItemDataWeather);

    }

    public WeatherButtonClass getWeatherDataItem()
    {
        return this.weatherDataItem;
    }

    public RSSWeatherParser()
    {
        this.weatherDataItem = new WeatherButtonClass();
        setWeatherDataItem(null);
    }

    public void parseweatherDataItem(XmlPullParser theWeatherParser, int theWeatherEventType)
    {
        try
        {
            while (theWeatherEventType != XmlPullParser.END_DOCUMENT)
            {
                if(theWeatherEventType == XmlPullParser.START_TAG)
                {
                    if (theWeatherParser.getName().equalsIgnoreCase("title"))
                    {
                        String temp = theWeatherParser.nextText();
                        weatherDataItem.setItemTitleWeather(temp);
                        Log.e("WeatherPArser",temp);
                    }
                    else
                    if (theWeatherParser.getName().equalsIgnoreCase("link"))
                    {
                        String temp = theWeatherParser.nextText();
                        weatherDataItem.setItemLinkWeather(temp);
                    }
                    else
                    if (theWeatherParser.getName().equalsIgnoreCase("description"))
                    {
                        String temp = theWeatherParser.nextText();
                        weatherDataItem.setItemDescWeather(temp);
                    }
                }

                theWeatherEventType = theWeatherParser.next();
            }
        }
        catch (XmlPullParserException weatherParserExp1)
        {
            Log.e("MyTag","Parsing error" + weatherParserExp1.toString());
        }
        catch (IOException weatherParserExp1)
        {
            Log.e("MyTag","IO error during parsing");
        }
    }

    public void parseRSSDataWeather(String RSSWeatherItemsToParse) throws MalformedURLException
    {
        URL rssWeatherURL = new URL(RSSWeatherItemsToParse);
        InputStream rssInputWeatherStream;
        try
        {
            XmlPullParserFactory weatherParseRSSFactory = XmlPullParserFactory.newInstance();
            weatherParseRSSFactory.setNamespaceAware(true);
            XmlPullParser RSSxlmPP = weatherParseRSSFactory.newPullParser();
            String weatherxmlRSS = getStringFromInputStream(getWeatherInputStream(rssWeatherURL), "UTF-8");
            RSSxlmPP.setInput(new StringReader(weatherxmlRSS));
            int weatherEventType = RSSxlmPP.getEventType();
            parseweatherDataItem(RSSxlmPP,weatherEventType);

        }
        catch (XmlPullParserException ael)
        {
            Log.e("MyTag", "Parsing error" + ael.toString());

        }
        catch (IOException ael)
        {
            Log.e("MyTag","IO error during parsing");
        }

        Log.e("MyTag","End Document");
    }

    public InputStream getWeatherInputStream(URL rssWeatherURL) throws IOException
    {
        return rssWeatherURL.openConnection().getInputStream();
    }

    public static String getStringFromInputStream(InputStream weatherStream, String weatherCharsetName) throws IOException
    {
        int weatherN = 0;
        char[] buffer3 = new char[1024*4];
        InputStreamReader weatherReader = new InputStreamReader(weatherStream,weatherCharsetName);
        StringWriter weatherWriter = new StringWriter();
        while (-1 != (weatherN = weatherReader.read(buffer3))) weatherWriter.write(buffer3, 0, weatherN);
        return weatherWriter.toString();
    }
}
