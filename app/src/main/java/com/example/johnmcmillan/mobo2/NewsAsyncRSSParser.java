package com.example.johnmcmillan.mobo2;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.util.LinkedList;

/**
 * Created by johnmcmillan on 22/11/2016.
 */




public class NewsAsyncRSSParser extends AsyncTask<String, Integer, LinkedList<NewsButtonClass>> {

    private Context newsAppContext;
    private String newsUrlRSSToParse;

    public LinkedList<NewsButtonClass> parselist =null;
    TextView newsTextView;
    TextView newsTextView1;
    public NewsAsyncRSSParser(Context newsCurrentAppContext, String newsUrlRSS)

    {
        newsAppContext = newsCurrentAppContext;
        newsUrlRSSToParse = newsUrlRSS;
    }
//Shows that parsing has started
    //@Override
    protected void onPreExecute()
    {
        Toast.makeText(newsAppContext,"Parsing started!", Toast.LENGTH_SHORT).show();
    }
//Parses the news feed in the background
    //@Override
    protected LinkedList<NewsButtonClass> doInBackground(String...params)
    {
        NewsButtonClass newsParsedData;
        RSSNewsParser newsRssParser = new RSSNewsParser();

            parselist= newsRssParser.ParseStart(newsUrlRSSToParse);

        return parselist;
    }
//Shows that the parsing has finished
    //@Override
    protected void newsOnPostExecute(NewsButtonClass newsResult)
    {
        Toast.makeText(newsAppContext,"Parsing finished!", Toast.LENGTH_SHORT).show();
    }


}
