package com.example.test;

import com.example.design.observer.Customer1;
import com.example.design.observer.Customer2;
import com.example.design.observer.MilkProvider;

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
}
