package com.notorein.bt;

import static android.content.ContentValues.TAG;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static com.notorein.bt.RepeatStorage.clickedAudioRight;
import static com.notorein.bt.RepeatStorage.clickedColorRight;
import static com.notorein.bt.RepeatStorage.clickedPositionRight;
import static com.notorein.bt.RepeatStorage.inOrDecreaseNBackLevel;
import static com.notorein.bt.RepeatStorage.matchesAudio;
import static com.notorein.bt.RepeatStorage.matchesColor;
import static com.notorein.bt.RepeatStorage.matchesPosition;
import static com.notorein.bt.RepeatStorage.percentageRightSessionAllModes;
import static com.notorein.bt.RepeatStorage.percentageRightTrialAllModes;
import static com.notorein.bt.RepeatStorage.percentageSessionAudio;
import static com.notorein.bt.RepeatStorage.percentageSessionColor;
import static com.notorein.bt.RepeatStorage.percentageSessionPosition;
import static com.notorein.bt.RepeatStorage.percentageTrialAudio;
import static com.notorein.bt.RepeatStorage.percentageTrialColor;
import static com.notorein.bt.RepeatStorage.percentageTrialPosition;
import static com.notorein.bt.RepeatStorage.percentagesSessionAllModes;
import static com.notorein.bt.RepeatStorage.percentagesSessionAudio;
import static com.notorein.bt.RepeatStorage.percentagesSessionColor;
import static com.notorein.bt.RepeatStorage.percentagesSessionPosition;
import static com.notorein.bt.RepeatStorage.percentagesTrialAudio;
import static com.notorein.bt.RepeatStorage.percentagesTrialColor;
import static com.notorein.bt.RepeatStorage.percentagesTrialPosition;
import static com.notorein.bt.RepeatStorage.shownIndexesAudio;
import static com.notorein.bt.RepeatStorage.shownIndexesColor;
import static com.notorein.bt.RepeatStorage.shownIndexesPosition;
import static com.notorein.bt.RepeatStorage.storeShownIndexes;
import static com.notorein.bt.SessionParameters.MAX_PRESENT_DEFAULT;
import static com.notorein.bt.SessionParameters.adMissedCounter;
import static com.notorein.bt.SessionParameters.allowCountingMatches;
import static com.notorein.bt.SessionParameters.allowToChangeColorStyle;
import static com.notorein.bt.SessionParameters.allowToChangeSquareSize;
import static com.notorein.bt.SessionParameters.aud;
import static com.notorein.bt.SessionParameters.audioIsClicked;
import static com.notorein.bt.SessionParameters.audioIsRight;
import static com.notorein.bt.SessionParameters.b;
import static com.notorein.bt.SessionParameters.col;
import static com.notorein.bt.SessionParameters.colorIsClicked;
import static com.notorein.bt.SessionParameters.colorIsRight;
import static com.notorein.bt.SessionParameters.colors;
import static com.notorein.bt.SessionParameters.countDownInterval;
import static com.notorein.bt.SessionParameters.countDownIntervalDefault;
import static com.notorein.bt.SessionParameters.counterClickedRightAudio;
import static com.notorein.bt.SessionParameters.counterClickedRightColor;
import static com.notorein.bt.SessionParameters.counterClickedRightPosition;
import static com.notorein.bt.SessionParameters.counterMatchTesholdMax;
import static com.notorein.bt.SessionParameters.counterMatchTresholdMin;
import static com.notorein.bt.SessionParameters.counterMatchesAud;
import static com.notorein.bt.SessionParameters.counterMatchesCol;
import static com.notorein.bt.SessionParameters.counterMatchesPos;
import static com.notorein.bt.SessionParameters.customColorSquare;
import static com.notorein.bt.SessionParameters.customSquareSize;
import static com.notorein.bt.SessionParameters.darkModeMenu;
import static com.notorein.bt.SessionParameters.darkModeTraining;
import static com.notorein.bt.SessionParameters.daySession;
import static com.notorein.bt.SessionParameters.dayTrial;
import static com.notorein.bt.SessionParameters.developerInfoAreVisible;
import static com.notorein.bt.SessionParameters.durationSessionTimer;
import static com.notorein.bt.SessionParameters.endOfSession;
import static com.notorein.bt.SessionParameters.endOfTrial;
import static com.notorein.bt.SessionParameters.endOfTrialDialogIsVisible;
import static com.notorein.bt.SessionParameters.fadeInterval;
import static com.notorein.bt.SessionParameters.fadeoutAnimationDuration;
import static com.notorein.bt.SessionParameters.firstStart;
import static com.notorein.bt.SessionParameters.g;
import static com.notorein.bt.SessionParameters.hexColor;
import static com.notorein.bt.SessionParameters.inOrDecreaseCulmulated;
import static com.notorein.bt.SessionParameters.includeAudio;
import static com.notorein.bt.SessionParameters.includeColor;
import static com.notorein.bt.SessionParameters.includePosition;
import static com.notorein.bt.SessionParameters.includedModes;
import static com.notorein.bt.SessionParameters.increasedCounterPosition;
import static com.notorein.bt.SessionParameters.maxPresentations;
import static com.notorein.bt.SessionParameters.missedAdDialogHasBeenShown;
import static com.notorein.bt.SessionParameters.nBack;
import static com.notorein.bt.SessionParameters.nBackBegin;
import static com.notorein.bt.SessionParameters.nBackCulmulated;
import static com.notorein.bt.SessionParameters.nBackMax;
import static com.notorein.bt.SessionParameters.orientation;
import static com.notorein.bt.SessionParameters.orientationWasChangedDuringTraining;
import static com.notorein.bt.SessionParameters.paused;
import static com.notorein.bt.SessionParameters.percentageRightTrialAudio;
import static com.notorein.bt.SessionParameters.percentageRightTrialColor;
import static com.notorein.bt.SessionParameters.percentageRightTrialPosition;
import static com.notorein.bt.SessionParameters.playButtonSoundDuringTraining;
import static com.notorein.bt.SessionParameters.pos;
import static com.notorein.bt.SessionParameters.positionIsClicked;
import static com.notorein.bt.SessionParameters.positionIsRight;
import static com.notorein.bt.SessionParameters.presentedScreens;
import static com.notorein.bt.SessionParameters.r;
import static com.notorein.bt.SessionParameters.resultScreenIndex;
import static com.notorein.bt.SessionParameters.scaleDefault;
import static com.notorein.bt.SessionParameters.scaleNoPos;
import static com.notorein.bt.SessionParameters.screenShowOrder;
import static com.notorein.bt.SessionParameters.sessionWasCanceledEarly;
import static com.notorein.bt.SessionParameters.setTestValues;
import static com.notorein.bt.SessionParameters.showGrid;
import static com.notorein.bt.SessionParameters.showHideScreen;
import static com.notorein.bt.SessionParameters.shownAndCounted;
import static com.notorein.bt.SessionParameters.speedPercentage;
import static com.notorein.bt.SessionParameters.squareDefaultColorIndex;
import static com.notorein.bt.SessionParameters.stringToStore;
import static com.notorein.bt.SessionParameters.textSizeMiddleResults;
import static com.notorein.bt.SessionParameters.textSizeMiddleTrial;
import static com.notorein.bt.SessionParameters.textUnit;
import static com.notorein.bt.SessionParameters.timeSession;
import static com.notorein.bt.SessionParameters.timeTrial;
import static com.notorein.bt.SessionParameters.trialCounter;
import static com.notorein.bt.SessionParameters.trialIndicator;
import static com.notorein.bt.SessionParameters.trialIsRunning;
import static com.notorein.bt.SessionParameters.trialsMax;
import static com.notorein.bt.SessionParameters.useTempResults;
import static com.notorein.bt.SessionParameters.zenMode;
import static com.notorein.bt.Strings.btnAudIIText;
import static com.notorein.bt.Strings.btnAudText;
import static com.notorein.bt.Strings.btnColText;
import static com.notorein.bt.Strings.btnPosIIText;
import static com.notorein.bt.Strings.btnPosText;
import static com.notorein.bt.Strings.cross;
import static com.notorein.bt.Strings.lengthTrial;
import static com.notorein.bt.Strings.nBackLevel;
import static com.notorein.bt.Strings.pressButtonToC;
import static com.notorein.bt.Strings.pressButtonToS;
import static com.notorein.bt.Strings.setDeveloperInfoText;
import static com.notorein.bt.Strings.setIntervalText;
import static com.notorein.bt.Strings.trialEndTextPercentage;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.BuildConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActivityTraining extends AppCompatActivity implements View.OnClickListener {


    private static Runnable fadeAction;
    public ConstraintLayout rectContainer;
    private TextView splashImage;
    ArrayList<Button> squares = new ArrayList<>();
    private CountDownTimer timerTrial;
    private CountDownTimer timerClock;
    private CountDownTimer timerClockDeveloper;
    private int screenOrientation;

    private Button btnAudioII;
    private Button btnPositionII;
    private Button btnPosition;
    private Button btnAudio;
    private Button btnColor;
    private Button btnExit;

    private TextView txtVwMiddle;
    private TextView txtVwInterval;
    public TextView lbl_n_back_Info_Pause;
    public TextView lbl_n_Back;
    public TextView infoTxt;

    public static Boolean lastClickRightPos;
    public static Boolean lastClickRightAud;
    public static Boolean lastClickRightCol;

    private CustomClock clockTrial = new CustomClock();
    private CustomClock clockSession = new CustomClock();
    private CustomClock clockTrialDevOps = new CustomClock();

    private ArrayList<MediaPlayer> audioFiles = new ArrayList<>();
    private View dividerVertical1;
    private View dividerVertical2;
    private View dividerHorizontal1;
    private View dividerHorizontal2;
    private View[] dividers;
    private AlertDialog.Builder builder;

    public static CountDownTimer developerTimer;
    public static int developerCountdown = 0;
    public static int developerCountdownClick = 0;
    public static int developerCountMax = 6;
    private boolean developerTimerIsRunning;
    //    private int nBackCulmulated;
// TestVariable
    public static int addedNBACK = 0;
    private ConstraintLayout layout;
    int alphaLayout = 0;
    private View[] views;
    private Button onlyColor;
    private SoundPool appSounds;
    private int buttonSound;
    private int[] trainingSound;
    //    public static double squareFadeDuration = 0.9;
//    public static double squareFadeDuration = 1.1;

    //    public static double squareFadeDuration = 1.7;

    private ImageView btnSoundOff;
    private ImageView btnOrientation;
    private ImageView btnMode;
    private ImageView btnStyle;
    private ImageView btnSize;
    private ImageView btnGrid;
    private ImageView btnFade;
    private Button positive;
    private Button negative;
    private TextView txtVwnBackLevelInfo;
    private ConstraintLayout colorChooseLayout;
    private Dialog dialog;
    private Activity activity;
    private int visibility, alpha;
    private TransitionActivityAToB transitionActivityAToB;
    private Dialog dialogAdReminder;
    private ConstraintLayout dialogAdReminderLayout;
    private boolean showReminderDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (BuildConfig.DEBUG) {
            StrictMode.enableDefaults();
        }
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_training_layout);
//        FileLogicSettings.readSettings(this);
        allowToChangeSquareSize = false;
        allowToChangeColorStyle = false;
        activity = this;
        setFullScreen();
        setOrientationListener();
        getViews();
        includeViewsToFadeInTransition();
        setCustomSize(false, 1);
        setModeColors();
        setSounds();
        setFadeInAnimationAndDingSound();
        setActivityTransitions();
        prepareOtherStuff();
        setTextsToView();
        setOnClickListenersToUiElements();
        setOnClickListenersToSquares();
        if (!orientationWasChangedDuringTraining) {
            resetSession();

//        setDeveloperCountdown((View) findViewById(R.id.textViewMiddle));
            pos = includePosition;
            aud = includeAudio;
            col = includeColor;

            if (trialCounter == 0) {
                timeSession = RepeatStorage.getStartTime();
                daySession = RepeatStorage.getDay();
                timeTrial = timeSession;
                dayTrial = daySession;
                screenInterruptionStart();
            } else {
                if (!trialIsRunning) {
                    startTrial();
                }
            }
        } else {
            if (orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                setCountdown(btnPosition, 2300);
            }
        }
        if (trialCounter == 0) {
            screenInterruptionStart();
        }
