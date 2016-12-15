package com.example.johnmcmillan.mobo2;

import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by johnmcmillan on 21/11/2016.
 */
//This class gets and sets the title and description for the news items within the news feed.
public class NewsButtonClass implements Serializable{

    private String itemTitleNews;
    private String itemDescNews;
    private String itemLinkNews;



    public String getItemTitleNews()
    {
        return this.itemTitleNews;
    }

    public void setItemTitleNews(String sItemTitleNews)
    {
        this.itemTitleNews = sItemTitleNews;
    }

    public String getItemDescNews()
    {
        return this.itemDescNews;
    }

    public void setItemDescNews(String sItemDescNews)
    {
        this.itemDescNews = sItemDescNews;
    }

    public String getItemLinkNews()
    {
        return this.itemLinkNews;
    }

    public void setItemLinkNews(String sItemLinkNews)
    {
        this.itemLinkNews = sItemLinkNews;
    }

    //Constructor

    public NewsButtonClass()
    {
        this.itemTitleNews = "";
        this.itemDescNews = "";
        this.itemLinkNews = "";
    }

    @Override
    public String toString()
    {
        String newsRSSData;
        newsRSSData = "NewsButtonClass [itemTitleNews=" + itemTitleNews;
        newsRSSData = ", itemDescNews=" + itemDescNews;
        newsRSSData = ",itemLinkNews=" + itemLinkNews +"]";
        return newsRSSData;

    }


}
