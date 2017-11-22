package allenhu.app.design.state;

import com.hlib.util.MLogUtil;

/**
 * Author：燕青 $ on 2016/3/17  15:21
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class NoMoneyState implements State {
    private VendingMachine machine;

    public NoMoneyState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney() {
        MLogUtil.e("NoMoneyState---->投币成功！");
        machine.setCurrentState(machine.getHasMoneyState());
    }

    @Override
    public void backMoney() {
        MLogUtil.e("NoMoneyState---->没投币，你想退钱？？？");
    }

    @Override
    public void turnCrank() {
        MLogUtil.e("NoMoneyState---->没投币，你想拿东西么???");
    }

    @Override
    public void dispense() {
        MLogUtil.e("NoMoneyState---->没投币式不肯能出货的~~~");
    }
}
