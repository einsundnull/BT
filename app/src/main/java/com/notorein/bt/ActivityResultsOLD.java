package com.notorein.bt;

import static android.content.ContentValues.TAG;
import static com.notorein.bt.SessionParameters.resultLineColorIndex;
import static com.notorein.bt.SessionParameters.showDayInResults;
import static com.notorein.bt.SessionParameters.stringToStore;
import static com.notorein.bt.SessionParameters.stringToStoreInitial;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.drawable.DrawableCompat;

import java.util.ArrayList;

public class ActivityResultsOLD extends AppCompatActivity implements View.OnClickListener {

    TextView[] datapointTrialPerc;
    TextView[] divider;
    // These two parameters can probably be replaced by SessionParameters.displayHeight and SessionParameters.displayWidth
    double displayHeight;
    double displayWidth;
    private double maxNBack;

    private double distanceX;
    private double distanceY;
    private double distanceYDevider;

    private final int dotSize = 9;
    private float strokeWidth = 7;
    private final int dividerHeight = 1;

    int dividerColor = Color.DKGRAY;

    private ConstraintLayout activity_results_layout;
    private boolean portraitMode;
    // This int sets the scale lines. If it is 3 and maxNBack is 5 it will give 3+5 = 8 scale lines.
    private final double linesMoreThanNBack = 4;
    ArrayList<ArrayList<Double[]>> resultLines;
    Bitmap bg;
    Canvas canvas;
    int lineColorTrial, lineColorSession, lineColorDay, backgroundColor;
    private ImageView setting_button_result_screen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_layout);
        activity_results_layout = findViewById(R.id.activity_results_layout);
        setting_button_result_screen = findViewById(R.id.setting_button_result_screen);
        setting_button_result_screen.setBackgroundResource(R.drawable.result_screen_settings_button_image);
        // This part was in onClick ResultsBtn in ActivityMain
