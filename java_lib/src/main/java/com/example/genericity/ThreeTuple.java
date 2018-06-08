package com.example.genericity;

/**
 * Author：HM $ on 18/3/23 16:44
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class ThreeTuple<A,B,C> extends TwoTuple<A,B> {




    private C c;
    public ThreeTuple(A a, B b,C c) {
        super(a, b);
        this.c = c;
    }

    public C getC() {
        return c;
    }

    public void setC(C c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return "ThreeTuple{" +
                "c=" + c.getClass().getName() + "  B=" + getB().getClass().getName() + "   A=" + getA().getClass().getName() +
                '}';
    }
}
