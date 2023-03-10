package com.notorein.bt;

import static android.content.ContentValues.TAG;
import static com.notorein.bt.SessionParameters.darkModeMenu;
import static com.notorein.bt.SessionParameters.loadInterstitialAd;
import static com.notorein.bt.SessionParameters.resultLineColorIndex;
import static com.notorein.bt.SessionParameters.showDayInResults;
import static com.notorein.bt.SessionParameters.showDayInResultsPercentage;
import static com.notorein.bt.SessionParameters.showSessionInResults;
import static com.notorein.bt.SessionParameters.showSessionInResultsPercentage;
import static com.notorein.bt.SessionParameters.showTrialInResults;
import static com.notorein.bt.SessionParameters.showTrialInResultsPercentage;
import static com.notorein.bt.SessionParameters.stringToStore;
import static com.notorein.bt.SessionParameters.stringToStoreInitial;
import static com.notorein.bt.SessionParameters.useTempResults;
import static com.notorein.bt.Strings.showDaysInResultsText;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.drawable.DrawableCompat;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class ActivityResults extends AppCompatActivity implements View.OnClickListener {

    TextView[] datapointTrialPerc;
    TextView[] divider;
    // These two parameters can probably be replaced by SessionParameters.displayHeight and SessionParameters.displayWidth
    double displayHeight;
    double displayWidth;

    //    private double distanceX;
    private double distanceY;

    private final int dotSize = 9;
    private float strokeWidth = 7;
    private final int dividerHeight = 1;

    int dividerColor = Color.DKGRAY;

    private ConstraintLayout layout;
    private boolean portraitMode;
    ArrayList<ArrayList<Double[]>> resultLines;
    Bitmap bg;
    Canvas canvas;
    int lineColorTrial, lineColorSession, lineColorDay, backgroundColor;
    private ImageView setting_button_result_screen;
    private InterstitialAd mInterstitialAd;
    private TextView goodJob;
    private AdView mAdView;
    private AdRequest adRequest;
    private Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_layout);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
            }
        });

        if (!showDayInResultsPercentage && !showSessionInResultsPercentage && !showTrialInResultsPercentage && !showDayInResults
                && !showSessionInResults && !showTrialInResults) {
            showTrialInResultsPercentage = true;
            showSessionInResultsPercentage = true;
            showDayInResultsPercentage = true;
        }

        mAdView = findViewById(R.id.adView);
        adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        getViews();
        setOnClickListener();
        SessionParameters.returnFromResultScreen = true;
        FileLogicSettings.saveSettings(this);
        goodJob.setText(Strings.goodJob);
        // This part was in onClick ResultsBtn in ActivityMain
        SessionParameters.resultsFilePath = ResultsFiles.initialiseStoringFilePaths(useTempResults);
        stringToStoreInitial = ResultsFiles.readResults(ActivityResults.this);
        ResultsFiles.calculateResultsForDisplay();
        stringToStore = stringToStoreInitial + stringToStore;
        if (resultLineColorIndex == 0) {
            resultLineColorIndex = 4;
        }

        getDisplaySize();
        setDivider();
        addResultLines();
        if (darkModeMenu) {
//            setting_button_result_screen.setBackgroundColor(getResources().getColor(R.color.white));
            setting_button_result_screen.setBackground(getResources().getDrawable(R.drawable.result_screen_settings_button_image_dark));
            setting_button_result_screen.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
        } else {
            setting_button_result_screen.setBackground(getResources().getDrawable(R.drawable.result_screen_settings_button_image));
//            setting_button_result_screen.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));

        }

    }


    private void loadInterstitialAd(AdRequest adRequest) {
        if (SessionParameters.loadInterstitialAd) {
            InterstitialAd.load(this, "ca-app-pub-3940256099942544/1033173712", adRequest,
                    new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                            // The mInterstitialAd reference will be null until
                            // an ad is loaded.
                            mInterstitialAd = interstitialAd;

                            Log.i(TAG, "onAdLoaded");
                            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                @Override
                                public void onAdClicked() {
                                    // Called when a click is recorded for an ad.
                                    Log.d(TAG, "Ad was clicked.");
                                }

                                @Override
                                public void onAdDismissedFullScreenContent() {
                                    // Called when ad is dismissed.
                                    // Set the ad reference to null so you don't show the ad a second time.
                                    Log.d(TAG, "Ad dismissed fullscreen content.");
                                    mInterstitialAd = null;
                                }

                                @Override
                                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                    // Called when ad fails to show.
                                    Log.e(TAG, "Ad failed to show fullscreen content.");
                                    mInterstitialAd = null;
                                }

                                @Override
                                public void onAdImpression() {
                                    // Called when an impression is recorded for an ad.
                                    Log.d(TAG, "Ad recorded an impression.");
                                }

                                @Override
                                public void onAdShowedFullScreenContent() {
                                    // Called when ad is shown.
                                    Log.d(TAG, "Ad showed fullscreen content.");
                                }
                            });
                            if (mInterstitialAd != null) {
                                mInterstitialAd.show(ActivityResults.this);
                            } else {
                                Log.d("TAG", "The interstitial ad wasn't ready yet.");
                                for (int i = 0; i < 100; i++) {
                                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                                }
                            }
                        }


                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            // Handle the error
                            Log.d(TAG, loadAdError.toString());
                            mInterstitialAd = null;
                        }


                    });


        }
        loadInterstitialAd = false;
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


    void setDivider() {
        double tempWidth;
        double tempHeight = 0;
        // This int sets the scale lines. If it is 3 and maxNBack is 5 it will give 3+5 = 8 scale lines.
        double linesMoreThanNBack = 4;
        double maxNBack = ResultsFiles.nBackMaxAbsolute + linesMoreThanNBack;
        if (portraitMode) {
            tempWidth = displayHeight;
            displayHeight = displayWidth;
            displayWidth = tempWidth;
        }
        distanceY = displayHeight / maxNBack;
        double distanceYDivider = displayHeight / maxNBack;
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

            layout.addView(number);
            layout.addView(divider[i]);
        }
    }

    ArrayList<Double> setPoints(ArrayList<Double> y, int color) {
        int size = y.size();
        double tempWidth;
        double tempHeight = 0;
        if (portraitMode) {
            tempWidth = displayHeight;
            displayHeight = displayWidth;
            displayWidth = tempWidth;
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
            datapointTrialPerc[i].setY((float) (((displayHeight - (distanceY * y.get(i))) - (strokeWidth + dotSize + dividerHeight) / 2) - 1 * distanceY));
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

            layout.addView(datapointTrialPerc[i]);
        }
        return layoutX;
    }


    ArrayList<Double[]> setLinesCoordinates(ArrayList<Double> y, ArrayList<Double> x, int color) {
        int size = y.size();
        double startX;
        double startY;
        double endX;
        double endY;
//        Paint paint = new Paint();
//        paint.setColor(color);
//        Bitmap bg = Bitmap.createBitmap((int) displayWidth, (int) displayHeight, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bg);
        ArrayList<Double[]> lineCoordinates = new ArrayList<>();
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
        Log.i(TAG, "setLinesCoordinates:  Coordinates Set");
//        Bitmap bg = Bitmap.createBitmap((int) displayWidth, (int) displayHeight, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bg);
//        activity_results_layout.setBackground(new BitmapDrawable(bg));
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

    private final void addResultLines() {
        setLineColors();
        if (resultLines != null) {
            resultLines.clear();
        }
        resultLines = new ArrayList<>();

//        if (SessionParameters.showPercentageInResults) {
        if (showTrialInResultsPercentage) {
            resultLines.add(setLinesCoordinates(setPoints(ResultsFiles.trialsPerc, lineColorTrial), ResultsFiles.trialsPerc, lineColorTrial));
        }
        if (showSessionInResultsPercentage) {
            resultLines.add(setLinesCoordinates(setPoints(ResultsFiles.sessionsPercAverage, lineColorSession), ResultsFiles.sessionsPercAverage, lineColorSession));
        }
        if (showDayInResultsPercentage) {
            resultLines.add(setLinesCoordinates(setPoints(ResultsFiles.daysPercAverage, lineColorDay), ResultsFiles.daysPercAverage, lineColorDay));
        }
//        }
//        if (SessionParameters.showNBackInResults) {
        if (showTrialInResults) {
            resultLines.add(setLinesCoordinates(setPoints(ResultsFiles.trialsNBack, lineColorTrial), ResultsFiles.trialsNBack, lineColorTrial));
        }
        if (showSessionInResults) {
            resultLines.add(setLinesCoordinates(setPoints(ResultsFiles.sessionsNBackMax, lineColorSession), ResultsFiles.sessionsNBackMax, lineColorSession));
        }
        if (showDayInResults) {
            resultLines.add(setLinesCoordinates(setPoints(ResultsFiles.daysNBackMax, lineColorDay), ResultsFiles.daysNBackMax, lineColorDay));
        }
//        }
        drawResultLines(resultLines);
        layout.removeView(setting_button_result_screen);
        layout.addView(setting_button_result_screen);
        layout.setBackground(new BitmapDrawable(bg));

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
        lineColorDay = Color.rgb(44, 135, 32);
        lineColorSession = Color.BLUE;
        lineColorTrial = Color.RED;
        backgroundColor = Color.rgb(232, 236, 235);
        if (darkModeMenu) {
            backgroundColor = Color.rgb(0, 0, 0);
        }

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

    private CheckBox one;
    private CheckBox two;
    private CheckBox three;
    private CheckBox four;
    private CheckBox five;
    private CheckBox six;

    private void createSettingsDialog() {

        dialog = new Dialog(this);
        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();

        wmlp.gravity = Gravity.TOP | Gravity.START;
        wmlp.x = 000;   //x position
        wmlp.y = (int) dpFromPx(this, 100);   //y position
        wmlp.alpha = 0.78f;
//        ConstraintLayout layout = dialog.findViewById(R.id.view_results);
        dialog.setContentView(R.layout.view_results_settings_layout);
        one = dialog.findViewById(R.id.showDayPercentage);
        two = dialog.findViewById(R.id.showSessionPercentage);
        three = dialog.findViewById(R.id.showTrialPercentage);
        four = dialog.findViewById(R.id.showDayNBack);
        five = dialog.findViewById(R.id.showSessionNBack);
        six = dialog.findViewById(R.id.showTrialNBack);
//        layout.setAlpha(0.3f);
//        dialog.setOwnerActivity(this);

//        getViews();
//        setOnClickListeners();
        if (darkModeMenu) {
            setting_button_result_screen.setBackgroundResource(R.drawable.result_screen_settings_button_image_dark);
        } else {
            setting_button_result_screen.setBackgroundResource(R.drawable.result_screen_settings_button_image);
        }


        one.setText(Strings.showDayInResultsPercentageText);
        two.setText(Strings.showSessionInResultsPercentageText);
        three.setText(Strings.showTrialsInResultsPercentageText);
        four.setText(showDaysInResultsText);
        five.setText(Strings.showSessionInResultsText);
        six.setText(Strings.showTrialInResultsText);

        one.setTextColor(getResources().getColor(R.color.black));
        two.setTextColor(getResources().getColor(R.color.black));
        three.setTextColor(getResources().getColor(R.color.black));
        four.setTextColor(getResources().getColor(R.color.black));
        five.setTextColor(getResources().getColor(R.color.black));
        six.setTextColor(getResources().getColor(R.color.black));



        one.setChecked(showDayInResultsPercentage);
        two.setChecked(showSessionInResultsPercentage);
        three.setChecked(showTrialInResultsPercentage);
        four.setChecked(showDayInResults);
        five.setChecked(showSessionInResults);
        six.setChecked(showTrialInResults);
        setOnClickListeners();
        dialog.show();
    }

    private void setOnClickListener() {
        layout.setOnClickListener(this);
        setting_button_result_screen.setOnClickListener(this);

    }

    private void setOnClickListeners() {


        one.setOnClickListener(c -> {
            SessionParameters.showDayInResultsPercentage = one.isChecked();
            layout.removeAllViews();
            setDivider();
            addResultLines();
            FileLogicSettings.saveSettings(ActivityResults.this);
        });
        two.setOnClickListener(c -> {
            SessionParameters.showSessionInResultsPercentage = two.isChecked();
            layout.removeAllViews();
            setDivider();
            addResultLines();
            FileLogicSettings.saveSettings(ActivityResults.this);
        });
        three.setOnClickListener(c -> {
            SessionParameters.showTrialInResultsPercentage = three.isChecked();
            layout.removeAllViews();
            setDivider();
            addResultLines();
            FileLogicSettings.saveSettings(ActivityResults.this);
        });


        four.setOnClickListener(c -> {
            SessionParameters.showDayInResults = four.isChecked();
            layout.removeAllViews();
            setDivider();
            addResultLines();
            FileLogicSettings.saveSettings(ActivityResults.this);
        });
        five.setOnClickListener(c -> {
            SessionParameters.showSessionInResults = five.isChecked();
            layout.removeAllViews();
            setDivider();
            addResultLines();
            FileLogicSettings.saveSettings(ActivityResults.this);
        });
        six.setOnClickListener(c -> {
            SessionParameters.showTrialInResults = six.isChecked();
            layout.removeAllViews();
            setDivider();
            addResultLines();
            FileLogicSettings.saveSettings(ActivityResults.this);
        });


    }

    private void getViews() {

        layout = findViewById(R.id.layout);
        setting_button_result_screen = layout.findViewById(R.id.setting_button_result_screen);
        goodJob = layout.findViewById(R.id.goodJob);

    }


}