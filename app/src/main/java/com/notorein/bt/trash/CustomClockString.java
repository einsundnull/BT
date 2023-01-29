package com.notorein.bt.trash;


/**
 *
 */
 class CustomClockString {


    public static int sceondCounterSecondDigit = 0;
    public static int secondCounterFirstDigit = 0;
    public static int minuteCounterFirstDigit = 0;
    public static int minuteCounterSecondDigit = 0;
    public static int hourCounterFirstDigit = 0;
    public static int hourCounterSecondDigit = 0;
    public static String completeTime = "";
    public static boolean paused = false;

    public CustomClockString() {
        
    }

    public static String getClockTime() {
        if (!paused) {
            sceondCounterSecondDigit++;
            if (sceondCounterSecondDigit > 9) {
                sceondCounterSecondDigit = 0;
                secondCounterFirstDigit++;
                if (secondCounterFirstDigit > 5) {
                    secondCounterFirstDigit = 0;
                    minuteCounterSecondDigit++;
                    if (minuteCounterSecondDigit > 9) {
                        minuteCounterSecondDigit = 0;
                        minuteCounterFirstDigit++;
                        if (minuteCounterFirstDigit > 5) {
                            minuteCounterFirstDigit = 0;
                            hourCounterSecondDigit++;
                            if (hourCounterSecondDigit > 9) {
                                hourCounterSecondDigit = 0;
                                hourCounterFirstDigit++;
                            }
                        }
                    }
                }
            }
            completeTime = hourCounterFirstDigit + "" + hourCounterSecondDigit + ":" + minuteCounterFirstDigit + "" + minuteCounterSecondDigit + ":" + secondCounterFirstDigit + ""
                    + sceondCounterSecondDigit;
        }
        return completeTime;
    }


    
    public static void pauseTimer(boolean paused) {
        CustomClockString.paused = paused;
    }





    public static void setTime(int time) {
        CustomClockString.sceondCounterSecondDigit = time;
    }
    
    public static String resetTime() {
        hourCounterFirstDigit = 0;
        hourCounterSecondDigit = 0;
        minuteCounterFirstDigit = 0;
        minuteCounterSecondDigit = 0;
        secondCounterFirstDigit = 0;
        sceondCounterSecondDigit = 0;
        completeTime = hourCounterFirstDigit + "" + hourCounterSecondDigit + ":" + minuteCounterFirstDigit + "" + minuteCounterSecondDigit + ":" + secondCounterFirstDigit + ""
                + sceondCounterSecondDigit;
        return completeTime;
    }





    public static int getSecondCounterFirstDigit() {
        return secondCounterFirstDigit;
    }

    public static void setSecondCounterFirstDigit(int secondCounterFirstDigit) {
        CustomClockString.secondCounterFirstDigit = secondCounterFirstDigit;
    }

    public static int getMinuteCounterFirstDigit() {
        return minuteCounterFirstDigit;
    }

    public static void setMinuteCounterFirstDigit(int minuteCounterFirstDigit) {
        CustomClockString.minuteCounterFirstDigit = minuteCounterFirstDigit;
    }

    public static int getMinuteCounterSecondDigit() {
        return minuteCounterSecondDigit;
    }

    public static void setMinuteCounterSecondDigit(int minuteCounterSecondDigit) {
        CustomClockString.minuteCounterSecondDigit = minuteCounterSecondDigit;
    }

    public static int getHourCounterFirstDigit() {
        return hourCounterFirstDigit;
    }

    public static void setHourCounterFirstDigit(int hourCounterFirstDigit) {
        CustomClockString.hourCounterFirstDigit = hourCounterFirstDigit;
    }

    public static int getHourCounterSecondDigit() {
        return hourCounterSecondDigit;
    }

    public static void setHourCounterSecondDigit(int hourCounterSecondDigit) {
        CustomClockString.hourCounterSecondDigit = hourCounterSecondDigit;
    }

    public static String getCompleteTime() {
        return completeTime;
    }

    public static void setCompleteTime(String completeTime) {
        CustomClockString.completeTime = completeTime;
    }

    public static boolean isPaused() {
        boolean temp = paused;
        return temp;
    }

    public static void setPaused(boolean paused) {
        CustomClockString.paused = paused;
    }



}
