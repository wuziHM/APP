package com.example.design.bridge;

/**
 * Author：燕青 $ on 16/7/27 17:40
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class SpeedWay extends AbstractRoad {
    @Override
    public void road() {
        System.out.print("我在高速上");
        car.run();
    }
}
