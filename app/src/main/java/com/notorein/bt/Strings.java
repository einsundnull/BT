package com.notorein.bt;


import static com.notorein.bt.RepeatStorage.shownIndexesAudio;
import static com.notorein.bt.RepeatStorage.shownIndexesColor;
import static com.notorein.bt.RepeatStorage.shownIndexesPosition;
import static com.notorein.bt.SessionParameters.aud;
import static com.notorein.bt.SessionParameters.col;
import static com.notorein.bt.SessionParameters.counterMatchesAud;
import static com.notorein.bt.SessionParameters.counterMatchesCol;
import static com.notorein.bt.SessionParameters.counterMatchesPos;
import static com.notorein.bt.SessionParameters.developerInfoAreVisible;
import static com.notorein.bt.SessionParameters.endOfSession;
import static com.notorein.bt.SessionParameters.endOfTrial;
import static com.notorein.bt.SessionParameters.includedModes;
import static com.notorein.bt.SessionParameters.maxPresentations;
import static com.notorein.bt.SessionParameters.nBack;
import static com.notorein.bt.SessionParameters.paused;
import static com.notorein.bt.SessionParameters.percentageRightTrialAudio;
import static com.notorein.bt.SessionParameters.percentageRightTrialColor;
import static com.notorein.bt.SessionParameters.percentageRightTrialPosition;
import static com.notorein.bt.SessionParameters.pos;
import static com.notorein.bt.SessionParameters.presentedScreens;
import static com.notorein.bt.SessionParameters.shownAndCounted;
import static com.notorein.bt.SessionParameters.trialCounter;
import static com.notorein.bt.SessionParameters.trialIsRunning;
import static com.notorein.bt.SessionParameters.trialsMax;

import android.annotation.SuppressLint;
import android.widget.TextView;

import java.text.BreakIterator;

public class Strings {

    public static String manualText;
    public static BreakIterator lbl_n_back_Info_Pause;
    public static String trialEndTextPercentage;
    public static String posText = "Your accuracy in the position task is: ";
    public static String audText = "Your accuracy in the audio task is:     ";
    public static String colText = "Your accuracy in the color task is:     ";

    public static String pause = "Pause";
    public static String finish = "End";
    public static String nBackLevel = "-back";
    public static String trialActualIndicator = " █ ";
    public static String trialDefaultIndicator = " ▄ ";
    public static String textViewTimeAdd_II = " min";
    public static String textViewTimeAdd_I = "~ ";
    public static String pleaseSaveResultsText = "Please save your results first!\nIf no the last results will be overwritten!";
    public static String dismissResults = "Dismiss Results";
    public static String goBackAndStore = "Go Back And Store";
    public static String sound_click_training_off= "Button Click Sound Is Off";
    public static String sound_click_training_on = "Button Click Sound Is On";
    public static String changeSquareSizeText = "Tap square to accept new size";
    public static String changeSquareColorText = "Tap square to accept new color";
    public static String showGridTextYes = "Show Grid";
    public static String showGridTextNo = "Hide Grid";
    public static String fadeTextYes = "Squares will be appear slowly";
    public static String fadeTextNo= "Squares will be appear immediately";
    public static String zemModeToastText = "Zen Mode Is Active: " ;
    public static String zenModeSlow = " and calm";


    public static String showPercentageInResults = "Show Percentage";
    public static String showNBackInResults= "Show n-Back Level";
    public static String showDayInResults= "Show Summary Day";
    public static String showSessionInResults= "Show Summary Session";
    public static String showTrialInResults= "Show Summary All Trials";
    public static String settingsText = "Settings";



    //    private static String nextLevel = "Next: ";
//    private static String endLevel = "Last: ";
    private static String nextLevel = nBackLevel;
    private static String endLevel = nBackLevel;
    public static String nBackMax = "Your Highest Level: ";
    public static String goodJob = "Good Job!";

    public static String btnPosText = "Position";
    public static String btnAudText = "Audio";
    public static String btnColText = "Color";

    public static String cross = "+";

    public static String time;
    public static String timeSession;
    public static String timeTrialDevOps;
    public static String timeSessionDevOps;
    public static String languageQualifier = "EN_";
    public static String exitText = "Stop Training?\nAll progress will be lost!";
    public static String pleasTurnYourDevice = "Please turn your device to the left!";
    public static String pressButtonToS = "Press button to start";
    public static String pressButtonToC = "Press button to continue";
    public static String firstStartTextOnlyPos = "Press button to start";
    public static String pressButtonToFinish = "Press button to finish";
    public static String firstStartTextPressButtonToS = "Press button to start\n\nTap the center to interrupt training";


    public static String firstStartTextOnlyAud;
    public static String firstStartTextOnlyCol;

