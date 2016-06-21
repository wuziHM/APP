package allenhu.app.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import allenhu.app.R;
import allenhu.app.widget.ScalePopup;
import razerdp.basepopup.BasePopupWindow;

/**
 * Created by 大灯泡 on 2016/1/15.
 */
public class ScalePopupFrag extends SimpleBaseFrag {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void bindEvent() {

    }

    @Override
    public BasePopupWindow getPopup() {
        return new ScalePopup(mContext);
    }

    @Override
    public Button getButton() {
        return (Button) mFragment.findViewById(R.id.popup_show);
    }

    @Override
    public View getFragment() {
        return mInflater.inflate(R.layout.frag_scale_popup, container, false);
    }
}