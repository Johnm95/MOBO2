package com.example.johnmcmillan.mobo2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by johnmcmillan on 13/12/2016.
 */
//This class essentially contains the code for the wave and the axis'
public class DrawThread extends Thread
{
    private int canvasWidth;
    private int canvasHeight;
    private float xPos = 0.0f;
    private float yPos = 0.0f;
    private int i;





    private float HalfAppletHeight;
    private float HalfAppletWidth;


    private int aCalcDays;
    private int cCalcDays;
    private int dDate;


    private boolean first = true;
    private boolean run = false;


    private SurfaceHolder shBioSurface;
    private Paint paintBio;
    private DrawView bioSF;


    public DrawThread(SurfaceHolder surfaceHolder, DrawView bioSurfV) {
        this.shBioSurface = surfaceHolder;
        this.bioSF = bioSurfV;
        paintBio = new Paint();

    }


    public void doStart() {
        synchronized (shBioSurface) {
            int aDays = 1;
            int aMonth = 1;
            int aYear = 1990;

            dDate = cCalcDays - aCalcDays;
            first = false;


        }
    }


    public void run() {
        while (run) {
            Canvas c = null;
            try {
                c = shBioSurface.lockCanvas(null);
                synchronized (shBioSurface) {
                    svDraw(c);
                }
            } finally {
                if (c != null) {
                    shBioSurface.unlockCanvasAndPost(c);
                }
            }
        }
    }


    public void setRunning(boolean b) {
        run = b;
    }
    public void setSurfaceSize(int width, int height) {
        synchronized (shBioSurface) {
            canvasWidth = width;
            canvasHeight = height;
            HalfAppletHeight = canvasHeight / 2;
            HalfAppletWidth  = canvasWidth / 32;
            doStart();
        }
    }




    private void svDraw(Canvas canvas) {
        if(run) {
            canvas.save();
            canvas.restore();
            canvas.drawColor(Color.WHITE);
            paintBio.setStyle(Paint.Style.FILL);
            drawAxes(canvas);


            paintBio.setColor(Color.GREEN);
            paintBio.setStrokeWidth(20);
            drawWave(canvas, 28);


        }
    }




    public void drawWave(Canvas theCanvas, int period)
    {
        float xPosOld = 0.0f;
        float yPosOld = 0.0f;
        int sDate = 0;
        int tDate = 0;


        sDate = 6;


        for (i=0;i<=30;i++)
        {
            xPos = i * HalfAppletWidth;


            tDate = sDate + i;
            yPos = (float)(500.0f * (Math.sin((tDate%period)*2*Math.PI/period)));


            if ( i == 0)
                paintBio.setStyle(Paint.Style.FILL);
            else
                theCanvas.drawLine(xPosOld, (yPosOld + HalfAppletHeight), xPos, (yPos + HalfAppletHeight), paintBio);
            xPosOld = xPos;
            yPosOld = yPos;
            if(i==25)
            {

            }
        }
    }


    public void drawAxes(Canvas theCanvas)
    {

        paintBio.setColor(Color.BLACK);
        theCanvas.drawLine(0,1000,30*HalfAppletWidth,1000, paintBio); // Horizontal X Axes
        theCanvas.drawLine(15* HalfAppletWidth,0,15* HalfAppletWidth,canvasHeight, paintBio);// Vertical Y Axes
        //theCanvas.drawText(sunRise,30*HalfAppletWidth, 15* HalfAppletWidth, paintBio);
    }
}






