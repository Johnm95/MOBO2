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

public class RSSTrafficParser {

    private TrafficButtonClass trafficDataItem;

    public void setTrafficDataItem(String sItemDataTraffic)
    {
        trafficDataItem.setItemTitleTraffic(sItemDataTraffic);
        trafficDataItem.setItemDescTraffic(sItemDataTraffic);
        trafficDataItem.setItemLinkTraffic(sItemDataTraffic);

    }

    public TrafficButtonClass getTrafficDataItem()
    {
        return this.trafficDataItem;
    }

    public RSSTrafficParser()
    {
        this.trafficDataItem = new TrafficButtonClass();
        setTrafficDataItem(null);
    }

    public void parsetrafficDataItem(XmlPullParser theTrafficParser, int theTrafficEventType)
    {
        try
        {
            while (theTrafficEventType != XmlPullParser.END_DOCUMENT)
            {
                if(theTrafficEventType == XmlPullParser.START_TAG)
                {
                    if (theTrafficParser.getName().equalsIgnoreCase("title"))
                    {
                        String temp = theTrafficParser.nextText();
                        trafficDataItem.setItemTitleTraffic(temp);
                    }
                    else
                    if (theTrafficParser.getName().equalsIgnoreCase("description"))
                    {
                        String temp = theTrafficParser.nextText();
                        trafficDataItem.setItemDescTraffic(temp);
                    }
                    else
                    if (theTrafficParser.getName().equalsIgnoreCase("link"))
                    {
                        String temp = theTrafficParser.nextText();
                        trafficDataItem.setItemLinkTraffic(temp);
                    }
                }

                theTrafficEventType = theTrafficParser.next();
            }
        }
        catch (XmlPullParserException trafficParserExp1)
        {
            Log.e("MyTag","Parsing error" + trafficParserExp1.toString());
        }
        catch (IOException trafficParserExp1)
        {
            Log.e("MyTag","IO error during parsing");
        }
    }

    public void parseRSSDataTraffic(String RSSTrafficItemsToParse) throws MalformedURLException
    {
        URL rssTrafficURL = new URL(RSSTrafficItemsToParse);
        InputStream rssInputTrafficStream;
        try
        {
            XmlPullParserFactory trafficParseRSSFactory = XmlPullParserFactory.newInstance();
            trafficParseRSSFactory.setNamespaceAware(true);
            XmlPullParser RSSxlmPP = trafficParseRSSFactory.newPullParser();
            String trafficxmlRSS = getStringFromInputStream(getTrafficInputStream(rssTrafficURL), "UTF-8");
            RSSxlmPP.setInput(new StringReader(trafficxmlRSS));
            int trafficEventType = RSSxlmPP.getEventType();
            parsetrafficDataItem(RSSxlmPP,trafficEventType);

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

    public InputStream getTrafficInputStream(URL rssTrafficURL) throws IOException
    {
        return rssTrafficURL.openConnection().getInputStream();
    }

    public static String getStringFromInputStream(InputStream trafficStream, String trafficCharsetName) throws IOException
    {
        int trafficN = 0;
        char[] buffer2 = new char[1024*4];
        InputStreamReader trafficReader = new InputStreamReader(trafficStream,trafficCharsetName);
        StringWriter trafficWriter = new StringWriter();
        while (-1 != (trafficN = trafficReader.read(buffer2))) trafficWriter.write(buffer2, 0, trafficN);
        return trafficWriter.toString();
    }
}
