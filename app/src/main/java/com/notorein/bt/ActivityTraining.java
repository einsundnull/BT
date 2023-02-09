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
import static com.notorein.bt.SessionParameters.allowCountingMatches;
import static com.notorein.bt.SessionParameters.allowToChangeColorStyle;
import static com.notorein.bt.SessionParameters.allowToChangeSquareSize;
import static com.notorein.bt.SessionParameters.aud;
import static com.notorein.bt.SessionParameters.audioIsClicked;
import static com.notorein.bt.SessionParameters.audioIsRight;
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
import static com.notorein.bt.SessionParameters.darkModeTraining;
import static com.notorein.bt.SessionParameters.daySession;
import static com.notorein.bt.SessionParameters.dayTrial;
import static com.notorein.bt.SessionParameters.developerInfoAreVisible;
import static com.notorein.bt.SessionParameters.durationSessionTimer;
import static com.notorein.bt.SessionParameters.endOfSession;
import static com.notorein.bt.SessionParameters.endOfTrial;
import static com.notorein.bt.SessionParameters.endOfTrialDialogIsVisible;
import static com.notorein.bt.SessionParameters.inOrDecreaseCulmulated;
import static com.notorein.bt.SessionParameters.includeAudio;
import static com.notorein.bt.SessionParameters.includeColor;
import static com.notorein.bt.SessionParameters.includePosition;
import static com.notorein.bt.SessionParameters.includedModes;
import static com.notorein.bt.SessionParameters.increasedCounterPosition;
import static com.notorein.bt.SessionParameters.maxPresentations;
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
import static com.notorein.bt.SessionParameters.zenMode;
import static com.notorein.bt.Strings.btnAudIIText;
import static com.notorein.bt.Strings.btnAudText;
import static com.notorein.bt.Strings.btnColText;
import static com.notorein.bt.Strings.btnPosIIText;
import static com.notorein.bt.Strings.btnPosText;
import static com.notorein.bt.Strings.cross;
import static com.notorein.bt.Strings.nBackLevel;
import static com.notorein.bt.Strings.pressButtonToC;
import static com.notorein.bt.Strings.pressButtonToS;
import static com.notorein.bt.Strings.setDeveloperInfoText;
import static com.notorein.bt.Strings.trialEndTextPercentage;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
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
    private ConstraintLayout layout_training;
    int alphaLayout = 0;
    private View[] views;
    private SoundPool appSounds;
    private int buttonSound;
    private int[] trainingSound;
    //    public static double squareFadeDuration = 0.9;
    public static double squareFadeDuration = 1.1;
    //    public static double squareFadeDuration = 1.2;
