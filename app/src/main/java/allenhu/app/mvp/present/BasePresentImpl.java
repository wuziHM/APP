package allenhu.app.mvp.present;

/**
 * Author：HM $ on 18/6/11 15:18
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class BasePresentImpl<T> implements BasePresenter {

    public T mView;

    protected void attachView(T mView) {
        this.mView = mView;
    }


    @Override
    public void detachView() {
        mView = null;
    }
}
