package com.example;

public class MyClass {

    public static void main(String[] args) {
//        List list = new ArrayList();
//        if(list.isEmpty()){
//            System.out.println("list是空的时候isEmtpty判断是正确的");
//        }

//        List a = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if (i == 50) {
                break;
            }
            System.out.println(i);
        }
    }
}
