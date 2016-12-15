package com.example.johnmcmillan.mobo2;

/**
 * Created by johnmcmillan on 21/11/2016.
 */

public class TrafficButtonClass {

    private String itemTitleTraffic;
    private String itemDescTraffic;
    private String itemLinkTraffic;

    public String getItemTitleTraffic()
    {
        return this.itemTitleTraffic;
    }

    public void setItemTitleTraffic(String sItemTitleTraffic)
    {
        this.itemTitleTraffic = sItemTitleTraffic;
    }

    public String getItemDescTraffic()
    {
        return this.itemDescTraffic;
    }

    public void setItemDescTraffic(String sItemDescTraffic)
    {
        this.itemDescTraffic = sItemDescTraffic;
    }

    public String getItemLinkTraffic()
    {
        return this.itemLinkTraffic;
    }

    public void setItemLinkTraffic(String sItemLinkTraffic)
    {
        this.itemLinkTraffic = sItemLinkTraffic;
    }

    //Constructor

    public TrafficButtonClass()
    {
        this.itemTitleTraffic = "";
        this.itemDescTraffic = "";
        this.itemLinkTraffic = "";
    }

    @Override
    public String toString()
    {
        String trafficRSSData;
        trafficRSSData = "TrafficButtonClass [itemTitleTraffic=" + itemTitleTraffic;
        trafficRSSData = ", itemDescTraffic=" + itemDescTraffic;
        trafficRSSData = ",itemLinkTraffic=" + itemLinkTraffic +"]";
        return trafficRSSData;

    }


}
