package allenhu.app.design.state;

import com.hlib.util.MLogUtil;

/**
 * Author：燕青 $ on 2016/3/17  15:56
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class SoldState implements State {

    private VendingMachine machine;

    public SoldState(VendingMachine machine)
    {
        this.machine = machine;
    }

    @Override
    public void insertMoney()
    {
        MLogUtil.e("正在出货，请勿投币");
    }

    @Override
    public void backMoney()
    {
        MLogUtil.e("正在出货，没有可退的钱");
    }

    @Override
    public void turnCrank()
    {
        MLogUtil.e("正在出货，请勿重复转动手柄");
    }

    @Override
    public void dispense()
    {
        machine.dispense();
        if (machine.getCount() > 0)
        {
            machine.setState(machine.getNoMoneyState());
        } else
        {
            MLogUtil.e("商品已经售罄");
            machine.setState(machine.getSoldOutState());
        }
    }
}
