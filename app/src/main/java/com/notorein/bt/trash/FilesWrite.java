package com.notorein.bt.trash;


public class FilesWrite {


//    public static void writeFileWords(Activity activity) {
//        FileOutputStream out;
//        Context con = activity.getApplicationContext();
//        String file = Lessons.lessonNames[Lessons.lessonIndex];
//        try {
//            out = con.openFileOutput(file, Context.MODE_PRIVATE);
//            for (int i = 0; i < Arrays.questioning.length; i++) {
//                for (int n = 0; n < Arrays.questioning[0].length; n++) {
//                    String text = Arrays.questioning[i][n] + "\t";
//                    out.write(text.getBytes());
//                }
//                String text = "\n";
//                out.write(text.getBytes());
//            }
//            out.flush();
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    public static void writeStartupSettings(Activity activity) {
//
//        String file = FilesRead.fileNameStartUpSettings;
//        FileOutputStream out;
//        String text;
//        try {
//            out = activity.getApplicationContext().openFileOutput(file, Context.MODE_PRIVATE);
//            text = "manualWasShown" + "\t" + ActivityMain.manualWasShown + "\n";
//            out.write(text.getBytes());
//            text = "settingsWereUsed" + "\t" + ActivityMain.settingsWereUsed + "\n";
//            out.write(text.getBytes());
//            text = "settingsWereUsedII" + "\t" + ActivityMain.settingsWereUsedII + "\n";
//            out.write(text.getBytes());
//            text = "showMenu" + "\t" + ActivityMain.showMenu + "\n";
//            out.write(text.getBytes());
//            text = "orientationTemporaryI" + "\t" + ActivityMain.orientationI + "\n";
//            out.write(text.getBytes());
//            text = "orientationTemporaryII" + "\t" + ActivityMain.orientationII + "\n";
//            out.write(text.getBytes());
//            text = "alwaysShowManualAtStart" + "\t" + ActivityMain.alwaysShowManualAtStart + "\n";
//            out.write(text.getBytes());
//            text = "nightMode" + "\t" + ActivityMain.nightMode + "\n";
//            out.write(text.getBytes());
//            text = "saveBatteryMode" + "\t" + ActivityMain.saveBatteryMode + "\n";
//            out.write(text.getBytes());
//            text = "playQuestionSound" + "\t" + ActivityMain.playQuestionSound + "\n";
//            out.write(text.getBytes());
//            text = "playAnswerSound" + "\t" + ActivityMain.playAnswerSound + "\n";
//            out.write(text.getBytes());
//            text = "isTrialVersion" + "\t" + ActivityMain.isTrialVersion + "\n";
//            out.write(text.getBytes());
//            text = "trialCount" + "\t" + Trial.trialCount + "\n";
//            out.write(text.getBytes());
//            if (ActivityMain.moveButtons) {
//                text = "buttonLayoutY" + "\t" + ActivityMain.buttonLayoutY + "\n";
//                out.write(text.getBytes());
//                text = "buttonLayoutYCounter" + "\t" + ActivityMain.buttonLayoutYCounter + "\n";
//                out.write(text.getBytes());
//            }
//            out.flush();
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    public static void writeLessonSettings(Activity activity) {
//
//        String file = FilesRead.fileNameLessonSettings;
//        FileOutputStream out;
//        String text;
//        try {
//            out = activity.getApplicationContext().openFileOutput(file, Context.MODE_PRIVATE);
//            text = "advancedRepeatLogicIsActive" + "\t" + AnswerLogic.advancedRepeatLogicIsActive + "\n";
//            out.write(text.getBytes());
//            text = "alwaysShowQuestionWithAnswer" + "\t" + AnswerLogic.alwaysShowQuestionWithAnswer + "\n";
//            out.write(text.getBytes());
//            text = "questionIsReversed" + "\t" + AnswerLogic.questionIsReversed + "\n";
//            out.write(text.getBytes());
//            text = "reversAnswerAtEnd" + "\t" + AnswerLogic.reversAnswerAtEnd + "\n";
//            out.write(text.getBytes());
//            text = "reversAnswerAtCertainPoint" + "\t" + AnswerLogic.reversAnswerAtCertainPoint + "\n";
//            out.write(text.getBytes());
//            text = "reversAnswerAtRandom" + "\t" + AnswerLogic.reversAnswerAtRandom + "\n";
//            out.write(text.getBytes());
//            text = "introduceNewCards" + "\t" + AnswerLogic.introduceNewCards + "\n";
//            out.write(text.getBytes());
//            text = "answerWasShownWhenDestroy" + "\t" + AnswerLogic.answerWasShownWhenDestroy + "\n";
//            out.write(text.getBytes());
//            text = "switchBetweenLessons" + "\t" + AnswerLogic.switchBetweenLessons + "\n";
//            out.write(text.getBytes());
//            text = "useTimeToRepeat" + "\t" + AnswerLogic.useTimeToRepeat + "\n";
//            out.write(text.getBytes());
//            text = "languageIndex" + "\t" + Lessons.languageIndex + "\n";
//            out.write(text.getBytes());
//            text = "lessonIndex" + "\t" + Lessons.lessonIndex + "\n";
//            out.write(text.getBytes());
//            text = "isTrialVersion" + "\t" + ActivityMain.isTrialVersion + "\n";
//            out.write(text.getBytes());
//            text = "trialCount" + "\t" + Trial.trialCount + "\n";
//            out.write(text.getBytes());
//            text = "playQuestionSound" + "\t" + ActivityMain.playSoundWhenQuestionIsShown + "\n";
//            out.write(text.getBytes());
//            text = "playAnswerSound" + "\t" + ActivityMain.playSoundWhenAnswerIsShown + "\n";
//            out.write(text.getBytes());
//            if (ActivityMain.moveButtons) {
//                text = "buttonLayoutY" + "\t" + ActivityMain.buttonLayoutY + "\n";
//                out.write(text.getBytes());
//                text = "buttonLayoutYCounter" + "\t" + ActivityMain.buttonLayoutYCounter + "\n";
//                out.write(text.getBytes());
//            }
//            out.flush();
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}

