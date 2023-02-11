package com.notorein.bt;

import android.content.Context;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;


public class FileLogicSettings {
    public static String fileNameLessonSettings = "settings";

    public static void readSettings(Context c) {
        try {
            InputStream ips = c.getApplicationContext().openFileInput(fileNameLessonSettings);
            Scanner scn;
            scn = new Scanner(ips, "UTF-8");
            while (scn.hasNext()) {
                try {
                    scn.next();
                    SessionParameters.squareSize = Integer.parseInt(scn.next());
                    scn.next();
                    SessionParameters.decreaseThreshold = Double.parseDouble(scn.next());
                    scn.next();
                    SessionParameters.increaseThreshold = Double.parseDouble(scn.next());
                    scn.next();
                    SessionParameters.duration = Integer.parseInt(scn.next());
                    scn.next();
                    SessionParameters.maxPresentations = Integer.parseInt(scn.next());
                    scn.next();
                    SessionParameters.nBackBegin = Integer.parseInt(scn.next());
                    scn.next();
                    SessionParameters.speedPercentage = Double.parseDouble(scn.next());
                    scn.next();
                    SessionParameters.countDownIntervalDefault = Integer.parseInt(scn.next());
                    scn.next();
                    SessionParameters.durationSessionTimer = Integer.parseInt(scn.next());
                    scn.next();
                    SessionParameters.randomIndexIncreaseFactor = Integer.parseInt(scn.next());
                    scn.next();
                    SessionParameters.includePosition = Boolean.parseBoolean(scn.next());
                    scn.next();
                    SessionParameters.includeAudio = Boolean.parseBoolean(scn.next());
                    scn.next();
                    SessionParameters.includeColor = Boolean.parseBoolean(scn.next());
                    scn.next();
                    SessionParameters.orientation = Integer.parseInt(scn.next());
                    scn.next();
                    SessionParameters.playButtonSoundDuringTraining = Boolean.parseBoolean(scn.next());
                    scn.next();
                    SessionParameters.dateOfLastUse = scn.next();
                    scn.next();
                    SessionParameters.squareDefaultColorIndex = Integer.parseInt(scn.next());
                    scn.next();
                    SessionParameters.darkModeTraining = Boolean.parseBoolean(scn.next());
                    scn.next();
                    SessionParameters.customSquareSize = Float.parseFloat(scn.next());
                    scn.next();
                    SessionParameters.showGrid = Boolean.parseBoolean(scn.next());
                    scn.next();
                    SessionParameters.zenMode = Boolean.parseBoolean(scn.next());
                    scn.next();
                    SessionParameters.showPercentageInResults = Boolean.parseBoolean(scn.next());
                    scn.next();
                    SessionParameters.showNBackInResults = Boolean.parseBoolean(scn.next());
                    scn.next();
                    SessionParameters.showDayInResults = Boolean.parseBoolean(scn.next());
                    scn.next();
                    SessionParameters.showSessionInResults = Boolean.parseBoolean(scn.next());
                    scn.next();
                    SessionParameters.showTrialInResults = Boolean.parseBoolean(scn.next());
                    scn.next();
                    SessionParameters.adMissedCounter = Integer.parseInt(scn.next());
                    scn.next();
                    SessionParameters.missedAdDialogHasBeenShown = Boolean.parseBoolean(scn.next());
                    scn.next();
                    SessionParameters.r= Integer.parseInt(scn.next());
                    scn.next();
                    SessionParameters.g= Integer.parseInt(scn.next());
                    scn.next();
                    SessionParameters.b= Integer.parseInt(scn.next());


                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
            Log.e("", "readSettings: " + SessionParameters.includePosition + SessionParameters.includeAudio + SessionParameters.includeColor);
            ips.close();
            scn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void saveSettings(Context c) {
        try {
            FileOutputStream out = c.getApplicationContext().openFileOutput(fileNameLessonSettings, Context.MODE_PRIVATE);
            String text = getFileSaveText();
            out.write(text.getBytes());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("", "saveSettings: " + SessionParameters.includePosition + SessionParameters.includeAudio + SessionParameters.includeColor);
    }

    private static String getFileSaveText() {
        String temp =
                "squareSize\t" + SessionParameters.squareSize + "\n" +
                        "decreaseThreshold\t" + SessionParameters.decreaseThreshold + "\n" +
                        "increaseThreshold\t" + SessionParameters.increaseThreshold + "\n" +
//                        "MAX_PRESENT_DEFAULT\t" + SessionParameters.MAX_PRESENT_DEFAULT + "\n" +
                        "durationSessionInTrials\t" + SessionParameters.duration + "\n" +
                        "maxPresentations\t" + SessionParameters.maxPresentations + "\n" +
                        "nBackBegin\t" + SessionParameters.nBackBegin + "\n" +
                        "speedPercentage\t" + SessionParameters.speedPercentage + "\n" +
                        "countDownIntervalDefault\t" + SessionParameters.countDownIntervalDefault + "\n" +
                        "durationSessionTimer\t" + SessionParameters.durationSessionTimer + "\n" +
                        "randomIndexIncreaseFactor\t" + SessionParameters.randomIndexIncreaseFactor + "\n" +
                        "includePosition\t" + SessionParameters.includePosition + "\n" +
                        "includeAudio\t" + SessionParameters.includeAudio + "\n" +
                        "includeColor\t" + SessionParameters.includeColor + "\n" +
                        "orientation\t" + SessionParameters.orientation + "\n" +
                        "playButtonSoundDuringTraining\t" + SessionParameters.playButtonSoundDuringTraining + "\n" +
                        "dateOfLastUse\t" + SessionParameters.dateOfCurrentUse + "\n" +
                        "squareDefaultColorIndex\t" + SessionParameters.squareDefaultColorIndex + "\n" +
                        "darkModeTraining\t" + SessionParameters.darkModeTraining + "\n" +
                        "customSquareSize\t" + SessionParameters.customSquareSize + "\n" +
                        "showDividers\t" + SessionParameters.showGrid + "\n" +
                        "zenMode\t" + SessionParameters.zenMode + "\n" +
                        "showPercentageInResults\t" + SessionParameters.showPercentageInResults + "\n" +
                        "showNBackInResults\t" + SessionParameters.showNBackInResults + "\n" +
                        "showDayInResults\t" + SessionParameters.showDayInResults + "\n" +
                        "showSessionInResults\t" + SessionParameters.showSessionInResults + "\n" +
                        "showTrialInResults\t" + SessionParameters.showTrialInResults + "\n" +
                        "adMissedCounter\t" + SessionParameters.adMissedCounter + "\n" +
                        "missedAdDialogHasBeenShown\t" + SessionParameters.missedAdDialogHasBeenShown + "\n" +
                        "r\t" + SessionParameters.r + "\n" +
                        "g\t" + SessionParameters.g + "\n" +
                        "b\t" + SessionParameters.b;
        return temp;
    }


}