//        stringToStoreInitial = ResultsFiles.readResults(ActivityResultsOLD.this);
//        ResultsFiles.calculateResultsForDisplay();
        stringToStore = stringToStoreInitial + stringToStore;
        if (resultLineColorIndex == 0) {
            resultLineColorIndex = 4;
        }
        getDisplaySize();
        setOnClickListener();
        setDivider();
        addResultLines();
    }


    public static float dpFromPx(final Context context, final float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    private void getDisplaySize() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        displayWidth = size.x;
        displayHeight = size.y;
    }

    private void getOrientation() {
        int orientation = this.getResources().getConfiguration().orientation;
        portraitMode = orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    private void setOnClickListener() {
        activity_results_layout.setOnClickListener(this);
        setting_button_result_screen.setOnClickListener(this);

    }

    void setDivider() {
        double tempWidht = 0;
        double tempHeight = 0;
        maxNBack = ResultsFiles.nBackMaxAbsolute + linesMoreThanNBack;
        if (portraitMode) {
            tempWidht = displayHeight;
            displayHeight = displayWidth;
            displayWidth = tempWidht;
        }
        distanceY = displayHeight / maxNBack;
        distanceYDevider = displayHeight / maxNBack;
        divider = new TextView[(int) maxNBack];
        Log.i(TAG, "divider.length " + divider.length);

        for (int i = 1; i < maxNBack; i++) {
            TextView temp = new TextView(this);
            TextView number = new TextView(this);
            number.setTextColor(getResources().getColor(R.color.buttonTextColor));
            number.setText("" + (i));
            divider[i] = temp;
            divider[i].setBackgroundColor(dividerColor);
            divider[i].setWidth((int) pxFromDp(this, (float) displayWidth));
            divider[i].setHeight((int) pxFromDp(this, dividerHeight));
            number.setWidth((int) pxFromDp(this, (float) 16));
            number.setHeight((int) pxFromDp(this, (float) 16));
            number.setY((float) (displayHeight - (distanceY * (i + 1))));
            number.setX(dpFromPx(this, 15));
            divider[i].setY((float) (displayHeight - (distanceY * (i + 1))));
            divider[i].setX((float) 0);

            activity_results_layout.addView(number);
            activity_results_layout.addView(divider[i]);
        }
    }

    ArrayList<Double> setPoints(ArrayList<Double> y, int color) {
        int size = y.size();
        double tempWidht = 0;
        double tempHeight = 0;
        if (portraitMode) {
            tempWidht = displayHeight;
            displayHeight = displayWidth;
            displayWidth = tempWidht;
        }
        ArrayList<Double> layoutX = new ArrayList<>();
        double distanceX = displayWidth / (size + 2);
        TextView[] datapointTrialPerc = new TextView[size];
        Log.i(TAG, "datapointTrialPerc.length: " + datapointTrialPerc.length);
        Drawable drawable = getResources().getDrawable(R.drawable.data_point);
        DrawableCompat.setTint(drawable, color);

        for (int i = 0; i < size; i++) {
            TextView temp = new TextView(this);
            temp.setBackground(drawable);
            datapointTrialPerc[i] = temp;
            datapointTrialPerc[i].setY((float) (((displayHeight - (distanceY * y.get(i))) - (strokeWidth+dotSize + dividerHeight) / 2) - 1 * distanceY));
            if (i > 0) {
                datapointTrialPerc[i].setX((float) distanceX * (i + 1));
                layoutX.add(distanceX * (i + 1));
            } else {
                datapointTrialPerc[i].setX((float) distanceX);
                layoutX.add(distanceX);
            }
//            datapointTrialPerc[i].setBackgroundColor(color);
            datapointTrialPerc[i].setWidth((int) pxFromDp(this, dotSize));
            datapointTrialPerc[i].setHeight((int) pxFromDp(this, dotSize));

            activity_results_layout.addView(datapointTrialPerc[i]);
        }
        return layoutX;
    }


    ArrayList<Double[]> setLinesCoordinates(ArrayList<Double> y, ArrayList<Double> x, int color) {
        int size = y.size();
        double startX = 0;
        double startY = 0;
        double endX = 0;
        double endY = 0;
//        Paint paint = new Paint();
//        paint.setColor(color);
//        Bitmap bg = Bitmap.createBitmap((int) displayWidth, (int) displayHeight, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bg);
        ArrayList<Double[]> lineCoordinates = new ArrayList<Double[]>();
        for (int i = 0; i < size - 1; i++) {
            lineCoordinates.add(new Double[]{0d, 10d, 0d, 10d, 0d});

            if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                startX = dotSize + y.get(i);
                endX = dotSize + y.get(i + 1);
//                startY = dotSize + displayHeight - ((x.get(i)) * distanceY) - 1 * distanceY;
//                endY = dotSize + displayHeight - ((x.get(i + 1)) * distanceY) - 1 * distanceY;
                startY = ((displayHeight - (x.get(i)) * distanceY) - 1 * distanceY) / dpFromPx(this, 1.733f);
                endY = ((displayHeight - (x.get(i + 1)) * distanceY) - 1 * distanceY) / dpFromPx(this, 1.733f);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
            } else {
                startX = dotSize + y.get(i);
                endX = dotSize + y.get(i + 1);
                startY = ((displayHeight - (x.get(i)) * distanceY) - 1 * distanceY) / dpFromPx(this, 1.65f);
                endY = ((displayHeight - (x.get(i + 1)) * distanceY) - 1 * distanceY) / dpFromPx(this, 1.65f);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
            }

            lineCoordinates.get(i)[0] = startX;
            lineCoordinates.get(i)[1] = startY;
            lineCoordinates.get(i)[2] = endX;
            lineCoordinates.get(i)[3] = endY;
            lineCoordinates.get(i)[4] = (double) color;

//            canvas.drawLine((float) startX, (float) startY, (float) endX, (float) endY, paint);
        }
        Log.i(TAG, "setLinesCoordinates:  Coordiantes Set");
//        Bitmap bg = Bitmap.createBitmap((int) displayWidth, (int) displayHeight, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bg);
//        activitiy_results_layout.setBackground(new BitmapDrawable(bg));
        return lineCoordinates;
    }

    private void drawResultLines(ArrayList<ArrayList<Double[]>> resultLines) {
//        Bitmap bg = Bitmap.createBitmap((int) displayWidth, (int) displayHeight, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bg);
//        canvas.drawRect(100, 110, (int) displayWidth, (int) displayHeight, new Paint(backgroundColor));
        Paint paint = new Paint();
        paint.setColor(backgroundColor);
        canvas.drawRect(0, 0, (int) displayWidth, (int) displayHeight, paint);

        for (int i = 0; i < resultLines.size(); i++) {
            for (int r = 0; r < resultLines.get(i).size(); r++) {
                double startX = resultLines.get(i).get(r)[0];
                double startY = resultLines.get(i).get(r)[1];
                double endX = resultLines.get(i).get(r)[2];
                double endY = resultLines.get(i).get(r)[3];
                double color = resultLines.get(i).get(r)[4];
                int col = (int) color;
                paint = new Paint();
                paint.setStrokeWidth(dpFromPx(this, strokeWidth));
                paint.setColor(col);
                canvas.drawLine((float) startX, (float) startY, (float) endX, (float) endY, paint);
            }
        }


    }

    private void addResultLines() {
        setLineColors();
//        if(resultLines != null){
//            resultLines.clear();
//        }
        resultLines = new ArrayList<ArrayList<Double[]>>();

//        if (SessionParameters.showPercentageInResults) {
//            if (showTrialInResults) {
//                resultLines.add(setLinesCoordinates(setPoints(ResultsFiles.listTrialsPercentage, lineColorTrial), ResultsFiles.listTrialsPercentage, lineColorTrial));
//            }
//            if (showSessionInResults) {
//                resultLines.add(setLinesCoordinates(setPoints(ResultsFiles.listSessionsNBackMax, lineColorSession), ResultsFiles.listSessionsNBackMax, lineColorSession));
//            }
//            if (showDayInResults) {
//                resultLines.add(setLinesCoordinates(setPoints(ResultsFiles.listDayAveragePercentage, lineColorDay), ResultsFiles.listDayAveragePercentage, lineColorDay));
//            }
//        }
//        if (SessionParameters.showNBackInResults) {
//            if (showTrialInResults) {
//                resultLines.add(setLinesCoordinates(setPoints(ResultsFiles.listTrialsNBack, lineColorTrial), ResultsFiles.listTrialsNBack, lineColorTrial));
//            }
//            if (showSessionInResults) {
//                resultLines.add(setLinesCoordinates(setPoints(ResultsFiles.listSessionsNBackMax, lineColorSession), ResultsFiles.listSessionsNBackMax, lineColorSession));
//            }
//            if (showDayInResults) {
//                resultLines.add(setLinesCoordinates(setPoints(ResultsFiles.listDaysNBackMax, lineColorDay), ResultsFiles.listDaysNBackMax, lineColorDay));
//            }
//        }
        drawResultLines(resultLines);
        activity_results_layout.removeView(setting_button_result_screen);
        activity_results_layout.addView(setting_button_result_screen);
        activity_results_layout.setBackground(new BitmapDrawable(bg));
    }


    @Override
    public void onClick(View v) {

//        if (v.getId() == R.id.activity_results_layout) {
//            resultLineColorIndex++;
//            activity_results_layout.removeAllViews();
//            setDivider();
//            addResultLines();
//        }
        if (v.getId() == R.id.setting_button_result_screen) {
//            ViewMenu menu = new ViewMenu(this);
//            menu.show();
            createSettingsDialog();
        }

    }

    private void setLineColors() {

        bg = Bitmap.createBitmap((int) displayWidth, (int) displayHeight, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bg);
        lineColorDay = Color.rgb(44,135,32);
        lineColorSession = Color.BLUE;
        lineColorTrial = Color.RED;
        backgroundColor = Color.rgb(232, 236, 235);
//        if (resultLineColorIndex == 1) {
//            lineColorDay = Color.rgb(44,135,32);
//            lineColorSession = Color.TRANSPARENT;
//            lineColorTrial = Color.TRANSPARENT;
//        }
//        if (resultLineColorIndex == 2) {
//            lineColorDay = Color.TRANSPARENT;
//            lineColorSession = Color.BLUE;
//            lineColorTrial = Color.TRANSPARENT;
//        }
//        if (resultLineColorIndex == 3) {
//            lineColorDay = Color.TRANSPARENT;
//            lineColorSession = Color.TRANSPARENT;
//            lineColorTrial = Color.RED;
//        }
//        if (resultLineColorIndex == 4) {
//            lineColorDay = Color.rgb(44,135,32);
//            lineColorSession = Color.BLUE;
//            lineColorTrial = Color.RED;
//            resultLineColorIndex = 0;
//        }
    }

