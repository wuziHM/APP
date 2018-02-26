package allenhu.app.fragment;

import android.view.View;
import android.widget.Button;

import allenhu.app.R;
import allenhu.app.widget.popup.InputPopup;
import razerdp.basepopup.BasePopupWindow;

/**
 * Created by 大灯泡 on 2016/1/18.
 * 自动弹出输入框的popup
 */
public class InputPopupFrag extends SimpleBaseFrag {

    @Override
    public void bindEvent() {

    }

    @Override
    public BasePopupWindow getPopup() {
        InputPopup inputPop= new InputPopup(mContext);
        inputPop.setPopupWindowFullScreen(false);
        inputPop.setAdjustInputMethod(true);
        return inputPop;
    }

    @Override
    public Button getButton() {
        return (Button) mFragment.findViewById(R.id.popup_show);
    }

    @Override
    public View getFragment() {
        return mInflater.inflate(R.layout.frag_input_popup,container,false);
    }
}
