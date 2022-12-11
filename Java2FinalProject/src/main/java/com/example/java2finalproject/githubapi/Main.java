package com.example.java2finalproject.githubapi;

import java.text.ParseException;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_WEEK, -7);
        System.out.println(c.getTime());

    }
}



