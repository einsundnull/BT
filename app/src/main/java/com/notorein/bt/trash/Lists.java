package com.notorein.bt.trash;

import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Lists {

    public static ArrayList<ArrayList<Button>> progressBars = new ArrayList<ArrayList<Button>>();
    public static ArrayList<ArrayList<String>> clicksList = new ArrayList<ArrayList<String>>();
    public static ArrayList<TextView> btns = new ArrayList<TextView>();
//	public static ArrayList<ArrayList<ArrayList<FadeTransition>>> fadeTransitionList = new ArrayList<ArrayList<ArrayList<FadeTransition>>>();

    public static int[][] indexes;
    public static int[] progressIndexModes;
    public static int[] randomIndex;
    public static double[] percentageList;
    public static double[] rightValueMode;
    public static boolean[] clickedRightMode;
    public static boolean[] matchesMode;
    public static boolean[] clickedToConfirmMatch;
    public static String[] confirmMatchKeys;
    public static Button[] confirmMatchMouesButtons;
    public static String[] colors;
    private static double opacity = 0.3;
//    private static double durationHide = 6 * SessionParameters.repeatTime;
//    private static double durationShow = 3.1 * SessionParameters.repeatTime;
    public static Enum[] mouseButtons;

    public static void setColorList() {
        colors = new String[]{"white", "yellow", "chocolate", "green", "rgb(136,163,19)", "rgb(3,211,252)", "blue", "red", "purple", "grey", "darkgrey"};
    }

//	public static void createTransitionList() {
//		int size = (int) (SessionParameters.maxPresentations +SessionParameters.nBack -1 );
//
//		for (int n = 0; n < Results.includedModes; n++) {
//			fadeTransitionList.add(new ArrayList<ArrayList<FadeTransition>>());
//			for (int i = 0; i < 2; i++) {
//				fadeTransitionList.get(n).add(new ArrayList<FadeTransition>());
//				for (int p = 0; p < size; p++) {
//					FadeTransition tr = new FadeTransition();
//					if (i == 0) {
//						tr.setFromValue(opacity);
//						tr.setToValue(0);
//						tr.setDuration(Duration.millis(durationHide));
//
//					} else if (i == 1) {
//						tr.setFromValue(0);
//						tr.setToValue(opacity);
//						tr.setDuration(Duration.millis(durationShow));
//
//					}
//					fadeTransitionList.get(n).get(i).add(tr);
//				}
//			}
//		}
//	}

//	public static ArrayList<ArrayList<Button>> createProgressBar(double layoutY, ArrayList<ArrayList<Button>> list) {
//		list.clear();
//		int size = (int) (SessionParameters.maxPresentations +SessionParameters.nBack -1 );
//		for (int n = 0; n < Results.includedModes; n++) {
//			list.add(new ArrayList<Button>());
//			double y = layoutY - DurationTraining.ButtonHeight - (Results.includedModes * n);
//			double width = (((TrainingScreen.width - 2) / (SessionParameters.maxPresentations - SessionParameters.nBack)) - DurationTraining.distanceX);
//			for (int i = 0; i < size; i++) {
//				Button Button = new Button();
//				Button.setOpacity(DurationTraining.opacity);
//				Button.setId("0");
//				Button.setPrefWidth(width);
//				Button.setPrefHeight(DurationTraining.ButtonHeight);
//				Button.setLayoutX(2 + (width * i) + DurationTraining.distanceX * i);
//				Button.setLayoutY(y);
//				Button.setStyle("-fx-background-color:" + DurationTraining.ButtonColor);
//				progressBars.get(n).add(Button);
//			}
//			progressBars.get(n).get((int) (SessionParameters.maxPresentations + SessionParameters.nBack - 2)).setStyle("-fx-background-color: blue");
//		}
//		return list;
//	}

//    public static ArrayList<ArrayList<String>> createClickList(ArrayList<ArrayList<String>> clickList) {
//        clickList.clear();
//        for (int n = 0; n < Results.includedModes; n++) {
//            clickList.add(new ArrayList<String>());
//        }
//        return clickList;
//    }
//
//    public static int[][] createIndexes(int[][] indexes) {
//        int size = SessionParameters.nBack * 5;
//        indexes = new int[Results.includedModes][size];
//        for (int n = 0; n < Results.includedModes; n++) {
//            for (int i = 0; i < size; i++) {
//                indexes[n][i] = -1;
//            }
//        }
//        return indexes;
//    }
}
