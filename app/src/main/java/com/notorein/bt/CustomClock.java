package com.notorein.bt;


/**
 *
 */
public class CustomClock{


    public  int sceondCounterSecondDigit = 0;
    public  int secondCounterFirstDigit = 0;
    public  int minuteCounterFirstDigit = 0;
    public  int minuteCounterSecondDigit = 0;
    public  int hourCounterFirstDigit = 0;
    public  int hourCounterSecondDigit = 0;
    public  String completeTime = "";
    public  boolean paused = false;

    public CustomClock() {

    }

    public  String getClockTime(boolean countTime) {
        if (!paused) {
            if(countTime) {
                sceondCounterSecondDigit++;
            }
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



    public  void pauseTimer(boolean paused) {
        paused = paused;
    }





    public  void setTime(int time) {
        sceondCounterSecondDigit = time;
    }

    public  String resetTime() {
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





    public  int getSecondCounterFirstDigit() {
        return secondCounterFirstDigit;
    }

    public  void setSecondCounterFirstDigit(int secondCounterFirstDigit) {
        secondCounterFirstDigit = secondCounterFirstDigit;
    }

    public  int getMinuteCounterFirstDigit() {
        return minuteCounterFirstDigit;
    }

    public  void setMinuteCounterFirstDigit(int minuteCounterFirstDigit) {
        minuteCounterFirstDigit = minuteCounterFirstDigit;
    }

    public  int getMinuteCounterSecondDigit() {
        return minuteCounterSecondDigit;
    }

    public  void setMinuteCounterSecondDigit(int minuteCounterSecondDigit) {
        minuteCounterSecondDigit = minuteCounterSecondDigit;
    }

    public  int getHourCounterFirstDigit() {
        return hourCounterFirstDigit;
    }

    public  void setHourCounterFirstDigit(int hourCounterFirstDigit) {
        hourCounterFirstDigit = hourCounterFirstDigit;
    }

    public  int getHourCounterSecondDigit() {
        return hourCounterSecondDigit;
    }

    public  void setHourCounterSecondDigit(int hourCounterSecondDigit) {
        hourCounterSecondDigit = hourCounterSecondDigit;
    }

    public  String getCompleteTime() {
        return completeTime;
    }

    public  void setCompleteTime(String completeTime) {
        completeTime = completeTime;
    }

    public  boolean isPaused() {
        boolean temp = paused;
        return temp;
    }

    public  void setPaused(boolean paused) {
        paused = paused;
    }



}
