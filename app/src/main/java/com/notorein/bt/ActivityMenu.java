package com.notorein.bt;

import static android.content.ContentValues.TAG;
import static com.notorein.bt.ResultsFiles.copyResults;
import static com.notorein.bt.ResultsFiles.deleteResults;
import static com.notorein.bt.ResultsFiles.initialiseStoringFilePaths;
import static com.notorein.bt.SessionParameters.adMissedCounter;

import static com.notorein.bt.SessionParameters.convertedTrialToTime;
import static com.notorein.bt.SessionParameters.countDownInterval;
import static com.notorein.bt.SessionParameters.darkModeMenu;
import static com.notorein.bt.SessionParameters.darkModeTraining;
import static com.notorein.bt.SessionParameters.dateOfCurrentUse;
import static com.notorein.bt.SessionParameters.daySession;

import static com.notorein.bt.SessionParameters.displayHeight;
import static com.notorein.bt.SessionParameters.displayWidth;
import static com.notorein.bt.SessionParameters.duration;
import static com.notorein.bt.SessionParameters.exitButtonWasPressed;
import static com.notorein.bt.SessionParameters.fadeoutAnimationDuration;
import static com.notorein.bt.SessionParameters.firstStart;
import static com.notorein.bt.SessionParameters.includeAudio;
import static com.notorein.bt.SessionParameters.includeColor;
import static com.notorein.bt.SessionParameters.includePosition;
import static com.notorein.bt.SessionParameters.includedModes;
import static com.notorein.bt.SessionParameters.loadInterstitialAd;
import static com.notorein.bt.SessionParameters.missedAdDialogHasBeenShown;
import static com.notorein.bt.SessionParameters.nBack;
import static com.notorein.bt.SessionParameters.nBackBegin;
import static com.notorein.bt.SessionParameters.openManual;
import static com.notorein.bt.SessionParameters.orientation;
import static com.notorein.bt.SessionParameters.paused;
import static com.notorein.bt.SessionParameters.resultsFilePath;
import static com.notorein.bt.SessionParameters.returnFromResultScreen;
import static com.notorein.bt.SessionParameters.returnFromTraining;
import static com.notorein.bt.SessionParameters.sessionWasCanceledEarly;
import static com.notorein.bt.SessionParameters.stringToStore;
import static com.notorein.bt.SessionParameters.stringToStoreInitial;
import static com.notorein.bt.SessionParameters.tempResultsAlreadyStored;
import static com.notorein.bt.SessionParameters.trialsMax;
import static com.notorein.bt.SessionParameters.useTempResults;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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

import java.io.File;


public class ActivityMenu extends AppCompatActivity implements View.OnClickListener {

    private Button btnStart;
    private Button btnResults;
    private Button btnSave;

    private Button btn_manual;
    private Button btn_recommendations;
    private Button btn_exit;

    private ImageView btnSun;


    private EditText editTextNBackLevel;
    private EditText editTextDuration;

    private TextView textViewInfo;
    private TextView textViewTime;

    private Switch switchPosition;
    private Switch switchAudio;
    private Switch switchColor;
    private boolean allowToStartSession = true;
    private boolean startWithoutModeHasBeenReseted = true;

