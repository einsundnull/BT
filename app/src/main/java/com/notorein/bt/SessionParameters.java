package com.notorein.bt;

import android.graphics.Color;
import android.view.View;

import java.util.ArrayList;

public class SessionParameters {

    public static String user;

    public static boolean playButtonSoundDuringTraining = true;
    public static boolean zenMode = true;

    public static boolean positionIsClicked;
    public static boolean audioIsClicked;
    public static boolean colorIsClicked;

    public static boolean positionIsRight;
    public static boolean audioIsRight;
    public static boolean colorIsRight;

    public static boolean durationTraining;
    public static boolean includePosition = true;
    public static boolean includeAudio = true;
    public static boolean includeColor = false;

    public static boolean pos;
    public static boolean aud;
    public static boolean col;

    public static boolean audioIsPlaying;
    public static boolean excludeFourFromRandomIndex = true;
    public static boolean showRightClicks = false;

    //    public static boolean firstStart;
    public static boolean trialIsRunning = false;
    public static boolean allowCountingMatches;
    public static boolean endOfTrial = false;
    public static boolean endOfSession = false;
    public static boolean showHideScreen = true;
    public static boolean sessionWasCanceledEarly = true;

    public static boolean paused = false;
    public static boolean developerInfoAreVisible;
    public static boolean endOfTrialDialogIsVisible;

    public static String[] trialIndicator;
    // DEFAULT VALUES
    public static double decreaseThreshold = 0.75;
    public static double increaseThreshold = 0.85;

    // Duration
    public static int duration = 7;
    public static int MAX_PRESENT_DEFAULT = 33;
    public static int maxPresentations = 22;
    public static int nBackBegin = 1;
    public static int nBack = 1;
    public static int nBackMax = nBackBegin;
    public static double counterMatchTesholdMin = 0.23;
    public static double counterMatchTesholdMax = 0.40;


    // Speed
    public static boolean increaseNumberOfMatches = true;

    public static int countDownIntervalDefaultZenMode = 1850;
    //    public static int countDownIntervalDefault = 1720;
    public static int countDownIntervalDefault = 1750;
    public static double speedPercentage = 1;
    public static double countDownInterval = countDownIntervalDefault * speedPercentage;

    public static int durationSessionTimer = 1000000;
    public static int randomIndexIncreaseFactor = 5;

    public static String timeSession;
    public static String timeTrial;
    public static String daySession;
    public static String dayTrial;

    // TEST VALUES
    public static boolean setTestValues;
    public static int color = Color.BLUE;
    public static int textUnit = 1;
    public static int textSizeMiddleResults = 18;
    public static int textSizeMiddleTrial = 20;
    public static int orientation = 7;
    public static int visibilityOfAllSquares = View.INVISIBLE;
    // UI
    public static int scaleNoPos = 2;
    public static int scaleDefault = 1;
    public static int squareSize = 115;
    public static float customSquareSize = 0.8f;

    //    #################################################

    // To setBeforeSession


    // To Reset at end of session
    public static int trialCounter = 0;
    public static int shownAndCounted = 0;

    public static int counterClickedRightPosition = 0;
    public static int counterClickedRightAudio = 0;
    public static int counterClickedRightColor = 0;

    // Will be set automatically
    public static int includedModes = 0;
    public static int increasedCounterPosition = 0;
    public static int increasedCounterAudio = 0;
    public static int increasedCounterColor = 0;

    // To Reset at end of trial
    public static int presentedScreens = -1;
    public static double percentageRightTrialPosition;
    public static double percentageRightTrialAudio;
    public static double percentageRightTrialColor;
    public static int counterMatchesPos = 0;
    public static int counterMatchesAud = 0;
    public static int counterMatchesCol = 0;

    public static int trialsMax = 10;

    // To Reset at end of session
    public static double nBackCulmulated = 0;
    public static double inOrDecreaseCulmulated = 0;
    public static ArrayList<Double> inOrDecreaseSingle = new ArrayList<>();

    public static int[] colors = new int[]{R.color.zero, R.color.one, R.color.two, R.color.three, R.color.four, R.color.five, R.color.six, R.color.seven, R.color.eight, R.color.nine, R.color.ten};
//    public static int[] colorsDefault = new int[]{R.color.square_color_one, R.color.caparol_yellow,
//            R.color.square_color_two, R.color.square_color_three,
//            R.color.square_color_four, R.color.square_color_five,
//            R.color.six, R.color.eleven, R.color.light_blue_900,R.color.zero, R.color.one, R.color.two, R.color.three, R.color.carmin,R.color.carmin2, R.color.four, R.color.five, R.color.six, R.color.seven, R.color.eight, R.color.nine, R.color.ten,R.color.menu_button_stroke_color_dark_2, R.color.menu_button_stroke_color_dark_4,R.color.menu_button_stroke_color_dark,R.color.menu_button_stroke_color_default};



    public static String stringToStore = "";
    public static String stringToStoreInitial = "";
    public static final String daySessionMarker = "DaySession";
    public static final String daySessionEndMarker = "DayEndSession";

    public static final String dayTrialMarker = "DayTrial";
    public static final String timeSessionMarker = "TimeSession";
    public static final String timeTrialMarker = "TimeTrial";
    public static final String percentageSessionMarker = "PercentageSession";
    public static final String percentageTrialMarker = "PercentageTrial";
    public static final String increaseSessionMarker = "IncreaseSession";
    public static final String nBackSessionMarker = "NBackSession";
    public static final String maxNBackMarker = "MaxNBack";
    public static final String nBackTrialMarker = "NBack";
    public static final String maxTrialsMarker = "TrialsMax";


    public static String modeOneDirectory;
    public static String resultsFilePath;
    public static int screenShowOrder = 0;
    public static int screenShowOrderTrial = 0;
    public static int resultScreenIndex = 0;
    public static int convertedTrialToTime;
    public static String dateOflastUse;
    public static String dateOfCurrentUse;
    public static boolean firstStart = true;
    public static int squareDefaultColorIndex = 5;
    public static String lastDayOfUse;
    public static String currentDayOfUse;
    public static int displayHeight;
    public static int displayWidth;
    public static int mode;
    public static String newDayMarker = "Next_Day";
    public static int resultLineColorIndex = 0;
    public static boolean showPercentageInResults = true;
    public static boolean showNBackInResults = false;
    public static boolean showTrialInResults = false;
    public static boolean showDayInResults = false;
    public static boolean showSessionInResults = true;
    public static boolean orientationWasChangedDuringTraining;
    public static boolean darkModeTraining;
    public static int fadeoutAnimationDuration = 1220;
    public static boolean allowToChangeColorStyle;
    public static boolean allowToChangeSquareSize;
    public static boolean showGrid = true;
    public static boolean loadInterstitialAd = true;
    public static int adMissedCounter = 0;
    public static boolean missedAdDialogHasBeenShown;


//    String[]  colors = new String[] { "white", "yellow", "chocolate", "green", "rgb(136,163,19)", "rgb(3,211,252)", "blue", "red", "purple","grey","darkgrey" };

}
