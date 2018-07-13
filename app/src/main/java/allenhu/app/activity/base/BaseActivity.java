package allenhu.app.activity.base;

import android.os.Bundle;

import com.hlib.app.HActivity;
import com.hlib.util.MStringUtil;
import com.maning.mndialoglibrary.MProgressDialog;
import com.maning.mndialoglibrary.config.MDialogConfig;
import com.maning.mndialoglibrary.listeners.OnDialogDismissListener;

import allenhu.app.R;

/**
 * Created by AllenHu on 2016/2/15.
 */
public abstract class BaseActivity extends HActivity {

    protected static final String PARAM1 = "data_param";
    protected static final String PARAM2 = "data_param2";

    private MDialogConfig mDialogConfig;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initProgressView();
    }

    protected void initProgressView() {

        mDialogConfig = new MDialogConfig.Builder()
                //点击外部是否可以取消
                .isCanceledOnTouchOutside(true)
                //全屏背景窗体的颜色
                .setBackgroundWindowColor(getResources().getColor(R.color.transparent))
                //View背景的颜色
                .setBackgroundViewColor(getResources().getColor(R.color.darkgray))
                //View背景的圆角
                .setCornerRadius(20)
                //View 边框的颜色
//                .setStrokeColor(getResources().getColor(R.color.slategray))
                //View 边框的宽度
//                .setStrokeWidth(2)
                //Progress 颜色
                .setProgressColor(getResources().getColor(R.color.white))
                //Progress 宽度
                .setProgressWidth(2)
                //Progress 内圈颜色
//                .setProgressRimColor(Color.YELLOW)
                //Progress 内圈宽度
//                .setProgressRimWidth(2)
                //文字的颜色
                .setTextColor(getResources().getColor(R.color.white))
                .setTextSize(15)
                //取消的监听
                .setOnDialogDismissListener((OnDialogDismissListener) () -> {

                }).build();


    }


    @Override
    public void showProgress() {
        showProgress("加载中...");
    }

    @Override
    public void showProgress(String msg) {
        try {
            if (!MStringUtil.isObjectNull(getMContext())) {
                if (mDialogConfig != null)
                    MProgressDialog.showProgress(this, msg, mDialogConfig);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hideProgress() {
        MProgressDialog.dismissProgress();
    }

}