    private RadioButton btnLand;
    private RadioButton btnPort;
    private int testCounter = 0;
    private AlertDialog.Builder builder;
    private ConstraintLayout layout;
    private Runnable fadeAction;
    private Animation fadeOut;
    private TextView hintTextViewDuration;
    private TextView hintTextViewNBack;
    private View dividerMenu1;
    private View dividerMenu2;
    private View dividerMenu3;
    private View[] views;
    private TextView splashImage;
    private SoundPool appSounds;
    private int buttonSound;
    private int dingSound;
    private View decorView;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private AdRequest adRequest;
    private Dialog dialogAdReminder;
    private ConstraintLayout dialogAdReminderLayout;
    private boolean showReminderDialog;
    private Dialog dialogAbout;
    //    TransitionActivityAToB transitionToTraining;
    private TransitionActivityAToB transitionActivityAToB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(7);
        decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_menu_layout);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        loadInterstitialAd();
        if(firstStart){
            FileLogicSettings.readSettings(ActivityMenu.this);
        }
        getViews();
        includeViewsToFadeInTransition();
        setImageNou();
        setSounds();
        setFadeInAnimationAndDingSound();
        setActivityTransitions();
        setOnClickListeners();
        setTextToInput();
        convertedTrialToTimeMethod();
        setSwitchesInPosition();
        getDisplaySize();
        btnStart.setEnabled(true);
        btnSave.setEnabled(!sessionWasCanceledEarly && !exitButtonWasPressed);
        if (!sessionWasCanceledEarly) {
            btnSave.setTextColor(getResources().getColor(R.color.button_save_enabled));
        }
        daySession = RepeatStorage.getDay();
        boolean internet = isInternetAvailable();
        if (internet && missedAdDialogHasBeenShown && adMissedCounter >= 5) {
            adMissedCounter = 0;
        }
        if (!internet) {
            if (!returnFromTraining) {
                adMissedCounter++;
            }
            FileLogicSettings.saveSettings(this);
            if (adMissedCounter >= 5) {
                showReminderDialog = true;
            }
        }
        if (firstStart || returnFromResultScreen) {
            if (new File(this.getFilesDir(), initialiseStoringFilePaths(true)).exists()) {
                showAlertPleaseSaveResults(this, Strings.dontForgetToSaveResults, Strings.storeResults, Strings.dismissResults, () -> {
                    copyResults(this, initialiseStoringFilePaths(false), initialiseStoringFilePaths(true));
                    deleteResults(this, initialiseStoringFilePaths(true));

                }, () -> {
                    deleteResults(this, initialiseStoringFilePaths(true));
                });
            }
        }

        returnFromTraining = false;
        SessionParameters.returnFromResultScreen = false;
        exitButtonWasPressed = false;
