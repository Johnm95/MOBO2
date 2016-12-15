package com.example.johnmcmillan.mobo2;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by johnmcmillan on 13/12/2016.
 */

public class DrawView extends SurfaceView implements SurfaceHolder.Callback
{
    private SurfaceHolder shBioSurface;


    DrawThread drawingThread = null;




    public DrawView(Context context)
    {
        super(context);
        shBioSurface = getHolder();
        shBioSurface.addCallback(this);
        drawingThread = new DrawThread(getHolder(), this);
        setFocusable(true);


    }


    public DrawThread getThread()
    {
        return drawingThread;
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {


        drawingThread.setRunning(true);
        drawingThread.start();
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {
        drawingThread.setSurfaceSize(width,height);


    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        boolean retry = true;
        drawingThread.setRunning(false);
        while(retry)
        {
            try {
                drawingThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}





