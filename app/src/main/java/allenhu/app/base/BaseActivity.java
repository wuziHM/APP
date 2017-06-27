package allenhu.app.base;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import com.hlib.app.HActivity;
import com.hlib.util.MStringUtil;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by AllenHu on 2016/2/15.
 */
public abstract class BaseActivity extends HActivity {


    private SweetAlertDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initProgressView();
    }

    protected void initProgressView() {
        progressDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        progressDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        progressDialog.setTitleText("加载中...");
        progressDialog.setCancelable(false);
    }

    @Override
    public void showProgress() {
        try {
            if (MStringUtil.isObjectNull(getMContext()) || MStringUtil.isObjectNull(progressDialog)) {
                return;
            }
            if (!MStringUtil.isObjectNull(getMContext())) {
                if (progressDialog != null && !progressDialog.isShowing())
                    progressDialog.show();
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
                    if (progressDialog != null)
                        progressDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 400);
    }

}
