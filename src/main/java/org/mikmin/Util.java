package org.mikmin;

public class Util {

    public static String toTitleCase(String str) {
        String titledStr = "";

        for (String string : str.split(" ")) {
            String firstLtr = string.trim().substring(0,1).toUpperCase();
            String remainingLtrs = string.trim().substring(1).toLowerCase();

            titledStr += firstLtr + remainingLtrs + ' ';
        }

        return  titledStr;
    }
}
