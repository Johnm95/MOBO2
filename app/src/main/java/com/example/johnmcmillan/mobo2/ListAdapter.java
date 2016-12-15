package com.example.johnmcmillan.mobo2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;



/**
 * Created by johnmcmillan on 12/12/2016.
 */

public class ListAdapter extends BaseAdapter {
    private List<NewsButtonClass> item;
    private LayoutInflater inflater;
    public ListAdapter(Context context, List<NewsButtonClass> item){
        inflater=LayoutInflater.from(context);
        this.item=item;
    }
    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        TextView textView1;
        TextView textView2;
        LinearLayout lLayout;
        ListView Lview;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            holder= new ViewHolder();
            convertView=inflater.inflate(R.layout.list_news_layout,null);
            holder.textView1=(TextView) convertView.findViewById(R.id.NewsTitle);
            holder.textView2=(TextView) convertView.findViewById(R.id.NewsDes);
            holder.Lview=(ListView) convertView.findViewById(R.id.NewsListAct);
            holder.lLayout=(LinearLayout) convertView.findViewById(R.id.newsList_layout);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder) convertView.getTag();
        }
        holder.textView1.setText(item.get(position).getItemTitleNews());
        holder.textView2.setText(item.get(position).getItemDescNews());
        return convertView;
    }
}
