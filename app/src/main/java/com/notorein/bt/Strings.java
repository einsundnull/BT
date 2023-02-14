package com.notorein.bt;


import static com.notorein.bt.RepeatStorage.shownIndexesAudio;
import static com.notorein.bt.RepeatStorage.shownIndexesColor;
import static com.notorein.bt.RepeatStorage.shownIndexesPosition;
import static com.notorein.bt.SessionParameters.MAX_PRESENT_DEFAULT;
import static com.notorein.bt.SessionParameters.aud;
import static com.notorein.bt.SessionParameters.col;
import static com.notorein.bt.SessionParameters.countDownInterval;
import static com.notorein.bt.SessionParameters.countDownIntervalDefault;
import static com.notorein.bt.SessionParameters.counterMatchesAud;
import static com.notorein.bt.SessionParameters.counterMatchesCol;
import static com.notorein.bt.SessionParameters.counterMatchesPos;
import static com.notorein.bt.SessionParameters.developerInfoAreVisible;
import static com.notorein.bt.SessionParameters.endOfSession;
import static com.notorein.bt.SessionParameters.endOfTrial;
import static com.notorein.bt.SessionParameters.estimatedLengthSession;
import static com.notorein.bt.SessionParameters.estimatedLengthSessionII;
import static com.notorein.bt.SessionParameters.estimatedTrialLength;
import static com.notorein.bt.SessionParameters.fadeInterval;
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
import static com.notorein.bt.SessionParameters.speedPercentage;
import static com.notorein.bt.SessionParameters.squareFadeDuration;
import static com.notorein.bt.SessionParameters.trialCounter;
import static com.notorein.bt.SessionParameters.trialIsRunning;
import static com.notorein.bt.SessionParameters.trialsMax;

import android.annotation.SuppressLint;
import android.widget.TextView;

import java.text.BreakIterator;

public class Strings {

    public static String manualText;
    public static BreakIterator lbl_n_back_Info_Pause;
    public static String trialEndTextPercentage= "";
    public static String posText = "Your accuracy in the position task is: ";
    public static String audText = "Your accuracy in the audio task is:     ";
    public static String colText = "Your accuracy in the color task is:     ";

    public static String pause = "Pause";
    public static String finish = "End";
    public static String nBackLevel = "-back";
    public static String trialActualIndicator = " █ ";
    public static String trialDefaultIndicator = " ▄ ";
    public static String textViewTimeAdd_II = "min";
    public static String textViewTimeAdd_I = "~ ";
    public static String pleaseSaveResultsText = "Please save your results first!\nOtherwise the last results will be lost!";
    public static String dismissResults = "Dismiss Results";
    public static String goBackAndStore = "Go Back And Store";
    public static String youForgotToStoreYourResultsText = "It seems like you forgot to save your results the last time you used the app! Do you want to keep them?";
    public static String dontForgetToSaveResults = "Please don't forget to store your results before you close the app!\nOtherwise the last results will be lost!";

    public static String storeResults = "Store";
    public static String sound_click_training_off = "Button Click Sound Is Off";
    public static String sound_click_training_on = "Button Click Sound Is On";
    public static String changeIntervalText = "Tap the + or - button to change interval";
    public static String changeIntervalInfoText = "Interval: ";
    public static String changeIntervalInfoTextII = "ms";
    public static String speedText = "  Speed: ";
    public static String changeFadeIntervalText = "Tap the + or - button to change fade interval";

    public static String changedFadeIntervalText = "Fade interval: ";
    public static String changeSquareSizeText = "Tap the + or - button to change size";
    public static String changeSquareColorText = "Tap a square to chose a new color";
    public static String showGridTextYes = "Show Grid";
    public static String showGridTextNo = "Hide Grid";
    public static String fadeTextYes = "Squares will be fade in";
    public static String fadeTextNo = "Squares will be appear immediately";
    public static String zemModeToastText = "Zen Mode Is Active: ";
    public static String zenModeSlow = " and calm";


