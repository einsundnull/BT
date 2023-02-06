package com.notorein.bt;

public class Test {

//    public static String test = "TimeSession\n" +
//            "08:50:28\n" +
//            "DaySession\n" +
//            "2022_12_05\n" +
//            "TrialsMax\n" +
//            "10\n" +
//            "NBack\n" +
//            "3\n" +
//            "PercentageTrial\n" +
//            "0.75\n" +
//            "NBack\n" +
//            "3\n" +
//            "PercentageTrial\n" +
//            "0.65\n" +
//            "NBack\n" +
//            "2\n" +
//            "PercentageTrial\n" +
//            "0.55\n" +
//            "PercentageSession\n" +
//            "0.55\n" +
//            "IncreaseSession\n" +
//            "0.2\n" +
//            "NBackSession\n" +
//            "4.9\n" +
//            "MaxNBack\n" +
//            "6\n" +
//            "DayEndSession\n" +
//            "2022_12_05\n" +
//            "TimeSession\n" +
//            "09:50:28\n" +
//            "DaySession\n" +
//            "2022_12_05\n" +
//            "TrialsMax\n" +
//            "10\n" +
//            "NBack\n" +
//            "3\n" +
//            "PercentageTrial\n" +
//            "0.75\n" +
//            "NBack\n" +
//            "1\n" +
//            "PercentageTrial\n" +
//            "0.65\n" +
//            "NBack\n" +
//            "2\n" +
//            "PercentageTrial\n" +
//            "0.55\n" +
//            "PercentageSession\n" +
//            "0.65\n" +
//            "IncreaseSession\n" +
//            "0.2\n" +
//            "NBackSession\n" +
//            "4.9\n" +
//            "MaxNBack\n" +
//            "6\n" +
//            "DayEndSession\n" +
//            "2022_12_05\n" +
//            "TimeSession\n" +
//            "10:50:28\n" +
//            "DaySession\n" +
//            "2022_12_06\n" +
//            "TrialsMax\n" +
//            "10\n" +
//            "NBack\n" +
//            "3\n" +
//            "PercentageTrial\n" +
//            "0.55\n" +
//            "NBack\n" +
//            "3\n" +
//            "PercentageTrial\n" +
//            "0.65\n" +
//            "NBack\n" +
//            "2\n" +
//            "PercentageTrial\n" +
//            "0.75\n" +
//            "PercentageSession\n" +
//            "0.75\n" +
//            "IncreaseSession\n" +
//            "0.2\n" +
//            "NBackSession\n" +
//            "4.9\n" +
//            "MaxNBack\n" +
//            "6\n" +
//            "DayEndSession\n" +
//            "2022_12_06\n" +
//            "TimeSession\n" +
//            "11:50:28\n" +
//            "DaySession\n" +
//            "2022_12_06\n" +
//            "TrialsMax\n" +
//            "10\n" +
//            "NBack\n" +
//            "3\n" +
//            "PercentageTrial\n" +
//            "0.55\n" +
//            "NBack\n" +
//            "3\n" +
//            "PercentageTrial\n" +
//            "0.65\n" +
//            "PercentageSession\n" +
//            "0.0.75\n" +
//            "IncreaseSession\n" +
//            "0.2\n" +
//            "NBackSession\n" +
//            "4.9\n" +
//            "MaxNBack\n" +
//            "6\n" +
//            "DayEndSession\n" +
//            "2022_12_06\n" +
//            "TimeSession\n" +
//            "12:50:28\n" +
//            "DaySession\n" +
//            "2022_12_07\n" +
//            "TrialsMax\n" +
//            "10\n" +
//            "NBack\n" +
//            "3\n" +
//            "PercentageTrial\n" +
//            "0.55\n" +
//            "NBack\n" +
//            "1\n" +
//            "PercentageTrial\n" +
//            "0.65\n" +
//            "PercentageSession\n" +
//            "0.8407348791559318\n" +
//            "IncreaseSession\n" +
//            "0.2\n" +
//            "NBackSession\n" +
//            "4.9\n" +
//            "MaxNBack\n" +
//            "6\n" +
//            "DayEndSession\n" +
//            "2022_12_07\n" +
//            "TimeSession\n" +
//            "08:50:28\n" +
//            "DaySession\n" +
//            "2022_12_05\n" +
//            "TrialsMax\n" +
//            "10\n" +
//            "NBack\n" +
//            "3\n" +
//            "PercentageTrial\n" +
//            "0.75\n" +
//            "NBack\n" +
//            "3\n" +
//            "PercentageTrial\n" +
//            "0.65\n" +
//            "NBack\n" +
//            "2\n" +
//            "PercentageTrial\n" +
//            "0.55\n" +
//            "PercentageSession\n" +
//            "0.55\n" +
//            "IncreaseSession\n" +
//            "0.2\n" +
//            "NBackSession\n" +
//            "4.9\n" +
//            "MaxNBack\n" +
//            "6\n" +
//            "DayEndSession\n" +
//            "2022_12_05\n" +
//            "TimeSession\n" +
//            "09:50:28\n" +
//            "DaySession\n" +
//            "2022_12_05\n" +
//            "TrialsMax\n" +
//            "10\n" +
//            "NBack\n" +
//            "3\n" +
//            "PercentageTrial\n" +
//            "0.75\n" +
//            "NBack\n" +
//            "1\n" +
//            "PercentageTrial\n" +
//            "0.65\n" +
//            "NBack\n" +
//            "2\n" +
//            "PercentageTrial\n" +
//            "0.55\n" +
//            "PercentageSession\n" +
//            "0.65\n" +
//            "IncreaseSession\n" +
//            "0.2\n" +
//            "NBackSession\n" +
//            "4.9\n" +
//            "MaxNBack\n" +
//            "6\n" +
//            "DayEndSession\n" +
//            "2022_12_05\n" +
//            "TimeSession\n" +
//            "10:50:28\n" +
//            "DaySession\n" +
//            "2022_12_06\n" +
//            "TrialsMax\n" +
//            "10\n" +
//            "NBack\n" +
//            "3\n" +
//            "PercentageTrial\n" +
//            "0.55\n" +
//            "NBack\n" +
//            "3\n" +
//            "PercentageTrial\n" +
//            "0.65\n" +
//            "NBack\n" +
//            "2\n" +
//            "PercentageTrial\n" +
//            "0.75\n" +
//            "PercentageSession\n" +
//            "0.75\n" +
//            "IncreaseSession\n" +
//            "0.2\n" +
//            "NBackSession\n" +
//            "4.9\n" +
//            "MaxNBack\n" +
//            "6\n" +
//            "DayEndSession\n" +
//            "2022_12_06\n" +
//            "TimeSession\n" +
//            "11:50:28\n" +
//            "DaySession\n" +
//            "2022_12_06\n" +
//            "TrialsMax\n" +
//            "10\n" +
//            "NBack\n" +
//            "3\n" +
//            "PercentageTrial\n" +
//            "0.55\n" +
//            "NBack\n" +
//            "3\n" +
//            "PercentageTrial\n" +
//            "0.65\n" +
//            "PercentageSession\n" +
//            "0.0.75\n" +
//            "IncreaseSession\n" +
//            "0.2\n" +
//            "NBackSession\n" +
//            "4.9\n" +
//            "MaxNBack\n" +
//            "6\n" +
//            "DayEndSession\n" +
//            "2022_12_06\n" +
//            "TimeSession\n" +
//            "12:50:28\n" +
//            "DaySession\n" +
//            "2022_12_07\n" +
//            "TrialsMax\n" +
//            "10\n" +
//            "NBack\n" +
//            "3\n" +
//            "PercentageTrial\n" +
//            "0.55\n" +
//            "NBack\n" +
//            "1\n" +
//            "PercentageTrial\n" +
//            "0.65\n" +
//            "PercentageSession\n" +
//            "0.8407348791559318\n" +
//            "IncreaseSession\n" +
//            "0.2\n" +
//            "NBackSession\n" +
//            "4.9\n" +
//            "MaxNBack\n" +
//            "6\n" +
//            "DayEndSession\n" +
//            "2022_12_07\n";

