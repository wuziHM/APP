package com.example.utils;

import java.util.ArrayList;

/**
 * Author：燕青 $ on 17/2/6 10:20
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class AryDemo {

    public static int calculate(int number, int ary) {

        ArrayList<Integer> arrayList = new ArrayList();

        int count = 0;
//        int shang = number / ary;

        while (number > 0) {
            int remainder = number % ary;
            arrayList.add(remainder);
            number = number / ary;
        }
        int size = arrayList.size();
        for (int i = size - 1; i >= 0; i--) {
            System.out.println("size:" + size + "    i:" + i + "   arrayList.get(i):" + arrayList.get(i));
            count += arrayList.get(i) * calculateMul(10, i);
        }
        return count;
    }

    static long temp = 1;

    public static long calculateMul(long x, int y) {
        if (y == 0) {
            return 1;
        }
        if (y == 1) {
            return x * temp;
        }
        if (y == 2) {
            return (x * x * temp);
        }
        if (y % 2 == 1) {
            temp = x;
        }

        return calculateMul(x * x, y / 2);

    }
}
