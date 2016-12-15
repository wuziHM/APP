package com.example.strategy;

/**
 * Author：燕青 $ on 16/12/8 15:11
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public enum RechargeTypeEnum {

    E_BANK(1, "网银"),

    BUSI_ACCOUNTS(2, "商户账号"),

    MOBILE(3, "手机卡充值"),

    CARD_RECHARGE(4, "充值卡");


    private int value;


    private String description;


    private RechargeTypeEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int value() {
        return value;
    }

    public String description() {
        return description;
    }


    public static RechargeTypeEnum valueOf(int value) {
        for (RechargeTypeEnum type : RechargeTypeEnum.values()) {
            if (type.value() == value) {
                return type;
            }
        }
        return null;
    }
}
