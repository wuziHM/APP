package com.example.design.Factory.abs;


import com.example.design.Factory.simple.Food;

/**
 * Author：燕青 $ on 16/7/29 18:00
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public abstract class Restaurant {
    public abstract Food getFish();

    public abstract Food getNoodles();

    public abstract Food getBread();
}
