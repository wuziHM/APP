package allenhu.app.fragment;

import android.view.View;
import android.widget.Button;

import allenhu.app.R;
import allenhu.app.widget.popup.MenuPopup;
import razerdp.basepopup.BasePopupWindow;

/**
 * Created by 大灯泡 on 2016/1/22.
 * menu
 */
public class MenuPopupFrag extends SimpleBaseFrag{
    private Button mButton;
    private MenuPopup mMenuPopup;
    @Override
    public void bindEvent() {
        mButton= (Button) mFragment.findViewById(R.id.popup_show);
        mMenuPopup=new MenuPopup(mContext);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMenuPopup.showPopupWindow(v);
            }
        });
    }

    @Override
    public BasePopupWindow getPopup() {
        return null;
    }

    @Override
    public Button getButton() {
        return null;
    }

    @Override
    public View getFragment() {
        return mInflater.inflate(R.layout.frag_menu_popup,container,false);
    }
}
