package com.example.test;

import com.example.Utils;
import com.example.design.Factory.Child;
import com.example.design.Factory.abs.HotRestaurant;
import com.example.design.Factory.abs.SweetRestaurant;
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
import com.example.other.IdcardValidator;

import junit.framework.TestCase;

/**
 * Author：燕青 $ on 16/7/22 16:17
 * E-mail：359222347@qq.com
 * <p>
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

    public void testSimpleFactory() {
        Restaurant restaurant = new Restaurant();
        restaurant.cooking(Restaurant.BREAD).getFood();
        restaurant.cooking(Restaurant.FISH).getFood();
        restaurant.cooking(Restaurant.NOODLES).getFood();

    }

    public void testMethodFactory() {
        MRestaurant restaurant = new FishRestaurant();
        restaurant.cooking().getFood();

        restaurant = new BreadRestaurant();
        restaurant.cooking().getFood();

        restaurant = new NoodlesRestaurant();
        restaurant.cooking().getFood();
    }

    public void testAbstractFactory() {
        com.example.design.Factory.abs.Restaurant restaurant = new SweetRestaurant();
        restaurant.getBread().getFood();
        restaurant.getFish().getFood();
        restaurant.getNoodles().getFood();

        restaurant = new HotRestaurant();
        restaurant.getBread().getFood();
        restaurant.getNoodles().getFood();
        restaurant.getFish().getFood();
    }

    public void testPattern() {
        String a = "12345678939876098X";
        String b = "123456789100917";
        System.out.println(Utils.isIdCardNum(a));
        System.out.println(Utils.isIdCardNum(b));
    }

    public void testIdCard() {

//        String idcard15 = "142431199001145";//15位
        String idcard18 = "431081199207075297";//18位
//        String idcard18 = "36232219930109002X";//18位
        IdcardValidator iv = new IdcardValidator();

        System.out.println(iv.isValidatedAllIdcard(idcard18));
//        String[] ss = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "x", "X"};
////        System.out.println(iv.isValidatedAllIdcard(idcard15));
//        for (String s : ss) {
//            String str = idcard18 + s;
//            System.out.println(iv.isValidatedAllIdcard(str));
//        }
    }
}
