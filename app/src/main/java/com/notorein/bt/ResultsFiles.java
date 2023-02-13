package com.notorein.bt;


import static android.content.ContentValues.TAG;
import static com.notorein.bt.SessionParameters.durationTraining;
import static com.notorein.bt.SessionParameters.includeAudio;
import static com.notorein.bt.SessionParameters.includeColor;
import static com.notorein.bt.SessionParameters.includePosition;
import static com.notorein.bt.SessionParameters.lastDayOfUse;
import static com.notorein.bt.SessionParameters.modeOneDirectory;
import static com.notorein.bt.SessionParameters.resultsFilePath;
import static com.notorein.bt.SessionParameters.stringToStore;
import static com.notorein.bt.SessionParameters.stringToStoreInitial;
import static com.notorein.bt.SessionParameters.tempResultsAlreadyStored;
import static com.notorein.bt.SessionParameters.useTempResults;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;


public class ResultsFiles {


    private static ArrayList<String> results;

    static ArrayList<Double> trialsNBack;
    static ArrayList<ArrayList<Double>> sessionsNBack;

    static ArrayList<Double> trialsNBackTemp;
    static ArrayList<ArrayList<Double>> sessionsNBackTemp;
    static ArrayList<ArrayList<ArrayList<Double>>> daysNBackTemp;

    //##################################################################################
    static ArrayList<Double> trialsPerc;
    static ArrayList<Double> trialsPercPre;
    static ArrayList<ArrayList<ArrayList<Double>>> daysPercTemp;

    static ArrayList<Double> sessionsPercAverage;
    static ArrayList<Double> sessionsNBackMax;
    static ArrayList<Double> daysNBackMax;
    static ArrayList<Double> daysPercAverage;

    public static int nBackMaxAbsolute = 0;
    public static boolean test;
    public static int testStringIndex = 0;


