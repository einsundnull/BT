package com.notorein.bt;

import static com.notorein.bt.SessionParameters.displayWidth;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class ColorMenu {

    int size = 32;
    int tileSize = 5;
    int xLimit = 5;
    int yLevel = 1;
    private ArrayList list;
    private Runnable run;
    private Context c;
    private int[] colors;
    private View[] views;

    public int[] getColors() {
        return colors;
    }

    public void setColors(int[] colors) {
        this.colors = colors;
    }


    public void alignViewsInChessboardStyle(Context c, ConstraintLayout layout , int width) {
        this.c = c;
//        getDisplayMetrics();
        if(width == 0){
            tileSize = displayWidth / xLimit;
        } else {
            tileSize = width / xLimit;
        }
        tileSize = (int) (tileSize * 0.95f);
        yLevel = 1;
        list = new ArrayList();
        int tempXLimit = 0;
        int childCount = layout.getChildCount();
        views = new View[childCount];
        for (int v = 0; v < childCount; v++) {
            views[v] = layout.getChildAt(v);
        }
        try {
            layout.removeAllViews();
        } catch (Exception e){

        }

        for (int i = 0; i < colors.length; i++) {
            TextView btn = new TextView(c);
            btn.setX((int) (tileSize * tempXLimit + (tileSize * 0.05)));
            btn.setY(tileSize * yLevel);
            btn.setWidth(tileSize);
            btn.setHeight(tileSize);
            btn.setBackgroundColor(colors[i]);
            btn.setGravity(Gravity.CENTER);
            btn.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            btn.setId(i);
            btn.setOnClickListener(d -> {
                if (run != null) {
                    run.run();
                }
            });
            tempXLimit++;
            try {
                layout.removeView(btn);
            } catch (Exception e) {

            }


            layout.addView(btn);
            if (i == xLimit * yLevel - 1) {
                yLevel++;
                tempXLimit = 0;
            }
        }
        for (int v = 0; v < childCount; v++) {
            layout.addView(views[v]);
        }
    }

    public void setClickLogic(Runnable run) {
        this.run = run;
    }
//    private void getDisplayMetrics() {
//        DisplayMetrics displaymetrics = new DisplayMetrics();
//       c. getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
//       c. displayHeight = displaymetrics.heightPixels;
//        displayWidth = displaymetrics.widthPixels;
//    }

    public static float dpFromPx(final Context context, final float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

}
