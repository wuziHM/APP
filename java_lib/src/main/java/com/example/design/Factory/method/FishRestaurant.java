package com.example.design.Factory.method;

import com.example.design.Factory.simple.Fish;
import com.example.design.Factory.simple.Food;

/**
 * Author：燕青 $ on 16/7/29 18:08
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class FishRestaurant extends MRestaurant {
    @Override
    public Food cooking() {
        return new Fish();
    }
}
