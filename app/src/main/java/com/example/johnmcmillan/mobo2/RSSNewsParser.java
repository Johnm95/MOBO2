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
import java.util.LinkedList;

/**
 * Created by johnmcmillan on 22/11/2016.
 */

public class RSSNewsParser {

        public LinkedList<NewsButtonClass> ParseStart(String dataToParse){

            URL url1 = null;
            try {
                url1 = new URL(dataToParse);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            Log.e("Checks", "Website " + url1);
            LinkedList<NewsButtonClass> alist = null;
            alist=parseData(url1);
            return alist;
        }
        /**

         */
        private LinkedList<NewsButtonClass> parseData(URL dataToParse)
        {
            NewsButtonClass Info=null;
            LinkedList<NewsButtonClass> alist = null;

            try
            {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(getInputStream(dataToParse), "UTF-8");

                boolean stared=false;
                int eventType = xpp.getEventType();
                alist  = new LinkedList<NewsButtonClass>();
                while (eventType != XmlPullParser.END_DOCUMENT)
                {

                    // Found a start tag
                    if(eventType == XmlPullParser.START_TAG)
                    {

                        // Check which Tag we have
                        if (xpp.getName().equalsIgnoreCase("item"))//channel
                        {
                            if(!stared){
                                stared=true;
                            }

                        }
                        else
                            // Check which Tag we have
                            if (xpp.getName().equalsIgnoreCase("title")&&stared)
                            {

                                Info = new NewsButtonClass();
                                //
                                String temp =xpp.nextText();
                                Info.setItemTitleNews(temp);
                                Log.e("ParserNews",""+temp);
                            }
                           else if (xpp.getName().equalsIgnoreCase("description")&&stared)
                            {
                                String temp =xpp.nextText();
                                Info.setItemDescNews(temp);
                                Log.e("ParserDes",""+temp);


                            }


                    }else
                    if(eventType == XmlPullParser.END_TAG)
                    {
                        if (xpp.getName().equalsIgnoreCase("item"))
                        {
                            //Log.e("MyTag","widget is " + widget.toString());
                            alist.add(Info);
                        }
                        else
                        if (xpp.getName().equalsIgnoreCase("channel"))
                        {
                            int size;
                            size = alist.size();
                            //Log.e("MyTag", "channel size is " + size);
                        }
                    }

                    // Get the next event
                    eventType = xpp.next();

                } // End of while
            }
            catch (XmlPullParserException ae1)
            {
                Log.e("MyTag", "Parsing error " + ae1.toString());
            }
            catch (IOException ae1)
            {
                Log.e("MyTag", "IO error during parsing");
            }
            return alist;

        }
        //open conection
        public InputStream getInputStream(URL url) {
            try {
                return url.openConnection().getInputStream();
            } catch (IOException e) {
                return null;
            }

        }





}
