package allenhu.app.bean;

import allenhu.app.util.LogUtil;

/**
 * Created by AllenHu on 2016/2/16.
 */
public class Hero {

    {
        LogUtil.e("竟然还有这种非主流的写法");
    }

    private int hIcon;
    private String hName;

    public Hero() {
    }

    public Hero(int hIcon, String hName) {
        this.hIcon = hIcon;
        this.hName = hName;
    }

    public int gethIcon() {
        return hIcon;
    }

    public String gethName() {
        return hName;
    }

    public void sethIcon(int hIcon) {
        this.hIcon = hIcon;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }
}
