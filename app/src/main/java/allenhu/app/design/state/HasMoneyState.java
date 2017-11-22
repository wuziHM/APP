package allenhu.app.design.state;

import com.hlib.util.MLogUtil;

import java.util.Random;

/**
 * Author：燕青 $ on 2016/3/17  15:34
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class HasMoneyState implements State {


    private VendingMachine machine;
    Random random = new Random();

    public HasMoneyState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney() {

        MLogUtil.e("HasMoneyState---->已经投过了，不需要再投了！");
    }

    @Override
    public void backMoney() {
        MLogUtil.e("HasMoneyState---->退币成功！");
        machine.setCurrentState(machine.getNoMoneyState());
    }

    @Override
    public void turnCrank() {
        MLogUtil.e("HasMoneyState---->你转动了手柄！");
        int winner = random.nextInt(10);
        if (winner == 0 && machine.getCount() > 1) {
            machine.setState(machine.getWinnerState());
        } else {
            machine.setState(machine.getSoldState());
        }
    }

    @Override
    public void dispense() {

    }
}
