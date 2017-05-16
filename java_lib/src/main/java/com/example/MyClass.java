package com.example;

import java.text.DecimalFormat;

public class MyClass {

    public static void main(String[] args) {
        String a = "18874442357";
        System.out.println(a.substring(0, 4));
        System.out.println(a.substring(4, a.length()));

        System.out.println(formatDouble(3332323232.32));

    }

    public static String formatDouble(double d) {
        DecimalFormat df = new DecimalFormat("######0.00");
        return df.format(d);
    }

}
