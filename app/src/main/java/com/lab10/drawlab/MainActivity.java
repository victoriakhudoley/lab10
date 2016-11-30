package com.lab10.drawlab;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Paint p;
    int mywidth = 0, myheight = 0;
    float xcoord, ycoord;
    CustomCanvas cCanvas;
    int k = 0;

    class CustomCanvas extends View {

        public CustomCanvas(Context context) {
            super(context);
            p = new Paint();
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            mywidth = w;
            myheight = h;
            super.onSizeChanged(w, h, oldw, oldh);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80, 102, 204, 255);
            int num = 0;
            int pieces = 8;
            int my = (myheight - 150) / pieces;
            int mx = mywidth / pieces;
            for (int i = 0; i < pieces; i++) {
                for (int j = 0; j < pieces; j++) {
                    if ((num & 1) == 1) {
                        p.setColor(Color.BLACK);
                    } else {
                        p.setColor(Color.WHITE);
                    }
                    Rect r = new Rect(j * mx, i * my, j * mx + mx, i * my + my);
                    canvas.drawRect(r, p);
                    num++;
                }
                num--;
            }
            p.setTextSize(40);
            p.setTextAlign(Paint.Align.CENTER);
            int mw = myheight - (myheight % 100) - 200;
            int mh = mywidth - (mywidth % 100);
            int sy = myheight - 100;
            int sx = mywidth - (mywidth / 2);
            if (xcoord == 0 && ycoord == 0) {
                String s = "Screen size " + mw + " " + mh;
                p.setColor(Color.BLACK);
                canvas.drawText(s, sx, sy, p);
            }
            if (xcoord > 0 && ycoord > 0) {
                invalidate(sx, sy, sx, sy);
                p.setColor(Color.BLACK);
                k++;
                String s = xcoord + " " + ycoord + " num of updates " + k;
                canvas.drawText(s, sx, sy, p);
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cCanvas = new CustomCanvas(this);
        setContentView(cCanvas);
        cCanvas.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                xcoord = (int) event.getX();
                ycoord = (int) event.getY();
                cCanvas.invalidate();
                return true;
            }
        });
    }
}
