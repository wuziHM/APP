package com.example.design.Factory.simple;

/**
 * Author：燕青 $ on 16/7/29 16:48
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class Restaurant {
    private Food food;
    public static final String FISH = "fish";
    public static final String BREAD = "bread";
    public static final String NOODLES = "noodles";

    public Food cooking(String name) {
        if (name == null || name.length() <= 0) {
            return null;
        } else if (name.equals(FISH)) {
            food = new Fish();
        } else if (name.equals(BREAD)) {
            food = new Bread();
        } else if (name.equals(NOODLES)) {
            food = new Noodles();
        } else {
            food = null;
        }
        return food;
    }
}
