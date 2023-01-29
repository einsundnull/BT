package com.notorein.bt.trash;

public class Clock {
    public static int secondCounterSecondDigit = 0;
    public static int secondCounterFirstDigit = 0;
    public static int minuteCounterFirstDigit = 0;
    public static int minuteCounterSecondDigit = 0;
    public static int hourCounterFirstDigit = 0;
    public static int hourCounterSecondDigit = 0;
    public static String completeTime = "";
    public static boolean paused = false;
    @SuppressWarnings("unused")
    public static String getClockTime() {
        if (!paused) {
            secondCounterSecondDigit++;
            if (secondCounterSecondDigit > 9) {
                secondCounterSecondDigit = 0;
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
                    + secondCounterSecondDigit;
        }
        return completeTime;
    }

    @SuppressWarnings("unused")
    public static String resetTime() {
        hourCounterFirstDigit = 0;
        hourCounterSecondDigit = 0;
        minuteCounterFirstDigit = 0;
        minuteCounterSecondDigit = 0;
        secondCounterFirstDigit = 0;
        secondCounterSecondDigit = 0;
        completeTime = hourCounterFirstDigit + "" + hourCounterSecondDigit + ":" + minuteCounterFirstDigit + "" + minuteCounterSecondDigit + ":" + secondCounterFirstDigit + ""
                + secondCounterSecondDigit;
        return completeTime;
    }
}