//        if (!includeColor) {
//            btnColor.setEnabled(false);
//            btnColor.setTextColor(getResources().getColor(R.color.training_button_text_color_disabled));
////            btnColor.setAlpha(0.1f);
//        }
        txtVwnBackLevelInfo.setText(String.valueOf(nBack));

        setSquareSize();
        changeSquaresStandardColor();
        setIntervalText();
        txtVwInterval.setText(lengthTrial);
//
    }


    private void setSquareSize() {

        if (includeColor && !includePosition) {

            onlyColor.setScaleX(customSquareSize * 1.48f);
            onlyColor.setScaleY(customSquareSize * 1.48f);
            squares.get(4).setScaleX(customSquareSize * 1.5f);
            squares.get(4).setScaleY(customSquareSize * 1.5f);
        } else {
            for (int i = 0; i < squares.size(); i++) {
                squares.get(i).setScaleX(customSquareSize);
                squares.get(i).setScaleY(customSquareSize);
            }
        }

    }

    private void showFirstScreenSplittedClick() {
        String indicator = getTrialIndicator();
        String text = Strings.firstStartTextPressButtonToS + "\n\n" + nBack + nBackLevel + "\n\n" + indicator;
        txtVwMiddle.setText(text);
        txtVwMiddle.setTextSize(textUnit, textSizeMiddleTrial);
        if (screenShowOrder == 0 || !includePosition) {
            screenShowOrder++;
            if (!includePosition) {
                setDividersVisibleAddaptToMode(INVISIBLE);
                setMiddleSquareToRightSizeUI(scaleNoPos, R.color.border_color_no_position, text, textUnit, textSizeMiddleTrial, VISIBLE, VISIBLE);
            }
        }
        btnExit.setVisibility(VISIBLE);
    }


    private void screenTrialHide() {
        if (includePosition) {
            try {
                squares.get(shownIndexesPosition.get(presentedScreens - 1)).setVisibility(INVISIBLE);
            } catch (Exception ignored) {
                // Do nothing if an exception occurs
            }
        }
        if (includeColor && !includePosition && !zenMode) {
            GradientDrawable border = new GradientDrawable();
            int bgColor = darkModeTraining ? R.color.menu_background_color_dark : R.color.menu_background_color;
            int borderColor = darkModeTraining ? R.color.training_text_color_dark : R.color.border_color_no_position;
            border.setColor(getResources().getColor(bgColor));
            border.setStroke(1, getResources().getColor(borderColor));
            onlyColor.setVisibility(INVISIBLE);
        }
    }




    private void screenTrialShow() {
        if (includePosition) {
            if (!zenMode) {
                try {
                    squares.get(shownIndexesPosition.get(presentedScreens)).setVisibility(VISIBLE);
                    if (includeColor) {
                        squares.get(shownIndexesPosition.get(presentedScreens)).setBackgroundColor(getResources().getColor(colors[shownIndexesColor.get(presentedScreens)]));
                    }
                } catch (Exception e) {
                    // handle the exception
                }
            } else {
                if (includeColor) {
                    squares.get(shownIndexesPosition.get(presentedScreens)).setBackgroundColor(getResources().getColor(colors[shownIndexesColor.get(presentedScreens)]));
                }
                setFadeInAnimationSquaresForZenMode(squares.get(shownIndexesPosition.get(presentedScreens)));
            }
        }
        if (includeAudio) {
            try {
                playAudio(shownIndexesAudio.get(presentedScreens));
            } catch (Exception e) {
                // handle the exception
            }
        }
        if (includeColor && !includePosition) {
            onlyColor.setBackground(new GradientDrawable() {{
                setColor(0x00FFFFFF);
                setStroke(1, getResources().getColor(R.color.border_color_no_position));
                setColor(getResources().getColor(colors[shownIndexesColor.get(presentedScreens)]));
            }});
            if (zenMode) {
                setFadeInAnimationSquaresForZenMode(onlyColor);
            } else {
                onlyColor.setAlpha(1);
                onlyColor.setVisibility(VISIBLE);
            }
        }
    }


    private void screenInterruptionStart() {
        String text = Strings.firstStartTextPressButtonToS + "\n\n" + nBack + nBackLevel + "\n\n" + getTrialIndicator();
        txtVwMiddle.setText(text);
        txtVwMiddle.setTextSize(textUnit, textSizeMiddleTrial);
        btnExit.setVisibility(View.VISIBLE);
        if (!includePosition) {
            setDividersVisibleAddaptToMode(View.INVISIBLE);
            setMiddleSquareToRightSizeUI(scaleNoPos, R.color.border_color_no_position, text, textUnit, textSizeMiddleTrial, View.VISIBLE, View.VISIBLE);
        }
    }


    private void setFullScreen() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        screenOrientation = this.getResources().getConfiguration().orientation - 1;
        setRequestedOrientation(orientation);
    }


    private void prepareOtherStuff() {
        for (View square : squares) {
            square.setVisibility(View.INVISIBLE);
        }
        squares.get(4).setVisibility(View.INVISIBLE);
        int resId = darkModeTraining ? R.drawable.button_training_settings_sound_off_dark : R.drawable.button_training_settings_sound_off;
        if (playButtonSoundDuringTraining) {
            resId = darkModeTraining ? R.drawable.button_training_settings_sound_on_dark : R.drawable.button_training_settings_sound_on;
        }
        btnSoundOff.setImageDrawable(getResources().getDrawable(resId));
    }


    private void getViews() {
        layout = (ConstraintLayout) findViewById(R.id.layout);
        rectContainer = (ConstraintLayout) findViewById(R.id.rectContainer);
//        colorChooseLayout = (ConstraintLayout) findViewById(R.id.colorChooseLayout);
        try {
            splashImage = findViewById(R.id.splashImage);
        } catch (Exception e) {

        }

        txtVwnBackLevelInfo = (TextView) findViewById(R.id.nBackLevelInfo);
        squares.add(findViewById(R.id.button0));
        squares.add(findViewById(R.id.button1));
        squares.add(findViewById(R.id.button2));
        squares.add(findViewById(R.id.button3));
        squares.add(findViewById(R.id.button4));
        squares.add(findViewById(R.id.button5));
        squares.add(findViewById(R.id.button6));
        squares.add(findViewById(R.id.button7));
        squares.add(findViewById(R.id.button8));
        onlyColor = findViewById(R.id.onlyColor);

        btnColor = (Button) findViewById(R.id.buttonColor);
        btnAudio = (Button) findViewById(R.id.buttonAudio);
        btnPosition = (Button) findViewById(R.id.buttonPosition);

        btnAudioII = (Button) findViewById(R.id.buttonAudio2);
        btnPositionII = (Button) findViewById(R.id.buttonPosition2);
        btnExit = (Button) findViewById(R.id.buttonExit);
        btnSoundOff = (ImageView) findViewById(R.id.button_click_training_sound_off);
        btnOrientation = (ImageView) findViewById(R.id.button_click_training_orientation);
        btnMode = (ImageView) findViewById(R.id.button_click_training_mode);
        btnStyle = (ImageView) findViewById(R.id.button_click_training_style);
        btnSize = (ImageView) findViewById(R.id.button_click_training_size);
        btnGrid = (ImageView) findViewById(R.id.button_click_training_grid);
        btnFade = (ImageView) findViewById(R.id.button_click_training_fade);
        txtVwMiddle = (TextView) findViewById(R.id.txtVwMiddle);
        txtVwInterval = (TextView) findViewById(R.id.txtVwInterval);
//        txtVwInterval.setVisibility(INVISIBLE);
        infoTxt = (TextView) findViewById(R.id.infoTxt);

        dividerVertical1 = findViewById(R.id.dividerVertical1);
        dividerVertical2 = findViewById(R.id.dividerVertical2);
        dividerHorizontal1 = findViewById(R.id.dividerHorizontal1);
        dividerHorizontal2 = findViewById(R.id.dividerHorizontal2);
        dividers = new View[]{
                dividerVertical1, dividerVertical2, dividerHorizontal1, dividerHorizontal2
        };
        if (!showGrid) {
            for (int i = 0; i < dividers.length; i++) {
                dividers[i].setVisibility(INVISIBLE);
                dividers[i].setAlpha(0f);
            }
        }
        // Here it is decided which elements are included in the fade in transition at the beginning of the Session.


        txtVwnBackLevelInfo.setTextColor(getResources().getColor(R.color.border_color_no_position));
    }


    private void includeViewsToFadeInTransition() {
        List<View> viewsList = new ArrayList<>();
        viewsList.add(btnPosition);
        viewsList.add(btnPositionII);
        viewsList.add(btnAudio);
        viewsList.add(btnAudioII);
        viewsList.add(btnColor);
        viewsList.add(btnExit);
        viewsList.add(btnSoundOff);
        viewsList.add(btnOrientation);
        viewsList.add(btnMode);
        viewsList.add(btnStyle);
        viewsList.add(btnSize);
        viewsList.add(btnGrid);
        viewsList.add(btnFade);
        viewsList.add(txtVwMiddle);
        viewsList.add(infoTxt);
        viewsList.add(txtVwInterval);

        if (includePosition && showGrid) {
            viewsList.addAll(Arrays.asList(dividerVertical1, dividerVertical2, dividerHorizontal1, dividerHorizontal2));
        }

        if (includePosition) {
            viewsList.add(txtVwnBackLevelInfo);
        } else {
            viewsList.add(squares.get(4));
            viewsList.add(txtVwnBackLevelInfo);
        }

        views = viewsList.toArray(new View[0]);
    }


    private void setTextsToView() {
        btnPosition.setText(btnPosText);
        btnAudio.setText(btnAudText);
        btnPositionII.setText(btnPosIIText);
        btnAudioII.setText(btnAudIIText);
        btnColor.setText(btnColText);
        txtVwMiddle.setText(cross);
    }

    private void setOrientationListener() {
        trialIsRunning = false;
        if (screenOrientation != this.getResources().getConfiguration().orientation) {
            screenOrientation = this.getResources().getConfiguration().orientation;
            if (timerTrial != null) {
                timerTrial.cancel();
                timerClock.cancel();
            }
        }
    }

    private final void startTransitionToActivityTraining() {
//        transitionActivityAToB = new TransitionActivityAToB();
        int colorFrom = 0;
        int colorTo = 0;
        if (darkModeTraining && darkModeMenu) {
            colorFrom = getResources().getColor(R.color.black);
            colorTo = getResources().getColor(R.color.black);
        } else if (!darkModeTraining && darkModeMenu) {
            colorFrom = getResources().getColor(R.color.white);
            colorTo = getResources().getColor(R.color.black);
        } else if (darkModeTraining && !darkModeMenu) {
            colorFrom = getResources().getColor(R.color.black);
            colorTo = getResources().getColor(R.color.white);
        } else if (!darkModeTraining && !darkModeMenu) {
            colorFrom = getResources().getColor(R.color.white);
            colorTo = getResources().getColor(R.color.white);
        }
        transitionActivityAToB.startAnimation(colorFrom, colorTo);
    }

    private final void setActivityTransitions() {

        transitionActivityAToB = new TransitionActivityAToB();
        transitionActivityAToB.setTransitionAToB(this, this, layout, fadeoutAnimationDuration, 0, () -> {
            FileLogicSettings.saveSettings(ActivityTraining.this);
        }, () -> {
            Intent intent = new Intent(ActivityTraining.this, ActivityMenu.class);
            startActivity(intent);
            finish();
        }, views);
        transitionActivityAToB.setFadeInAnimationFirst();
    }

    private void setTrainingTransition() {
        transitionActivityAToB = new TransitionActivityAToB();
        transitionActivityAToB.setTransitionAToB(this, this, layout, fadeoutAnimationDuration, 0, () -> {

        }, () -> {
            Intent intent = new Intent(this, ActivityTraining.class);
            startActivity(intent);
            finish();
        }, views);
    }

    private void setSounds() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setUsage(AudioAttributes.USAGE_GAME).build();
            appSounds = new SoundPool.Builder().setMaxStreams(4).setAudioAttributes(attributes).build();
        } else {
            appSounds = new SoundPool(6, AudioAttributes.CONTENT_TYPE_MUSIC, 0);
        }
        buttonSound = appSounds.load(this, R.raw.click_training, 1);
        trainingSound = new int[10];
        trainingSound[0] = appSounds.load(this, R.raw.en_0, 1);
        trainingSound[1] = appSounds.load(this, R.raw.en_1, 1);
        trainingSound[2] = appSounds.load(this, R.raw.en_2, 1);
        trainingSound[3] = appSounds.load(this, R.raw.en_3, 1);
        trainingSound[4] = appSounds.load(this, R.raw.en_4, 1);
        trainingSound[5] = appSounds.load(this, R.raw.en_5, 1);
        trainingSound[6] = appSounds.load(this, R.raw.en_6, 1);
        trainingSound[7] = appSounds.load(this, R.raw.en_7, 1);
        trainingSound[8] = appSounds.load(this, R.raw.en_8, 1);
        trainingSound[9] = appSounds.load(this, R.raw.en_9, 1);
    }

    private void setTrialIndexes() {
        trialIsRunning = true;
        paused = false;
        maxPresentations = MAX_PRESENT_DEFAULT + nBack;
        // This is only the █  ▄  ▄  ▄  field on the training screen
        getTrialIndicator();
        // The nBack >= 3 statement adapts the number of possible matches in order to give a better training experience
        double add = nBack >= 3 ? (nBack * 0.008) : 0;
        counterMatchTresholdMin = nBack >= 3 ? 0.27 + add : 0.23;
        counterMatchTesholdMax = nBack >= 3 ? 0.40 + add : 0.40;
//    SessionParameters.counterMatchTesholdMin = 0.26;
//    SessionParameters.counterMatchTesholdMax = 0.48;
        if (includePosition) {
            RandomArrays.precalculateRandomPosition();
        }
        if (includeAudio) {
            RandomArrays.precalculateRandomAudio();
        }
        if (includeColor) {
            RandomArrays.precalculateRandomColor();
        }

        if (includePosition && !includeColor) {
            setDividersVisibleAddaptToMode(VISIBLE);
            setDividersVisibleAddaptToMode();
        }
        if (!includePosition && includeColor) {
            setDividersVisibleAddaptToMode(INVISIBLE);
            setDividersVisibleAddaptToMode();
        }
    }


    private void playAudio(int index) {
        appSounds.play(trainingSound[index], 1, 0, 0, 0, 1);
    }


    private void setMiddleSquareToRightSizeUI(int squareScale, int borderColor, String textMiddle, int unit, int textSize, int middleBtnVisible, int middleTextViewVisible) {
        GradientDrawable border = new GradientDrawable();
        // TEST
        border.setColor(0x00FFFFFF); //white background
        border.setStroke(1, getResources().getColor(borderColor)); //black border with full opacity

        squares.get(4).setBackground(border);
        squares.get(4).setScaleX(squareScale);
        squares.get(4).setScaleY(squareScale);
        onlyColor.setScaleX(squareScale);
        onlyColor.setScaleY(squareScale);

        squares.get(4).setVisibility(middleBtnVisible);
        txtVwMiddle.setText(textMiddle);
        txtVwMiddle.setTextSize(unit, textSize);
        txtVwMiddle.setVisibility(middleTextViewVisible);
    }

    private void startTrial() {
        txtVwnBackLevelInfo.setText(String.valueOf(nBack));
        txtVwMiddle.setText(cross);
        txtVwMiddle.setTextSize(textUnit, textSizeMiddleTrial);
        endOfTrial = false;
        setSettingsButtonVisibility(View.INVISIBLE);

        if (includePosition) {
            for (int n = 0; n < squares.size(); n++) {
                squares.get(n).setVisibility(View.INVISIBLE);
            }
        }

        resetTrialCounters();
        setTrialIndexes();

        if (includePosition && (!includeColor || includeColor)) {
            setDividersVisibleAddaptToMode(View.VISIBLE);
        }
        if (trialCounter > 0) {
            timeTrial = RepeatStorage.getStartTime();
        }
        setSquareSize();
        startClockTimer();
        startTrialTimer();
    }


    private void startTrialTimer() {

        timerTrial = new CountDownTimer(durationSessionTimer, (long) countDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (presentedScreens < 0) {
                    presentedScreens++;
                    Strings.timeSession = clockSession.getDuration(true);
                    setDeveloperInfoText(infoTxt);
                }
                Strings.timeTrialDevOps = clockTrialDevOps.getDuration(true);
                if (endOfTrial) {
                    processInputClicksDuringTrial();
                    onFinish();
                }
                if (presentedScreens >= 0 && !paused) {
                    showHideScreen = !showHideScreen;
                    if (showHideScreen) {
                        screenTrialHide();
                    } else {
                        processInputClicksDuringTrial();
                        if (presentedScreens == maxPresentations) {
                            endOfTrial = true;
                        } else if (presentedScreens != maxPresentations) {
                            screenTrialShow();
                        }
                        if (presentedScreens >= nBack) {
                            allowCountingMatches = true;
                        }
                        presentedScreens++;
                    }
                }
            }

            @Override
            public void onFinish() {
                storeTrialResults();
                trialIsRunning = false;
                endOfTrial = false;
                trialCounter++;
                if (!sessionWasCanceledEarly) {
                    nBackCulmulated += nBack;
                    addedNBACK++;
                    culmulateTrialResultsAtEndOfSession();
                    RepeatStorage.createStringToStoreAndroid(trialCounter >= trialsMax);
                    Log.d(TAG, (trialCounter >= trialsMax) ? "onFinishEndOfSession:\n" + stringToStore : "onFinishEndOfTrial:\n" + stringToStore);
                    setDividersVisibleAddaptToMode();
                    timeTrial = RepeatStorage.getStartTime();
                    dayTrial = RepeatStorage.getDay();
                    storeTrialResults();
                    if (trialCounter >= trialsMax) {
                        endOfSession = true;
                    }
                }
                inOrDecreaseNBackLevel();
                showScreenEndTrialResults();
                Strings.timeTrialDevOps = clockTrialDevOps.getDuration(false);
                setDeveloperInfoText(infoTxt);
                for (int i = 0; i < squares.size(); i++) {
                    squares.get(i).setVisibility(INVISIBLE);
                }
                timerTrial.cancel();
                timerClock.cancel();
            }
        };
        timerTrial.start();

    }



    private void startClockTimer() {
        timerClock = new CountDownTimer(durationSessionTimer, (long) 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (!paused) {
                    Strings.time = clockTrial.getDuration(true);
                    Strings.timeSession = clockSession.getDuration(true);
                    setDeveloperInfoText(infoTxt);
                }
            }

            @Override
            public void onFinish() {
                timerClock.cancel();
            }
        };
        timerClock.start();
    }


    private void setDividersVisibleAddaptToMode(int visible) {
        dividerVertical1.setVisibility(visible);
        dividerVertical2.setVisibility(visible);
        dividerHorizontal1.setVisibility(visible);
        dividerHorizontal2.setVisibility(visible);
    }

    private void setDividersVisibleAddaptToMode() {
        if (includePosition && !includeColor) {
            // That has to be set. Otherwhise the trial results that are shown at
            // the end of the trial will be displayed to large end exceed the screen.
            // That method must be reversed when the trial is continued.
            setMiddleSquareToRightSizeUI(scaleDefault, R.color.border_color_default, cross, textUnit, textSizeMiddleTrial, INVISIBLE, VISIBLE);
            dividerVertical1.setVisibility(VISIBLE);
            dividerVertical2.setVisibility(VISIBLE);
            dividerHorizontal1.setVisibility(VISIBLE);
            dividerHorizontal2.setVisibility(VISIBLE);
        }
        if (includePosition && includeColor) {
            // That has to be set. Otherwhise the trial results that are shown at
            // the end of the trial will be displayed to large end exceed the screen.
            // That method must be reversed when the trial is continued.
            setMiddleSquareToRightSizeUI(scaleDefault, R.color.border_color_default, cross, textUnit, textSizeMiddleTrial, INVISIBLE, VISIBLE);
            dividerVertical1.setVisibility(VISIBLE);
            dividerVertical2.setVisibility(VISIBLE);
            dividerHorizontal1.setVisibility(VISIBLE);
            dividerHorizontal2.setVisibility(VISIBLE);
        }

        if (includeColor && !includePosition) {
            setMiddleSquareToRightSizeUI(scaleNoPos, R.color.border_color_no_position, "", textUnit, textSizeMiddleTrial, VISIBLE, VISIBLE);
        }
    }

    private void storeTrialResults() {
        if (pos) {
            percentagesTrialPosition.add(percentageRightTrialPosition);
        }
        if (aud) {
            percentagesTrialAudio.add(percentageRightTrialAudio);
        }
        if (col) {
            percentagesTrialColor.add(percentageRightTrialColor);
        }
        if (pos && aud && col) {
            percentagesSessionAllModes.add((percentageRightTrialPosition + percentageRightTrialAudio + percentageRightTrialColor) / 3);
        } else if (pos && aud && !col) {
            percentagesSessionAllModes.add((percentageRightTrialPosition + percentageRightTrialAudio) / 2);
        } else if (pos && !aud && col) {
            percentagesSessionAllModes.add((percentageRightTrialPosition + percentageRightTrialColor) / 2);
        } else if (!pos && aud && col) {
            percentagesSessionAllModes.add((percentageRightTrialAudio + percentageRightTrialColor) / 2);
        } else if (pos && !aud && !col) {
            percentagesSessionAllModes.add((percentageRightTrialPosition));
        } else if (!pos && aud && !col) {
            percentagesSessionAllModes.add((percentageRightTrialAudio));
        } else if (!pos && !aud && col) {
            percentagesSessionAllModes.add((percentageRightTrialColor));
        }
    }