//        dialogAdReminder.show();
//        btnLight.setText("" + adMissedCounter);
        setDayAndNightMode();
    }

    private final void setActivityTransitions() {

        transitionActivityAToB = new TransitionActivityAToB();
        transitionActivityAToB.setTransitionAToB(this, this, layout, fadeoutAnimationDuration, 0, () -> {
            FileLogicSettings.saveSettings(ActivityMenu.this);
        }, () -> {
//            for (int i = 0; i < views.length; i++) {
//                views[i].setAlpha(0);
//            }
            Intent intent = new Intent(ActivityMenu.this, ActivityTraining.class);
            startActivity(intent);
            finish();
        }, views);
//        transitionActivityAToB.setFadeInAnimationFirst();
    }


    private final void startTransitionToActivityTraining() {
//        transitionActivityAToB = new TransitionActivityAToB();
        int colorFrom = 0;
        int colorTo = 0;
        if (darkModeTraining && darkModeMenu) {
            colorFrom = getResources().getColor(R.color.black);
            colorTo = getResources().getColor(R.color.black);
            Log.i(TAG, "                                 startTransitionToActivityTraining: I");
        } else if (!darkModeTraining && darkModeMenu) {
            colorFrom = getResources().getColor(R.color.black);
            colorTo = getResources().getColor(R.color.white);
            Log.i(TAG, "                                 startTransitionToActivityTraining: II");
        } else if (darkModeTraining && !darkModeMenu) {
            colorFrom = getResources().getColor(R.color.white);
            colorTo = getResources().getColor(R.color.black);
            Log.i(TAG, "                                 startTransitionToActivityTraining: III");
        } else if (!darkModeTraining && !darkModeMenu) {
            colorFrom = getResources().getColor(R.color.white);
            colorTo = getResources().getColor(R.color.white);
            Log.i(TAG, "                                 startTransitionToActivityTraining: IV");
        }
        transitionActivityAToB.startAnimation(colorFrom, colorTo);
    }


    private void setDayAndNightMode() {
        if (darkModeMenu) {
            btnSun.setBackground(getResources().getDrawable(R.drawable.btn_background_sun_dark));
            layout.setBackgroundColor(getResources().getColor(R.color.menu_background_color_dark));
            hintTextViewNBack.setBackgroundColor(getResources().getColor(R.color.menu_background_color_dark));
            hintTextViewDuration.setBackgroundColor(getResources().getColor(R.color.menu_background_color_dark));
        } else {
            btnSun.setBackground(getResources().getDrawable(R.drawable.btn_background_sun));
            layout.setBackgroundColor(getResources().getColor(R.color.menu_background_color));
            hintTextViewNBack.setBackgroundColor(getResources().getColor(R.color.menu_background_color));
            hintTextViewDuration.setBackgroundColor(getResources().getColor(R.color.menu_background_color));
        }
    }

    private void loadInterstitialAd() {
        Intent intent = new Intent(this, ActivityResults.class);
//        if (SessionParameters.loadInterstitialAd) {

        mInterstitialAd.load(this, "ca-app-pub-3940256099942544/1033173712", this.adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
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
                                startActivity(intent);
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
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

//                            if (mInterstitialAd != null) {
//                                mInterstitialAd.show(ActivityMenu.this);
//                            } else {
//                                Log.d("TAG", "The interstitial ad wasn't ready yet.");
//                            }
                        Log.i(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
//                            Log.d(TAG, loadAdError.toString());
                        mInterstitialAd = null;
//                            startActivity(intent);
                    }
                });
    }


    public boolean isInternetAvailable() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            boolean connected = (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED);
            return connected;
        } catch (Exception e) {
            return false;
        }
    }


    private void getDisplaySize() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        displayWidth = size.x;
        displayHeight = size.y;
    }

    private void setSounds() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setUsage(AudioAttributes.USAGE_GAME).build();
            appSounds = new SoundPool.Builder().setMaxStreams(4).setAudioAttributes(attributes).build();

        } else {
            appSounds = new SoundPool(2, AudioAttributes.CONTENT_TYPE_MUSIC, 0);
        }
        buttonSound = appSounds.load(this, R.raw.click, 1);
        dingSound = appSounds.load(this, R.raw.ding, 1);
    }


    private void setImageNou() {
//        nouImage.setBackgroundResource(R.drawable.genkou);
        splashImage.setText("健康");
        if(!darkModeMenu){
            splashImage.setTextColor(getResources().getColor(R.color.black));
        }
        splashImage.setTextSize(184f);
    }

    private void setSwitchesInPosition() {
        switchPosition.setChecked(includePosition);
        switchAudio.setChecked(includeAudio);
        switchColor.setChecked(includeColor);
        if (orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            btnPort.setChecked(false);
            btnLand.setChecked(true);
        } else {
            btnPort.setChecked(true);
            btnLand.setChecked(false);
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
    }

    private void setTextToInput() {
        nBack = nBackBegin;
        editTextNBackLevel.setText("" + nBackBegin);
        editTextDuration.setText("" + duration);
    }

    private void getViews() {

        layout = (ConstraintLayout) findViewById(R.id.menu_layout);
        splashImage = findViewById(R.id.splashImage);

        btnSun = findViewById(R.id.btnSun);
//        btnLight.setVisibility(View.INVISIBLE);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnResults = (Button) findViewById(R.id.btnResults);
        btnSave = (Button) findViewById(R.id.btnSave);

        hintTextViewNBack = (TextView) findViewById(R.id.hintTextViewNBack);
        hintTextViewDuration = (TextView) findViewById(R.id.hintTextViewDuration);

        dividerMenu1 = (View) findViewById(R.id.dividerMenu1);
        dividerMenu2 = (View) findViewById(R.id.dividerMenu2);
        dividerMenu3 = (View) findViewById(R.id.dividerMenu3);

        editTextNBackLevel = (EditText) findViewById(R.id.editTextNBackLevel);
        editTextDuration = (EditText) findViewById(R.id.editTextDuration);

        textViewTime = (TextView) findViewById(R.id.textViewTime);
        textViewInfo = (TextView) findViewById(R.id.infoTextView);

        switchPosition = (Switch) findViewById(R.id.switchPosition);
        switchAudio = (Switch) findViewById(R.id.switchAudio);
        switchColor = (Switch) findViewById(R.id.switchColor);

        btnPort = (RadioButton) findViewById(R.id.radioButtonPort);
        btnLand = (RadioButton) findViewById(R.id.radioButtonLand);





    }

    private void includeViewsToFadeInTransition() {
        views = new View[]{btnStart, btnResults, btnSave, hintTextViewNBack, hintTextViewDuration,
                dividerMenu1, dividerMenu2, dividerMenu3,
                editTextNBackLevel, editTextDuration,
                textViewTime, textViewInfo,
                switchPosition, switchAudio, switchColor,
                btnPort, btnLand, mAdView, btnSun
        };
    }

    private void setOnClickListeners() {
        btnSun.setOnClickListener(this);
        btnStart.setOnClickListener(this);
        btnResults.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        textViewInfo.setOnClickListener(this);
        switchPosition.setOnClickListener(this);
        switchAudio.setOnClickListener(this);
        switchColor.setOnClickListener(this);
        btnLand.setOnClickListener(this);
        btnPort.setOnClickListener(this);
        editTextDuration.setOnClickListener(this);
        editTextNBackLevel.setOnClickListener(this);
        editTextDuration.setOnKeyListener(new EditText.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                try {
                    convertedTrialToTimeMethod();
                } catch (Exception e) {
                }
                return false;
            }
        });

    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnStart) {
            startButtonLogic();
            appSounds.play(buttonSound, 1, 1, 1, 0, 1);
        }
        if (v.getId() == R.id.btnSun) {
            darkModeMenu = !darkModeMenu;
            setDayAndNightMode();
            FileLogicSettings.saveSettings(this);

//            ResultsFiles.test = true;
//            btnSave.setEnabled(true);
//            ResultsFiles.testStringIndex++;
//            switch (ResultsFiles.testStringIndex) {
//                case 3:
//                    ResultsFiles.testStringIndex = 0;
//                    ResultsFiles.test = false;
//                    btnSave.setEnabled(ResultsFiles.test);
//                    break;
//            }
        }
        if (v.getId() == R.id.btnResults) {
            if (useTempResults && !tempResultsAlreadyStored) {
                // Here I am reading the original file
                resultsFilePath = initialiseStoringFilePaths(false);
                stringToStoreInitial = ResultsFiles.readResults(ActivityMenu.this);
                ResultsFiles.copyResults(this, resultsFilePath, initialiseStoringFilePaths(true));
                ResultsFiles.saveResults(this, true, initialiseStoringFilePaths(true));
                tempResultsAlreadyStored = true;
            }
            appSounds.play(buttonSound, 1, 1, 1, 0, 1);
            stringToStore = stringToStoreInitial + stringToStore;
            loadInterstitialAd = true;
            if (isInternetAvailable()) {

                if (mInterstitialAd != null) {
                    mInterstitialAd.show(ActivityMenu.this);
                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                    Intent intent = new Intent(this, ActivityResults.class);
                    startActivity(intent);
                }
            } else {
                Intent intent = new Intent(this, ActivityResults.class);
                startActivity(intent);
            }
        }

        if (v.getId() == R.id.btnSave) {
            // If I don't add the if statement, the results can be stored twice in the same file
            // from the temporary results file.
            if (tempResultsAlreadyStored) {
                Log.i(TAG, "onClick:  " + tempResultsAlreadyStored);

                resultsFilePath = initialiseStoringFilePaths(false);
                copyResults(this, initialiseStoringFilePaths(true), resultsFilePath);
                deleteResults(this, initialiseStoringFilePaths(true));

            } else {
                Log.i(TAG, "onClick:  " + tempResultsAlreadyStored);
                resultsFilePath = initialiseStoringFilePaths(false);
                Log.i(TAG, "tempFilePath: " + resultsFilePath + "   #############  ");
                ResultsFiles.saveResults(ActivityMenu.this, true, resultsFilePath);
            }
            useTempResults = false;
            tempResultsAlreadyStored = false;
            appSounds.play(buttonSound, 1, 1, 1, 0, 1);
            btnSave.setEnabled(false);
            btnSave.setTextColor(getResources().getColor(R.color.button_save_disabled));


        }

        if (v.getId() == R.id.infoTextView) {
            try {
                showDialogAbout();
                appSounds.play(buttonSound, 1, 1, 1, 0, 1);
            } catch (Exception e) {
                finish();
            }
        }

        if (v.getId() == R.id.editTextDuration) {
            preventEmptyEditTextOrZero();
            convertedTrialToTimeMethod();
        }

        if (v.getId() == R.id.editTextNBackLevel) {
            preventEmptyEditTextOrZero();
            convertedTrialToTimeMethod();
        }

        if (v.getId() == R.id.switchPosition) {
            appSounds.play(buttonSound, 1, 1, 1, 0, 1);
            switchPositionLogic();
        }

        if (v.getId() == R.id.switchAudio) {
            appSounds.play(buttonSound, 1, 1, 1, 0, 1);
            switchAudioLogic();
        }

        if (v.getId() == R.id.switchColor) {
            appSounds.play(buttonSound, 1, 1, 1, 0, 1);
            switchColorLogic();
        }

        if (v.getId() == R.id.radioButtonLand) {
            appSounds.play(buttonSound, 1, 1, 1, 0, 1);
            orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        }

        if (v.getId() == R.id.radioButtonPort) {
            appSounds.play(buttonSound, 1, 1, 1, 0, 1);
            orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        }


    }

    private void switchPositionLogic() {
        if (!btnSave.isEnabled()) {
            includedModes++;
            includePosition = !includePosition;
            btnSave.setEnabled(false);
            btnSave.setTextColor(getResources().getColor(R.color.button_save_disabled));
            stringToStore = "";
        } else {
            showAlertPleaseSaveResults(() -> {
                if (useTempResults) {
                    ResultsFiles.deleteResults(this, initialiseStoringFilePaths(true));
                }
                useTempResults = false;
                tempResultsAlreadyStored = false;
                btnSave.setEnabled(false);
                btnSave.setTextColor(getResources().getColor(R.color.button_save_disabled));
                switchPositionLogic();
            }, () -> {
                FileLogicSettings.readSettings(ActivityMenu.this);
                setSwitchesInPosition();
            });
        }
    }

    private void switchAudioLogic() {
        if (!btnSave.isEnabled()) {
            includedModes++;
            includeAudio = !includeAudio;
            btnSave.setEnabled(false);
            btnSave.setTextColor(getResources().getColor(R.color.button_save_disabled));
            stringToStore = "";
        } else {
            showAlertPleaseSaveResults(() -> {
                if (useTempResults) {
                    ResultsFiles.deleteResults(this, initialiseStoringFilePaths(true));
                }
                useTempResults = false;
                tempResultsAlreadyStored = false;
                btnSave.setEnabled(false);
                btnSave.setTextColor(getResources().getColor(R.color.button_save_disabled));
                switchAudioLogic();
            }, () -> {
                FileLogicSettings.readSettings(ActivityMenu.this);
                setSwitchesInPosition();
            });
        }
    }

    private void switchColorLogic() {
        if (!btnSave.isEnabled()) {
            includedModes++;
            includeColor = !includeColor;
            btnSave.setEnabled(false);
            btnSave.setTextColor(getResources().getColor(R.color.button_save_disabled));
            stringToStore = "";
        } else {
            showAlertPleaseSaveResults(() -> {
                if (useTempResults) {
                    ResultsFiles.deleteResults(this, initialiseStoringFilePaths(true));
                }
                useTempResults = false;
                tempResultsAlreadyStored = false;
                btnSave.setEnabled(false);
                btnSave.setTextColor(getResources().getColor(R.color.button_save_disabled));
                switchColorLogic();
            }, () -> {
                FileLogicSettings.readSettings(ActivityMenu.this);
                setSwitchesInPosition();
            });
        }
    }


    private void startButtonLogic() {
        if (!btnSave.isEnabled()) {
            preventStartWithoutMode();
            if (allowToStartSession) {
                paused = true;
                btnStart.setEnabled(false);
                btnResults.setEnabled(false);
                btnSave.setEnabled(false);
                try {
                    nBackBegin = Integer.parseInt(editTextNBackLevel.getText().toString());
                    nBack = nBackBegin;
                    duration = Integer.parseInt(editTextDuration.getText().toString());
                    if (duration < 1) {
                        duration = 1;
                    }
                    trialsMax = duration;
                    if (trialsMax < 1) {
                        trialsMax = 1;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dateOfCurrentUse = RepeatStorage.getDay();
//                setFadeOutAnimation(fadeoutAnimationDuration, 0, views);

                startTransitionToActivityTraining();
            } else {
                resetStartWithoutMode();
                setTextToInput();
                setSwitchesInPosition();
            }
        } else {
            showAlertPleaseSaveResults(() -> {
                useTempResults = false;
                btnSave.setEnabled(false);
                btnSave.setTextColor(getResources().getColor(R.color.button_save_disabled));
                startButtonLogic();
                stringToStoreInitial = ResultsFiles.readResults(ActivityMenu.this);
                stringToStore = "";
            }, null);
        }
    }


    private void preventEmptyEditTextOrZero() {
        try {
            int i = Integer.parseInt(editTextDuration.getText().toString());
            if (i < 1) {
                i = 1;
                editTextDuration.setText("" + i);
            }
        } catch (Exception e) {
            editTextDuration.setText("1");
        }
        try {
            int i = Integer.parseInt(editTextNBackLevel.getText().toString());
            if (i < 1) {
                i = 1;
                editTextNBackLevel.setText("" + i);
            }
        } catch (Exception e) {
            editTextNBackLevel.setText("1");
        }
    }


    private void convertedTrialToTimeMethod() {
        double duration = Double.parseDouble(editTextDuration.getText().toString());
        double temp = duration * ((2 * countDownInterval) / 100000) * 60;
        convertedTrialToTime = (int) Math.round(temp);
        if (convertedTrialToTime <= 1) {
            convertedTrialToTime = 2;
        }
        textViewTime.setText(String.format(Strings.textViewTimeAdd_I + "%d - %d" + Strings.textViewTimeAdd_II, convertedTrialToTime, convertedTrialToTime + 1));
    }

    private void preventStartWithoutMode() {
        allowToStartSession = true;
        includedModes = 0;
        if (!includePosition && !includeAudio && !includeColor) {
            allowToStartSession = false;
        }
        if (includePosition) {
            includedModes++;
        }
        if (includeAudio) {
            includedModes++;
        }
        if (includeColor) {
            includedModes++;
        }
    }

    private void resetStartWithoutMode() {
        includePosition = true;
        includeAudio = true;
        includeColor = false;
    }

    private void showDialogAbout() {

        dialogAbout = new Dialog(this);
        dialogAbout.setContentView(R.layout.view_menu_about);
        ConstraintLayout layout = dialogAbout.findViewById(R.id.layout);
        if(darkModeMenu){
            layout.setBackground(getResources().getDrawable(R.drawable.alert_background_dark));
        }
        btn_manual = (Button) dialogAbout.findViewById(R.id.btnBottom);
        btn_manual.setText(Strings.btnManualText);
        btn_recommendations = (Button) dialogAbout.findViewById(R.id.btn_about);
        btn_recommendations.setText(Strings.btnRecommendationsText);
        btn_exit = (Button) dialogAbout.findViewById(R.id.btn_exit);
        btn_manual.setOnClickListener(c -> {
            openManual = true;
            Intent intent = new Intent(ActivityMenu.this, ActivityAbout.class);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            startActivity(intent);
        });
        btn_recommendations.setOnClickListener(c -> {
            openManual = false;
            Intent intent = new Intent(ActivityMenu.this, ActivityAbout.class);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            startActivity(intent);
        });
//        btn_manual.setOnClickListener(c -> {
////            initialiseStoringFilePaths();
////            ViewMenu.testFile(this);
//        });
        btn_exit.setOnClickListener(c -> {
            if (!btnSave.isEnabled()) {
                dialogAbout.cancel();
                exitButtonWasPressed = true;
                finish();
            } else {
                showAlertPleaseSaveResults(() -> {
                    dialogAbout.cancel();
                    btnSave.setEnabled(false);
                    exitButtonWasPressed = true;
                    finish();
                }, () -> {
                    dialogAbout.cancel();
                });
            }
        });
        dialogAbout.show();

    }

    private void createDialogAdReminder() {
        if (showReminderDialog) {
            dialogAdReminder = new Dialog(this);
            dialogAdReminder.setContentView(R.layout.view_menu_ad_reminder);
            dialogAdReminderLayout = findViewById(R.id.dialogAdReminderLayout);
            TextView btn_manual = dialogAdReminder.findViewById(R.id.btnBottom);
            btn_manual.setText(Strings.adReminderTextI + adMissedCounter + Strings.adReminderTextII);

            TextView btn_about = (TextView) dialogAdReminder.findViewById(R.id.btn_about);
            btn_about.setText(Strings.adReminderTextIII);
            btn_about.setOnClickListener(c -> {
                dialogAdReminder.cancel();
                missedAdDialogHasBeenShown = true;
                FileLogicSettings.saveSettings(this);
            });

            dialogAdReminder.show();
        }
    }

    private void showAlertPleaseSaveResults(Runnable dissmissLogic, Runnable goBackLogic) {

        builder = new AlertDialog.Builder(ActivityMenu.this);
        builder.setMessage(Strings.pleaseSaveResultsText).setCancelable(true).setNegativeButton(Strings.goBackAndStore, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (goBackLogic != null)
                    goBackLogic.run();

            }
        }).setPositiveButton(Strings.dismissResults, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (dissmissLogic != null)
                    dissmissLogic.run();
            }
        });
        AlertDialog alertDialog = builder.create();
        builder.show();
    }


    public static void showAlertPleaseSaveResults(Context c, String title, String dismiss, String confirm, Runnable dismissLogic, Runnable confirmLogic) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setMessage(title).setCancelable(true).setNegativeButton(confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (confirmLogic != null)
                    confirmLogic.run();
            }
        }).setPositiveButton(dismiss, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (dismissLogic != null)
                    dismissLogic.run();
            }
        });
        AlertDialog alertDialog = builder.create();
        builder.show();
    }

    public void setFadeInAnimationAndDingSound(long duration, long delay, View... v) {
        Animation mLoadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
        mLoadAnimation.setDuration(duration);
        mLoadAnimation.setStartOffset(delay);
        for (View i : v) {
            i.startAnimation(mLoadAnimation);
        }
    }

    private final void setFadeInAnimationAndDingSound() {
        // Here I make sure that the splash screen will only be shown on startup
        if (firstStart) {
            // This is called at the first start. It fades the splash screen out.
            // Then it calls the setFadeOutAnima
            firstStart = false;
            for (View i : views) {
                i.setAlpha(0);
            }
            if (splashImage != null) {
                setFadeOutAnimationLogo(1000, 2500, splashImage);
            }
            // Here I set the sound that will appear with the splash screen.
            long millisInFuture = 80;
            long countDownInterval = 20;
            CountDownTimer tmr = new CountDownTimer(millisInFuture, countDownInterval) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    appSounds.play(dingSound, 1, 1, 1, 0, 1);

                }
            };
            tmr.start();
        } else {
            // Here I make sure that the splash screen will only be shown on startup
            if (splashImage != null) {
                splashImage.setVisibility(View.INVISIBLE);
            }
            setFadeInAnimationAndDingSound(1800, 50, views);
        }
    }


    private final void setFadeOutAnimationLogo(long duration, long delay, View... v) {
        Animation mLoadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_out);
        Animation fadeOut = new AlphaAnimation(1f, 0f);
        mLoadAnimation.setInterpolator(new AccelerateInterpolator()); //and this
        mLoadAnimation.setDuration(duration);
        mLoadAnimation.setStartOffset(delay);
        mLoadAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
//                main_menu.setAlpha(0);
                // This three lines set the image invisible after fade_out
                for (View i : v) {
                    i.setAlpha(0f);
                }
                setFadeInAnimationAndDingSound(1800, 50, views);
                // Here the delay sets the time when the image disappeared and the buttons start to appear.
//                setFadeInAnimationFirst(1200, 0, views);
//                transitionActivityAToB.setFadeInAnimationFirst(null,()->{
//                    setTrainingTransition();
//                });
//                transitionActivityAToB.setFadeInAnimationFirst();
//                transitionActivityAToB.startAnimationIn();
                if (views != null) {
                    for (View i : views) {
                        i.setAlpha(1f);
                    }
                }
                createDialogAdReminder();

            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationStart(Animation arg0) {
            }
        });
        for (View i : v) {
            i.startAnimation(mLoadAnimation);
        }
    }

}