    //    public static String test =
//            "TimeSession\n" +
//                    "08:50:28\n" +
//                    "DaySession\n" +
//                    "2022_12_05\n" +
//                    "TrialsMax\n" +
//                    "10\n" +
//                    "NBack\n" +
//                    "3\n" +
//                    "PercentageTrial\n" +
//                    "0.75\n" +
//                    "NBack\n" +
//                    "3\n" +
//                    "PercentageTrial\n" +
//                    "0.65\n" +
//                    "NBack\n" +
//                    "2\n" +
//                    "PercentageTrial\n" +
//                    "0.55\n" +
//                    "PercentageSession\n" +
//                    "0.55\n" +
//                    "IncreaseSession\n" +
//                    "0.2\n" +
//                    "NBackSession\n" +
//                    "4.9\n" +
//                    "MaxNBack\n" +
//                    "6\n" +
//                    "DayEndSession\n" +
//                    "2022_12_05\n" +
//                    "TimeSession\n" +
//                    "09:50:28\n" +
//                    "DaySession\n" +
//                    "2022_12_05\n" +
//                    "TrialsMax\n" +
//                    "10\n" +
//                    "NBack\n" +
//                    "3\n" +
//                    "PercentageTrial\n" +
//                    "0.75\n" +
//                    "NBack\n" +
//                    "1\n" +
//                    "PercentageTrial\n" +
//                    "0.65\n" +
//                    "NBack\n" +
//                    "2\n" +
//                    "PercentageTrial\n" +
//                    "0.55\n" +
//                    "PercentageSession\n" +
//                    "0.65\n" +
//                    "IncreaseSession\n" +
//                    "0.2\n" +
//                    "NBackSession\n" +
//                    "4.9\n" +
//                    "MaxNBack\n" +
//                    "6\n" +
//                    "DayEndSession\n" +
//                    "2022_12_05\n" +
//                    "TimeSession\n" +
//                    "10:50:28\n" +
//                    "DaySession\n" +
//                    "2022_12_06\n" +
//                    "TrialsMax\n" +
//                    "10\n" +
//                    "NBack\n" +
//                    "3\n" +
//                    "PercentageTrial\n" +
//                    "0.55\n" +
//                    "NBack\n" +
//                    "3\n" +
//                    "PercentageTrial\n" +
//                    "0.65\n" +
//                    "NBack\n" +
//                    "2\n" +
//                    "PercentageTrial\n" +
//                    "0.75\n" +
//                    "PercentageSession\n" +
//                    "0.75\n" +
//                    "IncreaseSession\n" +
//                    "0.2\n" +
//                    "NBackSession\n" +
//                    "4.9\n" +
//                    "MaxNBack\n" +
//                    "6\n" +
//                    "DayEndSession\n" +
//                    "2022_12_06\n" +
//                    "TimeSession\n" +
//                    "11:50:28\n" +
//                    "DaySession\n" +
//                    "2022_12_06\n" +
//                    "TrialsMax\n" +
//                    "10\n" +
//                    "NBack\n" +
//                    "3\n" +
//                    "PercentageTrial\n" +
//                    "0.55\n" +
//                    "NBack\n" +
//                    "3\n" +
//                    "PercentageTrial\n" +
//                    "0.65\n" +
//                    "PercentageSession\n" +
//                    "0.0.75\n" +
//                    "IncreaseSession\n" +
//                    "0.2\n" +
//                    "NBackSession\n" +
//                    "4.9\n" +
//                    "MaxNBack\n" +
//                    "6\n" +
//                    "DayEndSession\n" +
//                    "2022_12_06\n" +
//                    "TimeSession\n" +
//                    "12:50:28\n" +
//                    "DaySession\n" +
//                    "2022_12_07\n" +
//                    "TrialsMax\n" +
//                    "10\n" +
//                    "NBack\n" +
//                    "3\n" +
//                    "PercentageTrial\n" +
//                    "0.55\n" +
//                    "NBack\n" +
//                    "1\n" +
//                    "PercentageTrial\n" +
//                    "0.65\n" +
//                    "PercentageSession\n" +
//                    "0.8407348791559318\n" +
//                    "IncreaseSession\n" +
//                    "0.2\n" +
//                    "NBackSession\n" +
//                    "4.9\n" +
//                    "MaxNBack\n" +
//                    "6\n" +
//                    "DayEndSession\n" +
//                    "2022_12_07\n+";
    public static String test =
            "TimeSession\n" +
                    "08:50:28\n" +
                    "DaySession\n" +
                    "2022_12_05\n" +
                    "TrialsMax\n" +
                    "10\n" +
                    "NBack\n" +
                    "3\n" +
                    "PercentageTrial\n" +
                    "0.75\n" +
                    "NBack\n" +
                    "3\n" +
                    "PercentageTrial\n" +
                    "0.65\n" +
                    "NBack\n" +
                    "2\n" +
                    "PercentageTrial\n" +
                    "0.55\n" +
                    "PercentageSession\n" +
                    "0.55\n" +
                    "IncreaseSession\n" +
                    "0.2\n" +
                    "NBackSession\n" +
                    "4.9\n" +
                    "MaxNBack\n" +
                    "6\n" +
                    "DayEndSession\n" +
                    "2022_12_05\n" +
                    "TimeSession\n" +
                    "09:50:28\n" +
                    "DaySession\n" +
                    "2022_12_05\n" +
                    "TrialsMax\n" +
                    "10\n" +
                    "NBack\n" +
                    "3\n" +
                    "PercentageTrial\n" +
                    "0.75\n" +
                    "NBack\n" +
                    "1\n" +
                    "PercentageTrial\n" +
                    "0.65\n" +
                    "NBack\n" +
                    "2\n" +
                    "PercentageTrial\n" +
                    "0.55\n" +
                    "PercentageSession\n" +
                    "0.65\n" +
                    "IncreaseSession\n" +
                    "0.2\n" +
                    "NBackSession\n" +
                    "4.9\n" +
                    "MaxNBack\n" +
                    "6\n" +
                    "DayEndSession\n" +
                    "2022_12_05\n";
}
