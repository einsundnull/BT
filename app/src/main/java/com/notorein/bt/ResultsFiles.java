package com.notorein.bt;


import static android.content.ContentValues.TAG;
import static com.notorein.bt.SessionParameters.durationTraining;
import static com.notorein.bt.SessionParameters.includeAudio;
import static com.notorein.bt.SessionParameters.includeColor;
import static com.notorein.bt.SessionParameters.includePosition;
import static com.notorein.bt.SessionParameters.lastDayOfUse;
import static com.notorein.bt.SessionParameters.modeOneDirectory;
import static com.notorein.bt.SessionParameters.nBackMax;
import static com.notorein.bt.SessionParameters.resultsFilePath;
import static com.notorein.bt.SessionParameters.stringToStore;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;


public class ResultsFiles {

    private static ArrayList<String> results;
//    public static ArrayList<Double> listTrialsNBack = new ArrayList<>();
//    public static ArrayList<Double> listTrialsPercentage = new ArrayList<>();
//
//    public static ArrayList<Double> listTrialsNBackTemp = new ArrayList<>();
//    public static ArrayList<Double> listTrialsPercentageTemp = new ArrayList<>();
//    //##############
//    public static ArrayList<ArrayList<Double>> listSessionsNBack = new ArrayList<>();
//    public static ArrayList<ArrayList<Double>> listSessionsPercentage = new ArrayList<>();
//
//    public static ArrayList<ArrayList<Double>> listSessionsNBackTemp = new ArrayList<>();
//    public static ArrayList<ArrayList<Double>> listSessionsPercentageTemp = new ArrayList<>();
//
//    public static ArrayList<Double> listSessionsNBackMax = new ArrayList<>();
//    public static ArrayList<Double> listSessionsNBackMaxTemp = new ArrayList<>();
//    public static ArrayList<ArrayList<Double>> listDaysNBackMaxTemp = new ArrayList<>();
//    public static ArrayList<Double> listDaysNBackMax = new ArrayList<>();
//
//
//    public static ArrayList<Double> listDayAverageNBack = new ArrayList<>();
//    public static ArrayList<Double> listDayAveragePercentage = new ArrayList<>();
//
//    public static ArrayList<ArrayList<ArrayList<Double>>> listDayNBack = new ArrayList<>();
//    public static ArrayList<ArrayList<ArrayList<Double>>> listDayPercentages = new ArrayList<>();


    static ArrayList<Double> trialsNBack;
    static ArrayList<ArrayList<Double>> sessionsNBack;
    static ArrayList<ArrayList<ArrayList<Double>>> daysNBack;

    static ArrayList<Double> trialsNBackTemp;
    static ArrayList<ArrayList<Double>> sessionsNBackTemp;
    static ArrayList<ArrayList<ArrayList<Double>>> daysNBackTemp;

    //##################################################################################
    static ArrayList<Double> trialsPerc;
    static ArrayList<ArrayList<Double>> sessionsPerc;
    static ArrayList<ArrayList<ArrayList<Double>>> daysPerc;

    static ArrayList<Double> trialsPercTemp;
    static ArrayList<ArrayList<Double>> sessionsPercTemp;
    static ArrayList<ArrayList<ArrayList<Double>>> daysPercTemp;


    static double sessionAveragePercentage = 0;
    static double sessionAverageNBack = 0;

    static double dayAveragePercentage = 0;
    static double dayAverageNBack = 0;

    static double nBackMaxSession = 0;
    static double nBackMaxDay = 0;
    public static double nBackMaxAbsolute = 0;
    static int trialCounter = 0;
    private static ArrayList<ArrayList<Double>> daysPercII = new ArrayList<>();
    public static boolean test;
    private static ArrayList<ArrayList<Double>> daysNBackII;
    private static ArrayList<Double> trialsPercTempII;
    private static ArrayList<Double> trialsNBackTempII;


    public static void readResultsAndCalculatePercentage() {

    }

    public static void initialiseStoringFilePaths() {
        if (durationTraining) {
            modeOneDirectory = "durationSessionInTrials";
        } else {
            modeOneDirectory = "classic";
        }


        if (includeAudio && includeColor && includePosition) {
            resultsFilePath = "Pos_Aud_Col";
        }
        // only audio
        if (includeAudio && !includeColor && !includePosition) {
            resultsFilePath = "Aud";

        }
        // only color
        if (!includeAudio && includeColor && !includePosition) {
            resultsFilePath = "Col";
        }

        // only position
        if (!includeAudio && !includeColor && includePosition) {
            resultsFilePath = "Pos";
        }

        // audio and color
        if (includeAudio && includeColor && !includePosition) {
            resultsFilePath = "Aud_Col";
        }

        // audio and position
        if (includeAudio && !includeColor && includePosition) {
            resultsFilePath = "Pos_Aud";
        }

        // color and position
        if (!includeAudio && includeColor && includePosition) {
            resultsFilePath = "Pos_Col";
        }

        resultsFilePath = modeOneDirectory + resultsFilePath;
    }

