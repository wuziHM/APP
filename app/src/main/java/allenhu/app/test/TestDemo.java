package allenhu.app.test;

import android.test.AndroidTestCase;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;

import allenhu.app.util.LogUtil;
import allenhu.app.util.StringMatcher;

/**
 * Created by AllenHu on 2016/2/16.
 */
public class TestDemo extends AndroidTestCase {

    public void testCity1() {
        Double d = Math.sin(30 * Math.PI / 180);
        Log.e("wuzi", "" + d);

        double d2 = Math.asin(0.5) * (180 / Math.PI);
        Log.e("wuzi", "" + d2);

        double d3 = Math.acos(10.0 / 20.0) * (180 / Math.PI);
        Log.e("wuzi", "" + d3);

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
        LogUtil.e("" + StringMatcher.mathch(value, key));
    }
}