//    private void storeTrialResults() {
//
//        if (pos && aud && col) {
//            percentagesTrialPosition.add(percentageRightTrialPosition);
//            percentagesTrialAudio.add(percentageRightTrialAudio);
//            percentagesTrialColor.add(percentageRightTrialColor);
//            percentagesSessionAllModes.add((percentageRightTrialPosition + percentageRightTrialAudio + percentageRightTrialColor) / 3);
//        }
//        if (pos && aud && !col) {
//            percentagesTrialPosition.add(percentageRightTrialPosition);
//            percentagesTrialAudio.add(percentageRightTrialAudio);
//            percentagesSessionAllModes.add((percentageRightTrialPosition + percentageRightTrialAudio) / 2);
//        }
//        if (pos && !aud && col) {
//            percentagesTrialPosition.add(percentageRightTrialPosition);
//            percentagesTrialColor.add(percentageRightTrialColor);
//            percentagesSessionAllModes.add((percentageRightTrialPosition + percentageRightTrialColor) / 2);
//        }
//        if (!pos && aud && col) {
//            percentagesTrialAudio.add(percentageRightTrialAudio);
//            percentagesTrialColor.add(percentageRightTrialColor);
//            percentagesSessionAllModes.add((percentageRightTrialAudio + percentageRightTrialColor) / 2);
//        }
//
//        if (pos && !aud && !col) {
//            percentagesTrialPosition.add(percentageRightTrialPosition);
//            percentagesSessionAllModes.add((percentageRightTrialPosition));
//        }
//        if (!pos && aud && !col) {
//            percentagesTrialAudio.add(percentageRightTrialAudio);
//            percentagesSessionAllModes.add((percentageRightTrialAudio));
//        }
//        if (!pos && !aud && col) {
//            percentagesTrialColor.add(percentageRightTrialColor);
//            percentagesSessionAllModes.add((percentageRightTrialColor));
//        }
//    }

    private void culmulateTrialResultsAtEndOfSession() {
        // Deletes the values from any recent trial
        // so only the previous trial will be shown
        percentageTrialPosition = 0;
        percentageTrialAudio = 0;
        percentageTrialColor = 0;
        //These lines make sure that the percentage will be displayed correctly when the culmulatedString is
        //set the first time.

        if (pos && aud && col) {
            countPosAtEndOfTrial();
            countAudAtEndOfTrial();
            countColAtEndOfTrial();
        }

        if (pos && aud && !col) {
            countPosAtEndOfTrial();
            countAudAtEndOfTrial();
        }
        if (pos && !aud && col) {
            countPosAtEndOfTrial();
            countColAtEndOfTrial();
        }
        if (!pos && aud && col) {
            countAudAtEndOfTrial();
            countColAtEndOfTrial();
        }

        if (pos && !aud && !col) {
            countPosAtEndOfTrial();
        }
        if (!pos && aud && !col) {
            countAudAtEndOfTrial();
        }

        if (!pos && !aud && col) {
            countColAtEndOfTrial();
        }
        percentageRightTrialAllModes = (percentageRightTrialPosition + percentageRightTrialAudio + percentageRightTrialColor) / includedModes;
        percentageRightSessionAllModes = percentageRightSessionAllModes + percentageRightTrialAllModes;
        Log.d(TAG, "culmulateTrialResultsAtEndOfSession:\n" + stringToStore);

    }

    private double countColAtEndOfTrial() {
        try {
            for (int i = 0; i < trialCounter; i++) {
                percentagesSessionColor.add(percentagesTrialColor.get(i));
                percentageTrialColor = percentageTrialColor + percentagesTrialColor.get(i);
            }
            percentageSessionColor = percentageTrialColor / trialCounter;
        } catch (Exception e) {
            percentageSessionColor = 0;
        }
        return percentageSessionColor;
    }

    private double countAudAtEndOfTrial() {
        try {
            for (int i = 0; i < trialCounter; i++) {
                percentagesSessionAudio.add(percentagesTrialAudio.get(i));
                percentageSessionAudio = percentageSessionAudio + percentagesTrialAudio.get(i);
            }
            percentageSessionAudio = percentageSessionAudio / trialCounter;
        } catch (Exception e) {
            percentageSessionAudio = 0;
        }
        return percentageSessionAudio;
    }

    private double countPosAtEndOfTrial() {
        try {
            for (int i = 0; i < trialCounter; i++) {
                percentagesSessionPosition.add(percentagesTrialPosition.get(i));
                percentageSessionPosition = percentageSessionPosition + percentagesTrialPosition.get(i);
            }
            percentageSessionPosition = percentageSessionPosition / trialCounter;
        } catch (Exception e) {
            percentageSessionPosition = 0;
        }
        return percentageSessionPosition;
    }




    private void resetSession() {
        //This method must run always after the session was stored
        //In case that the user interrupts the session nothing will be stored
        //but this method has to run anyway
        allowCountingMatches = false;
        trialCounter = 0;
        endOfSession = false;
        sessionWasCanceledEarly = false;
        nBackMax = nBackBegin;
        stringToStore = "";
        // TESTVALUE
        addedNBACK = 0;
        Strings.timeSession = "";
        nBackCulmulated = 0;
        inOrDecreaseCulmulated = 0;
        clockSession.resetTime();

    }


    private void processInputClicksDuringTrial() {
        if (allowCountingMatches) {
            shownAndCounted++;
            if (includePosition) {
                processPositionInputDuringTrial();
            }
            if (includeAudio) {
                processAudioInputDuringTrial();
            }
            if (includeColor) {
                processColorDuringTrial();
            }
        }
    }

    private void processPositionInputDuringTrial() {
        clickedPositionRight = storeShownIndexes(matchesPosition, clickedPositionRight, btnPosition, presentedScreens, nBack, positionIsClicked, allowCountingMatches);
        lastClickRightPos = clickedPositionRight.get(clickedPositionRight.size() - 1);
        if (lastClickRightPos) {
            counterClickedRightPosition++;
        }
        double tempShownCounter = shownAndCounted;
        double tempCounterClickedRightPosition = counterClickedRightPosition;
        percentageRightTrialPosition = tempCounterClickedRightPosition / tempShownCounter;
        positionIsClicked = false;
        positionIsRight = false;
    }

    private void processAudioInputDuringTrial() {
        clickedAudioRight = storeShownIndexes(matchesAudio, clickedAudioRight, btnAudio, presentedScreens, nBack, audioIsClicked, allowCountingMatches);
        lastClickRightAud = clickedAudioRight.get(clickedAudioRight.size() - 1);
        if (lastClickRightAud) {
            counterClickedRightAudio++;
        }
        double tempShownCounter = shownAndCounted;
        double tempCounterClickedRightAudio = counterClickedRightAudio;
        percentageRightTrialAudio = tempCounterClickedRightAudio / tempShownCounter;
        audioIsClicked = false;
        audioIsRight = false;

    }

    private void processColorDuringTrial() {
        clickedColorRight = storeShownIndexes(matchesColor, clickedColorRight, btnColor, presentedScreens, nBack, colorIsClicked, allowCountingMatches);
        lastClickRightCol = clickedColorRight.get(clickedColorRight.size() - 1);
        if (lastClickRightCol) {
            counterClickedRightColor++;
        }
        double tempShownCounter = shownAndCounted;
        double tempCounterClickedRightColor = counterClickedRightColor;
        percentageRightTrialColor = tempCounterClickedRightColor / tempShownCounter;
        colorIsClicked = false;
        colorIsRight = false;
    }

