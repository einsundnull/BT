package com.notorein.bt;

import static android.content.ContentValues.TAG;
import static com.notorein.bt.SessionParameters.daySession;
import static com.notorein.bt.SessionParameters.daySessionEndMarker;
import static com.notorein.bt.SessionParameters.daySessionMarker;
import static com.notorein.bt.SessionParameters.duration;
import static com.notorein.bt.SessionParameters.inOrDecreaseCulmulated;
import static com.notorein.bt.SessionParameters.increaseSessionMarker;
import static com.notorein.bt.SessionParameters.maxNBackMarker;
import static com.notorein.bt.SessionParameters.maxTrialsMarker;
import static com.notorein.bt.SessionParameters.nBack;
import static com.notorein.bt.SessionParameters.nBackCulmulated;
import static com.notorein.bt.SessionParameters.nBackMax;
import static com.notorein.bt.SessionParameters.nBackSessionMarker;
import static com.notorein.bt.SessionParameters.nBackTrialMarker;
import static com.notorein.bt.SessionParameters.percentageSessionMarker;
import static com.notorein.bt.SessionParameters.percentageTrialMarker;
import static com.notorein.bt.SessionParameters.stringToStore;
import static com.notorein.bt.SessionParameters.timeSession;
import static com.notorein.bt.SessionParameters.timeSessionMarker;
import static com.notorein.bt.SessionParameters.trialCounter;
import static com.notorein.bt.SessionParameters.trialsMax;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RepeatStorage {

    //    public static ArrayList<ArrayList<Boolean>> listOfClickAndMatchPosition = new ArrayList<>();
//    public static ArrayList<ArrayList<Boolean>> listOfClickAndMatchAudio = new ArrayList<>();
//    public static ArrayList<ArrayList<Boolean>> listOfClickAndMatchColor = new ArrayList<>();


    public static ArrayList<Double> percentagesSessionAllModes = new ArrayList<>();
    public static ArrayList<Double> percentagesSessionPosition = new ArrayList<>();
    public static ArrayList<Double> percentagesSessionAudio = new ArrayList<>();
    public static ArrayList<Double> percentagesSessionColor = new ArrayList<>();

    public static double percentageRightTrialAllModes = 0;
    public static double percentageRightSessionAllModes = 0;
    public static double percentageSessionPosition = 0;
    public static double percentageSessionAudio = 0;
    public static double percentageSessionColor = 0;

    public static double percentageTrialPosition = 0;
    public static double percentageTrialAudio = 0;
    public static double percentageTrialColor = 0;

    public static ArrayList<Double> percentagesTrialPosition = new ArrayList<>();
    public static ArrayList<Double> percentagesTrialAudio = new ArrayList<>();
    public static ArrayList<Double> percentagesTrialColor = new ArrayList<>();

    public static ArrayList<Integer> shownIndexesPosition = new ArrayList<>();
    public static ArrayList<Integer> shownIndexesAudio = new ArrayList<>();
    public static ArrayList<Integer> shownIndexesColor = new ArrayList<>();

    public static ArrayList<Boolean> matchesPosition = new ArrayList<>();
    public static ArrayList<Boolean> matchesAudio = new ArrayList<>();
    public static ArrayList<Boolean> matchesColor = new ArrayList<>();

    public static ArrayList<Boolean> clickedPositionRight = new ArrayList<>();
    public static ArrayList<Boolean> clickedAudioRight = new ArrayList<>();
    public static ArrayList<Boolean> clickedColorRight = new ArrayList<>();

    public static int shownCounter = 0;
//    // These two Strings must be set to = ""; in    MainActivity : startTrial() {resetTrialCounters();
//    public static String culmulatedStringTrial = "";
//    public static String culmulatedOverAllPercentageStringAllModesTrial = "";

    // These two Strings must be set to = ""; in    MainActivity : resetSession();
    public static String culmulatedStringSession = "";
    public static String culmulatedOverAllPercentageStringAllModesSession = "";


    public static ArrayList<Boolean> storeShownIndexes(ArrayList<Boolean> matches, ArrayList<Boolean> clickedRight, Button btnClick, int presentations, int nBack, boolean clicked, boolean allowCountingMatches) {
        if (allowCountingMatches) {
            boolean match;
            try {
                match = matches.get(presentations - 1);
                if (match) {
                    if (clicked) {
                        if (SessionParameters.showRightClicks) {
                            btnClick.setBackgroundColor(Color.GREEN);
                            btnClick.setText("right yes");
                        }
                        match = true;
                    } else {
                        if (SessionParameters.showRightClicks) {
                            btnClick.setBackgroundColor(Color.RED);
                            btnClick.setText("false no");
                        }
                        match = false;
                    }
                } else {
                    if (clicked) {
                        if (SessionParameters.showRightClicks) {
                            btnClick.setBackgroundColor(Color.MAGENTA);
                            btnClick.setText("false yes");
                        }
                        match = false;
                    } else {
                        if (SessionParameters.showRightClicks) {
                            btnClick.setBackgroundColor(Color.CYAN);
                            btnClick.setText("right no");
                        }
                        match = true;
                    }
                }
            } catch (Exception e) {
                match = true;
            }
            clickedRight.add(match);
        }
        return clickedRight;
    }


    public static int decideWhetherToInOrDecreaseNBackLevel(double percentage) {
        if (percentage < SessionParameters.increaseThreshold && percentage >= SessionParameters.decreaseThreshold) {
            return 0;
        }
        if (percentage < SessionParameters.decreaseThreshold) {
            return -1;
        }
        return 1;
    }

    public static void inOrDecreaseNBackLevel() {
        int increase = 0;
        boolean allowIncrease = true;
        boolean forceDecrease = false;
        if (SessionParameters.includePosition) {
            increase = RepeatStorage.decideWhetherToInOrDecreaseNBackLevel(SessionParameters.percentageRightTrialPosition);
        }
        if (SessionParameters.includeAudio && increase > -1) {
            increase = RepeatStorage.decideWhetherToInOrDecreaseNBackLevel(SessionParameters.percentageRightTrialAudio);
        }
        if (SessionParameters.includeColor && increase > -1) {
            increase = RepeatStorage.decideWhetherToInOrDecreaseNBackLevel(SessionParameters.percentageRightTrialColor);
        }
        if (increase < 0) {
            forceDecrease = true;
        }
        if (increase < 1) {
            allowIncrease = false;
        }
        if (!forceDecrease) {
            if (!allowIncrease) {
                increase = 0;
            }
        }
    }

//    public static void inOrDecreaseNBackLevel() {
//        int increase = 0;
//        boolean allowIncrease = true;
//        boolean forceDecrease = false;
//        if (SessionParameters.includePosition) {
//            increase = RepeatStorage.decideWhetherToInOrDecreaseNBackLevel(SessionParameters.percentageRightTrialPosition);
//        }
//        //############
//        if (increase < 1) {
//            allowIncrease = false;
//        }
//        if (increase < 0) {
//            forceDecrease = true;
//        }
//        //############
//        if (SessionParameters.includeAudio && increase > -1) {
//            increase = RepeatStorage.decideWhetherToInOrDecreaseNBackLevel(SessionParameters.percentageRightTrialAudio);
//        }
//        //############
//        if (increase < 0) {
//            forceDecrease = true;
//        }
//        if (increase < 1) {
//            allowIncrease = false;
//        }
//        //############
//        if (SessionParameters.includeColor && increase > -1) {
//            increase = RepeatStorage.decideWhetherToInOrDecreaseNBackLevel(SessionParameters.percentageRightTrialColor);
//        }
//        if (increase < 0) {
//            forceDecrease = true;
//        }
//        if (increase < 1) {
//            allowIncrease = false;
//        }
//        //############
//        if (!forceDecrease) {
//            if (!allowIncrease) {
//                increase = 0;
//            }
//        } else {
//            increase = -1;
//        }
//
////        nBackCulmulated = nBackCulmulated + nBack;
////        // TEST VARIABLE
////        addedNBACK++;
//        Log.i(TAG, "inOrDecreaseNBackLevel: " + " nBackStart: " + nBackBegin + " + addedNBACK + " + addedNBACK + " NBack " + nBack + " Cul: " + nBackCulmulated);
//        inOrDecreaseCulmulated = inOrDecreaseCulmulated + increase;
//        inOrDecreaseSingle.add((double) increase);
//        if (increase < 0) {
//            SessionParameters.nBack--;
//        }
//        if (increase == 1) {
//            SessionParameters.nBack++;
//        }
//        if (SessionParameters.nBack < 1) {
//            SessionParameters.nBack = 1;
//        }
//        if ((nBackMax <= nBack) && (trialCounter < trialsMax)) {
//            nBackMax = nBack;
//        }
//        maxPresentations = MAX_PRESENT_DEFAULT + SessionParameters.nBack;
//    }

    public static String getDay() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
        String timeAndDate = sdf.format(new Date());
        return sdf.format(new Date());
    }

    public static String getStartTime() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String timeAndDate = sdf.format(new Date());
        return sdf.format(new Date());
    }


    public static void createStringToStoreAndroid(boolean endOfSession) {
        if (duration == 1) {
            stringToStore = stringToStore
                    + timeSessionMarker + "\n"
                    + timeSession + "\n"

                    + daySessionMarker + "\n"
                    + daySession + "\n"

                    + maxTrialsMarker + "\n"
                    + trialsMax + "\n"
                    + nBackTrialMarker + "\n"
                    + nBack + "\n"
                    + percentageTrialMarker + "\n"
                    + percentageRightTrialAllModes + "\n"
                    + percentageSessionMarker + "\n"
                    + (percentageRightSessionAllModes / duration) + "\n"
                    + increaseSessionMarker + "\n"
                    + (inOrDecreaseCulmulated / duration) + "\n"
                    + nBackSessionMarker + "\n"
                    + (nBackCulmulated / duration) + "\n"
                    + maxNBackMarker + "\n"
                    + nBackMax + "\n"
                    + daySessionMarker + "\n"
                    + daySession + "\n";
        } else {

            if (trialCounter == 1 && !endOfSession) {
//            Log.d(TAG, "createStringToStore() returned: " + "I");
                stringToStore = stringToStore
                        + timeSessionMarker + "\n"
                        + timeSession + "\n"

                        + daySessionMarker + "\n"
                        + daySession + "\n"

                        + maxTrialsMarker + "\n"
                        + trialsMax + "\n"
                        + nBackTrialMarker + "\n"
                        + nBack + "\n"
                        + percentageTrialMarker + "\n"
                        + percentageRightTrialAllModes + "\n";
            }
            if (trialCounter > 1 && !endOfSession) {
                Log.i(TAG, "createStringToStore() returned: " + "II");
                stringToStore = stringToStore
                        + nBackTrialMarker + "\n"
                        + nBack + "\n"
                        + percentageTrialMarker + "\n"
                        + percentageRightTrialAllModes + "\n";

            }
            if (endOfSession) {
                Log.i(TAG, "createStringToStore() returned: " + "III\n" + "(" + nBackCulmulated + "/" + duration + ")" + "\n" + (nBackCulmulated / duration));
                stringToStore = stringToStore
                        + nBackTrialMarker + "\n"
                        + nBack + "\n"
                        + percentageTrialMarker + "\n"
                        + percentageRightTrialAllModes + "\n"
                        + percentageSessionMarker + "\n"
                        + (percentageRightSessionAllModes / duration) + "\n"
                        + increaseSessionMarker + "\n"
                        + (inOrDecreaseCulmulated / duration) + "\n"
                        + nBackSessionMarker + "\n"
                        + (nBackCulmulated / duration) + "\n"
                        + maxNBackMarker + "\n"
                        + nBackMax + "\n"
                        + daySessionEndMarker + "\n"
                        + daySession + "\n";
            }
        }

    }

    public static void createStringToStoreJava(boolean endOfSession) {
        if (duration == 1) {
            stringToStore = stringToStore
                    + SessionParameters.mode + "\n"
                    + timeSession + "\n"
                    + daySessionMarker + "\n"
                    + daySession + "\n"
                    + nBackTrialMarker + "\n"
                    + nBack + "\n"
                    + percentageTrialMarker + "\n"
                    + percentageRightTrialAllModes + "\n"
                    + timeSessionMarker + "\n"
                    + timeSession + "\n"
                    + daySessionMarker + "\n"
                    + daySession + "\n";
        } else {

            if (trialCounter == 1 && !endOfSession) {
//            Log.d(TAG, "createStringToStore() returned: " + "I");
                stringToStore = stringToStore
                        + SessionParameters.mode + "\n"
                        + timeSession + "\n"
                        + daySessionMarker + "\n"
                        + daySession + "\n"
                        + nBackTrialMarker + "\n"
                        + nBack + "\n"
                        + percentageTrialMarker + "\n"
                        + percentageRightTrialAllModes + "\n";
            }
            if (trialCounter > 1 && !endOfSession) {
                Log.i(TAG, "createStringToStore() returned: " + "II");
                stringToStore = stringToStore
                        + nBackTrialMarker + "\n"
                        + nBack + "\n"
                        + percentageTrialMarker + "\n"
                        + percentageRightTrialAllModes + "\n";

            }
            if (endOfSession) {
                Log.i(TAG, "createStringToStore() returned: " + "III\n" + "(" + nBackCulmulated + "/" + duration + ")" + "\n" + (nBackCulmulated / duration));
                stringToStore = stringToStore
                        + nBackTrialMarker + "\n"
                        + nBack + "\n"
                        + percentageTrialMarker + "\n"
                        + percentageRightTrialAllModes + "\n"
                        + timeSessionMarker + "\n"
                        + timeSession + "\n"
                        + daySessionEndMarker + "\n"
                        + daySession + "\n";
            }
        }

    }
}
