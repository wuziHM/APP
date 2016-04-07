package allenhu.app.design.Observer;

import java.util.ArrayList;

/**
 * Author：燕青 $ on 2016/3/17  17:21
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class D3Subject implements Subject {

    private String msg;
    private ArrayList<Observer> list = new ArrayList<>();


    @Override
    public void registerObserver(Observer observer) {
        list.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        list.remove(observer);
    }

    @Override
    public void myNotifyAll() {
        for(Observer observer1:list){
            observer1.update(msg);
        }
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
        myNotifyAll();
    }
}
