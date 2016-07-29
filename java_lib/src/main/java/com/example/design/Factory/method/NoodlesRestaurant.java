package com.example.design.Factory.method;

import com.example.design.Factory.simple.Food;
import com.example.design.Factory.simple.Noodles;

/**
 * Author：燕青 $ on 16/7/29 18:08
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class NoodlesRestaurant extends MRestaurant {
    @Override
    public Food cooking() {
        return new Noodles();
    }
}
