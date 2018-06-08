package com.example.genericity;

/**
 * Author：HM $ on 18/3/23 16:44
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class TwoTuple<A,B> {

    private A a;
    private B b;


    public TwoTuple(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }
}
