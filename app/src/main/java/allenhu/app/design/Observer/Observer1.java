package allenhu.app.design.Observer;

import allenhu.app.util.LogUtil;

/**
 * Author：燕青 $ on 2016/3/17  17:27
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class Observer1 implements Observer {

    private Subject subject;

    public Observer1(Subject subject)
    {
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public void update(String msg) {
        LogUtil.e("observer1 得到 3D 号码  -->" + msg + ", 我要记下来。");
    }
}