    public static String showPercentageInResults = "Show Percentage";
    public static String showNBackInResults = "Show n-Back Level";
    public static String showDayInResults = "Show Summary Day";
    public static String showSessionInResults = "Show Summary Session";
    public static String showTrialInResults = "Show Summary All Trials";
    public static String settingsText = "Settings";
    public static String adReminderTextI = "Dear valued user, we hope you have enjoyed using our app on ";
    public static String adReminderTextII = " ad-free occasions. " +
            "You can continue to use this app for free. This is just a reminder." +
            " To continue supporting our development efforts, " +
            "we kindly request that you turn on your internet " +
            "connection when using the app. This is because advertisements " +
            "are a crucial source of income for us developers and enable us to keep " +
            "improving and providing the best possible experience for you. " +
            "Your support means a lot to us, and we thank you in advance.\n\nSincerely, The Developers.";
    public static String adReminderTextIII = "Okay! I got that!";
    public static String recommendationsTextHeader = "Training Recommendations";
    public static String recommendationsText = "\n" +
            "\n" +
            "For optimal results, we recommend the following settings:\n\n" +
            "-------------------------------------------------------------------------\n" +
            "1. N-Back Level: Adjust according to your progress. Begin at level 1. As you becomming increasingly better" +
            " start at level 2 and so on. Start at a level that feels convenient for you to start with.\n" +
            "\n" +
            "2. Duration: High efficiency has been observed with sessions lasting 15 to 20 minutes (7 " +
            "to 10 trials). If you have not developed the stamina to focus for longer split you training." +
            " One twenty minutes session can be split into two ten minute sessions." +
            "\n\n" +
            "3. Mode: The Position + Sound task without fading squares is highly recommended.\n" +
            "\n" +
            "4. Consistency: Once you decided for one training mode for your personal training, stick to the same settings.\n" +
            "\n" +
            "5. Frequency: Train for a minimum of 15 to 20 minutes each day, three to four times a week, for at least three months.\n" +
            "-------------------------------------------------------------------------" +
            "\n" +
            "\n" +
            "It is important to approach this training in a calm and relaxed environment with a serious mindset, rather than treating it as a game. " +
            "The training can be challenging and may require a lot of focus and patience, but the results will be worth it. " +
            "If you lose focus during a session, don't give up and hit the \"Exit\" button. " +
            "Instead, try to keep memorizing, even if you make mistakes and drop to a lower level. " +
            "This will help to train your stamina. Don't be discouraged if your performance decreases at first. " +
            "The goal is to do the training consistently, rather than focusing solely on the statistics. " +
            "Over time, you will see improvements in your everyday life as a result of your efforts.";
    public static String aboutTextHeader = "Revised Program Sequence";
    public static String aboutText = "\n" +
            "\n" +
            "The program sequence requires a minimum duration of 120 seconds. Every two seconds, a square appears and one of eight letters can be heard. A minimum of 33 squares and 33 letters will be presented during each run. The program contains a randomly set number of matches.\n" +
            "\n" +
            "To ensure accuracy, it's important to remember the following:\n" +
            "\n" +
            "If a match occurs and the corresponding key is not pressed, it will be considered incorrect.\n\n" +
            "If a key is pressed without a match, it will also be considered incorrect.\n\n" +
            "If a match occurs and the corresponding key is pressed, it will be considered correct.\n\n" +
            "If there is no match and no key is pressed, it will also be considered correct.\n\n" +
            "\n" +
            "The program will increase the level if at least 85% of the visual and auditory components are rated as correct. If less than 70% of either the auditory or visual components are rated as correct, the program will lower the level. If the correct answer rate falls between 70% and 85%, the level of difficulty will remain unchanged.\n" +
            "\n" +
            "To maintain a constant probability of possible matches, the training time will be adjusted by\n" +
            "3 * n-back seconds with each level increase.\n" +
            "\n" +
            "For example:\n" +
            "\n" +
            "When n = 3, the total training time will be 129 seconds (120 + 3*3 seconds).\n\n" +
            "When n = 4, the total training time will be 132 seconds (120 + 3*4 seconds).\n" +
            "\n" +
            "Training Recommendation:\n\n" +
            "For optimal results, it's recommended to create a calm and distraction-free environment during the task.";
    public static String btnManualText = "ABOUT";
    public static String btnRecommendationsText = "RECOMMENDATIONS";
    public static String labelEditTextCustomColorTextR = "RED";
    public static String labelEditTextCustomColorTextG= "GREEN";
    public static String labelEditTextCustomColorTextB= "BLUE";
    public static String estimatedSessionLengthText = " Session: ";
    public static String estimatedTrialLengthText = " Trial: ";
    public static String lengthSession = "";
    public static String lengthSessionII= "";

