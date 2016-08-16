package com.example.design.bridge;

/**
 * Author：燕青 $ on 16/7/27 17:43
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class Car extends AbstractCar {
    @Override
    public void run() {
        System.out.println("我是小汽车，正在开");
    }
}
