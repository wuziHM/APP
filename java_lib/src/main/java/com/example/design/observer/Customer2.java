package com.example.design.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Author：燕青 $ on 16/7/22 16:16
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class Customer2 implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        MilkProvider provider = (MilkProvider) o;
        System.out.println(provider.getName());
    }
}
