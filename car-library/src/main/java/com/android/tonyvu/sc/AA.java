package com.android.tonyvu.sc;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Author：燕青 $ on 2016/8/24  16:54
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class AA {
    public static void main(String[] args) {

        Admin admin = new Admin();
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("--请选择要执行的操作--");
            System.out.println("１.添加食品");
            System.out.println("２.查询食品");
            System.out.println("３.修改价格");
            System.out.println("４.删除食品");
            System.out.println("99.退出");
            String number = in.next();
            if (number.equals("1")) {
                System.out.println("请输入食品编号：");
                String on = in.next();
                System.out.println("请输入食品名称：");
                String name = in.next();
                System.out.println("请输入食品价格：");
                double price = in.nextInt();
                Sp sp = new Sp(on, name, price);
                admin.input(sp);

            } else if (number.equals("2")) {
                System.out.println("请输入食品编号：");
                String on = in.next();
                admin.spxx(on);
            } else if (number.equals("3")) {

            }

        }
    }
}

class Admin {
    private ArrayList shop = null;

    Admin() {
        shop = new ArrayList();
    }

    void input(Sp sp) {
        shop.add(sp);
        System.out.println("食品添加成功");
        for (int j = 0; j < shop.size(); j++) {
            Sp aa = (Sp) shop.get(j);
            System.out.println("该食品的信息为：");
            System.out.println("编号" + aa.getOn());
            System.out.println("该食品名字为：" + aa.getName());
            System.out.println("该食品的价格为：：" + aa.getPrice());
        }
    }

    void spxx(String on) {
        int i;
        for (i = 0; i < shop.size(); i++) {
            Sp sp = (Sp) shop.get(i);
            if (sp.getOn().equals("on")) {
                System.out.println("该食品的信息为：");
                System.out.println("编号" + on);
                System.out.println("该食品名字为：" + sp.getName());
                System.out.println("该食品的价格为：：" + sp.getPrice());
                return;
            }
        }
        if (i == shop.size()) {
            System.out.println("找不到这个编号。");
        }
    }
}

class Sp {

    Sp(String on, String name, double price) {
        this.name = name;
        this.on = on;
        this.price = price;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOn() {
        return on;
    }

    public void setOn(String on) {
        this.on = on;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private String on;
    private double price;

}