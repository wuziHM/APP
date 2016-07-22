package com.example.design.observer;

import java.util.Observable;
import java.util.Random;

/**
 * Author：燕青 $ on 16/7/22 14:46
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class MilkProvider extends Observable {
    public void milkProduced() {
        setChanged();//状态改变，必须调用
        notifyObservers();
    }

    public int getPrice() {
        return new Random().nextInt(100);
    }

    public String getName() {
        return getRandomString(5);
    }

    public static String getRandomString(int length) {
        StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        int range = buffer.length();
        for (int i = 0; i < length; i++) {
            sb.append(buffer.charAt(random.nextInt(range)));
        }
        return sb.toString();
    }
}
