package allenhu.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import allenhu.app.R;
import allenhu.app.activity.base.BaseActivity;
import allenhu.app.util.ObjectFormatUtil;
import allenhu.app.widget.popup.VerPopup;
import razerdp.basepopup.BasePopupWindow;

public class ControlsActivity extends BaseActivity implements View.OnClickListener {

    private static int i = 1;
    private TextView tvFormat1;
    private TextView tvFormat2;

    private void assignViews() {
        tvFormat1 = (TextView) findViewById(R.id.tv_format1);
        tvFormat2 = (TextView) findViewById(R.id.tv_format2);
        double a = 10;
        double b = 0.8;
        tvFormat1.setText(ObjectFormatUtil.formatCurrency(a));
        tvFormat2.setText(ObjectFormatUtil.formatCurrency(b));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controls);

        findViewById(R.id.btn_gallery).setOnClickListener(this);
        findViewById(R.id.btn_spinner).setOnClickListener(this);
        findViewById(R.id.btn_recycle).setOnClickListener(this);
        findViewById(R.id.btn_listView).setOnClickListener(this);
        findViewById(R.id.btn_popupWindow).setOnClickListener(this);
        findViewById(R.id.tv_pop).setOnClickListener(this);
        final EditText editText = (EditText) findViewById(R.id.edt_test);
        editText.setText("是不是设置成了final就不能改变值了？");
        assignViews();

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_gallery:
                intent = new Intent(ControlsActivity.this, GridViewDemoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_spinner:
                intent = new Intent(ControlsActivity.this, SpinnerActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_recycle:
                intent = new Intent(ControlsActivity.this, RecycleActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_listView:
                intent = new Intent(ControlsActivity.this, ListViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_popupWindow:
                intent = new Intent(ControlsActivity.this, PopupWindowActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_pop:


                final VerPopup verPopup = new VerPopup(this);
                verPopup.setPopupWindowFullScreen(false);
                verPopup.setAdjustInputMethod(true);

                verPopup.setOnDismissListener(new BasePopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {

                    }
                });


                verPopup.setOnBeforeShowCallback(new BasePopupWindow.OnBeforeShowCallback() {
                    @Override
                    public boolean onBeforeShow(View popupRootView, View anchorView, boolean hasShowAnima) {
                        verPopup.setDismissWhenTouchOutside(true); //是否点击外部消失
                        verPopup.setBackPressEnable(true);         //是否可以返回键消失
                        verPopup.setInterceptTouchEvent(true);     //是否拦截事件
                        return true;
                    }
                });

                verPopup.showPopupWindow();

//                final InputPopup verPopup = new InputPopup(this);
//                verPopup.getPopupWindow().setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
//                verPopup.setOnBeforeShowCallback(new BasePopupWindow.OnBeforeShowCallback() {
//                    @Override
//                    public boolean onBeforeShow(View popupRootView, View anchorView, boolean hasShowAnima) {
//                        verPopup.setDismissWhenTouchOutside(true); //是否点击外部消失
//                        verPopup.setBackPressEnable(true);         //是否可以返回键消失
//                        verPopup.setInterceptTouchEvent(true);     //是否拦截事件
//                        return true;
//                    }
//                });
//
//                verPopup.showPopupWindow();
//
//
//                final DismissControlPopup dismissControlPopup = new DismissControlPopup(this);
//                dismissControlPopup.setOnBeforeShowCallback(new BasePopupWindow.OnBeforeShowCallback() {
//                    @Override
//                    public boolean onBeforeShow(View popupRootView, View anchorView, boolean hasShowAnima) {
//                        dismissControlPopup.setDismissWhenTouchOutside(true); //是否点击外部消失
//                        dismissControlPopup.setBackPressEnable(true);         //是否可以返回键消失
//                        dismissControlPopup.setInterceptTouchEvent(true);     //是否拦截事件
//                        return true;
//                    }
//                });
//
//                dismissControlPopup.showPopupWindow();
////                intent = new Intent(ControlsActivity.this, PopupWindowActivity.class);
////                startActivity(intent);
                break;
        }
    }
}