//    public static double squareFadeDuration = 1.7;
    private final long fadeInterval_DEFAULT = (long) (countDownIntervalDefault / squareFadeDuration);
    private long fadeInterval = fadeInterval_DEFAULT;
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
        activity = this;
        setFullScreen();
        setOrientationListener();
        getViews();
        setCustomSize(false, 1);
        setModeColors();
        setSquareSize();
        setSounds();
        setFadeInAnimation();
        setFadeInAnimation(views);
        prepareOtherStuff();
        changeSquaresStandardColor(false);
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

    }

    private void setSquareSize() {
        for (int i = 0; i < squares.size(); i++) {
            squares.get(i).setScaleX(SessionParameters.customSquareSize);
            squares.get(i).setScaleY(SessionParameters.customSquareSize);
        }
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

    private void setTransitionToBlack() {
        if (darkModeTraining) {
            TransitionActivityAToB transitionActivityAToB = new TransitionActivityAToB();
            int colorFrom = getResources().getColor(R.color.menu_background_color);
            int colorTo = getResources().getColor(R.color.black);
            transitionActivityAToB.setTransitionToBlack(this, layout_training, colorFrom, colorTo, true);
        }
    }

    private void setFadeOutAnimation(View... v) {

        setTransitionToBlack();

        Animation mLoadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_out);
        mLoadAnimation.setDuration(1200);
        mLoadAnimation.setStartOffset(00);
        mLoadAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                SessionParameters.returnFromTraining = true;
                Intent intent = new Intent(ActivityTraining.this, ActivityMenu.class);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < v.length; i++) {
                    v[i].setAlpha(0);
                }
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationStart(Animation arg0) {
            }
        });

        for (int i = 0; i < v.length; i++) {
            v[i].startAnimation(mLoadAnimation);
        }
    }


    public void setFadeInAnimation(View... v) {
        Animation mLoadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
        mLoadAnimation.setDuration(1000);
        for (int i = 0; i < v.length; i++) {
            try {
                v[i].startAnimation(mLoadAnimation);
            } catch (Exception e) {
                break;
            }

        }
    }

    private void showFirstScreenSplittedClick() {

        if (screenShowOrder == 0) {
            screenShowOrder++;
            String indicator = getTrialIndicator();
            String text = Strings.firstStartTextPressButtonToS + "\n\n" + +nBack + nBackLevel + "\n\n" + indicator;
            txtVwMiddle.setTextSize(textUnit, textSizeMiddleTrial);
            txtVwMiddle.setText(text);
            if (!includePosition) {
                setDividersVisibleAddaptToMode(INVISIBLE);
                setMiddleSquareToRightSizeUI(scaleNoPos, R.color.border_color_no_position, text, textUnit, textSizeMiddleTrial, VISIBLE, VISIBLE);
            }
            btnExit.setVisibility(VISIBLE);
        } else {
            screenShowOrder = 0;
            String indicator = getTrialIndicator();
            String text = Strings.firstStartTextPressButtonToS + "\n\n" + +nBack + nBackLevel + "\n\n" + indicator;
            txtVwMiddle.setText(text);
            txtVwMiddle.setTextSize(textUnit, textSizeMiddleTrial);
            if (!includePosition) {
                setDividersVisibleAddaptToMode(INVISIBLE);
                setMiddleSquareToRightSizeUI(scaleNoPos, R.color.border_color_no_position, text, textUnit, textSizeMiddleTrial, VISIBLE, VISIBLE);

            }
        }

//        textViewMiddle.setVisibility(VISIBLE);
//         If these thre lines not exist the
//        setDividersVisibleAddaptToMode(INVISIBLE);
//        if(includeColor && !includePosition){
//            btns.get(4).setVisibility(INVISIBLE);
//        }
    }


    private void screenTrialHide() {
        if (includePosition) {
            try {
                squares.get(shownIndexesPosition.get(presentedScreens - 1)).setVisibility(INVISIBLE);
            } catch (Exception e) {

            }
        }
        if (includeAudio) {

        }
        if (includeColor && !includePosition) {
            GradientDrawable border = new GradientDrawable();
            if (darkModeTraining) {
                border.setColor(getResources().getColor(R.color.menu_background_color_dark)); // Background Color
                border.setStroke(1, getResources().getColor(R.color.training_text_color_dark)); // BorderColor
                squares.get(4).setBackground(border);
                squares.get(4).setBackgroundColor(getResources().getColor(R.color.menu_background_color_dark));
            } else {
                border.setColor(getResources().getColor(R.color.menu_background_color)); // Background Color
                border.setStroke(1, getResources().getColor(R.color.border_color_no_position)); // BorderColor
                squares.get(4).setBackground(border);
                squares.get(4).setBackgroundColor(getResources().getColor(R.color.menu_background_color));
            }


        }
    }

    private void screenTrialShow() {

        if (includePosition) {
            if (zenMode) {
                setFadeInAnimationSquaresForZenMode(squares.get(shownIndexesPosition.get(presentedScreens)));
            } else {
                try {
                    squares.get(shownIndexesPosition.get(presentedScreens)).setVisibility(VISIBLE);
                    if (includeColor) {
                        squares.get(shownIndexesPosition.get(presentedScreens)).setBackgroundColor(getResources().getColor(colors[shownIndexesColor.get(presentedScreens)]));
                    }
                } catch (Exception e) {

                }
            }
        }
        if (includeAudio) {
            try {
                // Here is still an error. Check Logcat
                playAudio(shownIndexesAudio.get(presentedScreens));
            } catch (Exception e) {

            }
        }
        if (includeColor && !includePosition) {
            GradientDrawable border = new GradientDrawable();
            border.setColor(getResources().getColor(colors[shownIndexesColor.get(presentedScreens)])); // Background Color
            border.setStroke(1, getResources().getColor(R.color.border_color_default)); // BorderColor
            squares.get(4).setBackground(border);
        }
    }


    private void screenInterruptionStart() {
        String indicator = getTrialIndicator();
        String text = Strings.firstStartTextPressButtonToS + "\n\n" + +nBack + nBackLevel + "\n\n" + indicator;
        txtVwMiddle.setTextSize(textUnit, textSizeMiddleTrial);
        txtVwMiddle.setText(text);
        btnExit.setVisibility(VISIBLE);
        if (!includePosition) {
            setDividersVisibleAddaptToMode(INVISIBLE);
            setMiddleSquareToRightSizeUI(scaleNoPos, R.color.border_color_no_position, text, textUnit, textSizeMiddleTrial, VISIBLE, VISIBLE);
        }
    }

    private void setFullScreen() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        screenOrientation = this.getResources().getConfiguration().orientation - 1;
        setRequestedOrientation(orientation);
    }

    private void prepareOtherStuff() {
        // activity_main_layout.setBackgroundColor(Color.BLACK);
        for (int i = 0; i < squares.size(); i++) {
            squares.get(i).setVisibility(View.INVISIBLE);
        }

        squares.get(4).setVisibility(View.INVISIBLE);/**/
        if (!playButtonSoundDuringTraining) {
            int resId = darkModeTraining ? R.drawable.button_training_settings_sound_off_dark : R.drawable.button_training_settings_sound_off;
            btnSoundOff.setImageDrawable(getResources().getDrawable(resId));
        } else {
            int resId = darkModeTraining ? R.drawable.button_training_settings_sound_on_dark : R.drawable.button_training_settings_sound_on;
            btnSoundOff.setImageDrawable(getResources().getDrawable(resId));
        }
    }


    private void getViews() {
        layout_training = (ConstraintLayout) findViewById(R.id.activity_main_layout);
        rectContainer = (ConstraintLayout) findViewById(R.id.rectContainer);
//        colorChooseLayout = (ConstraintLayout) findViewById(R.id.colorChooseLayout);
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


        btnPosition = (Button) findViewById(R.id.buttonPosition);
        btnAudio = (Button) findViewById(R.id.buttonAudio);
        btnPositionII = (Button) findViewById(R.id.buttonPosition2);
        btnAudioII = (Button) findViewById(R.id.buttonAudio2);
        btnColor = (Button) findViewById(R.id.buttonColor);
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
        txtVwInterval.setVisibility(INVISIBLE);
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
        includeViewsToFadeInTransition();

        txtVwnBackLevelInfo.setTextColor(getResources().getColor(R.color.border_color_no_position));
    }

    private void includeViewsToFadeInTransition() {
        // Here it is decided which elements are included in the fade in transition at the beginning of the Session.
        if (includePosition) {
            List<View> viewsList = Arrays.asList(btnPosition, btnPositionII, btnAudio, btnAudioII, btnColor, btnExit, btnSoundOff, btnOrientation, btnMode, btnStyle, btnSize, btnGrid, btnFade,
                    txtVwMiddle, infoTxt, txtVwnBackLevelInfo);
            if (showGrid) {
//                viewsList.addAll(Arrays.asList(dividerVertical1, dividerVertical2, dividerHorizontal1, dividerHorizontal2));
            }
            views = viewsList.toArray(new View[0]);
        } else {
            views = new View[]{btnPosition, btnPositionII, btnAudio, btnAudioII, btnColor, btnExit, btnSoundOff, btnOrientation, btnMode, btnStyle, btnSize, btnGrid, btnFade,
                    txtVwMiddle, infoTxt,
                    squares.get(4), txtVwnBackLevelInfo};
        }
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

    private void setOnClickListenersToUiElements() {
        btnPosition.setOnClickListener(this);
        btnAudio.setOnClickListener(this);
        btnPositionII.setOnClickListener(this);
        btnAudioII.setOnClickListener(this);
        btnColor.setOnClickListener(this);
        btnExit.setOnClickListener(this);
        squares.get(4).setOnClickListener(this);
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
//        border.setStroke(3, getResources().getColor(borderColor)); //white border with full opacity
        // TEST
        border.setColor(0x00FFFFFF); //white background
        border.setStroke(1, getResources().getColor(borderColor)); //black border with full opacity
        // END TEST
        squares.get(4).setBackground(border);
        squares.get(4).setScaleX(squareScale);
        squares.get(4).setScaleY(squareScale);
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
//            SessionParameters.dayStartTrial = RepeatStorage.getDay();
        }
        startClockTimer();
        startTrialTimer();
    }


    private void startTrialTimer() {

        timerTrial = new CountDownTimer(durationSessionTimer, (long) countDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                //###################
                // These lines make sure that the trial does not start by
                // out of the sudden showing a square after starting trial by
                // pressing a button. The integer "presentedScreens" is set to
                // -2 at every start of a new trial.

                if (presentedScreens < 0) {
                    presentedScreens++;
                    Strings.timeSession = clockSession.getClockTime(true);
                    setDeveloperInfoText(infoTxt);
                    // if presentedScreens >= 0 the  presentedScreens++; will be executed
                    // 15-20 lines below
                }
                Strings.timeTrialDevOps = clockTrialDevOps.getClockTime(true);
                //###################

                if (presentedScreens >= 0 && !paused) {
                    showHideScreen = !showHideScreen;
                    if (showHideScreen) {
                        screenTrialHide();
                    } else {
                        processInputClicksDuringTrial();
                        if (presentedScreens == maxPresentations) {
                            endOfTrial = true;
                            onFinish();
                        } else {
                            // Sets the aquares visible or plays the sound colro etc.
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
                    // TEST VARIABLE
                    addedNBACK++;
                    culmulateTrialResultsAtEndOfSession();
                    RepeatStorage.createStringToStoreAndroid(trialCounter >= trialsMax);
                    Log.d(TAG, (trialCounter >= trialsMax) ? "onFinishEndOfSession:\n" + stringToStore : "onFinishEndOfTrial:\n" + stringToStore);
                    setDividersVisibleAddaptToMode();
                    // These two Strings must be reset here in order to display them right
//  culmulatedOverAllPercentageStringAllModesTrial = "";
//  culmulatedStringTrial = "";
                    timeTrial = RepeatStorage.getStartTime();
                    dayTrial = RepeatStorage.getDay();
                    storeTrialResults();
                    if (trialCounter >= trialsMax) {
                        endOfSession = true;
                        storeSessionResults();
                    }
                }

                inOrDecreaseNBackLevel();
                showScreenEndTrialResults();
                Strings.timeTrialDevOps = clockTrialDevOps.getClockTime(false);
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
                    Strings.time = clockTrial.getClockTime(true);
                    Strings.timeSession = clockSession.getClockTime(true);
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

        if (pos && aud && col) {
            percentagesTrialPosition.add(percentageRightTrialPosition);
            percentagesTrialAudio.add(percentageRightTrialAudio);
            percentagesTrialColor.add(percentageRightTrialColor);
            percentagesSessionAllModes.add((percentageRightTrialPosition + percentageRightTrialAudio + percentageRightTrialColor) / 3);
        }
        if (pos && aud && !col) {
            percentagesTrialPosition.add(percentageRightTrialPosition);
            percentagesTrialAudio.add(percentageRightTrialAudio);
            percentagesSessionAllModes.add((percentageRightTrialPosition + percentageRightTrialAudio) / 2);
        }
        if (pos && !aud && col) {
            percentagesTrialPosition.add(percentageRightTrialPosition);
            percentagesTrialColor.add(percentageRightTrialColor);
            percentagesSessionAllModes.add((percentageRightTrialPosition + percentageRightTrialColor) / 2);
        }
        if (!pos && aud && col) {
            percentagesTrialAudio.add(percentageRightTrialAudio);
            percentagesTrialColor.add(percentageRightTrialColor);
            percentagesSessionAllModes.add((percentageRightTrialAudio + percentageRightTrialColor) / 2);
        }

        if (pos && !aud && !col) {
            percentagesTrialPosition.add(percentageRightTrialPosition);
            percentagesSessionAllModes.add((percentageRightTrialPosition));
        }
        if (!pos && aud && !col) {
            percentagesTrialAudio.add(percentageRightTrialAudio);
            percentagesSessionAllModes.add((percentageRightTrialAudio));
        }
        if (!pos && !aud && col) {
            percentagesTrialColor.add(percentageRightTrialColor);
            percentagesSessionAllModes.add((percentageRightTrialColor));
        }
    }

    private void culmulateTrialResultsAtEndOfSession() {
        // Deletes the values from any recent trial
        // so only the previous trial will be shown
//        culmulatedStringTrial = "";
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


    private void storeSessionResults() {

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

    private boolean modeButtonClicked() {
        boolean clicked = false;
        if (playButtonSoundDuringTraining) {
            appSounds.play(buttonSound, 1, 1, 1, 0, 1);
        }
        if (!paused && trialIsRunning) {
            clicked = true;
        }
        if (endOfSession) {
            resetTrialCounters();
//            finish();
//            txtVwMiddle.setTextSize(32f);
//            txtVwMiddle.setText(Strings.goodJob);

            setFadeOutAnimation(views);
//            Intent intent = new Intent(ActivityTraining.this, ActivityMenu.class);
//            startActivity(intent);
        } else if (!trialIsRunning) {
            if (resultScreenIndex == 0) {
                startTrial();
            } else {
                showScreenEndTrialResults();
            }
        } else if (paused && trialIsRunning) {
            showScreenPause();
        }
        setDeveloperInfoText(infoTxt);
        return clicked;
    }

    private void setPausedFromMiddleSplittedClick() {

        developerTimerCounting();

        if (trialIsRunning) {
            paused = true;
            String indicator = getTrialIndicator();
            setDividersVisibleAddaptToMode(INVISIBLE);
            btnExit.setVisibility(VISIBLE);
            txtVwMiddle.setText(Strings.pause + "\n\n" + nBack + nBackLevel + "\n\n" + pressButtonToC + "\n\n" + indicator);
            txtVwMiddle.setTextSize(textUnit, textSizeMiddleTrial);
            for (int i = 0; i < squares.size(); i++) {
                squares.get(i).setVisibility(View.INVISIBLE);
            }
        }
    }

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

    private void showScreenPause() {
        paused = false;
        if (developerTimer == null) {
            startDeveloperTimer();
        }

        // INCLUDES POSITION
        if (!paused) {
            paused = false;
            setSettingsButtonVisibility(INVISIBLE);
            txtVwMiddle.setText(cross);
            txtVwMiddle.setTextSize(textUnit, textSizeMiddleTrial);
            for (int i = 0; i < squares.size(); i++) {
                squares.get(i).setVisibility(View.INVISIBLE);
            }
            if (includePosition) {
                setDividersVisibleAddaptToMode(VISIBLE);
            } else {
                squares.get(4).setVisibility(View.VISIBLE);
                setDividersVisibleAddaptToMode(INVISIBLE);
            }
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
    }


    private void showAlertExit() {

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

            setFadeOutAnimation(views);
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

    private void changeSquaresStandardColor(boolean countIndex) {
//        if (countIndex) {
//            SessionParameters.squareDefaultColorIndex++;
//        }
//
        if (squareDefaultColorIndex > SessionParameters.colors.length - 1) {
            squareDefaultColorIndex = 0;
        }
        for (int i = 0; i < squares.size(); i++) {
            squares.get(i).setBackgroundColor(getResources().getColor(SessionParameters.colors[squareDefaultColorIndex]));
        }
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
//            if (i != 4) {
            squares.get(i).setBackgroundColor(getResources().getColor(colors[squareDefaultColorIndex]));
            squares.get(i).setVisibility(INVISIBLE);
//            squares.get(i).setScaleX(SessionParameters.customSquareSize);
//            squares.get(i).setScaleY(SessionParameters.customSquareSize);
//            }
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

        SessionParameters.allowToChangeColorStyle = false;
        FileLogicSettings.saveSettings(this);
    }

    private void squaresOnClickLogic(int i) {
        if (allowToChangeColorStyle) {
            squareDefaultColorIndex = i;
            changeSquaresStandardColor(true);
        }
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
            speedPercentage = speedPercentage + 0.05;
            if (speedPercentage >= 2) {
                speedPercentage = 2;
            }
            countDownInterval = countDownIntervalDefault * speedPercentage;
            fadeInterval = (long) (countDownInterval / squareFadeDuration);
            if (timerTrial != null) {
                timerTrial.cancel();
                startTrialTimer();
            }
            if (SessionParameters.allowToChangeSquareSize) {
//                setCustomSize(true, 1);
//                FileLogicSettings.saveSettings(this);
            }
            txtVwInterval.setVisibility(VISIBLE);
            txtVwInterval.setText(Strings.changeIntervalInfoText + fadeInterval +Strings.changeIntervalInfoTextII);
        }
        if (v.getId() == R.id.buttonAudio2) {
            //+
            if (SessionParameters.allowToChangeSquareSize) {

//                setCustomSize(true, -1);
//                FileLogicSettings.saveSettings(this);
            }
            speedPercentage = speedPercentage - 0.05;
            if (speedPercentage <= 0.5) {
                speedPercentage = 0.5;
            }
            countDownInterval = countDownIntervalDefault * speedPercentage;
            fadeInterval = (long) (countDownInterval / squareFadeDuration);
            if (timerTrial != null) {
                timerTrial.cancel();
                startTrialTimer();
            }
            txtVwInterval.setVisibility(VISIBLE);
            txtVwInterval.setText(Strings.changeIntervalInfoText + fadeInterval +Strings.changeIntervalInfoTextII);
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
            showAlertExit();
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
            SessionParameters.darkModeTraining = !SessionParameters.darkModeTraining;
            FileLogicSettings.saveSettings(this);
            setModeColors();
//            setDividersVisible(true);
        }
        if (v.getId() == R.id.button_click_training_style) {
            if (!SessionParameters.allowToChangeSquareSize) {
                if (!SessionParameters.allowToChangeColorStyle) {
                    try {
                        SessionParameters.allowToChangeColorStyle = true;
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
                        SessionParameters.allowToChangeColorStyle = false;
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
            }
        }
        if (v.getId() == R.id.button_click_training_size) {
            allowToChangeColorStyle = false;
            if (!SessionParameters.allowToChangeSquareSize) {
                setSquareSize();
                for (int i = 0; i < squares.size(); i++) {
                    squares.get(i).setVisibility(VISIBLE);
                }
                SessionParameters.allowToChangeSquareSize = true;
                Toast.makeText(this, Strings.changeSquareSizeText, Toast.LENGTH_SHORT).show();
            } else {
                setColorAndInvisible();
                setCustomSize(true, 1);
                FileLogicSettings.saveSettings(this);
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

    private void setDividersVisible(boolean changeVisibility) {
        if (changeVisibility) {
            SessionParameters.showGrid = !SessionParameters.showGrid;
        }
        int visibility, alpha;
        if (showGrid) {
            visibility = View.VISIBLE;
            alpha = 1;
            Toast.makeText(this, Strings.showGridTextYes, Toast.LENGTH_SHORT).show();
        } else {
            visibility = View.INVISIBLE;
            alpha = 0;
            Toast.makeText(this, Strings.showGridTextNo, Toast.LENGTH_SHORT).show();
        }
        dividerVertical1.setAlpha(alpha);
        dividerVertical2.setAlpha(alpha);
        dividerHorizontal1.setAlpha(alpha);
        dividerHorizontal2.setAlpha(alpha);

        dividerVertical1.setVisibility(visibility);
        dividerVertical2.setVisibility(visibility);
        dividerHorizontal1.setVisibility(visibility);
        dividerHorizontal2.setVisibility(visibility);
    }

    private void styleButtonLogic() {
        int visibility = INVISIBLE;
        if (SessionParameters.allowToChangeColorStyle) {
            SessionParameters.allowToChangeColorStyle = false;
            changeSquaresStandardColor(true);
            FileLogicSettings.saveSettings(this);
        } else {
            visibility = VISIBLE;
            SessionParameters.allowToChangeColorStyle = true;
        }

        for (int i = 0; i < squares.size(); i++) {
            if (i != 4) {
                squares.get(i).setVisibility(visibility);
            }
        }
    }

    private void setModeColors() {
        if (!darkModeTraining) {
            layout_training.setBackground(getResources().getDrawable(R.drawable.training_background));
            btnMode.setBackground(getResources().getDrawable(R.drawable.custom_button_training_settings_mode));
            btnOrientation.setBackground(getResources().getDrawable(R.drawable.custom_button_training_settings_orientation));
            btnStyle.setBackground(getResources().getDrawable(R.drawable.custom_button_training_settings_style));
            btnSize.setBackground(getResources().getDrawable(R.drawable.button_training_settings_size));
            btnGrid.setBackground(getResources().getDrawable(R.drawable.button_training_settings_grid));
            if (zenMode) {
                btnFade.setBackground(getResources().getDrawable(R.drawable.button_training_settings_fade_true));
            } else {
                btnFade.setBackground(getResources().getDrawable(R.drawable.button_training_settings_fade_false));
            }
            btnPosition.setAlpha(0.8f);
            btnAudio.setAlpha(0.8f);
            btnColor.setAlpha(0.8f);
            btnPositionII.setAlpha(0.8f);
            btnAudioII.setAlpha(0.8f);

            btnExit.setAlpha(0.6f);
            btnMode.setAlpha(0.6f);
            btnOrientation.setAlpha(0.6f);
            btnSoundOff.setAlpha(0.6f);
            btnStyle.setAlpha(0.6f);
            btnSize.setAlpha(0.6f);
            btnGrid.setAlpha(0.6f);
            btnFade.setAlpha(0.6f);

            txtVwnBackLevelInfo.setAlpha(0.6f);
            txtVwnBackLevelInfo.setTextColor(getResources().getColor(R.color.training_text_color));

            txtVwMiddle.setTextColor(getResources().getColor(R.color.training_text_color));

            dividerHorizontal1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.training_text_color)));
            dividerHorizontal2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.training_text_color)));

            dividerVertical1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.training_text_color)));
            dividerVertical2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.training_text_color)));


        } else {
            layout_training.setBackground(getResources().getDrawable(R.drawable.training_background_dark));
            btnPosition.setAlpha(0.3f);
            btnAudio.setAlpha(0.3f);
            btnColor.setAlpha(0.3f);
            btnPositionII.setAlpha(0.3f);
            btnAudioII.setAlpha(0.3f);

//            txtVwMiddle.setAlpha(0.45f);
            txtVwnBackLevelInfo.setTextColor(getResources().getColor(R.color.training_text_color_dark));
            txtVwMiddle.setTextColor(getResources().getColor(R.color.training_text_color_dark));
            btnMode.setBackground(getResources().getDrawable(R.drawable.custom_button_training_settings_mode_dark));
            btnOrientation.setBackground(getResources().getDrawable(R.drawable.custom_button_training_settings_orientation_dark));
            btnStyle.setBackground(getResources().getDrawable(R.drawable.custom_button_training_settings_style_dark));
            btnSize.setBackground(getResources().getDrawable(R.drawable.button_training_settings_size_dark));
            btnGrid.setBackground(getResources().getDrawable(R.drawable.button_training_settings_grid_dark));
            if (zenMode) {
                btnFade.setBackground(getResources().getDrawable(R.drawable.button_training_settings_fade_true_dark));
            } else {
                btnFade.setBackground(getResources().getDrawable(R.drawable.button_training_settings_fade_false_dark));
            }
            btnExit.setAlpha(0.6f);
            btnMode.setAlpha(0.45f);
            btnOrientation.setAlpha(0.45f);
            btnSoundOff.setAlpha(0.45f);
            btnStyle.setAlpha(0.45f);
            btnSize.setAlpha(0.45f);
            btnGrid.setAlpha(0.45f);
            btnFade.setAlpha(0.45f);
            txtVwnBackLevelInfo.setAlpha(0.45f);

            dividerHorizontal1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.training_text_color_dark)));
            dividerHorizontal2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.training_text_color_dark)));

            dividerVertical1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.training_text_color_dark)));
            dividerVertical2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.training_text_color_dark)));
        }
        setSettingsBtnSoundColor();
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


