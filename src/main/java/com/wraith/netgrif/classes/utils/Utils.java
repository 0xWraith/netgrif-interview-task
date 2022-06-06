package com.wraith.netgrif.classes.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils
{
    private static Pattern pattern;

    public static boolean controlDate(String date)
    {
        if(date == null)
            return false;

        pattern = Pattern.compile("^\\d{4}\\.(0[1-9]|1[012])\\.(0[1-9]|[12][0-9]|3[01])$");
        return pattern.matcher(date).find();
    }

    public static boolean controlIdentificationNumber(String identNumber)
    {
        if(identNumber == null)
            return false;

        pattern = Pattern.compile("^[0-9]{6}/[0-9]{4}$");
        return pattern.matcher(identNumber).find();
    }

    public static boolean controlAddress(String address)
    {
        //Accepts
        //841 04, Bratislava, Stare Grunty, 53
        //84104,Bratislava,Stare Grunty,53

        pattern = Pattern.compile("^[0-9]{3} ?[0-9]{2}, ?[A-Z][a-zA-Z]+, ?[a-zA-Z ]+, ?[0-9a-zA-Z]+");
        return pattern.matcher(address).find();
    }
}
