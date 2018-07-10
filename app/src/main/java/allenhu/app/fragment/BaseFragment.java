package allenhu.app.fragment;

import android.support.v4.app.Fragment;

import com.hlib.app.IBaseView;
import com.orhanobut.logger.Logger;

import allenhu.app.activity.base.BaseActivity;

/**
 * Author：HM $ on 18/7/9 16:46
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class BaseFragment extends Fragment implements IBaseView {
    /**
     * 加载的转圈
     */
    @Override
    public void showProgress(String msg) {
        if (getActivity() instanceof BaseActivity) {
            BaseActivity baseActivity = (BaseActivity) getActivity();
            baseActivity.showProgress(msg);
        } else {
            Logger.d("showProgress ---> getActivity()  is not instanceof BaseActivity");
        }

    }

    @Override
    public void showProgress() {
        showProgress("加载中...");
    }

    @Override
    public void hideProgress() {
        if (getActivity() instanceof BaseActivity) {
            BaseActivity baseActivity = (BaseActivity) getActivity();
            baseActivity.hideProgress();
        } else {
            Logger.d("hideProgress --- >getActivity()  is not instanceof BaseActivity");
        }
    }
}
