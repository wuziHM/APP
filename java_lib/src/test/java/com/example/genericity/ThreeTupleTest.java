package com.example.genericity;

import com.example.design.bridge.Bus;
import com.example.design.bridge.Car;
import com.example.design.bridge.Street;

import org.junit.Test;

/**
 * Author：HM $ on 18/3/23 17:14
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class ThreeTupleTest {


    @Test
    public void testGener() {

        ThreeTuple<Bus, Car, Street> threeTuple = new ThreeTuple<>(new Bus(), new Car(), new Street());
        System.out.println("A:" + threeTuple.getA().getClass().getName()
                + "  B:" + threeTuple.getB().getClass().getName()
                + "  C:" + threeTuple.getC().getClass().getName());

    }


//    @Test
//    public void toString() throws Exception {
//    }

}