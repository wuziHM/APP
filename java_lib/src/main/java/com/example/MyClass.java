package com.example;

public class MyClass {

    public static void main(String[] args) {
        String a = "18874442357";
        System.out.println(a.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
    }
}