//    private boolean modeButtonClicked() {
//        boolean clicked = false;
//        if (playButtonSoundDuringTraining) {
//            appSounds.play(buttonSound, 1, 1, 1, 0, 1);
//        }
//        if (!paused && trialIsRunning) {
//            clicked = true;
//        }
//        if (endOfSession) {
//            endSession();
//        } else if (!trialIsRunning) {
//            if (resultScreenIndex == 0) {
//                startTrial();
//            } else {
//                showScreenEndTrialResults();
//            }
//        } else if (paused && trialIsRunning) {
//
//            showAndHidePauseScreen();
//        }
//        setDeveloperInfoText(infoTxt);
//        return clicked;
//    }

    private boolean modeButtonClicked() {
        if (playButtonSoundDuringTraining) {
            appSounds.play(buttonSound, 1, 1, 1, 0, 1);
        }
        if (endOfSession) {
            endSession();
        } else if (trialIsRunning) {
            if (paused) {
                showAndHidePauseScreen();
            } else {
                return true;
            }
        } else {
            if (resultScreenIndex == 0) {
                startTrial();
            } else {
                showScreenEndTrialResults();
            }
        }
        setDeveloperInfoText(infoTxt);
        return false;
    }


    private void endSession() {
        resetTrialCounters();
//            finish();
//            txtVwMiddle.setTextSize(32f);
//            txtVwMiddle.setText(Strings.goodJob);
        useTempResults = true;

//            setFadeOutAnimation(views);
//            transitionActivityAToB.startAnimation();
        setDividersVisibleAddaptToMode(INVISIBLE);
        ResultsFiles.storeTempResults(this);
        startTransitionToActivityTraining();
//            Intent intent = new Intent(ActivityTraining.this, ActivityMenu.class);
//            startActivity(intent);
    }
    private void setPausedFromMiddleSplittedClick() {
        developerTimerCounting();

        if (trialIsRunning) {
            paused = true;
            String indicator = getTrialIndicator();
            setDividersVisibleAddaptToMode(INVISIBLE);
            btnExit.setVisibility(VISIBLE);
            txtVwMiddle.setText(String.format("%s\n\n%s%s\n\n%s\n\n%s", Strings.pause, nBack, nBackLevel, pressButtonToC, indicator));
            txtVwMiddle.setTextSize(textUnit, textSizeMiddleTrial);
            squares.forEach(square -> square.setVisibility(View.INVISIBLE));
        }
    }