//    private void setSettingsBtnSoundColor() {
//        if (!playButtonSoundDuringTraining) {
//            if (darkModeTraining) {
//                btnSoundOff.setImageDrawable(getResources().getDrawable(R.drawable.button_training_settings_sound_off_dark));
//            } else {
//                btnSoundOff.setImageDrawable(getResources().getDrawable(R.drawable.button_training_settings_sound_off));
//            }
//            Toast.makeText(getApplicationContext(), Strings.sound_click_training_off, Toast.LENGTH_SHORT);
//        } else {
//            if (darkModeTraining) {
//                btnSoundOff.setImageDrawable(getResources().getDrawable(R.drawable.button_training_settings_sound_on_dark));
//            } else {
//                btnSoundOff.setImageDrawable(getResources().getDrawable(R.drawable.button_training_settings_sound_on));
//            }
//        }
//    }

    private void setCustomSize(boolean changeSize, int pos) {
        if (changeSize) {
            SessionParameters.customSquareSize = SessionParameters.customSquareSize - 0.05f * pos;
            if (SessionParameters.customSquareSize <= 0.1f) {
                SessionParameters.customSquareSize = 0.1f;
            }
            if (SessionParameters.customSquareSize > 0.99f) {
                SessionParameters.customSquareSize = 1f;
            }
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

}