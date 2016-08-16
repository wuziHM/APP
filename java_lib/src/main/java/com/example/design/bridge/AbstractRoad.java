package com.example.design.bridge;

/**
 * Author：燕青 $ on 16/7/27 11:07
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public abstract class AbstractRoad {

    protected AbstractCar car;

    public AbstractCar getCar() {
        return car;
    }

    public void setCar(AbstractCar car) {
        this.car = car;
    }

    public abstract void road();
}
