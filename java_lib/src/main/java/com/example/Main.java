package com.example;

import java.util.Scanner;

public class Main {
    static int flagA;
    static int flagB;
    static int result;

    public static void main(String[] args) {
        int a, b, t;
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            a = scanner.nextInt();
            b = scanner.nextInt();
            if (a < b)  //保证A大B小
            {
                t = a;
                a = b;
                b = t;
            }

            /*
             flagA =0;   //先假定AB都说假话
        flagB =0;
        dfs(A,B,1); //判断AB矛盾

        *要求:
        *较小者发起挑战，若较大者被证明说谎，较小者胜（较小者说真话，同时较大者说了假话）;
        *若较大者可以成立，则较大者胜;
        *若较小者对自己的结果计算错误，也就是较小者不能成立，如因子中包含一个大于100的质数，则挑战不会举行，较大者胜


            result =A;
            if(flagA ==0 && flagB ==1)  //只有证明A说了假话，并且B说了真话，才算B赢
                result =B;
            printf("%d\n",result);
             */

            flagA = 0;
            flagB = 0;
            dfs(a, b, 1);
            result = a;
            if (flagA == 0 && flagB == 1) {
                result = b;
            }
            System.out.println(result);
        }
    }

    private static void dfs(int m, int n, int tt) {
        int k = tt;
        if (m == 1 && n == 1)    //在两个数的所有各不相同的因子中，有因子能重新乘出给出的两个数，则A说了真话
        {
            flagA = 1; //A说了真话
            return;
        }
        if (n == 1) //在两个数的所有各不相同的因子中，没有任何因子能重新乘出给出的两个数，则B说了真话
            flagB = 1;   //B说了真话
        while ((k < m || k < n) && (k < 100)) {
            k++;
            if (m % k == 0) {
                dfs(m / k, n, k);
                if (flagA==0)
                    return;
            }
            if (n % k == 0) {
                dfs(m, n / k, k);
                if (flagA==0)
                    return;
            }
        }
    }


}