//    private void setPausedFromMiddleSplittedClick() {
//
//        developerTimerCounting();
//
//        if (trialIsRunning) {
//            paused = true;
//            String indicator = getTrialIndicator();
//            setDividersVisibleAddaptToMode(INVISIBLE);
//            btnExit.setVisibility(VISIBLE);
//            txtVwMiddle.setText(Strings.pause + "\n\n" + nBack + nBackLevel + "\n\n" + pressButtonToC + "\n\n" + indicator);
//            txtVwMiddle.setTextSize(textUnit, textSizeMiddleTrial);
//            for (int i = 0; i < squares.size(); i++) {
//                squares.get(i).setVisibility(View.INVISIBLE);
//            }
//        }
//    }

    private void setPausedFromMiddle() {

        developerTimerCounting();
        if (trialIsRunning) {
            paused = true;
            showHideScreen = false;
            String indicator = getTrialIndicator();
            setDividersVisibleAddaptToMode(INVISIBLE);
            setSettingsButtonVisibility(VISIBLE);
            txtVwMiddle.setText(Strings.pause + "\n\n" + nBack + nBackLevel + "\n\n" + pressButtonToC + "\n\n" + indicator);
            txtVwMiddle.setTextSize(textUnit, textSizeMiddleTrial);
            for (int i = 0; i < squares.size(); i++) {
                squares.get(i).setVisibility(View.INVISIBLE);
            }
        }
    }

    private String getTrialIndicator() {
        String indicator = "";
        trialIndicator = new String[trialsMax + 1];
        for (int i = 0; i <= trialsMax; i++) {
            trialIndicator[i] = Strings.trialDefaultIndicator;
        }
        trialIndicator[trialCounter] = Strings.trialActualIndicator;
        for (int i = 0; i < trialsMax; i++) {
            indicator = indicator + trialIndicator[i];
        }
        return indicator;
    }

    private void showAndHidePauseScreen() {
        if (paused) {
            FileLogicSettings.saveSettings(this);
            allowToChangeSquareSize = false;
            allowToChangeColorStyle = false;
            if (!includePosition && includeColor) {
                onlyColor.setAlpha(0);
                onlyColor.setVisibility(INVISIBLE);
            }
        } else {
            paused = false;
            if (developerTimer == null) {
                startDeveloperTimer();
            }

            if (!includePosition && includeColor) {
                txtVwMiddle.setText("");
            } else {
                txtVwMiddle.setText(cross);
            }
            txtVwMiddle.setTextSize(textUnit, textSizeMiddleTrial);
            squares.forEach(square -> square.setVisibility(View.INVISIBLE));

            if (includePosition) {
                setDividersVisibleAddaptToMode(VISIBLE);
            } else {
                squares.get(4).setVisibility(View.VISIBLE);
                onlyColor.setVisibility(INVISIBLE);
                setDividersVisibleAddaptToMode(INVISIBLE);
            }
            setSettingsButtonVisibility(INVISIBLE);
        }
    }





    private static void handleColorEdit(EditText editText, Button btn, int colorComponent) {
        try {
            String temp = editText.getText().toString();
            if (!temp.isEmpty()) {
                colorComponent = Integer.parseInt(temp);
                colorComponent = Math.max(0, Math.min(255, colorComponent)); // limit the value to the range [0, 255]
                editText.setText(Integer.toString(colorComponent)); // update the text in the EditText
            }
            customColorSquare = Color.rgb(r, g, b);
            hexColor = String.format("#%06X", (0xFFFFFF & customColorSquare));
            btn.setBackgroundColor(customColorSquare);
        } catch (Exception e) {
            // do nothing if an exception is thrown
        }
    }


    private void setSettingsButtonVisibility(int visibility) {
        btnPositionII.setVisibility(visibility);
        btnAudioII.setVisibility(visibility);
        btnExit.setVisibility(visibility);
        btnSoundOff.setVisibility(visibility);
        btnOrientation.setVisibility(visibility);
        btnMode.setVisibility(visibility);
        btnStyle.setVisibility(visibility);
        btnSize.setVisibility(visibility);
        btnGrid.setVisibility(visibility);
        btnFade.setVisibility(visibility);
        txtVwInterval.setVisibility(visibility);
    }


    private void showCustomColorDialog() {
        ConstraintLayout constraintLayout;
        Dialog dialog = new Dialog(this);
        int color = Color.rgb(r, g, b);
        int backgroundColor, backgroundColorLabel, strokeColor, strokeColorLabel;
        if (!darkModeTraining) {
            dialog.setContentView(R.layout.view_custom_color_box);
            constraintLayout = (ConstraintLayout) dialog.findViewById(R.id.alert_background);
            backgroundColor = getResources().getColor(R.color.menu_background_color);
            backgroundColorLabel = getResources().getColor(R.color.menu_background_color);
            strokeColor = getResources().getColor(R.color.menu_background_stroke_color);
            strokeColorLabel = getResources().getColor(R.color.transparent);
        } else {
            dialog.setContentView(R.layout.view_custom_color_box_dark);
            constraintLayout = (ConstraintLayout) dialog.findViewById(R.id.alert_background);
            backgroundColor = getResources().getColor(R.color.menu_background_color_dark);
            backgroundColorLabel = getResources().getColor(R.color.menu_background_color);
            strokeColor = getResources().getColor(R.color.menu_background_stroke_color);
            strokeColorLabel = getResources().getColor(R.color.transparent);
        }

        GradientDrawable gradientDrawableBackground = new GradientDrawable();
        gradientDrawableBackground.setShape(GradientDrawable.RECTANGLE);
        gradientDrawableBackground.setColor(backgroundColor);
        gradientDrawableBackground.setStroke(2, strokeColor);
        gradientDrawableBackground.setCornerRadius(5);

        GradientDrawable gradientDrawableLabel = new GradientDrawable();
        gradientDrawableLabel.setShape(GradientDrawable.RECTANGLE);
        gradientDrawableLabel.setColor(backgroundColor);
        gradientDrawableLabel.setStroke(2, strokeColorLabel);
        gradientDrawableLabel.setCornerRadius(5);

//        dialog.setContentView(R.layout.view_menu_custom_color);


        EditText edR = dialog.findViewById(R.id.editTextColorR);
        EditText edG = dialog.findViewById(R.id.editTextColorG);
        EditText edB = dialog.findViewById(R.id.editTextColorB);
        TextView edRH = dialog.findViewById(R.id.hintTextViewColorR);
        TextView edGH = dialog.findViewById(R.id.hintTextViewColorG);
        TextView edBH = dialog.findViewById(R.id.hintTextViewColorB);

        edR.setBackground(gradientDrawableBackground);
        edG.setBackground(gradientDrawableBackground);
        edB.setBackground(gradientDrawableBackground);

        EditText repeat = dialog.findViewById(R.id.repeat);
        TextView repeatHint = dialog.findViewById(R.id.repeatHint);
        repeat.setBackground(gradientDrawableBackground);
        repeatHint.setBackground(gradientDrawableBackground);

        edRH.setBackground(gradientDrawableLabel);
        edGH.setBackground(gradientDrawableLabel);
        edBH.setBackground(gradientDrawableLabel);
        repeatHint.setBackground(gradientDrawableLabel);

        edR.setText("" + r);
        edG.setText("" + g);
        edB.setText("" + b);
        repeat.setText("" + MAX_PRESENT_DEFAULT);
        customColorSquare = Color.rgb(r, g, b);
        hexColor = String.format("#%06X", (0xFFFFFF & customColorSquare));

        edRH.setText(Strings.labelEditTextCustomColorTextR);
        edGH.setText(Strings.labelEditTextCustomColorTextG);
        edBH.setText(Strings.labelEditTextCustomColorTextB);
        Button btn = dialog.findViewById(R.id.btnBottom);
//        btn.setBackgroundColor(customColorSquare);
        Button sampleSquare = dialog.findViewById(R.id.sampleSquare);
        sampleSquare.setBackgroundColor(customColorSquare);

        btn.setOnClickListener(c -> {
            FileLogicSettings.saveSettings(this);
        });

        repeat.setOnKeyListener(new EditText.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                try {
                    String temp = repeat.getText().toString();
                    int MAX_PRESENT = Integer.parseInt(temp);
                    if (!temp.isEmpty()) {
                        MAX_PRESENT = Integer.parseInt(temp);
                        if (MAX_PRESENT < 1) {
                            MAX_PRESENT = 15;
                            repeat.setText("" + MAX_PRESENT);
                        }
                        if (MAX_PRESENT > 150) {
                            MAX_PRESENT = 50;
                            repeat.setText("" + MAX_PRESENT);
                        }
                        MAX_PRESENT_DEFAULT = MAX_PRESENT;
                    }
                } catch (Exception e) {

                }
                return false;
            }
        });

        edR.setOnKeyListener(new EditText.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                try {
                    String temp = edR.getText().toString();
                    if (!temp.isEmpty()) {
                        r = Integer.parseInt(temp);
                        if (r < 0) {
                            r = 0;
                            edR.setText("" + r);
                        }
                        if (r > 255) {
                            r = 255;
                            edR.setText("" + r);
                        }
//                        edR.setText(""+SessionParameters.r);
                    }
                    customColorSquare = Color.rgb(r, g, b);
                    hexColor = String.format("#%06X", (0xFFFFFF & customColorSquare));
                    btn.setText("" + hexColor);
                    sampleSquare.setBackgroundColor(customColorSquare);
                } catch (Exception e) {

                }
                return false;
            }
        });
        edG.setOnKeyListener(new EditText.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                try {
                    String temp = edG.getText().toString();
                    if (!temp.isEmpty()) {
                        g = Integer.parseInt(temp);
                        if (g < 0) {
                            g = 0;
                            edG.setText("" + g);
                        }
                        if (g > 255) {
                            g = 255;
                            edG.setText("" + g);
                        }
//
                    }
                    customColorSquare = Color.rgb(r, g, b);
                    hexColor = String.format("#%06X", (0xFFFFFF & customColorSquare));
                    btn.setText("" + hexColor);
                    sampleSquare.setBackgroundColor(customColorSquare);

                } catch (Exception e) {

                }
                return false;
            }
        });
        edB.setOnKeyListener(new EditText.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                try {
                    String temp = edB.getText().toString();
                    if (!temp.isEmpty()) {
                        b = Integer.parseInt(temp);
                        if (b < 0) {
                            b = 0;
                            edB.setText("" + b);
                        }
                        if (b > 255) {
                            b = 255;
                            edB.setText("" + b);
                        }
//
                    }
                    customColorSquare = Color.rgb(r, g, b);
                    hexColor = String.format("#%06X", (0xFFFFFF & customColorSquare));
                    btn.setText("" + hexColor);
                    sampleSquare.setBackgroundColor(customColorSquare);
                } catch (Exception e) {

                }
                return false;
            }
        });


