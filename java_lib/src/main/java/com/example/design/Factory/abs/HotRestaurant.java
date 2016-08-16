package com.example.design.Factory.abs;

import com.example.design.Factory.simple.Food;

/**
 * Author：燕青 $ on 16/8/1 12:00
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class HotRestaurant extends Restaurant {
    private HotBread hotBread;
    private HotNoodles hotNoodles;
    private HotFish hotFish;


    @Override
    public Food getFish() {
        hotFish = new HotFish();
        return hotFish;
    }

    @Override
    public Food getNoodles() {
        hotNoodles = new HotNoodles();
        return hotNoodles;

    }

    @Override
    public Food getBread() {
        hotBread = new HotBread();
        return hotBread;
    }
}
