package com.example.johnmcmillan.mobo2;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

/**
 * Created by johnmcmillan on 12/12/2016.
 */

public class NewsActivity extends AppCompatActivity {
    LinkedList<NewsButtonClass> NewsInfo;
    private ListView RssNewsListView;
    FragmentManager FmAbout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);


        android.support.v7.app.ActionBar ccActionBar=getSupportActionBar();
        if(ccActionBar !=null){
            ccActionBar.setDisplayShowHomeEnabled(true);
            ccActionBar.setDisplayHomeAsUpEnabled(true);
        }
        FmAbout=this.getFragmentManager();
        String NewsRSSFeedURL = "http://feeds.bbci.co.uk/news/rss.xml?edition=uk";
        NewsAsyncRSSParser newsRSSAsyncParser = new NewsAsyncRSSParser(this,NewsRSSFeedURL);
        try {
            NewsInfo = newsRSSAsyncParser.execute("").get();
        }
        catch (InterruptedException e) {
          e.printStackTrace();
        }
        catch(ExecutionException e)
        {
          e.printStackTrace();
        }
        RssNewsListView=(ListView) findViewById(R.id.NewsListAct);
        RssNewsListView.setAdapter(new ListAdapter(this,NewsInfo));
         //newsTextView = (TextView) findViewById(R.id.newsTextView1);
        //newsTextView.setText(userInfo.getItemDescNews());

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case  android.R.id.home:
                onBackPressed();
                return true;
            case R.id.About:
                DialogFragment AboutDlg= new AboutDialogue();
                AboutDlg.show(FmAbout,"menu");
                return true;
            // Lab 7 code goes here
            case R.id.Settings: // Lab 7
                startActivity(new Intent(this,SettingsActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