    public void scanForLineGraph(ArrayList<Object> line, ArrayList<Double> layoutY, String directoryAddition, String fileAddition, String visOrAud, TrialSessionOrDay choose,
                                 boolean showPercentage, ArrayList<Object> rect, ArrayList<String> dateList) {
        try {

            int i = 0;
//            double firstlinesY = StartScreen.screenHeight - StartScreen.screenWidth * 0.05625;
            double firstlinesY = SessionParameters.displayHeight - SessionParameters.displayWidth * 0.05625;
            double distance = firstlinesY / (nBackMax + 2);
            String sessionID = "";
            String tempSessionID = "";
            String day = "";
            String temp = "";
            String date = "";
            double nback = 0;
            double tempNback = 0;
            double percentage = 0;
            double maxNBack = 0;
            double tempLayoutY = 0;
            double tempPercentage = 0;

//            String path = "";
//            if (StartScreen.durationTraining) {
//                path = ResultsFiles.storingPathDuration;
//            } else {
//                path = ResultsFiles.storingPathRegular;
//            }
            initialiseStoringFilePaths();
            File file = new File(resultsFilePath);

            if (file.exists()) {
                try {
                    Scanner scn = new Scanner(file);
                    while (scn.hasNext()) {
                        temp = scn.next();
                        if (temp.equals("date:")) {
                            date = scn.next();
                        }
                        if (temp.equals("sessionID:")) {
                            sessionID = scn.next();
                        }
                        if (temp.equals("day:") && !day.equals(date)) {
                            day = scn.next();
                        }

                        switch (choose) {
                            case TRIAL:
                                if (temp.equals("n-back:")) {
                                    nback = (int) Double.parseDouble(scn.next());
                                    i++;
                                }
                                if (temp.equals("percentage:")) {
                                    if (showPercentage) {
                                        percentage = Double.parseDouble(scn.next());
                                        layoutY.add(firstlinesY - nback * distance - distance * percentage);
                                    } else if (showPercentage == false) {
                                        layoutY.add(firstlinesY - nback * distance);
                                    }
                                }
                                break;
                            case SESSION:
                                if (temp.equals("n-back:")) {
                                    if (showPercentage) {
                                        nback = nback + Double.parseDouble(scn.next());
                                        i++;
                                    } else {
                                        nback = Double.parseDouble(scn.next());
                                        if (nback > maxNBack) {
                                            maxNBack = nback;
                                        }
                                        i++;
                                    }
                                }
                                if (temp.equals("percentage:")) {
                                    percentage = percentage + Double.parseDouble(scn.next());
                                }
                                if (!tempSessionID.equals(sessionID)) {
                                    tempSessionID = sessionID;
                                    if (showPercentage) {
                                        tempNback = nback / i;
                                        tempPercentage = percentage / i;
                                        tempLayoutY = firstlinesY - tempNback * distance - distance * tempPercentage;
                                        layoutY.add(tempLayoutY);
                                    } else {
                                        tempLayoutY = firstlinesY - maxNBack * distance;
                                        layoutY.add(tempLayoutY);
                                    }
                                    tempPercentage = 0;
                                    tempNback = 0;
                                    maxNBack = 0;
                                    nback = 0;
                                    percentage = 0;
                                    i = 0;
                                }
                                break;
                            case DAY:
                                if (temp.equals("n-back:")) {
                                    if (showPercentage) {
                                        nback = nback + Double.parseDouble(scn.next());
                                        i++;
                                    } else {
                                        nback = Double.parseDouble(scn.next());
                                        if (nback > maxNBack) {
                                            maxNBack = nback;
                                        }
                                        i++;
                                    }
                                }
                                if (temp.equals("percentage:")) {
                                    percentage = percentage + Double.parseDouble(scn.next());
                                }
                                if (temp.equals("Next_Day") || !scn.hasNext()) {

                                    if (showPercentage) {
                                        tempNback = nback / i;
                                        tempPercentage = percentage / i;
                                        tempLayoutY = firstlinesY - tempNback * distance - distance * tempPercentage;
                                        layoutY.add(tempLayoutY);
                                    } else {
                                        tempLayoutY = firstlinesY - maxNBack * distance;
                                        layoutY.add(tempLayoutY);
                                    }
                                    tempPercentage = 0;
                                    tempNback = 0;
                                    maxNBack = 0;
                                    nback = 0;
                                    percentage = 0;
                                    i = 0;
                                }
                                break;
                        }
                    }
                    scn.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {

        }
    }

    public static void calculateResultsForDisplay() {

        String timeOne = "";
        String timeTwo = "";
        String dayOne = "";
        String dayTwo = "";
        String value = "";

        double maxNBack = 0;
        double[] returnValues = new double[2];

        // The  Array trialsPercTemp is used to get the average of a trial or session

        trialsNBack = new ArrayList<>();
        sessionsNBack = new ArrayList<>();
        daysNBack = new ArrayList<>();

        trialsNBackTemp = new ArrayList<>();
        sessionsNBackTemp = new ArrayList<>();
        daysNBackTemp = new ArrayList<>();

//##################################################################################
        trialsPerc = new ArrayList<>();
        sessionsPerc = new ArrayList<>();
        daysPerc = new ArrayList<>();

        trialsPercTemp = new ArrayList<>();
        sessionsPercTemp = new ArrayList<>();
        daysPercTemp = new ArrayList<>();
//##################################################################################
        daysNBackII = new ArrayList<>();
        daysPercII = new ArrayList<>();

        trialsPercTempII = new ArrayList<>();
        trialsNBackTempII = new ArrayList<>();
        daysPerc.add(new ArrayList<>());
        daysNBack.add(new ArrayList<>());

        for (int i = 0; i < results.size(); i++) {
            value = results.get(i);
            if (i == 0) {
                i++;
                value = results.get(i);
                timeTwo = value;
                timeOne = value;
                // Here I make sure that the first day is recognized as the first day
                dayTwo = results.get(i + 2);
                dayOne = results.get(i + 2);
            }

            if (value.equals(SessionParameters.timeSessionMarker)) {
                i++;
                value = results.get(i);
                timeTwo = value;
                // In this line it recognizes that a new session has started.
                if (!timeTwo.equals(timeOne)) {
                    // If I don't add this if statement it creates an Array list with list.get(0) = NAN;
                    // Here I get the average of the trial
                    sessionsPerc.add(trialsPercTemp);
                    sessionsPercTemp.add(trialsPercTemp);
                    sessionsNBack.add(trialsNBackTemp);
                    sessionsNBackTemp.add(trialsNBackTemp);
                    for (int m = 0; m < trialsPercTemp.size(); m++) {
                        daysPercII.get(daysPercII.size() - 1).add(trialsPercTemp.get(m));
                        daysNBackII.get(daysNBackII.size() - 1).add(trialsNBackTemp.get(m));
                    }
                    trialsPercTemp = new ArrayList<>();
                    trialsNBackTemp = new ArrayList<>();
                }
                trialsPercTempII = trialsPercTemp;
                trialsNBackTempII = trialsNBackTemp;
                timeOne = timeTwo;
            }

            if (value.equals(SessionParameters.daySessionEndMarker)) {
                i++;
                value = results.get(i);
                try {
                    dayTwo = results.get(i + 4);
                } catch (Exception e) {
//                    dayTwo = dayOne;
                    e.printStackTrace();
                    sessionsPerc.add(trialsPercTemp);
                    sessionsNBack.add(trialsNBackTemp);
                    if (dayTwo.equals(dayOne)) {
                        daysPercII.add(new ArrayList<Double>());
                        daysNBackII.add(new ArrayList<Double>());
                    }
                    for (int m = 0; m < trialsPercTempII.size(); m++) {
                        daysPercII.get(daysPercII.size() - 1).add(trialsPercTempII.get(m));
                        daysNBackII.get(daysNBackII.size() - 1).add(trialsNBackTempII.get(m));
                    }
                    sessionsPercTemp = new ArrayList<>();
                    sessionsNBackTemp = new ArrayList<>();
                }
                // In this line it recognizes that a new day has started.
                if (dayTwo.equals(dayOne)) {
                    daysPercII.add(new ArrayList<Double>());
                    daysNBackII.add(new ArrayList<Double>());
                }

                dayOne = dayTwo;
            }

            if (value.equals(SessionParameters.nBackTrialMarker)) {
                i++;
                value = results.get(i);
                trialsNBack.add(Double.parseDouble(value));
                trialsNBackTemp.add(Double.parseDouble(value));
                Log.e(TAG, "trialsNBackTemp " + trialsNBackTemp);
                value = results.get(i + 2);
                trialsPerc.add(Double.parseDouble(value));
                trialsPercTemp.add(Double.parseDouble(value));
            }
            Log.i(TAG, "calculateResultsForDisplay: " + i);
        }
        daysPercII.remove(daysPercII.size() - 1);
        daysNBackII.remove(daysNBackII.size() - 1);
//        Log.e(TAG, "trialsPerc: " + trialsPerc);
//        Log.e(TAG, "trialsPercTemp: " + trialsPercTemp);
        Log.e(TAG, "trialsNBack " + trialsNBack);
//        Log.e(TAG, "sessionsPerc: " + sessionsPerc);
        Log.e(TAG, "sessionsNBack: " + sessionsNBack);
//        Log.i(TAG, "daysPerc: " + daysPerc);
//        Log.i(TAG, "daysNBack: " + daysNBack);
        Log.i(TAG, "daysNBackII: " + daysNBackII);
        Log.i(TAG, "returnValues[1]: " + returnValues[1]);
    }

    private static double[] getMaxValue(ArrayList<Double> values, double maxNBack) {
        double value = 0;
        double max = 0;
        double[] returnValue = {0, 0};
        for (int i = 0; i < values.size(); i++) {
            value = values.get(i);
            if (value > max) {
                max = value;
                returnValue[0] = value;
                if (maxNBack < max) {
                    returnValue[1] = max;
                }
            }
        }
        return returnValue;
    }

    private static Double getAverage(ArrayList<Double> trialsTemp) {
        double value = 0;
        for (int i = 0; i < trialsTemp.size(); i++) {
            value = value + trialsTemp.get(i);
        }
        return value / trialsTemp.size();
    }


//    public static void calculateResultsForDisplay() {
//
//        String dayNextSessionMarker = "";
//        String daySessionEndMarker = "";
//        resetValues();
//        String temp = "";
//        double tempNBack = 0;
//        double tempPercentage = 0;
//        double sessionAverageNBack = 0;
//        double sessionAveragePercentage = 0;
//        double dayAverageNBack = 0;
//        double dayAveragePercentage = 0;
//        int trialCounter = 0;
//        double nBackMaxSession = 0;
//        List<List<Double>> listDaysNBackMaxTemp = new ArrayList<>();
//        List<Double> listSessionsNBackMax = new ArrayList<>();
//        List<Double> listSessionsNBackMaxTemp = new ArrayList<>();
//        List<Double> listDayAverageNBack = new ArrayList<>();
//        List<Double> listDayAveragePercentage = new ArrayList<>();
//        int size = results.size();
//
//        for (int i = 0; i < size; i++) {
//            temp = results.get(i);
//
//            if (temp.equals(SessionParameters.nBackTrialMarker)) {
//                i++;
//                tempNBack = Integer.parseInt(results.get(i));
//                listTrialsNBack.add(tempNBack);
//                listTrialsNBackTemp.add(tempNBack);
//
//                listTrialsPercentage.add((tempNBack ) + Double.parseDouble(results.get(i + 2)));
//                listTrialsPercentageTemp.add((tempNBack ) + Double.parseDouble(results.get(i + 2)));
//            }
//            if (temp.equals(SessionParameters.daySessionEndMarker)) {
//                listSessionsNBack.add(listTrialsNBackTemp);
//                listSessionsPercentage.add(listTrialsPercentageTemp);
//                listSessionsNBackTemp.add(listTrialsNBackTemp);
//                listSessionsPercentageTemp.add(listTrialsPercentageTemp);
//                listTrialsNBackTemp = new ArrayList<>();
//                listTrialsPercentageTemp = new ArrayList<>();
//            }
//            if (temp.equals(SessionParameters.daySessionEndMarker)) {
//                i++;
//                daySessionEndMarker = results.get(i);
//                try {
//                    dayNextSessionMarker = results.get(i + 4);
//                    if (!compareIfDatasAreTheSame("Found daySessionEndMarker ", daySessionEndMarker, dayNextSessionMarker)) {
//                        listDayNBack.add(listSessionsNBackTemp);
//                        listDayPercentages.add(listSessionsPercentageTemp);
//                        listSessionsPercentageTemp = new ArrayList<>();
//                        listSessionsNBackTemp = new ArrayList<>();
//                    }
//                } catch (Exception e) {
//                    Log.i(TAG, "calculateResultsForDisplay: " + " noNextDay");
//                    listDayNBack.add(listSessionsNBackTemp);
//                    listDayPercentages.add(listSessionsPercentageTemp);
//                    listSessionsPercentageTemp = new ArrayList<>();
//                    listSessionsNBackTemp = new ArrayList<>();
//                }
//            }
//        }
//
//        for (int i = 0; i < size; i++) {
//            temp = results.get(i);
//
//            if (temp.equals(SessionParameters.nBackTrialMarker)) {
//                i++;
//                tempNBack = Integer.parseInt(results.get(i));
//                listTrialsNBack.add(tempNBack);
//                listTrialsNBackTemp.add(tempNBack);
//                listTrialsPercentage.add((tempNBack ) + Double.parseDouble(results.get(i + 2)));
//                listTrialsPercentageTemp.add((tempNBack ) + Double.parseDouble(results.get(i + 2)));
//            }
////            if (temp.equals(SessionParameters.daySessionEndMarker)) {
////                listSessionsNBack.add(listTrialsNBackTemp);
////                listSessionsPercentage.add(listTrialsPercentageTemp);
////                listSessionsNBackTemp.add(listTrialsNBackTemp);
////                listSessionsPercentageTemp.add(listTrialsPercentageTemp);
////                listTrialsNBackTemp = new ArrayList<>();
////                listTrialsPercentageTemp = new ArrayList<>();
////            }
//            if (temp.equals(SessionParameters.daySessionEndMarker)) {
//                i++;
//                daySessionEndMarker = results.get(i);
//                try {
//                    dayNextSessionMarker = results.get(i + 4);
//                    if (!compareIfDatasAreTheSame("Found daySessionEndMarker ", daySessionEndMarker, dayNextSessionMarker)) {
//                        listDayNBack.add(listSessionsNBackTemp);
//                        listDayPercentages.add(listSessionsPercentageTemp);
//                        listSessionsPercentageTemp = new ArrayList<>();
//                        listSessionsNBackTemp = new ArrayList<>();
//                    }
//                } catch (Exception e) {
//                    Log.i(TAG, "calculateResultsForDisplay: " + " noNextDay");
//                    listDayNBack.add(listSessionsNBackTemp);
//                    listDayPercentages.add(listSessionsPercentageTemp);
//                    listSessionsPercentageTemp = new ArrayList<>();
//                    listSessionsNBackTemp = new ArrayList<>();
//                }
//            }
//        }
//
//
//
////        for (int d = 0; d < listDayNBack.size(); d++) {
////            for (int s = 0; s < listDayNBack.get(d).size(); s++) {
////                for (int t = 0; t < listDayNBack.get(d).get(s).size(); t++) {
////                    double nBack = listDayNBack.get(d).get(s).get(t);
////                    double percentage = listDayPercentages.get(d).get(s).get(t);
////                    sessionAverageNBack += nBack;
////                    nBackMaxSession = Math.max(nBackMaxSession, nBack);
////                    sessionAveragePercentage += percentage;
////                    trialCounter++;
////                }
////                listSessionsNBackMax.add(nBackMaxSession);
////                listSessionsNBackMaxTemp.add(nBackMaxSession);
////            }
////            listDaysNBackMaxTemp.add(listSessionsNBackMaxTemp);
////            listSessionsNBackMaxTemp = new ArrayList<>();
////            dayAverageNBack += sessionAverageNBack / trialCounter;
////            dayAveragePercentage += sessionAveragePercentage / trialCounter;
////            listDayAverageNBack.add(dayAverageNBack);
////            listDayAveragePercentage.add(dayAveragePercentage);
////            trialCounter = 0;
////            sessionAverageNBack = 0;
////            sessionAveragePercentage = 0;
////            dayAverageNBack = 0;
////            dayAveragePercentage = 0;
////            nBackMaxSession = 0;
////        }
//
//        List<Double> listDaysNBackMax = new ArrayList<>();
//        for (int d = 0; d < listDayNBack.size(); d++) {
//            double maxNBackDay = 0;
//            for (int s = 0; s < listDayNBack.get(d).size(); s++) {
//                for (int t = 0; t < listDayNBack.get(d).get(s).size(); t++) {
//                    maxNBackDay = Math.max(maxNBackDay, listDayNBack.get(d).get(s).get(t));
//                }
//            }
//            listDaysNBackMax.add(maxNBackDay);
//        }
//
//        // Here I get the absolute highest nback value. It is important to display the
//        // results in a proper scale.
//        for (int m = 0; m < listDaysNBackMax.size(); m++) {
//            if (nBackMaxAbsolute < listDaysNBackMax.get(m)) {
//                nBackMaxAbsolute = listDaysNBackMax.get(m);
//            }
//        }
////        Log.i(TAG, "\n");
////        Log.i(TAG, "listTrialsNBack " + listTrialsNBack);
////        Log.i(TAG, "listTrialsPercentage " + listTrialsPercentage);
////        Log.i(TAG, "listSessionsNBack " + listSessionsNBack);
////        Log.i(TAG, "listSessionsPercentage " + listSessionsPercentage);
////        Log.i(TAG, "listSessionsNBackMax " + listSessionsNBackMax);
////
////        Log.i(TAG, "listDayNBack " + listDayNBack);
////        Log.i(TAG, "listDayPercentages " + listDayPercentages);
////        Log.i(TAG, "listDayAverageNBack:  " + listDayAverageNBack + "\n");
////        Log.i(TAG, "listDayAverage:  " + listDayAveragePercentage + "\n");
////        Log.i(TAG, "listDaysNBackMax:  " + listDaysNBackMax + "\n");
////        Log.i(TAG, "nBackMaxAbsolute:  " + nBackMaxAbsolute + "\n");
////        Log.i(TAG, "\n");
//
//
//    }

//    public static void calculateResultsForDisplay() {
//
//        String dayNextSessionMarker = "";
//        String daySessionEndMarker = "";
//        resetValues();
//        String temp = "";
//        double tempNBack = 0;
//        double tempPercentage = 0;
//        double sessionAverageNBack = 0;
//        double sessionAveragePercentage = 0;
//        double dayAverageNBack = 0;
//        double dayAveragePercentage = 0;
//        int trialCounter = 0;
//        double nBackMaxSession = 0;
//        List<List<Double>> listDaysNBackMaxTemp = new ArrayList<>();
//        List<Double> listSessionsNBackMax = new ArrayList<>();
//        List<Double> listSessionsNBackMaxTemp = new ArrayList<>();
//        List<Double> listDayAverageNBack = new ArrayList<>();
//        List<Double> listDayAveragePercentage = new ArrayList<>();
//        int size = results.size();
//
//        for (int i = 0; i < size; i++) {
//            temp = results.get(i);
//
//            if (temp.equals(SessionParameters.nBackTrialMarker)) {
//                i++;
//                tempNBack = Integer.parseInt(results.get(i));
//                listTrialsNBack.add(tempNBack);
//                listTrialsNBackTemp.add(tempNBack);
//
//                listTrialsPercentage.add((tempNBack ) + Double.parseDouble(results.get(i + 2)));
//                listTrialsPercentageTemp.add((tempNBack ) + Double.parseDouble(results.get(i + 2)));
//            }
//            if (temp.equals(SessionParameters.daySessionEndMarker)) {
//                listSessionsNBack.add(listTrialsNBackTemp);
//                listSessionsPercentage.add(listTrialsPercentageTemp);
//                listSessionsNBackTemp.add(listTrialsNBackTemp);
//                listSessionsPercentageTemp.add(listTrialsPercentageTemp);
//                listTrialsNBackTemp = new ArrayList<>();
//                listTrialsPercentageTemp = new ArrayList<>();
////            }
////            if (temp.equals(SessionParameters.daySessionEndMarker)) {
//                i++;
//                daySessionEndMarker = results.get(i);
//                try {
//                    dayNextSessionMarker = results.get(i + 4);
//                    if (!compareIfDatasAreTheSame("Found daySessionEndMarker ", daySessionEndMarker, dayNextSessionMarker)) {
//                        listDayNBack.add(listSessionsNBackTemp);
//                        listDayPercentages.add(listSessionsPercentageTemp);
//                        listSessionsPercentageTemp = new ArrayList<>();
//                        listSessionsNBackTemp = new ArrayList<>();
//                    }
//                } catch (Exception e) {
//                    Log.i(TAG, "calculateResultsForDisplay: " + " noNextDay");
//                    listDayNBack.add(listSessionsNBackTemp);
//                    listDayPercentages.add(listSessionsPercentageTemp);
//                    listSessionsPercentageTemp = new ArrayList<>();
//                    listSessionsNBackTemp = new ArrayList<>();
//                }
//            }
//        }
//
//        for (int i = 0; i < size; i++) {
//            temp = results.get(i);
//
//            if (temp.equals(SessionParameters.nBackTrialMarker)) {
//                i++;
//                tempNBack = Integer.parseInt(results.get(i));
//                listTrialsNBack.add(tempNBack);
//                listTrialsNBackTemp.add(tempNBack);
//                listTrialsPercentage.add((tempNBack ) + Double.parseDouble(results.get(i + 2)));
//                listTrialsPercentageTemp.add((tempNBack ) + Double.parseDouble(results.get(i + 2)));
//            }
//            if (temp.equals(SessionParameters.daySessionEndMarker)) {
//                listSessionsNBack.add(listTrialsNBackTemp);
//                listSessionsPercentage.add(listTrialsPercentageTemp);
//                listSessionsNBackTemp.add(listTrialsNBackTemp);
//                listSessionsPercentageTemp.add(listTrialsPercentageTemp);
//                listTrialsNBackTemp = new ArrayList<>();
//                listTrialsPercentageTemp = new ArrayList<>();
//            }
//            if (temp.equals(SessionParameters.daySessionEndMarker)) {
//                i++;
//                daySessionEndMarker = results.get(i);
//                try {
//                    dayNextSessionMarker = results.get(i + 4);
//                    if (!compareIfDatasAreTheSame("Found daySessionEndMarker ", daySessionEndMarker, dayNextSessionMarker)) {
//                        listDayNBack.add(listSessionsNBackTemp);
//                        listDayPercentages.add(listSessionsPercentageTemp);
//                        listSessionsPercentageTemp = new ArrayList<>();
//                        listSessionsNBackTemp = new ArrayList<>();
//                    }
//                } catch (Exception e) {
//                    Log.i(TAG, "calculateResultsForDisplay: " + " noNextDay");
//                    listDayNBack.add(listSessionsNBackTemp);
//                    listDayPercentages.add(listSessionsPercentageTemp);
//                    listSessionsPercentageTemp = new ArrayList<>();
//                    listSessionsNBackTemp = new ArrayList<>();
//                }
//            }
//        }
//
//
//
//        for (int d = 0; d < listDayNBack.size(); d++) {
//            for (int s = 0; s < listDayNBack.get(d).size(); s++) {
//                for (int t = 0; t < listDayNBack.get(d).get(s).size(); t++) {
//                    double nBack = listDayNBack.get(d).get(s).get(t);
//                    double percentage = listDayPercentages.get(d).get(s).get(t);
//                    sessionAverageNBack += nBack;
//                    nBackMaxSession = Math.max(nBackMaxSession, nBack);
//                    sessionAveragePercentage += percentage;
//                    trialCounter++;
//                }
//                listSessionsNBackMax.add(nBackMaxSession);
//                listSessionsNBackMaxTemp.add(nBackMaxSession);
//            }
//            listDaysNBackMaxTemp.add(listSessionsNBackMaxTemp);
//            listSessionsNBackMaxTemp = new ArrayList<>();
//            dayAverageNBack += sessionAverageNBack / trialCounter;
//            dayAveragePercentage += sessionAveragePercentage / trialCounter;
//            listDayAverageNBack.add(dayAverageNBack);
//            listDayAveragePercentage.add(dayAveragePercentage);
//            trialCounter = 0;
//            sessionAverageNBack = 0;
//            sessionAveragePercentage = 0;
//            dayAverageNBack = 0;
//            dayAveragePercentage = 0;
//            nBackMaxSession = 0;
//        }
//
//        List<Double> listDaysNBackMax = new ArrayList<>();
//        for (int d = 0; d < listDayNBack.size(); d++) {
//            double maxNBackDay = 0;
//            for (int s = 0; s < listDayNBack.get(d).size(); s++) {
//                for (int t = 0; t < listDayNBack.get(d).get(s).size(); t++) {
//                    maxNBackDay = Math.max(maxNBackDay, listDayNBack.get(d).get(s).get(t));
//                }
//            }
//            listDaysNBackMax.add(maxNBackDay);
//        }
//
//        // Here I get the absolute highest nback value. It is important to display the
//        // results in a proper scale.
//        for (int m = 0; m < listDaysNBackMax.size(); m++) {
//            if (nBackMaxAbsolute < listDaysNBackMax.get(m)) {
//                nBackMaxAbsolute = listDaysNBackMax.get(m);
//            }
//        }
////        Log.i(TAG, "\n");
////        Log.i(TAG, "listTrialsNBack " + listTrialsNBack);
////        Log.i(TAG, "listTrialsPercentage " + listTrialsPercentage);
////        Log.i(TAG, "listSessionsNBack " + listSessionsNBack);
////        Log.i(TAG, "listSessionsPercentage " + listSessionsPercentage);
////        Log.i(TAG, "listSessionsNBackMax " + listSessionsNBackMax);
////
////        Log.i(TAG, "listDayNBack " + listDayNBack);
////        Log.i(TAG, "listDayPercentages " + listDayPercentages);
////        Log.i(TAG, "listDayAverageNBack:  " + listDayAverageNBack + "\n");
////        Log.i(TAG, "listDayAverage:  " + listDayAveragePercentage + "\n");
////        Log.i(TAG, "listDaysNBackMax:  " + listDaysNBackMax + "\n");
////        Log.i(TAG, "nBackMaxAbsolute:  " + nBackMaxAbsolute + "\n");
////        Log.i(TAG, "\n");
//
//
//    }

    private static void resetValues() {
//        listTrialsNBack = new ArrayList<>();
//        listTrialsPercentage = new ArrayList<>();
//
//        listTrialsNBackTemp = new ArrayList<>();
//        listTrialsPercentageTemp = new ArrayList<>();
////##############
//        listSessionsNBack = new ArrayList<>();
//        listSessionsPercentage = new ArrayList<>();
//
//        listSessionsNBackTemp = new ArrayList<>();
//        listSessionsPercentageTemp = new ArrayList<>();
//
//        listSessionsNBackMax = new ArrayList<>();
//        listSessionsNBackMaxTemp = new ArrayList<>();
//        listDaysNBackMaxTemp = new ArrayList<>();
//        listDaysNBackMax = new ArrayList<>();
//
//
//        listDayAverageNBack = new ArrayList<>();
//        listDayAveragePercentage = new ArrayList<>();
//
//        listDayNBack = new ArrayList<>();
//        listDayPercentages = new ArrayList<>();

        sessionAveragePercentage = 0;
        sessionAverageNBack = 0;

        dayAveragePercentage = 0;
        dayAverageNBack = 0;

        nBackMaxSession = 0;
        nBackMaxDay = 0;
        nBackMaxAbsolute = 0;
        trialCounter = 0;
    }


    public static boolean compareIfDatasAreTheSame(String msg, String dayOne, String dayTwo) {
        if (!dayOne.equals(dayTwo) && !dayOne.isEmpty()) {
            Log.i(TAG, "calculateResultsForDisplay: " + msg + " Dates not equal: " + dayOne + " # " + dayTwo + " #\n");
            return false;
        } else {
            Log.i(TAG, "calculateResultsForDisplay: " + msg + " Dates are equal: " + dayOne + " # " + dayTwo + " #\n");
            return true;
        }
    }


    public static void saveResults(Context c) {
        // Initialize the file path
        initialiseStoringFilePaths();

        try (FileOutputStream out = c.getApplicationContext().openFileOutput(resultsFilePath, Context.MODE_APPEND)) {
            // Write the text to the file and flush the output stream
            if (test) {
                stringToStore = Test.test;
            }
            out.write(stringToStore.getBytes());
            out.flush();
        } catch (IOException e) {
            // Ignore the exception
        }
    }

    public static String readResults(Context c) {
        // Initialize the file paths
        initialiseStoringFilePaths();

        // Initialize the variables
        StringBuilder stringToStore = new StringBuilder();
        boolean readDayOfUse = false;
        results = new ArrayList<>();
        lastDayOfUse = "";

        try (InputStream ips = c.getApplicationContext().openFileInput(resultsFilePath);
             Scanner scn = new Scanner(ips, "UTF-8")) {
            String read = "";
            // Read each line from the file
            while (scn.hasNext()) {
                read = scn.next();

                // Check if the line indicates the day of use
                if (readDayOfUse) {
                    lastDayOfUse = read;
                    readDayOfUse = false;
                    Log.i(TAG, "\nlastDayOfUse: " + lastDayOfUse + "\n");
                }
                if (read.equals(SessionParameters.daySessionMarker)) {
                    readDayOfUse = true;
                    Log.i(TAG, "\nread.equals(SessionParameters.daySessionMarker): " + lastDayOfUse + "\n");
                }

                // Add the line to the results list and the string to store
                results.add(read);
                stringToStore.append(read).append("\n");
            }

            // Check if the last day of use is different from the current day
            Log.i(TAG, "readResults before: " + results);
            if (!compareIfDatasAreTheSame("Compared Days", lastDayOfUse, RepeatStorage.getDay())) {
                if (!read.equals(SessionParameters.newDayMarker)) {
                    results.add(SessionParameters.newDayMarker);
                }
            }
            Log.i(TAG, "readResults after: " + results);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return the string to store
        return stringToStore.toString();
    }
}
