package com.notorein.bt.trash;


public class FilesRead {

//    public static File pathRaw;
//    public static String fileNameStartUpSettings = "startUpSettings";
//    public static String fileNameLessonSettings = "settings";
//    private static String[] files;
//    private static int[] lessonIDs;
//    private static String[] lessonNames;
//    private static int lessonIndex;
//    private static int size;
//
//
//    public static void getAllFilesInRawDirectory(Context c) {
//
//                try {
//                    Field[] fields = R.raw.class.getFields();
//                    pathRaw = c.getApplicationContext().getFilesDir();
//                    files = new String[fields.length];
//                    lessonIDs = new int[fields.length];
//                    for (int i = 0; i < fields.length; i++) {
//                        Log.i("Raw Asset: ", fields[i].getName());
//                        lessonNames[i] = fields[i].getName();
//                        lessonIDs[i] = fields[i].getInt(fields[i]);
//                    }
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//    }
//
//    public static void readToArray() {
//        size = fromFile.size();
//        questioning = new String[size][numberOfElements];
//        for (int i = 0; i < size; i++) {
//            for (int n = 0; n < numberOfElements; n++) {
//                try {
//                    questioning[i][n] = fromFile.get((size - 1) - i).get(n);
//                } catch (Exception e) {
//                    break;
//                }
//            }
//        }
//    }
//
//    public static void loadLessonFile(Activity activity) {
//        if (new File(pathRaw, lessonNames[lessonIndex]).exists()) {
//            try {
//                readFromExistingWordFile(activity);
//                Arrays.readToArray();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            readFromStandardWordFile(activity.getApplicationContext());
//            Arrays.readToArray();
//            com.notorein.BasicApp.FilesWrite.writeFileWords(activity);
//        }
//    }
//
//    public static void readFromExistingWordFile(Activity activity) {
//        Scanner scn;
//        try {
//            FileInputStream in = activity.getApplicationContext().openFileInput(Lessons.lessonNames[Lessons.lessonIndex]);
//            scn = new Scanner(in, "UTF-8");
//            Arrays.fromFile.clear();
//            scanLessonFile(scn);
//            scn.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public static void readFromStandardWordFile(Context c) {
//        System.out.println("readFromStandardWordFile");
//        InputStream ips = c.getResources().openRawResource(Lessons.lessonIDs[Lessons.lessonIndex]);
//        Scanner scn;
//        scn = new Scanner(ips, "UTF-8");
//        Arrays.fromFile.clear();
//        scanLessonFile(scn);
//        scn.close();
//    }
//
//    public static void scanLessonFile(Scanner scn) {
//        int index = 0;
//        while (scn.hasNext()) {
//            try {
//                Arrays.fromFile.add(index, new ArrayList<>());
//                for (int n = 0; n < Arrays.numberOfElements; n++) {
//                    String temp = scn.next();
//                    if (!temp.isEmpty()) Arrays.fromFile.get(index).add(temp);
//                }
//            } catch (Exception e) {
//                break;
//            }
//        }
//    }
//
//    public static void scanStartUpSettingsFile(Activity activity) {
//        InputStream ips = activity.getApplicationContext().getResources().openRawResource(Lessons.lessonIDs[Lessons.lessonIndex]);
//        Scanner scn = new Scanner(ips, "UTF-8");
//        while (scn.hasNext()) {
//            try {
//
//                scn.next();
//                ActivityMain.manualWasShown = scn.nextBoolean();
//                scn.next();
//                ActivityMain.settingsWereUsed = scn.nextBoolean();
//                scn.next();
//                ActivityMain.settingsWereUsedII = scn.nextBoolean();
//                scn.next();
//                ActivityMain.showMenu = scn.nextBoolean();
//                scn.next();
//                ActivityMain.orientationI = scn.nextInt();
//                scn.next();
//                ActivityMain.orientationII = scn.nextInt();
//                scn.next();
//                ActivityMain.alwaysShowManualAtStart = scn.nextBoolean();
//                scn.next();
//                ActivityMain.nightMode = scn.nextBoolean();
//                scn.next();
//                ActivityMain.saveBatteryMode = scn.nextBoolean();
//                scn.next();
//                ActivityMain.playQuestionSound = scn.nextBoolean();
//                scn.next();
//                ActivityMain.playAnswerSound = scn.nextBoolean();
//                scn.next();
//                ActivityMain.isTrialVersion = scn.nextBoolean();
//                scn.next();
//                Trial.trialCount = scn.nextInt();
//                scn.next();
//                ActivityMain.buttonLayoutY = scn.nextFloat();
//                scn.next();
//                ActivityMain.buttonLayoutYCounter = scn.nextInt();
//                scn.next();
//            } catch (Exception e) {
//                break;
//            }
//            System.err.println("Lessons.lessonIndex " + Lessons.lessonIndex);
//        }
//        scn.close();
//    }
//
//    public static void scanLessonSettingsFile(Activity activity) {
//        try {
//            InputStream ips;
//            ips = activity.getApplicationContext().openFileInput(fileNameLessonSettings);
//
//            Scanner scn = new Scanner(ips, "UTF-8");
//            while (scn.hasNext()) {
//                try {
//                    scn.next();
//                    AnswerLogic.advancedRepeatLogicIsActive = scn.nextBoolean();
//                    scn.next();
//                    AnswerLogic.alwaysShowQuestionWithAnswer = scn.nextBoolean();
//                    scn.next();
//                    AnswerLogic.questionIsReversed = scn.nextBoolean();
//                    scn.next();
//                    AnswerLogic.reversAnswerAtEnd = scn.nextBoolean();
//                    scn.next();
//                    AnswerLogic.reversAnswerAtCertainPoint = scn.nextBoolean();
//                    scn.next();
//                    AnswerLogic.reversAnswerAtRandom = scn.nextBoolean();
//                    scn.next();
//                    AnswerLogic.introduceNewCards = scn.nextBoolean();
//                    scn.next();
//                    AnswerLogic.answerWasShownWhenDestroy = scn.nextBoolean();
//                    scn.next();
//                    AnswerLogic.switchBetweenLessons = scn.nextBoolean();
//                    scn.next();
//                    AnswerLogic.useTimeToRepeat = scn.nextBoolean();
//                    scn.next();
//                    Lessons.languageIndex = scn.nextInt();
//                    scn.next();
//                    Lessons.lessonIndex = scn.nextInt();
//                    scn.next();
//                    ActivityMain.isTrialVersion = scn.nextBoolean();
//                    scn.next();
//                    Trial.trialCount = scn.nextInt();
//                    scn.next();
//                    ActivityMain.playSoundWhenQuestionIsShown = scn.nextBoolean();
//                    scn.next();
//                    ActivityMain.playSoundWhenAnswerIsShown = scn.nextBoolean();
//                    scn.next();
//                    ActivityMain.buttonLayoutY = scn.nextFloat();
//                    scn.next();
//                    ActivityMain.buttonLayoutYCounter = scn.nextInt();
//                    scn.next();
//
//
//                } catch (Exception e) {
//                    break;
//                }
//            }
//            scn.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void switchBetweenLessons() {
//        AnswerLogic.switchLessonCounter++;
//        if (AnswerLogic.switchLessonCounter >= AnswerLogic.switchLessonCountLimit) {
//            if (AnswerLogic.switchBetweenLessons)
//                AnswerLogic.switchLessonCounter = 0;
//        }
//    }


}
