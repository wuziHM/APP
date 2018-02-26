package allenhu.app.widget.popup;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jungly.gridpasswordview.GridPasswordView;

import allenhu.app.R;
import razerdp.basepopup.BasePopupWindow;

/**
 * Created by 大灯泡 on 2016/1/23.
 * dialogpopup :)
 * 客串一下dialog
 */
public class VerPopup extends BasePopupWindow implements View.OnClickListener {

    TextView tvFinger;
    TextView tvPwd;
    View lineFinger;
    View linePwd;
    LinearLayout lyFinger;
    LinearLayout lyPwd;
    GridPasswordView verificationCodeInput;
    RelativeLayout popupAnima;



    int currentPostion = 0;

    public VerPopup(final Activity context) {
        super(context);

        tvFinger = (TextView) getPopupWindowView().findViewById(R.id.tv_finger);
        tvPwd = (TextView) getPopupWindowView().findViewById(R.id.tv_pwd);
        lineFinger = getPopupWindowView().findViewById(R.id.line_finger);
        linePwd = getPopupWindowView().findViewById(R.id.line_pwd);
        tvFinger.setSelected(true);
//        pop.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        lyFinger = (LinearLayout) getPopupWindowView().findViewById(R.id.ly_finger);
        lyPwd = (LinearLayout) getPopupWindowView().findViewById(R.id.ly_pwd);
        verificationCodeInput = (GridPasswordView) getPopupWindowView().findViewById(R.id.pswView);
        popupAnima = (RelativeLayout) getPopupWindowView().findViewById(R.id.popup_anima);
        verificationCodeInput.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String psw) {

            }

            @Override
            public void onInputFinish(String psw) {
                if (psw.equals("123456")) {
                    Toast.makeText(context, "验证成功", Toast.LENGTH_LONG).show();
                    dismiss();
                } else {
                    Toast.makeText(context, "密码错误", Toast.LENGTH_LONG).show();
                    verificationCodeInput.clearPassword();
                }
            }
        });

        setViewClickListener(this, tvFinger, tvPwd);
    }


    @Override
    protected Animation initShowAnimation() {
        return getDefaultScaleAnimation();
    }

    @Override
    protected Animation initExitAnimation() {
        return getDefaultScaleAnimation(false);
    }

    @Override
    public View getClickToDismissView() {
        return null;
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.popup_fingerprint);
    }

    @Override
    public View initAnimaView() {
        return getPopupWindowView().findViewById(R.id.popup_anima);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_finger:

                if (currentPostion == 0) {
                    return;
                }
                currentPostion = 0;
                updateView(currentPostion);


                break;
            case R.id.tv_pwd:
                if (currentPostion == 1) {
                    return;
                }
                currentPostion = 1;
                updateView(currentPostion);
                break;
            default:
                break;
        }

    }

    private void updateView(int positon) {
        if (positon == 0) {
            lyFinger.setVisibility(View.VISIBLE);
            lyPwd.setVisibility(View.GONE);
            tvFinger.setSelected(true);
            tvPwd.setSelected(false);
            lineFinger.setVisibility(View.VISIBLE);
            linePwd.setVisibility(View.INVISIBLE);
        } else {
            lyPwd.setVisibility(View.VISIBLE);
            lyFinger.setVisibility(View.GONE);
            tvPwd.setSelected(true);
            tvFinger.setSelected(false);
            linePwd.setVisibility(View.VISIBLE);
            lineFinger.setVisibility(View.INVISIBLE);

        }
    }

}