    public static String firstStartTextPosAud;
    public static String firstStartTextPosCol;

    public static String firstStartTextAudCol;

    public static String firstStartTextPosAudCol;


    public static void createTrialEndTextPercentage() {
        trialEndTextPercentage = "";
        int posPerc = (int) (percentageRightTrialPosition * 100);
        int audPerc = (int) (percentageRightTrialAudio * 100);
        int colPerc = (int) (percentageRightTrialColor * 100);

        if (pos && aud && col) {
            trialEndTextPercentage = Strings.posText + posPerc + "%\n\n" +
                    Strings.audText + audPerc + "%\n\n" +
                    Strings.colText + colPerc + "%";
        }
        if (pos && aud && !col) {
            trialEndTextPercentage = Strings.posText + posPerc + "%\n\n" +
                    Strings.audText + audPerc + "%";
        }
        if (pos && !aud && col) {
            trialEndTextPercentage = Strings.posText + posPerc + "%\n\n" +
                    Strings.colText + colPerc + "%";
        }
        if (!pos && aud && col) {
            trialEndTextPercentage = Strings.audText + audPerc + "%\n\n" +
                    Strings.colText + colPerc + "%";
        }
        if (pos && !aud && !col) {
            trialEndTextPercentage = Strings.posText + posPerc + "%";
        }
        if (!pos && aud && !col) {
            trialEndTextPercentage = Strings.audText + audPerc + "%";
        }
        if (!pos && !aud && col) {
            trialEndTextPercentage = Strings.colText + colPerc + "%";
        }
//        trialEndTextPercentage = trialEndTextPercentage +"\n\n"+nextLevel+nBack+nBackLevel+"\n\n" + pauseTextPressButtonToC;
    }

    @SuppressLint("SetTextI18n")
    public static void setDeveloperInfoText(TextView v) {
        if (developerInfoAreVisible) {
            StringBuilder textPos = new StringBuilder();
            StringBuilder textAud = new StringBuilder();
            StringBuilder textCol = new StringBuilder();
            for (int i = presentedScreens; i <= presentedScreens + nBack; i++) {
                try {
                    textPos.append(shownIndexesPosition.get(i)).append(" ");
                } catch (Exception e) {
//                e.printStackTrace();
                    textPos.append("");
                }
                try {
                    textAud.append(shownIndexesAudio.get(i)).append(" ");
                } catch (Exception e) {
//                e.printStackTrace();
                    textAud.append("");

                }
                try {
                    textCol.append(shownIndexesColor.get(i)).append(" ");
                } catch (Exception e) {
//                e.printStackTrace();
                    textCol.append("");
                }
            }
            try {
                v.setText("NB: " + nBack
                        + "\nIncPos: " + SessionParameters.increasedCounterPosition
                        + "\nIncAud: " + SessionParameters.increasedCounterAudio
                        + "\nIncCol: " + SessionParameters.increasedCounterColor
                        + "\n\nDecPer: " + SessionParameters.decreaseThreshold
                        + "\nIncPer: " + SessionParameters.increaseThreshold

                        + "\n\nTimeDevOps: " + Strings.timeTrialDevOps
                        + "\nTime: " + time
                        + "\nTimeSession: " + timeSession
                        + "\n\nTrCou: " + trialCounter + "/" + trialsMax
                        + "\nPresScr: " + presentedScreens
                        + "\nMaxPres: "  + maxPresentations
                        + "\nShwCnt: " + shownAndCounted

                        + "\n\nMtchPos: " + counterMatchesPos
                        + "\nMtchAud: " + counterMatchesAud
                        + "\nMtchCol: " + counterMatchesCol

                        + "\n\nPresPos: " + textPos
                        + "\nPresAud: " + textAud
                        + "\nPresCol: " + textCol

                        + "\n\nIncPos: " + SessionParameters.includePosition
                        + "\nIncAud: " + SessionParameters.includeAudio
                        + "\nIncCol: " + SessionParameters.includeColor

                        + "\n\nRghtPos: " + SessionParameters.counterClickedRightPosition
                        + "\nRghtAud: " + SessionParameters.counterClickedRightAudio
                        + "\nRghtCol: " + SessionParameters.counterClickedRightColor

                        + "\n\nPctRPos: " + percentageRightTrialPosition
                        + "\nPctRAud: " + percentageRightTrialAudio
                        + "\nPctRCol: " + percentageRightTrialColor

                        + "\n\nPctAll: " + ((percentageRightTrialPosition + percentageRightTrialAudio + percentageRightTrialColor) / includedModes)

                        + "\n\nPause: " + paused
                        + "\nIsRun: " + trialIsRunning
                        + "\nEndTrial: " + endOfTrial
                        + "\nEndSess: " + endOfSession

                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
