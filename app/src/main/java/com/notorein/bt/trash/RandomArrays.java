package com.notorein.bt.trash;

import static com.notorein.bt.RepeatStorage.matchesAudio;
import static com.notorein.bt.RepeatStorage.matchesColor;
import static com.notorein.bt.RepeatStorage.matchesPosition;
import static com.notorein.bt.RepeatStorage.shownIndexesAudio;
import static com.notorein.bt.RepeatStorage.shownIndexesColor;
import static com.notorein.bt.RepeatStorage.shownIndexesPosition;
import static com.notorein.bt.SessionParameters.counterMatchTesholdMax;
import static com.notorein.bt.SessionParameters.counterMatchTesholdMin;
import static com.notorein.bt.SessionParameters.counterMatchesAud;
import static com.notorein.bt.SessionParameters.counterMatchesCol;
import static com.notorein.bt.SessionParameters.counterMatchesPos;
import static com.notorein.bt.SessionParameters.excludeFourFromRandomIndex;
import static com.notorein.bt.SessionParameters.increasedCounterAudio;
import static com.notorein.bt.SessionParameters.increasedCounterPosition;
import static com.notorein.bt.SessionParameters.maxPresentations;
import static com.notorein.bt.SessionParameters.nBack;
import static com.notorein.bt.SessionParameters.randomIndexIncreaseFactor;

import com.notorein.bt.SessionParameters;

import java.util.ArrayList;
import java.util.Random;

public class RandomArrays {

    private static int rnd;

    public static ArrayList<Integer> precalculateRandom() {
        ArrayList<Integer> precalculatedIndexes = new ArrayList<>();
        for (int i = 0; i <= maxPresentations; i++) {
            int rnd = new Random().nextInt(9);
            if (excludeFourFromRandomIndex) {
                while (rnd == 4) {
                    rnd = new Random().nextInt(9);
                }
            }
            precalculatedIndexes.add(rnd);
        }
        return precalculatedIndexes;
    }

    public static ArrayList<Integer> increaseNumberOfMatches(ArrayList<Integer> precalculatedIndexes, int mode) {
        ArrayList<Integer> temp = new ArrayList<>();
        int size = precalculatedIndexes.size();
        int last;

        for (int i = 0; i < size; i++) {
            rnd = new Random().nextInt(randomIndexIncreaseFactor);
//            Log.e(TAG, "#####################increaseNumberOfMatches() returned: " + rnd + "\n" + randomIndexIncreaseFactor);
            if (rnd == 0) {
                try {
                    last = precalculatedIndexes.get(i - nBack);
                    if (mode == 0) {
                        increasedCounterPosition++;
                    }
                    if (mode == 1) {
                        increasedCounterAudio++;
                    }
                    if (mode == 2) {
                        increasedCounterPosition++;
                    }

                } catch (Exception e) {
                    last = precalculatedIndexes.get(i);
                }
            } else {
                last = precalculatedIndexes.get(i);
            }
            temp.add(last);
        }
        return temp;
    }


    public static ArrayList<Boolean> precalculateMatches(ArrayList<Integer> precalculatedIndexes, int nBack, int mode) {
        ArrayList<Boolean> matches = new ArrayList<>();
        int size = precalculatedIndexes.size();
        int shownCounter = 0;
        boolean match;
        int previous;
        int last;

        if (mode == 0) {
            counterMatchesPos = 0;
        }
        if (mode == 1) {
            counterMatchesAud = 0;
        }
        if (mode == 2) {
            counterMatchesCol = 0;
        }


        for (int i = 0; i < size; i++) {
            match = false;
            last = precalculatedIndexes.get(i);
            if (shownCounter >= nBack) {
                previous = precalculatedIndexes.get(i - nBack);
                if (last == previous) {
                    match = true;
                    if (mode == 0) {
                        counterMatchesPos++;
//                        if (match) {
//                            Log.i(TAG, "\nprecalculateMatches:\nmatch at position index: " + i);
//                        }
                    }
                    if (mode == 1) {
                        counterMatchesAud++;
//                        if (match) {
//                            Log.i(TAG, "\nprecalculateMatches:\nmatch at audio index: " + i);
//                        }
                    }
                    if (mode == 2) {
                        counterMatchesCol++;
//                        if (match) {
//                            Log.i(TAG, "\nprecalculateMatches:\nmatch at color index: " + i);
//                        }
                    }


                }
            }
            matches.add(match);
            shownCounter++;
        }
        return matches;
    }

    public static void precalculateRandomPosition() {
        SessionParameters.increasedCounterPosition = 0;
        counterMatchesPos = 0;
        shownIndexesPosition = precalculateRandom();
        matchesPosition = precalculateMatches(shownIndexesPosition, nBack, 0);
        if (SessionParameters.increaseNumberOfMatches) {
            while (counterMatchesPos < (maxPresentations * counterMatchTesholdMin)) {
                shownIndexesPosition = increaseNumberOfMatches(shownIndexesPosition, 0);
                matchesPosition = precalculateMatches(shownIndexesPosition, nBack, 0);
            }
            while (counterMatchesPos < (maxPresentations * counterMatchTesholdMin) || counterMatchesPos > (maxPresentations * counterMatchTesholdMax)) {
                precalculateRandomPosition();
            }
        }
    }

    public static void precalculateRandomAudio() {
        counterMatchesAud = 0;
        SessionParameters.increasedCounterAudio = 0;
        shownIndexesAudio = precalculateRandom();
        matchesAudio = precalculateMatches(shownIndexesAudio, nBack, 1);
        if (SessionParameters.increaseNumberOfMatches) {
            while (counterMatchesAud < (maxPresentations * counterMatchTesholdMin)) {
                shownIndexesAudio = increaseNumberOfMatches(shownIndexesAudio, 1);
                matchesAudio = precalculateMatches(shownIndexesAudio, nBack, 1);
            }
            while (counterMatchesAud < (maxPresentations * counterMatchTesholdMin) || counterMatchesAud > (maxPresentations * counterMatchTesholdMax)) {
                precalculateRandomAudio();
            }
        }
    }

    public static void precalculateRandomColor() {
        counterMatchesCol = 0;
        SessionParameters.increasedCounterColor = 0;
        shownIndexesColor = precalculateRandom();
        matchesColor = precalculateMatches(shownIndexesColor, nBack, 2);
        if (SessionParameters.increaseNumberOfMatches) {
            while (counterMatchesCol < (maxPresentations * counterMatchTesholdMin)) {
                shownIndexesColor = increaseNumberOfMatches(shownIndexesColor, 2);
                matchesColor = precalculateMatches(shownIndexesColor, nBack, 2);
            }
            while (counterMatchesCol < (maxPresentations * counterMatchTesholdMin) || counterMatchesCol > (maxPresentations * counterMatchTesholdMax)) {
                precalculateRandomColor();
            }
        }
    }


}
