package com.notorein.bt;

public class Results {





















//        public static void executeTrialResults() {
//
//            double value = 0;
//            for (int n = 0; n < Results.includedModes; n++) {
//               Lists. percentageList[n] = calculateTrialResults(Lists.rightValueMode[n]);
//                value = value +Lists. percentageList[n];
//            }
//          SessionParameters.  overallPercentage = value / includedModes;
//            combinedFileWriteLogic();
//            inOrDecreaseNBackLevelByResultsDualTraining(Lists.percentageList);
//
////            Platform.runLater(() -> {
//            MainActivity.lbl_n_back_Info_Pause.setText(String.valueOf(SessionParameters.nBack) + LanguageClass.word(SessionParameters.indexLang, 25));
//            MainActivity.lbl_n_Back.setText(String.valueOf(SessionParameters.nBack) + " - back");
////            });
//            SessionParameters.indexCreateCounter = 0;
//        }

//        public static void countIncludedModes() {
//            includedModes = 0;
//            if (SessionParameters.includeAudio) {
//                includedModes++;
//            }
//
//            if (SessionParameters.includeColor) {
//                includedModes++;
//            }
//            if (SessionParameters.includePosition) {
//                includedModes++;
//            }
//          Lists.  progressIndexModes = new int[includedModes];
//            Lists.   matchesMode = new boolean[includedModes];
//            Lists.   randomIndex = new int[includedModes];
//            Lists.  clickedToConfirmMatch = new boolean[includedModes];
//            Lists.  rightValueMode = new double[includedModes];
//            Lists.  percentageList = new double[includedModes];
//            Lists. clickedRightMode = new boolean[includedModes];
//        }

//        public static void determineMode() {
//            // all
//            if (SessionParameters.includeAudio && SessionParameters.includeColor && SessionParameters.includePosition) {
//                mode = Modes.AUDIO_COLOR_POSITION;
//                ResultsFiles.storingPathDuration = ResultsFiles.tempCombinedAudioColorPositionDuration;
//                ResultsFiles.storingPathRegular = ResultsFiles.tempCombinedAudioColorPositionRegular;
//                SessionParameters.indexPlayAudio = 0;
//                SessionParameters.indexShowColor = 1;
//                SessionParameters.indexShowPosition = 2;
//               confirmMatchKeys = new String[] { SessionParameters.confirmMatchAudio, SessionParameters.confirmMatchColor, SessionParameters.confirmMatchPosition };
//                Lists.  confirmMatchMouesButtons = new Button[] { Button.SECONDARY, Button.PRIMARY,  Button.MIDDLE };
//
//                Strings.manualText = manualText + confirmMatchKeys[0] + "  For Audio Match\n";
//                Strings.manualText = Strings.manualText + manualText + confirmMatchKeys[1] + "  For Color Match\n";
//                Strings.manualText = Strings.manualText + manualText + confirmMatchKeys[2] + "  For Position Match";
//            }
//            // only audio
//            if (SessionParameters.includeAudio && !SessionParameters.includeColor && !SessionParameters.includePosition) {
//                mode = Modes.AUDIO;
//                ResultsFiles.storingPathDuration = ResultsFiles.tempCombinedAudioDuration;
//                ResultsFiles.storingPathRegular = ResultsFiles.tempCombinedAudioRegular;
//                SessionParameters.indexPlayAudio = 0;
//                confirmMatchKeys = new String[] { SessionParameters.confirmMatchAudio };
//               Lists. confirmMatchMouesButtons = new Button[] { Button.SECONDARY };
//                Strings.manualText = manualText + confirmMatchKeys[0] + "  For Audio Match";
//            }
//            // only color
//            if (!SessionParameters.includeAudio && SessionParameters.includeColor && !SessionParameters.includePosition) {
//                mode = Modes.COLOR;
//                ResultsFiles.storingPathDuration = ResultsFiles.tempCombinedColorDuration;
//                ResultsFiles.storingPathRegular = ResultsFiles.tempCombinedColorRegular;
//                SessionParameters.indexShowColor = 0;
//                confirmMatchKeys = new String[] { SessionParameters.confirmMatchColor };
//                Strings.manualText = manualText + confirmMatchKeys[0] + "  For Color Match";
//              Lists.  confirmMatchMouesButtons = new Button[] { Button.PRIMARY };
//            }
//
//            // only position
//            if (!SessionParameters.includeAudio && !SessionParameters.includeColor && SessionParameters.includePosition) {
//                mode = Modes.POSITION;
//                ResultsFiles.storingPathDuration = ResultsFiles.tempCombinedPositionDuration;
//                ResultsFiles.storingPathRegular = ResultsFiles.tempCombinedPositionRegular;
//                SessionParameters.indexShowPosition = 0;
//                confirmMatchKeys = new String[] { SessionParameters.confirmMatchPosition };
//                Lists.   confirmMatchMouesButtons = new Button[] {  Button.PRIMARY };
//                Strings.manualText = manualText + confirmMatchKeys[0] + "  For Position Match";
//            }
//
//            // audio and color
//            if (SessionParameters.includeAudio && SessionParameters.includeColor && !SessionParameters.includePosition) {
//                mode = Modes.AUDIO_COLOR;
//                ResultsFiles.storingPathDuration = ResultsFiles.tempCombinedAudioColorDuration;
//                ResultsFiles.storingPathRegular = ResultsFiles.tempCombinedAudioColorRegular;
//                SessionParameters.indexPlayAudio = 0;
//                SessionParameters.indexShowColor = 1;
//                confirmMatchKeys = new String[] {  SessionParameters.confirmMatchAudio, SessionParameters.confirmMatchColor };
//                Lists.   confirmMatchMouesButtons = new Button[] { Button.SECONDARY, Button.PRIMARY };
//                Strings.manualText = manualText + confirmMatchKeys[0] + "  For Audio Match\n";
//                Strings.manualText = Strings.manualText + manualText + confirmMatchKeys[1] + "  For Color Match";
//            }
//
//            // audio and position
//            if (SessionParameters.includeAudio && !SessionParameters.includeColor && SessionParameters.includePosition) {
//                mode = Modes.AUDIO_POSITION;
//                ResultsFiles.storingPathDuration = ResultsFiles.tempCombinedAudioPositionDuration;
//                ResultsFiles.storingPathRegular = ResultsFiles.tempCombinedAudioPositionRegular;
//                SessionParameters.indexPlayAudio = 0;
//                SessionParameters.indexShowPosition = 1;
//                confirmMatchKeys = new String[] {SessionParameters.confirmMatchAudio, SessionParameters.confirmMatchPosition };
//                Strings.manualText = manualText + confirmMatchKeys[0] + "  For Audio Match\n";
//                Strings.manualText = Strings.manualText + manualText + confirmMatchKeys[1] + "  For Position Match";
//                Lists.  confirmMatchMouesButtons = new Button[] { Button.SECONDARY, Button.PRIMARY };
//            }
//
//            // color and position
//            if (!SessionParameters.includeAudio && SessionParameters.includeColor && SessionParameters.includePosition) {
//                mode = Modes.COLOR_POSITION;
//                ResultsFiles.storingPathDuration = ResultsFiles.tempCombinedColorPositionDuration;
//                ResultsFiles.storingPathRegular = ResultsFiles.tempCombinedColorPositionRegular;
//                SessionParameters.indexShowPosition = 0;
//                confirmMatchKeys = new String[] { SessionParameters.confirmMatchColor, SessionParameters.confirmMatchPosition };
//                Strings.manualText = manualText + confirmMatchKeys[0] + "  For Color Match\n";
//                Strings.manualText = Strings.manualText + manualText + confirmMatchKeys[1] + "  For Position Match";
//                Lists.confirmMatchMouesButtons = new Button[] { Button.SECONDARY, Button.PRIMARY };
//            }
//        }
//
//        public static void setManualText() {
//            for (int i = 0; i < includedModes; i++) {
//
//            }
//
//        }
    public static void combinedFileWriteLogic() {
//        String day = ResultsFiles.getDay();
//        String sessionID = "" + new Random().nextInt(99999999);
//        ID_I = "date:\n" + day + "\n" + "n-back:\n" + SessionParameters.nBack + "\n";
//        ID_II = "\nsessionID:\n" + sessionID + "\n" + "day:\n" + day + "\n";
//        writeToProgressArray();
//        writeToOverallPercentage();
    }

