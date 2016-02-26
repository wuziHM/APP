package allenhu.app.bean;

/**
 * Created by AllenHu on 2016/2/25.
 * <p/>
 * 打印机A的实体类
 */
public class PrintA {

    /**
     * 调用这个方法进行打印
     */
    public void doPrint() {
        prepare1();
        prepare2();
    }

    /**
     * A 打印机已经做好的方法1，这个方法必须是要这样写
     */
    private void prepare1() {
        //具体实现
    }

    /**
     * A 打印机已经做好的方法2，这个方法必须是要这样写
     */
    private void prepare2() {
        //具体实现
    }
}
