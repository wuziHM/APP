package com.example.design.Factory.abs;

import com.example.design.Factory.simple.Food;

/**
 * Author：燕青 $ on 16/8/1 11:57
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class SweetRestaurant extends Restaurant {

    private SweetFish sweetFish;
    private SweetBread sweetBread;
    private SweetNoodles sweetNoodles;


    @Override
    public Food getFish() {
        sweetFish = new SweetFish();
        return sweetFish;
    }

    @Override
    public Food getNoodles() {
        sweetNoodles = new SweetNoodles();
        return sweetNoodles;
    }

    @Override
    public Food getBread() {
        sweetBread = new SweetBread();
        return sweetBread;
    }
}