//        btn_manual.setOnClickListener(c -> {
//            SessionParameters.openManual = true;
//            Intent intent = new Intent(ActivityMenu.this, ActivityAbout.class);
//            try {
//                Thread.sleep(300);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            startActivity(intent);
//        });

        dialog.show();

    }


//    public GradientDrawable createDrawable(Context context) {
//        GradientDrawable gradientDrawable = new GradientDrawable();
//        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
//        gradientDrawable.setColor(ContextCompat.getColor(context, R.color.menu_item_background_color));
//        gradientDrawable.setStroke(2, ContextCompat.getColor(context, R.color.menu_item_stroke_color));
//        gradientDrawable.setCornerRadius(5);
//        return gradientDrawable;
//    }

    private void showDialogAlertExit() {

        dialog = new Dialog(this);
        if (!darkModeTraining) {
            dialog.setContentView(R.layout.view_alert_box);
        } else {
            dialog.setContentView(R.layout.view_alert_box_dark);
        }

        positive = (Button) dialog.findViewById(R.id.btn_positive);
        negative = (Button) dialog.findViewById(R.id.btn_negative);
        negative.setOnClickListener(c -> {
            appSounds.play(buttonSound, 1, 1, 1, 0, 1);
            dialog.hide();
            btnPosition.setEnabled(false);
            btnAudio.setEnabled(false);
            btnPositionII.setEnabled(false);
            btnAudioII.setEnabled(false);
            btnColor.setEnabled(false);
            btnExit.setEnabled(false);
            sessionWasCanceledEarly = true;
            orientationWasChangedDuringTraining = false;
            if (timerTrial != null) {
                timerTrial.onFinish();
                timerClock.onFinish();
            }
            dialog.cancel();
//            transitionActivityAToB.startAnimation();
            setDividersVisibleAddaptToMode(INVISIBLE);
            startTransitionToActivityTraining();
//            setFadeOutAnimation(views);
        });
        positive.setOnClickListener(c -> {
            appSounds.play(buttonSound, 1, 1, 1, 0, 1);
            dialog.cancel();
            paused = true;
        });
        dialog.show();
    }

    private void showScreenEndTrialResults() {
        btnExit.setVisibility(View.VISIBLE);

        if (!sessionWasCanceledEarly) {
            setDividersVisibleAddaptToMode(View.INVISIBLE);
            txtVwMiddle.setVisibility(View.VISIBLE);
            squares.get(4).setVisibility(View.INVISIBLE);


            if (resultScreenIndex == 0) {
                Strings.createTrialEndTextPercentage();
                endOfTrialDialogIsVisible = true;
                setMiddleSquareToRightSizeUI(scaleDefault, R.color.border_color_no_position, trialEndTextPercentage, textUnit, textSizeMiddleResults, View.INVISIBLE, View.VISIBLE);
                txtVwMiddle.setTextSize(textUnit, textSizeMiddleResults);
                txtVwMiddle.setText(trialEndTextPercentage + "\n\n" + pressButtonToC);
            } else {
                txtVwMiddle.setTextSize(textUnit, textSizeMiddleTrial);
                String indicator = getTrialIndicator();
                if (endOfSession) {
                    txtVwMiddle.setText(trialEndTextPercentage + "\n\n" + Strings.nBackMax + nBackMax + nBackLevel + "\n\n" + Strings.pressButtonToFinish + "\n\n" + indicator);
                } else {
                    txtVwMiddle.setText(nBack + nBackLevel + "\n\n" + pressButtonToS + "\n\n" + indicator);
                }
            }
            resultScreenIndex++;
            if (resultScreenIndex == 2) {
                resultScreenIndex = 0;
            }
        }
    }


    private void resetTrialCounters() {

        if (!orientationWasChangedDuringTraining) {
            presentedScreens = -2;
            shownAndCounted = 0;
            increasedCounterPosition = 0;

            counterMatchesPos = 0;
            counterClickedRightPosition = 0;
            percentageRightTrialPosition = 0;

            counterMatchesAud = 0;
            counterClickedRightAudio = 0;
            percentageRightTrialAudio = 0;

            counterMatchesCol = 0;
            counterClickedRightColor = 0;
            percentageRightTrialColor = 0;

            positionIsClicked = false;
            audioIsClicked = false;
            colorIsClicked = false;
            Strings.time = clockTrial.resetTime();
        } else {
            orientationWasChangedDuringTraining = false;
        }
    }

    private void developerTimerCounting() {
        if (!developerTimerIsRunning) {
            startDeveloperTimer();
        }
        if (developerCountdownClick == 0) {
            developerTimer.start();
        }
        developerCountdownClick++;
    }

    public void startDeveloperTimer() {
        int durationInMilliSeconds = 2000;
        developerTimerIsRunning = true;
        developerTimer = new CountDownTimer(durationInMilliSeconds, 1000 /*Tick durationSessionInTrials*/) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (developerCountdown >= developerCountMax) {
                    developerTimer.onFinish();
                }
                developerCountdown++;
            }

            @Override
            public void onFinish() {
                if (developerCountdownClick >= developerCountMax) {
                    if (developerInfoAreVisible) {
                        infoTxt.setVisibility(View.INVISIBLE);
                        developerInfoAreVisible = false;
                        btnPosition.setAlpha(1.0f);
                    } else {
                        infoTxt.setVisibility(VISIBLE);
                        developerInfoAreVisible = true;
                        btnPosition.setAlpha(0.5f);
                    }
                }
                setDeveloperInfoText(infoTxt);
                developerTimer.cancel();
                developerCountdown = 0;
                developerCountdownClick = 0;
            }
        };
    }

    private void changeSquaresStandardColor() {
        if (squareDefaultColorIndex > colors.length - 1) {
            squareDefaultColorIndex = 1;
        }
        for (int i = 0; i < squares.size(); i++) {
            squares.get(i).setBackgroundColor(getResources().getColor(colors[squareDefaultColorIndex]));
            squares.get(i).setVisibility(INVISIBLE);
        }
        if (!includePosition && includeColor) {
            onlyColor.setBackgroundColor(getResources().getColor(colors[squareDefaultColorIndex]));
        }
        FileLogicSettings.saveSettings(this);
    }

    private void setFadeInAnimationSquaresForZenMode(View v) {


        Animation mLoadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
        mLoadAnimation.setDuration(fadeInterval);
        mLoadAnimation.setStartOffset(0);
        mLoadAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                setFadeOutAnimationSquares(v);
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationStart(Animation arg0) {
                v.setAlpha(1);
            }
        });

        v.startAnimation(mLoadAnimation);
    }


    private void setFadeOutAnimationSquares(View v) {
        Animation mLoadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_out);
        mLoadAnimation.setDuration(fadeInterval);
        mLoadAnimation.setStartOffset(0);
        mLoadAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                if (zenMode && includeColor && !includePosition) {
                    v.setAlpha(0);
                }
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationStart(Animation arg0) {

            }
        });
        v.startAnimation(mLoadAnimation);
    }

    private void setColorAndInvisible() {

        for (int i = 0; i < squares.size(); i++) {
            if (i != 4) {
                squares.get(i).setBackgroundColor(getResources().getColor(colors[squareDefaultColorIndex]));
                squares.get(i).setVisibility(INVISIBLE);
            }
        }
        if (!includePosition && includeColor) {
            squares.get(4).setVisibility(VISIBLE);
            squares.get(4).setAlpha(1);
            onlyColor.setVisibility(INVISIBLE);
            onlyColor.setAlpha(0);


        }
        allowToChangeSquareSize = false;
        allowToChangeColorStyle = false;
    }

    private void setOnClickListenersToSquares() {
//        if (SessionParameters.allowToChangeColorStyle == true) {

        squares.get(0).setOnClickListener(c -> {
            squaresOnClickLogic(1);
        });
        squares.get(1).setOnClickListener(c -> {
            squaresOnClickLogic(2);
        });
        squares.get(2).setOnClickListener(c -> {
            squaresOnClickLogic(3);
        });
        squares.get(3).setOnClickListener(c -> {
            squaresOnClickLogic(4);
        });
        squares.get(4).setOnClickListener(c -> {
            setPausedFromMiddle();
            squaresOnClickLogic(5);
        });
        onlyColor.setOnClickListener(c -> {
            setPausedFromMiddle();
//            squaresOnClickLogic(5);
        });
        squares.get(5).setOnClickListener(c -> {
            squaresOnClickLogic(6);
        });
        squares.get(6).setOnClickListener(c -> {
            squaresOnClickLogic(7);
        });
        squares.get(7).setOnClickListener(c -> {
            squaresOnClickLogic(8);
        });
        squares.get(8).setOnClickListener(c -> {
            squaresOnClickLogic(9);
        });

        allowToChangeColorStyle = false;
        FileLogicSettings.saveSettings(this);
    }

    private void squaresOnClickLogic(int i) {
        if (allowToChangeColorStyle) {
            squareDefaultColorIndex = i;
            changeSquaresStandardColor();
        }
    }

    private void setOnClickListenersToUiElements() {
        btnPosition.setOnClickListener(this);
        btnAudio.setOnClickListener(this);
        btnPositionII.setOnClickListener(this);
        btnAudioII.setOnClickListener(this);
        btnColor.setOnClickListener(this);
        btnExit.setOnClickListener(this);
        squares.get(4).setOnClickListener(this);
        onlyColor.setOnClickListener(this);
        txtVwMiddle.setOnClickListener(this);
        infoTxt.setOnClickListener(this);
        btnSoundOff.setOnClickListener(this);
        btnOrientation.setOnClickListener(this);
        btnMode.setOnClickListener(this);
        btnStyle.setOnClickListener(this);
        btnSize.setOnClickListener(this);
        btnGrid.setOnClickListener(this);
        btnFade.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonPosition) {
            positionIsClicked = modeButtonClicked();
        }
        if (v.getId() == R.id.buttonAudio) {
            audioIsClicked = modeButtonClicked();
        }
        if (v.getId() == R.id.buttonPosition2) {
            //-
            if (allowToChangeSquareSize) {
                setCustomSize(true, 1);
                FileLogicSettings.saveSettings(this);
            } else {
                speedPercentage = speedPercentage + 0.05;
                setIntervalText();
                txtVwInterval.setText(lengthTrial);
                if (timerTrial != null) {
                    timerTrial.cancel();
                    startTrialTimer();
                }
            }
        }
        if (v.getId() == R.id.buttonAudio2) {
            //+
            if (allowToChangeSquareSize) {
                setCustomSize(true, -1);
                FileLogicSettings.saveSettings(this);

            } else {
                speedPercentage = speedPercentage - 0.05;
                setIntervalText();
                txtVwInterval.setText(lengthTrial);
                if (timerTrial != null) {
                    timerTrial.cancel();
                    startTrialTimer();
                }
            }

        }
        if (v.getId() == R.id.buttonColor) {
            colorIsClicked = modeButtonClicked();
        }
        if (v.getId() == R.id.button4) {
            appSounds.play(buttonSound, 1, 1, 1, 0, 1);
            setPausedFromMiddle();
        }
        if (v.getId() == R.id.buttonExit) {
            appSounds.play(buttonSound, 1, 1, 1, 0, 1);
            showDialogAlertExit();
        }
        if (v.getId() == R.id.button_click_training_sound_off) {
            playButtonSoundDuringTraining = !playButtonSoundDuringTraining;
            FileLogicSettings.saveSettings(this);
            setSettingsBtnSoundColor();
            if (playButtonSoundDuringTraining) {
                Toast.makeText(getApplicationContext(), Strings.sound_click_training_on, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), Strings.sound_click_training_off, Toast.LENGTH_SHORT).show();
            }
        }
        if (v.getId() == R.id.button_click_training_orientation) {
            if (timerTrial != null) {
                timerTrial.cancel();
            }
            orientationWasChangedDuringTraining = true;
            if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
            } else {
                orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
            }

            setRequestedOrientation(orientation);
            FileLogicSettings.saveSettings(this);
        }

        if (v.getId() == R.id.button_click_training_mode) {
            darkModeTraining = !darkModeTraining;
            FileLogicSettings.saveSettings(this);
            setModeColors();
        }
        if (v.getId() == R.id.button_click_training_style) {
            if (!allowToChangeSquareSize) {

                if (!allowToChangeColorStyle) {
                    try {
                        allowToChangeColorStyle = true;
                        for (int i = 0; i < squares.size(); i++) {
                            squares.get(i).setBackgroundColor(getResources().getColor(colors[i + 1]));
                            squares.get(i).setVisibility(VISIBLE);
                            squares.get(i).setScaleX(0.9f);
                            squares.get(i).setScaleY(0.9f);
                        }
                    } catch (Exception e) {
                        // Handle error
                    }
                    Toast.makeText(this, Strings.changeSquareColorText, Toast.LENGTH_LONG).show();
                } else {
                    try {
                        allowToChangeSquareSize = false;
                        allowToChangeColorStyle = false;
                        for (int i = 0; i < squares.size(); i++) {
                            squares.get(i).setVisibility(INVISIBLE);
                        }
                        setSquareSize();
                    } catch (Exception e) {
                        // Handle error
                    }
                }
            } else {
                for (int i = 0; i < squares.size(); i++) {
                    squares.get(i).setVisibility(INVISIBLE);
                    setSquareSize();
                }
                allowToChangeSquareSize = false;
                allowToChangeColorStyle = false;
            }
        }
        if (v.getId() == R.id.button_click_training_size) {

            allowToChangeColorStyle = false;
            if (!allowToChangeSquareSize) {
                showCustomColorDialog();
//                setSquareSize();
                if (!includePosition && includeColor) {
                    squares.get(4).setVisibility(VISIBLE);
                    squares.get(4).setAlpha(1);

                    onlyColor.setVisibility(VISIBLE);
                    onlyColor.setAlpha(1);
                } else {
                    for (int i = 0; i < squares.size(); i++) {
                        squares.get(i).setVisibility(VISIBLE);
                    }

                }

                allowToChangeSquareSize = true;
                Toast.makeText(this, Strings.changeSquareSizeText, Toast.LENGTH_SHORT).show();
            } else {
                squares.get(4).setAlpha(alpha);
                squares.get(4).setVisibility(visibility);
                setColorAndInvisible();
                setCustomSize(false, 1);
                FileLogicSettings.saveSettings(this);
                allowToChangeSquareSize = false;
            }
        }


        if (v.getId() == R.id.button_click_training_grid) {
            setDividersVisible(true);
            FileLogicSettings.saveSettings(this);
        }
        if (v.getId() == R.id.button_click_training_fade) {
            zenMode = !zenMode;
            if (!darkModeTraining) {
                if (zenMode) {
                    btnFade.setBackground(getResources().getDrawable(R.drawable.button_training_settings_fade_true));
                } else {
                    btnFade.setBackground(getResources().getDrawable(R.drawable.button_training_settings_fade_false));
                }
            } else {
                if (zenMode) {
                    btnFade.setBackground(getResources().getDrawable(R.drawable.button_training_settings_fade_true_dark));
                } else {
                    btnFade.setBackground(getResources().getDrawable(R.drawable.button_training_settings_fade_false_dark));
                }
            }
            if (zenMode) {
                Toast.makeText(this, Strings.fadeTextYes, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, Strings.fadeTextNo, Toast.LENGTH_SHORT).show();
            }
            FileLogicSettings.saveSettings(this);
        }
        if (v.getId() == R.id.txtVwMiddle) {
            setPausedFromMiddle();
        }
        if (v.getId() == R.id.btn_positive) {

        }
        if (v.getId() == R.id.btn_negative) {

        }
        if (v.getId() == R.id.infoTxt) {
            if (!setTestValues) {
                setTestValues = true;
//                decreaseThreshold = 0.45;
//                increaseThreshold = 0.65;
                speedPercentage = 0.07;
            } else {
                setTestValues = false;
//                decreaseThreshold = 0.75;
//                increaseThreshold = 0.85;
                speedPercentage = 1;
            }
            countDownInterval = countDownIntervalDefault * speedPercentage;
            if (timerTrial != null) {
                timerTrial.cancel();
                timerClock.cancel();
            }
            startTrial();
        }
        if (v.getId() == R.id.nBackLevelInfo) {

        }
    }


