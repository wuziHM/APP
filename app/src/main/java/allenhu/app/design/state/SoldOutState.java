package allenhu.app.design.state;

import com.hlib.util.MLogUtil;

/**
 * Author：燕青 $ on 2016/3/17  15:51
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class SoldOutState implements State {

    private VendingMachine machine;

    public SoldOutState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney() {
        MLogUtil.e("SoldOutState---->没货了，别投币了");
    }

    @Override
    public void backMoney() {
        MLogUtil.e("SoldOutState---->你都没投币，怎么退钱???");
    }

    @Override
    public void turnCrank() {
        MLogUtil.e("SoldOutState---->没货了，你转动木柄也没用");
    }

    @Override
    public void dispense() {
        MLogUtil.e("SoldOutState---->没货了，不可能出货的");
    }
}
