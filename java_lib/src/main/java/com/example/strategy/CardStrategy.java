package com.example.strategy;

/**
 * Author：燕青 $ on 16/12/8 15:12
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class CardStrategy implements Strategy {

    @Override
    public Double calRecharge(Double charge, RechargeTypeEnum type) {
        return charge + charge * 0.01;
    }

}