//    public CheckBox one;
//    public CheckBox two;
//    public CheckBox three;
//    public CheckBox four;
//    public CheckBox five;

    private void createSettingsDialog() {

        Dialog dialog = new Dialog(this);
        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();

        wmlp.gravity = Gravity.TOP | Gravity.LEFT;
        wmlp.x = 000;   //x position
        wmlp.y = (int)dpFromPx(this,100);   //y position
        wmlp.alpha = 0.78f;
        ConstraintLayout layout = dialog.findViewById(R.id.view_results);
        dialog.setContentView(R.layout.view_results_settings_layout);

//        layout.setAlpha(0.3f);
//        dialog.setOwnerActivity(this);
        CheckBox one;
        CheckBox two;
        CheckBox three;
        CheckBox four;
        CheckBox five;
        one = dialog.findViewById(R.id.showPercentage);
        two = dialog.findViewById(R.id.showNBack);
        three = dialog.findViewById(R.id.showDay);
        four = dialog.findViewById(R.id.showSession);
        five = dialog.findViewById(R.id.showTrial);

        one.setText(Strings.showPercentageInResults);
        two.setText(Strings.showNBackInResults);
        three.setText(Strings.showDayInResults);
        four.setText(Strings.showSessionInResults);
        five.setText(Strings.showTrialInResults);

        one.setTextColor(getResources().getColor(R.color.black));
        two.setTextColor(getResources().getColor(R.color.black));
        three.setTextColor(getResources().getColor(R.color.black));
        four.setTextColor(getResources().getColor(R.color.black));
        five.setTextColor(getResources().getColor(R.color.black));


        one.setOnClickListener(c -> {
            SessionParameters.showPercentageInResults = one.isChecked();
            activity_results_layout.removeAllViews();
            setDivider();
            addResultLines();
        });
        two.setOnClickListener(c -> {
            SessionParameters.showNBackInResults = two.isChecked();
            activity_results_layout.removeAllViews();
            setDivider();
            addResultLines();
        });
        three.setOnClickListener(c -> {
            showDayInResults = three.isChecked();
            activity_results_layout.removeAllViews();
            setDivider();
            addResultLines();
        });
        four.setOnClickListener(c -> {
            SessionParameters.showSessionInResults = four.isChecked();
            activity_results_layout.removeAllViews();
            setDivider();
            addResultLines();
        });
        five.setOnClickListener(c -> {
            SessionParameters.showTrialInResults = five.isChecked();
            activity_results_layout.removeAllViews();
            setDivider();
            addResultLines();
        });

        one.setChecked(SessionParameters.showPercentageInResults);
        two.setChecked(SessionParameters.showNBackInResults);
        three.setChecked(SessionParameters.showDayInResults);
        four.setChecked(SessionParameters.showSessionInResults);
        five.setChecked(SessionParameters.showTrialInResults);
        dialog.show();
    }


}