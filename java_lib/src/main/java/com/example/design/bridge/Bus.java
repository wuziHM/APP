package com.example.design.bridge;

/**
 * Author：燕青 $ on 16/7/27 18:33
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class Bus extends AbstractCar {
    @Override
    public void run() {
        System.out.println("我是公共汽车");
    }
}
