package com.example.design.proxy;

/**
 * Author：燕青 $ on 16/7/28 17:59
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class ProxyWang extends Bitch {
    Bitch bitch;
    public ProxyWang() {
        this.bitch = new Panjinlian();
    }

    public ProxyWang(Bitch bitch) {
        this.bitch = bitch;
    }

    @Override
    public void playWithMan() {
        bitch.playWithMan();
    }

    @Override
    public void makeEyesWithMan() {
        bitch.makeEyesWithMan();
    }
}