//    public static void setIntervalText() {
//
//        if (speedPercentage < 0.25) {
//            speedPercentage = 0.25;
//        }
//        if (speedPercentage > 2.75) {
//            speedPercentage = 2.75;
//        }
//        countDownInterval = countDownIntervalDefault * speedPercentage;
//        double percentage = countDownIntervalDefault / countDownInterval * 100;
//        int tempPercentage = (int) percentage;
//        percentage = tempPercentage;
//        if (percentage == 99) {
//            percentage = 100;
//        }
//        SessionParameters.fadeInterval = (long) (countDownInterval / squareFadeDuration);
//        double percentageCorrected = percentage - 100;
//        String add = "±";
//        if (percentageCorrected > 0) {
//            add = "+";
//        } else if (percentageCorrected == 0) {
//            add = "±";
//        } else if (percentageCorrected < 0) {
//            add = "";
//        }
//        estimatedTrialLength = (long) (countDownInterval * MAX_PRESENT_DEFAULT) * 2;
//
//        estimatedLengthSession = estimatedTrialLength * trialsMax;
//        estimatedLengthSessionII = estimatedTrialLength * (trialsMax + 1);
//        lengthTrial = CustomClock.convertMillisecondsToMinutesAndSeconds(estimatedTrialLength);
//        lengthSession = CustomClock.convertToMinutes(estimatedLengthSession);
//        lengthSessionII = CustomClock.convertToMinutes(estimatedLengthSessionII);
//        if (lengthSessionII.equals(lengthSession)) {
//            lengthSessionII = Strings.textViewTimeAdd_I + lengthSession + textViewTimeAdd_II;
//        } else {
//            lengthSessionII = Strings.textViewTimeAdd_I + lengthSession + " -" + lengthSessionII + textViewTimeAdd_II;
//        }
//
//
////        add = "    " + Strings.speedText + add;
////        add = Strings.speedText + Strings.changeIntervalInfoText + fadeInterval + Strings.changeIntervalInfoTextII;
//        add = Strings.changeIntervalInfoText + fadeInterval
//                + Strings.changeIntervalInfoTextII + Strings.speedText + add
//                + +(percentageCorrected) + "%  "
//                + Strings.estimatedTrialLengthText
//                + lengthTrial
//                + textViewTimeAdd_II
//                + Strings.estimatedSessionLengthText
//                + lengthSessionII;
//        lengthTrial = add;
//    }


    private void setDividersVisible(boolean changeVisibility) {
        if (changeVisibility) {
            showGrid = !showGrid;
        }

        if (showGrid) {
            visibility = View.VISIBLE;
            alpha = 1;
            Toast.makeText(this, Strings.showGridTextYes, Toast.LENGTH_SHORT).show();
        } else {
            visibility = View.INVISIBLE;
            alpha = 0;
            Toast.makeText(this, Strings.showGridTextNo, Toast.LENGTH_SHORT).show();
        }
        if (!includePosition && includeColor) {
            squares.get(4).setAlpha(alpha);
            squares.get(4).setVisibility(visibility);
        } else {
            dividerVertical1.setAlpha(alpha);
            dividerVertical2.setAlpha(alpha);
            dividerHorizontal1.setAlpha(alpha);
            dividerHorizontal2.setAlpha(alpha);

            dividerVertical1.setVisibility(visibility);
            dividerVertical2.setVisibility(visibility);
            dividerHorizontal1.setVisibility(visibility);
            dividerHorizontal2.setVisibility(visibility);
        }

    }

    private void styleButtonLogic() {
        int visibility = INVISIBLE;
        if (allowToChangeColorStyle) {
            allowToChangeColorStyle = false;
            changeSquaresStandardColor();
            FileLogicSettings.saveSettings(this);
        } else {
            visibility = VISIBLE;
            allowToChangeColorStyle = true;
        }

        for (int i = 0; i < squares.size(); i++) {
            if (i != 4) {
                squares.get(i).setVisibility(visibility);
            }
        }
    }


    private final void setModeColors() {
        Drawable trainingBackground;
        Drawable modeButtonBackground;
        Drawable orientationButtonBackground;
        Drawable styleButtonBackground;
        Drawable sizeButtonBackground;
        Drawable gridButtonBackground;
        Drawable fadeButtonBackground;
        float alphaPositionButtons;
        float alphaAudioButtons;
        float alphaColorButtons;
        float alphaExitButton;
        float alphaModeButton;
        float alphaOrientationButton;
        float alphaSoundOffButton;
        float alphaStyleButton;
        float alphaSizeButton;
        float alphaGridButton;
        float alphaFadeButton;
        float alphaIntervalTextView;
        float alphaBackLevelInfoTextView;
        int textColor;

        if (darkModeTraining) {
            trainingBackground = getResources().getDrawable(R.drawable.training_background_dark);
            modeButtonBackground = getResources().getDrawable(R.drawable.custom_button_training_settings_mode_dark);
            orientationButtonBackground = getResources().getDrawable(R.drawable.custom_button_training_settings_orientation_dark);
            styleButtonBackground = getResources().getDrawable(R.drawable.custom_button_training_settings_style_dark);
            sizeButtonBackground = getResources().getDrawable(R.drawable.button_training_settings_size_dark);
            gridButtonBackground = getResources().getDrawable(R.drawable.button_training_settings_grid_dark);
            if (zenMode) {
                fadeButtonBackground = getResources().getDrawable(R.drawable.button_training_settings_fade_true_dark);
            } else {
                fadeButtonBackground = getResources().getDrawable(R.drawable.button_training_settings_fade_false_dark);
            }
            alphaPositionButtons = 0.17f;
            alphaAudioButtons = 0.17f;
            alphaColorButtons = 0.17f;
            alphaExitButton = 0.6f;
            alphaModeButton = 0.45f;
            alphaOrientationButton = 0.45f;
            alphaSoundOffButton = 0.45f;
            alphaStyleButton = 0.45f;
            alphaSizeButton = 0.45f;
            alphaGridButton = 0.45f;
            alphaFadeButton = 0.45f;
            alphaIntervalTextView = 0.45f;
            alphaBackLevelInfoTextView = 0.45f;
            textColor = getResources().getColor(R.color.training_text_color_dark);
        } else {
            trainingBackground = getResources().getDrawable(R.drawable.training_background);
            modeButtonBackground = getResources().getDrawable(R.drawable.custom_button_training_settings_mode);
            orientationButtonBackground = getResources().getDrawable(R.drawable.custom_button_training_settings_orientation);
            styleButtonBackground = getResources().getDrawable(R.drawable.custom_button_training_settings_style);
            sizeButtonBackground = getResources().getDrawable(R.drawable.button_training_settings_size);
            gridButtonBackground = getResources().getDrawable(R.drawable.button_training_settings_grid);
            if (zenMode) {
                fadeButtonBackground = getResources().getDrawable(R.drawable.button_training_settings_fade_true);
            } else {
                fadeButtonBackground = getResources().getDrawable(R.drawable.button_training_settings_fade_false);
            }
            alphaPositionButtons = 0.8f;
            alphaAudioButtons = 0.8f;
            alphaColorButtons = 0.8f;
            alphaExitButton = 0.6f;
            alphaModeButton = 0.6f;
            alphaOrientationButton = 0.6f;
            alphaSoundOffButton = 0.6f;
            alphaStyleButton = 0.6f;
            alphaSizeButton = 0.6f;
            alphaGridButton = 0.6f;
            alphaFadeButton = 0.6f;
            alphaIntervalTextView = 0.6f;
            alphaBackLevelInfoTextView = 0.6f;
            textColor = getResources().getColor(R.color.training_text_color);
        }

        layout.setBackground(trainingBackground);
        btnMode.setBackground(modeButtonBackground);
        btnOrientation.setBackground(orientationButtonBackground);
        btnStyle.setBackground(styleButtonBackground);
        btnSize.setBackground(sizeButtonBackground);
        btnGrid.setBackground(gridButtonBackground);

        if (zenMode) {
            btnFade.setBackground(fadeButtonBackground);
        } else {
            btnFade.setBackground(fadeButtonBackground);
        }

        btnPosition.setAlpha(alphaPositionButtons);
        btnAudio.setAlpha(alphaAudioButtons);
        btnColor.setAlpha(alphaColorButtons);
        btnPositionII.setAlpha(alphaExitButton);
        btnAudioII.setAlpha(alphaExitButton);
        txtVwInterval.setAlpha(alphaExitButton);
        txtVwnBackLevelInfo.setAlpha(alphaExitButton);

        btnExit.setAlpha(alphaExitButton);
        btnMode.setAlpha(alphaModeButton);
        btnOrientation.setAlpha(alphaOrientationButton);
        btnSoundOff.setAlpha(alphaSoundOffButton);
        btnStyle.setAlpha(alphaStyleButton);
        btnSize.setAlpha(alphaSizeButton);
        btnGrid.setAlpha(alphaGridButton);
        btnFade.setAlpha(alphaFadeButton);
        txtVwInterval.setAlpha(alphaIntervalTextView);
        txtVwnBackLevelInfo.setAlpha(alphaIntervalTextView);

        txtVwMiddle.setTextColor(textColor);
        txtVwInterval.setTextColor(textColor);
        dividerVertical2.setBackgroundTintList(ColorStateList.valueOf(textColor));
//
        dividerHorizontal1.setBackgroundTintList(ColorStateList.valueOf(textColor));
        dividerHorizontal2.setBackgroundTintList(ColorStateList.valueOf(textColor));
        dividerVertical1.setBackgroundTintList(ColorStateList.valueOf(textColor));
        dividerVertical2.setBackgroundTintList(ColorStateList.valueOf(textColor));

    }

    private void setSettingsBtnSoundColor() {
        // Get the appropriate drawable resource based on the value of playButtonSoundDuringTraining and darkModeTraining
        int drawableResourceId = playButtonSoundDuringTraining && darkModeTraining ?
                R.drawable.button_training_settings_sound_on_dark :
                R.drawable.button_training_settings_sound_off_dark;
        if (!playButtonSoundDuringTraining) {
            drawableResourceId = darkModeTraining ? R.drawable.button_training_settings_sound_off_dark : R.drawable.button_training_settings_sound_off;
        }
        // Set the drawable for the button
        btnSoundOff.setImageDrawable(getResources().getDrawable(drawableResourceId));
        // Show a toast message indicating the current sound setting
//        String toastMessage = playButtonSoundDuringTraining ? Strings.sound_click_training_on : Strings.sound_click_training_off;
//        Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
    }


    private void setCustomSize(boolean changeSize, int pos) {
        if (changeSize) {
            float squareSize;

            squareSize = customSquareSize;

            if (squareSize <= 0.1f) {
                squareSize = 0.1f;
            }
            if (squareSize > 0.99f) {
                squareSize = 1f;
            }

            customSquareSize = (squareSize - 0.05f * pos);
        }
        setSquareSize();
    }

    public static void setCountdown(View view, int durationInMilliSeconds) {
        view.setEnabled(false);
        CountDownTimer countDown = new CountDownTimer(durationInMilliSeconds, 1000 /*Tick duration*/) {
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                view.setEnabled(true);
            }
        };
        countDown.start();
    }

    @Override
    public boolean onMenuOpened(int featureId, android.view.Menu menu) {
        setPausedFromMiddle();
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public void onBackPressed() {
        if (paused) {
            moveTaskToBack(true);
        } else {
            setPausedFromMiddle();
        }
        return;
    }


    private final void setFadeInAnimationAndDingSound(long duration, long delay, View... v) {
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
//                    appSounds.play(SessionParameters.dingSound, 1, 1, 1, 0, 1);
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

    private final void createDialogAdReminder() {
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

}