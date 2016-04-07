package allenhu.app.design.Observer;

/**
 * Author：燕青 $ on 2016/3/17  16:34
 * E-mail：359222347@qq.com
 * <p/>
 * use to...主题接口，所有的主题必须实现此接口
 */
public interface Subject {
    /**
     * 注册观察者
     *
     * @param observer
     */
    void registerObserver(Observer observer);

    /**
     * 注销观察者
     *
     * @param observer
     */
    void removeObserver(Observer observer);

    /**
     * 通知所有的观察者观察者
     *
     * @param
     */
    void myNotifyAll();
}
