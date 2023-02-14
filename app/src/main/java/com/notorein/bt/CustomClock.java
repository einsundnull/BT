package com.notorein.bt;


/**
 *
 */
public class CustomClock {


    public int secondCounterSecondDigit = 0;
    public int secondCounterFirstDigit = 0;
    public int minuteCounterFirstDigit = 0;
    public int minuteCounterSecondDigit = 0;
    public int hourCounterFirstDigit = 0;
    public int hourCounterSecondDigit = 0;
    public String completeTime = "";
    public boolean paused = false;

    public CustomClock() {

    }

    public String getDuration(boolean countTime) {
        if (!paused) {
            if (countTime) {
                secondCounterSecondDigit++;
            }
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

    public String getDuration(double givenTime) {
        for (int i = 0; i < givenTime; i++) {
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
        }
        completeTime = hourCounterFirstDigit + "" + hourCounterSecondDigit + ":" + minuteCounterFirstDigit + "" + minuteCounterSecondDigit + ":" + secondCounterFirstDigit + ""
                + secondCounterSecondDigit;

        return completeTime;
    }

    public void pauseTimer(boolean paused) {
        this.paused = paused;
    }


    public void setTime(int time) {
        secondCounterSecondDigit = time;
    }

    public String resetTime() {
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


    public int getSecondCounterFirstDigit() {
        return secondCounterFirstDigit;
    }

    public void setSecondCounterFirstDigit(int secondCounterFirstDigit) {
        this.secondCounterFirstDigit = secondCounterFirstDigit;
    }

    public int getMinuteCounterFirstDigit() {
        return minuteCounterFirstDigit;
    }

    public void setMinuteCounterFirstDigit(int minuteCounterFirstDigit) {
        this.minuteCounterFirstDigit = minuteCounterFirstDigit;
    }

    public int getMinuteCounterSecondDigit() {
        return minuteCounterSecondDigit;
    }

    public void setMinuteCounterSecondDigit(int minuteCounterSecondDigit) {
        this.minuteCounterSecondDigit = minuteCounterSecondDigit;
    }

    public int getHourCounterFirstDigit() {
        return hourCounterFirstDigit;
    }

    public void setHourCounterFirstDigit(int hourCounterFirstDigit) {
        this.hourCounterFirstDigit = hourCounterFirstDigit;
    }

    public int getHourCounterSecondDigit() {
        return hourCounterSecondDigit;
    }

    public void setHourCounterSecondDigit(int hourCounterSecondDigit) {
        this.hourCounterSecondDigit = hourCounterSecondDigit;
    }

    public String getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(String completeTime) {
        completeTime = completeTime;
    }

    public boolean isPaused() {
        boolean temp = paused;
        return temp;
    }

    public void setPaused(boolean paused) {
        paused = paused;
    }

    public static String convertMillisecondsToMinutesAndSeconds(long milliseconds) {
        int minutes = (int) (milliseconds / 1000) / 60;
        int seconds = (int) (milliseconds / 1000) % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public static String convertToMinutes(long milliseconds) {
        int minutes = (int) (milliseconds / 1000) / 60;

        return String.format("%02d", minutes);
    }


}
