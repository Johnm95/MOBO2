package com.example.johnmcmillan.mobo2;

import android.content.Context;
import android.widget.Toast;

import java.net.MalformedURLException;

/**
 * Created by johnmcmillan on 22/11/2016.
 */




public class TrafficAsyncRSSParser {

    private Context trafficAppContext;
    private String trafficUrlRSSToParse;

    public TrafficAsyncRSSParser(Context trafficCurrentAppContext, String trafficUrlRSS)

    {
        trafficAppContext = trafficCurrentAppContext;
        trafficUrlRSSToParse = trafficUrlRSS;
    }

    //@Override
    protected void trafficOnPreExecute()
    {
        Toast.makeText(trafficAppContext,"Parsing started!", Toast.LENGTH_SHORT).show();
    }

    //@Override
    protected TrafficButtonClass doInBackground(String...params)
    {
        TrafficButtonClass trafficParsedData;
        RSSTrafficParser trafficRssParser = new RSSTrafficParser();
        try {
            trafficRssParser.parseRSSDataTraffic(trafficUrlRSSToParse);
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }

        trafficParsedData = trafficRssParser.getTrafficDataItem();

        return trafficParsedData;
    }

    //@Override
    protected void trafficOnPostExecute(TrafficButtonClass trafficResult)
    {
        Toast.makeText(trafficAppContext,"Parsing finished!", Toast.LENGTH_SHORT).show();
    }
}
