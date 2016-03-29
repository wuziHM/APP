package allenhu.app.design.state;

/**
 * Created by AllenHu on 2016/3/17.
 *
 * 状态的接口
 */
public interface State {

    /**
     * 放钱
     */
    public void insertMoney();
    /**
     * 退钱
     */
    public void backMoney();
    /**
     * 转动曲柄
     */
    public void turnCrank();
    /**
     * 出商品
     */
    public void dispense();

}
