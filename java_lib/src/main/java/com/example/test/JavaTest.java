package com.example.test;

import com.example.design.Factory.Child;
import com.example.design.Factory.method.BreadRestaurant;
import com.example.design.Factory.method.FishRestaurant;
import com.example.design.Factory.method.MRestaurant;
import com.example.design.Factory.method.NoodlesRestaurant;
import com.example.design.Factory.simple.Restaurant;
import com.example.design.observer.Customer1;
import com.example.design.observer.Customer2;
import com.example.design.observer.MilkProvider;
import com.example.design.proxy.Panqiaoyun;
import com.example.design.proxy.ProxyWang;

import junit.framework.TestCase;

/**
 * Author：燕青 $ on 16/7/22 16:17
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class JavaTest extends TestCase {
    public void testOb() {
        MilkProvider provider = new MilkProvider();
        Customer1 customer1 = new Customer1();
        Customer2 customer2 = new Customer2();
        provider.addObserver(customer1);
        provider.addObserver(customer2);
        provider.milkProduced();
    }

    public void testSuper() {
        Child child = new Child();
        child.say();
    }

    public void testProxy() {
        ProxyWang wang = new ProxyWang();
        wang.makeEyesWithMan();
        wang.playWithMan();

        wang = new ProxyWang(new Panqiaoyun());
        wang.playWithMan();
        wang.makeEyesWithMan();
    }

    public void testSimpleFactory(){
        Restaurant restaurant = new Restaurant();
        restaurant.cooking(Restaurant.BREAD).getFood();
        restaurant.cooking(Restaurant.FISH).getFood();
        restaurant.cooking(Restaurant.NOODLES).getFood();

    }
    public void testMethodFactory(){
        MRestaurant restaurant = new FishRestaurant();
        restaurant.cooking().getFood();

        restaurant = new BreadRestaurant();
        restaurant.cooking().getFood();

        restaurant = new NoodlesRestaurant();
        restaurant.cooking().getFood();
    }
}