    public static String lengthTrial= "";


    //    private static String nextLevel = "Next: ";
//    private static String endLevel = "Last: ";
    private static String nextLevel = nBackLevel;
    private static String endLevel = nBackLevel;
    public static String nBackMax = "Your Highest Level: ";
    public static String goodJob = "Good Job!";

    public static String btnPosText = "Position";
    public static String btnAudText = "Audio";
    public static String btnPosIIText = "-";
    public static String btnAudIIText = "+";
    public static String btnColText = "Color";

    public static String cross = "+";

    public static String time= "";
    public static String timeSession= "";
    public static String timeTrialDevOps= "";
    public static String timeSessionDevOps;
    public static String languageQualifier = "EN_";
    public static String exitText = "Stop Training?\nAll progress will be lost!";
    public static String pleasTurnYourDevice = "Please turn your device to the left!";
    public static String pressButtonToS = "Press button to start";
    public static String pressButtonToC = "Press button to continue";
    public static String firstStartTextOnlyPos = "Press button to start";
    public static String pressButtonToFinish = "Press button to finish";
    public static String firstStartTextPressButtonToS = "Tap the center to pause training\n\nPress button to start";


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
                        + "\nMaxPres: " + maxPresentations
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
    public static void setIntervalText() {


        if (speedPercentage < 0.25) {
            speedPercentage = 0.25;
        }
        if (speedPercentage > 2.75) {
            speedPercentage = 2.75;
        }
        countDownInterval = countDownIntervalDefault * speedPercentage;
        double percentage = countDownIntervalDefault / countDownInterval * 100;
        int tempPercentage = (int) percentage;
        percentage = tempPercentage;
        if (percentage == 99) {
            percentage = 100;
        }
        SessionParameters.fadeInterval = (long) (countDownInterval / squareFadeDuration);
        double percentageCorrected = percentage - 100;
        String add = "±";
        if (percentageCorrected > 0) {
            add = "+";
        } else if (percentageCorrected == 0) {
            add = "±";
        } else if (percentageCorrected < 0) {
            add = "";
        }
        estimatedTrialLength = (long) (countDownInterval * MAX_PRESENT_DEFAULT) * 2;

        estimatedLengthSession = estimatedTrialLength * trialsMax;
        estimatedLengthSessionII = estimatedTrialLength * (trialsMax + 1);
        lengthTrial = CustomClock.convertMillisecondsToMinutesAndSeconds(estimatedTrialLength);
        lengthSession = CustomClock.convertToMinutes(estimatedLengthSession);
        lengthSessionII = CustomClock.convertToMinutes(estimatedLengthSessionII);
        if (lengthSessionII.equals(lengthSession)) {
            lengthSessionII = Strings.textViewTimeAdd_I + lengthSession + textViewTimeAdd_II;
        } else {
            lengthSessionII = Strings.textViewTimeAdd_I + lengthSession + " -" + lengthSessionII + textViewTimeAdd_II;
        }


//        add = "    " + Strings.speedText + add;
//        add = Strings.speedText + Strings.changeIntervalInfoText + fadeInterval + Strings.changeIntervalInfoTextII;
        add = Strings.changeIntervalInfoText + fadeInterval
                + Strings.changeIntervalInfoTextII + Strings.speedText + add
                + +(percentageCorrected) + "%  "
                + Strings.estimatedTrialLengthText
                + lengthTrial
                + textViewTimeAdd_II
                + Strings.estimatedSessionLengthText
                + lengthSessionII;
        lengthTrial = add;
    }

}