    private static void writeToOverallPercentage() {
//        if (SessionParameters.durationTraining) {
//            writeToFile(ID_I + "percentage: " + Results.overallPercentage + ID_II, storingPathDuration);
//        } else {
//            writeToFile(ID_I + "percentage: " + Results.overallPercentage + ID_II, storingPathRegular);
//        }
    }

    private static void writeToProgressArray() {
//        if (Results.mode.equals(Modes.AUDIO_COLOR_POSITION)) {
//            if (SessionParameters.durationTraining) {
//                writeToFile(ID_I + clicksList.get(Rect.indexPlayAudio) + ID_II, tempAudioDuration);
//                writeToFile(ID_I + clicksList.get(Rect.indexShowColor) + ID_II, tempColorDuration);
//                writeToFile(ID_I + clicksList.get(Rect.indexShowPosition) + ID_II, tempPositionDuration);
//            }
//            writeToFile(ID_I + "percentage:\n" + percentageList[Rect.indexPlayAudio] + ID_II, tempAudioDurationPercentage);
//            writeToFile(ID_I + "percentage:\n" + percentageList[Rect.indexShowColor] + ID_II, tempColorDurationPercentage);
//            writeToFile(ID_I + "percentage:\n" + percentageList[Rect.indexShowPosition] + ID_II, tempPositionDurationPercentage);
//        }
//        if (Results.mode.equals(Modes.AUDIO_COLOR)) {
//            if (SessionParameters.durationTraining) {
//                writeToFile(ID_I + clicksList.get(Rect.indexPlayAudio) + ID_II, tempAudioDuration);
//                writeToFile(ID_I + clicksList.get(Rect.indexShowColor) + ID_II, tempColorDuration);
//            }
//            writeToFile(ID_I + "percentage:\n" + percentageList[Rect.indexPlayAudio] + ID_II, tempAudioDurationPercentage);
//            writeToFile(ID_I + "percentage:\n" + percentageList[Rect.indexShowColor] + ID_II, tempColorDurationPercentage);
//        }
//        if (Results.mode.equals(Modes.AUDIO_POSITION)) {
//            if (SessionParameters.durationTraining) {
//                writeToFile(ID_I + clicksList.get(Rect.indexPlayAudio) + ID_II, tempAudioDuration);
//                writeToFile(ID_I + clicksList.get(Rect.indexShowPosition) + ID_II, tempPositionDuration);
//            }
//            writeToFile(ID_I + "percentage:\n" + percentageList[Rect.indexPlayAudio] + ID_II, tempAudioDurationPercentage);
//            writeToFile(ID_I + "percentage:\n" + percentageList[Rect.indexShowColor] + ID_II, tempColorDurationPercentage);
//
//        }
//        if (Results.mode.equals(Modes.COLOR_POSITION)) {
//            if (SessionParameters.durationTraining) {
//                writeToFile(ID_I + clicksList.get(Rect.indexShowColor) + ID_II, tempColorDuration);
//                writeToFile(ID_I + clicksList.get(Rect.indexShowPosition) + ID_II, tempPositionDuration);
//            }
//            writeToFile(ID_I + "percentage:\n" + percentageList[Rect.indexShowColor] + ID_II, tempColorDurationPercentage);
//            writeToFile(ID_I + "percentage:\n" + percentageList[Rect.indexShowPosition] + ID_II, tempPositionDurationPercentage);
//        }
//        if (Results.mode.equals(Modes.AUDIO)) {
//            if (SessionParameters.durationTraining) {
//                writeToFile(ID_I + clicksList.get(Rect.indexPlayAudio) + ID_II, tempAudioDuration);
//            }
//            writeToFile(ID_I + "percentage:\n" + percentageList[Rect.indexPlayAudio] + ID_II, tempAudioDurationPercentage);
//        }
//        if (Results.mode.equals(Modes.COLOR)) {
//            if (SessionParameters.durationTraining) {
//                writeToFile(ID_I + clicksList.get(Rect.indexShowColor) + ID_II, tempColorDuration);
//            }
//            writeToFile(ID_I + "percentage:\n" + percentageList[Rect.indexShowColor] + ID_II, tempColorDurationPercentage);
//        }
//        if (Results.mode.equals(Modes.POSITION)) {
//            if (SessionParameters.durationTraining) {
//                writeToFile(ID_I + clicksList.get(Rect.indexShowPosition) + ID_II, tempPositionDuration);
//            }
//            writeToFile(ID_I + "percentage:\n" + percentageList[Rect.indexShowPosition] + ID_II, tempPositionDurationPercentage);
//        }

    }
        

    }