    public static void calculateResultsForDisplay() {

        String timeOne = "";
        String timeTwo;
        String dayOne = "";
        String dayTwo = "";
        String value;
        double valueII;

        // The  Array trialsPercTemp is used to get the average of a trial or session

        trialsNBack = new ArrayList<>();
        sessionsNBack = new ArrayList<>();
        ArrayList<ArrayList<Double>> daysNBack = new ArrayList<>();
        daysNBackMax = new ArrayList<>();

        trialsNBackTemp = new ArrayList<>();
        sessionsNBackTemp = new ArrayList<>();
        daysNBackTemp = new ArrayList<>();

        trialsPercPre = new ArrayList<>();
        trialsPerc = new ArrayList<>();
        sessionsPercAverage = new ArrayList<>();
        daysPercAverage = new ArrayList<>();

        daysPercTemp = new ArrayList<>();
        ArrayList<Double> trialsNBackTempII = new ArrayList<>();
        nBackMaxAbsolute = 0;

        for (int i = 0; i < results.size(); i++) {
            value = results.get(i);
            if (i == 0) {
                i++;
                value = results.get(i);
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
                    sessionsNBack.add(trialsNBackTemp);
                    sessionsNBackTemp.add(trialsNBackTemp);
                    for (int m = 0; m < trialsNBackTemp.size(); m++) {
                        try {
                            daysNBack.get(daysNBack.size() - 1).add(trialsNBackTemp.get(m));
                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                    }
                    trialsNBackTemp = new ArrayList<>();
                }
                trialsNBackTempII = trialsNBackTemp;
                timeOne = timeTwo;
            }

            if (value.equals(SessionParameters.daySessionEndMarker)) {
                i++;
                value = results.get(i);
                try {
                    dayTwo = results.get(i + 4);
                } catch (Exception e) {
                    e.printStackTrace();
                    sessionsNBack.add(trialsNBackTemp);
                    if (dayTwo.equals(dayOne)) {
                        try {
                            for (int m = 0; m < trialsNBackTempII.size(); m++) {
                                daysNBack.get(daysNBack.size() - 1).add(trialsNBackTempII.get(m));
                            }
                        } catch (Exception ze) {
                            daysNBack.add(new ArrayList<>());
                            for (int m = 0; m < trialsNBackTempII.size(); m++) {
                                daysNBack.get(daysNBack.size() - 1).add(trialsNBackTempII.get(m));
                            }
                        }

                    }
                    sessionsNBackTemp = new ArrayList<>();
                }
                // In this line it recognizes that a new day has started.
                if (dayTwo.equals(dayOne)) {
                    daysNBack.add(new ArrayList<>());
                }

                dayOne = dayTwo;
            }

            if (value.equals(SessionParameters.nBackTrialMarker)) {
                i++;
                value = results.get(i);
                valueII = Double.parseDouble(value);
                trialsNBack.add(Double.parseDouble(value));
                trialsNBackTemp.add(Double.parseDouble(value));
                if (valueII > nBackMaxAbsolute) {
                    nBackMaxAbsolute = (int) valueII;
                }
                Log.e(TAG, "trialsNBackTemp " + trialsNBackTemp);
                value = results.get(i + 2);
                valueII = valueII + Double.parseDouble(value);
                if (valueII > 0) {
                    trialsPerc.add(valueII);
                }
            }
            Log.i(TAG, "calculateResultsForDisplay: " + i);
        }
        try {
            // If i don't add these lines the calculated arrays misses one value in the end
            sessionsNBack.get(sessionsNBack.size() - 1).add(trialsNBackTemp.get(trialsNBackTemp.size() - 1));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // If i don't add these lines the calculated arrays contain an empty array in the end
        if (daysNBack.size() > 1) {
            daysNBack.remove(daysNBack.size() - 1);
        }

//        // Otherwise the app will crash when there are no result
        try {
            if (daysNBack.get(0).isEmpty()) {
                daysNBack.get(0).add((double) nBackMaxAbsolute);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // In these steps I reduce the arrays to the display values
        double maxNBack = getMaxValue(trialsNBack);
        sessionsNBackMax = getMaxValueArray(sessionsNBack);
        daysNBackMax = getMaxValueArray(daysNBack);

        sessionsPercAverage = getAverage(sessionsNBack);
        daysPercAverage = getAverage(daysNBack);

        Log.e(TAG, "trialsNBack " + trialsNBack);
        Log.e(TAG, "sessionsNBack: " + sessionsNBack);
        Log.i(TAG, "daysNBack: " + daysNBack);
        Log.i(TAG, "nBackMaxAbsolute: " + nBackMaxAbsolute);

        Log.i(TAG, "");

        // This represents the arrays I want to display
        Log.e(TAG, "maxNBack: " + maxNBack);

        Log.e(TAG, "trialsPerc: " + trialsPerc);
        Log.e(TAG, "sessionsPercAverage: " + sessionsPercAverage);
        Log.e(TAG, "daysPercAverage: " + daysPercAverage);

        Log.e(TAG, "trialsNBack: " + trialsNBack);
        Log.e(TAG, "sessionsNBackMax: " + sessionsNBackMax);
        Log.e(TAG, "daysNBackMax: " + daysNBackMax);

    }


    private static double getMaxValue(ArrayList<Double> values) {
        double value;
        double max = 0;
        double maxNBack = 0;

//        for (int i = 0; i < values.size(); i++) {
        for (int n = 0; n < values.size(); n++) {
            value = values.get(n);
            if (value > max) {
                max = value;
                maxNBack = max;
            }
        }
//        }
        return maxNBack;
    }

    private static ArrayList<Double> getMaxValueArray(ArrayList<ArrayList<Double>> values) {
        double value;
        double max;
        double maxNBack;
        ArrayList<Double> maxArray = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            max = 0;
            maxNBack = 0;
            for (int n = 0; n < values.get(i).size(); n++) {
                value = values.get(i).get(n);
                if (value > max) {
                    max = value;
                    maxNBack = max;
                }
            }
            maxArray.add(maxNBack);
        }

        return maxArray;
    }

    public static ArrayList<Double> getAverage(ArrayList<ArrayList<Double>> list) {
        ArrayList<Double> average = new ArrayList<>();
        double value;
        for (int i = 0; i < list.size(); i++) {
            value = 0;
            for (int n = 0; n < list.get(i).size(); n++) {
                try {
                    value = value + list.get(i).get(n);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            value = value / list.get(i).size();
            average.add(value);
        }
        return average;

    }


    public static boolean compareIfDataAreTheSame(String msg, String dayOne, String dayTwo) {
        if (!dayOne.equals(dayTwo) && !dayOne.isEmpty()) {
            Log.i(TAG, "calculateResultsForDisplay: " + msg + " Dates not equal: " + dayOne + " # " + dayTwo + " #\n");
            return false;
        } else {
//            Log.i(TAG, "calculateResultsForDisplay: " + msg + " Dates are equal: " + dayOne + " # " + dayTwo + " #\n");
            return true;
        }
    }

    public static String initialiseStoringFilePaths(boolean useTempFile) {
        // This first IF-Statement is not really in use. It is supposed to change the filename when I implemented a duration mode
        // what I have not done yet.
        String resultsFilePath = "";
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

        if (useTempFile) {
            resultsFilePath = resultsFilePath + "TEMP";
        }
        resultsFilePath = modeOneDirectory + resultsFilePath;

        return resultsFilePath;
    }

    public static void saveResults(Context c, boolean append, String resultsFilePath) {
        int mode = Context.MODE_APPEND;
        if(!append){
            mode = Context.MODE_PRIVATE;
        }
        Log.i(TAG, "saveResults: "+ mode);
        try (FileOutputStream out = c.getApplicationContext().openFileOutput(resultsFilePath, mode)) {
            // Write the text to the file and flush the output stream
//            if (test) {
//                switch (testStringIndex) {
//                    case 0:
//                        stringToStore = Test.testI;
//                        break;
//                    case 1:
//                        stringToStore = Test.testII;
//                        break;
//                    case 2:
//                        stringToStore = Test.testIII;
//                        break;
//                }
//
//            }
            out.write(stringToStore.getBytes());
            out.flush();
        } catch (IOException e) {
            // Ignore the exception
        }
    }

    public static String readResults(Context c) {
        // Initialize the file paths
//        initialiseStoringFilePaths(useTempFile);

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
//                    Log.i(TAG, "\nlastDayOfUse: " + lastDayOfUse + "\n");
                }
                if (read.equals(SessionParameters.daySessionMarker)) {
                    readDayOfUse = true;
//                    Log.i(TAG, "\nread.equals(SessionParameters.daySessionMarker): " + lastDayOfUse + "\n");
                }

                // Add the line to the results list and the string to store
                results.add(read);
                stringToStore.append(read).append("\n");
            }

            // Check if the last day of use is different from the current day
//            Log.i(TAG, "readResults before: " + results);
            if (!compareIfDataAreTheSame("Compared Days", lastDayOfUse, RepeatStorage.getDay())) {
                if (!read.equals(SessionParameters.newDayMarker)) {
                    results.add(SessionParameters.newDayMarker);
                }
            }
//            Log.i(TAG, "readResults after: " + results);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return the string to store
        return stringToStore.toString();
    }


    public static void deleteResults(Context c, String filePath) {
        File file = new File(c.getApplicationContext().getFilesDir(),filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    public static void storeTempResults(Context c) {
        if (useTempResults && !tempResultsAlreadyStored) {
            // Here I am reading the original file
            resultsFilePath = initialiseStoringFilePaths(false);
            stringToStoreInitial = ResultsFiles.readResults(c);
            ResultsFiles.copyResults(c, resultsFilePath, initialiseStoringFilePaths(true));
            ResultsFiles.saveResults(c, true, initialiseStoringFilePaths(true));
            tempResultsAlreadyStored = true;
        }
    }
    public static void copyResults(Context c, String inputPath, String outputPath) {
        try {

            InputStream in = c.getApplicationContext().openFileInput(inputPath);
            OutputStream out = c.getApplicationContext().openFileOutput(outputPath, Context.MODE_PRIVATE);
            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            Log.e("copyFile", "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.e("copyFile", "Error copying file: " + e.getMessage());
        }


    }
}
