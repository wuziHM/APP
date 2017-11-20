package allenhu.app.activity.base;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import com.hlib.app.HActivity;
import com.hlib.util.MStringUtil;
import com.maning.mndialoglibrary.MProgressDialog;

import allenhu.app.R;

/**
 * Created by AllenHu on 2016/2/15.
 */
public abstract class BaseActivity extends HActivity {


    //    private AlertDialog progressDialog;
    private MProgressDialog mMProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initProgressView();
    }

    protected void initProgressView() {

        //新建一个Dialog
        mMProgressDialog = new MProgressDialog.Builder(this)
                //点击外部是否可以取消
                .isCanceledOnTouchOutside(true)
                //全屏背景窗体的颜色
                .setBackgroundWindowColor(getResources().getColor(R.color.half_transparent))
                //View背景的颜色
                .setBackgroundViewColor(getResources().getColor(R.color.slategray))
                //View背景的圆角
                .setCornerRadius(20)
                //View 边框的颜色
                .setStrokeColor(getResources().getColor(R.color.slategray))
                //View 边框的宽度
                .setStrokeWidth(2)
                //Progress 颜色
                .setProgressColor(getResources().getColor(R.color.white))
                //Progress 宽度
                .setProgressWidth(3)
                //Progress 内圈颜色
                .setProgressRimColor(Color.YELLOW)
                //Progress 内圈宽度
                .setProgressRimWidth(2)
                //文字的颜色
                .setTextColor(getResources().getColor(R.color.white))
                //取消的监听
                .setOnDialogDismissListener(new MProgressDialog.OnDialogDismissListener() {
                    @Override
                    public void dismiss() {
//                        mHandler.removeCallbacksAndMessages(null);
                    }
                }).build();
//        mMProgressDialog.show();


//        progressDialog = new AlertDialog(this);
////        progressDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
////        progressDialog.setTitleText("加载中...");
//        progressDialog.setCancelable(false);
    }

    @Override
    public void showProgress() {
        try {
            if (MStringUtil.isObjectNull(getMContext()) || MStringUtil.isObjectNull(mMProgressDialog)) {
                return;
            }
            if (!MStringUtil.isObjectNull(getMContext())) {
                if (mMProgressDialog != null)
                    mMProgressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hideProgress() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    if (mMProgressDialog != null)
                        mMProgressDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 400);
    }

}
