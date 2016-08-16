package com.example.design.bridge;

/**
 * Author：燕青 $ on 16/7/27 17:46
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class Street extends AbstractRoad {
    @Override
    public void road() {
        System.out.println("我在大街上");
        car.run();
    }
}
