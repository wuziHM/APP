package allenhu.app.design.Observer;

import com.hlib.util.MLogUtil;

/**
 * Author：燕青 $ on 2016/3/17  17:29
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class Observer2 implements Observer{
    private Subject subject;

    public Observer2(Subject subject)
    {
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public void update(String msg) {
        MLogUtil.e("Observer2 得到 3D 号码  -->" + msg + ", xxxxxxxxxxxxx。");

    }
}
