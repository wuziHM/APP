package allenhu.app.test;

import android.test.AndroidTestCase;
import android.test.AndroidTestRunner;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;
import android.util.TypedValue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import allenhu.app.design.Observer.D3Subject;
import allenhu.app.design.Observer.Observer1;
import allenhu.app.design.Observer.Observer2;
import allenhu.app.design.state.VendingMachine;
import allenhu.app.util.LogUtil;
import allenhu.app.util.StringMatcher;

/**
 * Created by AllenHu on 2016/2/16.
 */
public class TestDemo extends AndroidTestCase {

    public TestDemo() {
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @SmallTest
    public void testAdd() {
        assertEquals(2, 1 + 1);
    }

    public void testXX() throws Exception {
        assertEquals(2, 1 + 1);
    }


    public void testCity1() {
        Double d = Math.sin(Math.toRadians(30));
        LogUtil.e("" + d);

        double d2 = Math.asin(0.5) * (180 / Math.PI);
        LogUtil.e("" + d2);

        double d3 = Math.acos(10.0 / 20.0) * (180 / Math.PI);
        LogUtil.e("" + d3);

//        Math.toRadians(1)
//        double d4 = Math.tan(45 * Math.PI / 180);
        double d4 = Math.toDegrees(Math.atan(1.0));
        LogUtil.e("" + d4);
    }

    public void testList() {
        ArrayList arrayList = new ArrayList();
        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < 1000000; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }
        long first = System.currentTimeMillis();
        arrayList.remove(30);
        long later = System.currentTimeMillis();
        Log.e("wuzi", "arrayList删除操作花的时间是:" + (later - first));

        first = System.currentTimeMillis();
        linkedList.remove(30);
        later = System.currentTimeMillis();
        Log.e("wuzi", "linkedList删除操作花的时间:" + (later - first));


        first = System.currentTimeMillis();
        arrayList.get(30000);
        later = System.currentTimeMillis();
        Log.e("wuzi", "arrayList查找操作花的时间是:" + (later - first));


        first = System.currentTimeMillis();
        linkedList.get(30000);
        later = System.currentTimeMillis();
        Log.e("wuzi", "linkedList查找操作花的时间是:" + (later - first));
    }

    public void testString() {
        String s = "aaa=222&ddd=555&123=111&eee=666&ccc=444&bbb=333";
        String[] ss = s.split("&");
        Log.e("wuzi", ss.length + "");
        for (String s1 : ss) {
            String[] sss = s1.split("=");
            for (String s2 : sss) {
                Log.e("wuzi", s2);
            }
        }
    }

    public void testSet() {
        String value = "aadcdaaa";
        String key = "aad";
        LogUtil.e("" + StringMatcher.match(value, key));
    }

    public void testScanner() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你的姓名：");
        String name = sc.nextLine();
        System.out.println("请输入你的年龄：");

        int age = sc.nextInt();

        System.out.println("请输入你的工资：");

        float salary = sc.nextFloat();

        System.out.println("你的信息如下：");

        System.out.println("姓名：" + name + "\n" + "年龄：" + age + "\n" + "工资：" + salary);
    }

    public void testState() {
        VendingMachine vendingMachine = new VendingMachine(10);
        vendingMachine.insertMoney();
        vendingMachine.insertMoney();
        vendingMachine.backMoney();
        vendingMachine.turnCrank();
        vendingMachine.insertMoney();
//        vendingMachine.dispense();
        vendingMachine.turnCrank();
//        vendingMachine.dispense();

        LogUtil.e("==================");
        vendingMachine.insertMoney();
        vendingMachine.backMoney();
        vendingMachine.backMoney();
        vendingMachine.turnCrank();// 无效操作
        vendingMachine.turnCrank();// 无效操作
        vendingMachine.backMoney();
    }

    public void testObserver() {
        D3Subject d3Subject = new D3Subject();
        Observer1 observer1 = new Observer1(d3Subject);
        Observer2 observer2 = new Observer2(d3Subject);
//        d3Subject.registerObserver(observer1);
//        d3Subject.registerObserver(observer2);

        d3Subject.setMsg("苦，才是人生");
        d3Subject.setMsg("累，才是工作");
    }


    public void testUnit() {

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 10, getContext().getResources().getDisplayMetrics());
        LogUtil.e("px:" + left);

        left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getContext().getResources().getDisplayMetrics());
        LogUtil.e("dip:" + left);

        left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getContext().getResources().getDisplayMetrics());
        LogUtil.e("sp:" + left);

        List list1 = new ArrayList();
        list1.add("xixi");

        List list2 = list1;
    }
}
