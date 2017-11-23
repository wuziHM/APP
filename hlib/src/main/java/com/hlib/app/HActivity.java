package com.hlib.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.hlib.R;
import com.hlib.app.SwipeBack.SwipeBackActivity;
import com.hlib.app.SwipeBack.SwipeBackLayout;
import com.hlib.util.MCommonUtil;

/**
 * Author：燕青 $ on 17/6/24 18:06
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public abstract class HActivity extends SwipeBackActivity implements HActivityAble {


    private static final String TAG = HActivity.class.getSimpleName();
    private Context context;
    private SwipeBackLayout swipeBackLayout;
//    private RelativeLayout mRootView;
//    protected MHeaderViewAble mHeaderViewAble;


    /**
     * 获取初始化Layout id
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 对应系统onCreate
     *
     * @param savedInstanceState
     */
    protected void onMCreate(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        context = this;
        setContentView(getLayoutId());

        initSwipeBackLayout();
//        initHeaderView();
        onMCreate(savedInstanceState);
    }


    public Context getMContext() {
        return context;
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, null, false);
        setContentView(view);
    }

    @Override
    public void setContentView(View view) {
//        CoordinatorLayout coordinatorLayout = null;
//        try {
//            coordinatorLayout = (CoordinatorLayout) LayoutInflater.from(this).inflate(R.layout.m_root_view, null, false);
//            if (coordinatorLayout == null || null == view) {
//                return;
//            }
//            mRootView = (RelativeLayout) coordinatorLayout.findViewById(R.id.mRootView);
//            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//            view.setLayoutParams(params);
//
//            mRootView.addView(view);
//        } catch (Exception e) {
//            Log.e(TAG, "setContentView is fault");
//            e.printStackTrace();
//        }

        super.setContentView(view);
    }


    private void initSwipeBackLayout() {
        swipeBackLayout = getSwipeBackLayout();
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        swipeBackLayout.addSwipeListener(new SwipeBackLayout.SwipeListener() {
            @Override
            public void onScrollStateChange(int state, float scrollPercent) {

            }

            @Override
            public void onEdgeTouch(int edgeFlag) {
                MCommonUtil.vibrate(getBaseContext(), 20);
            }

            @Override
            public void onScrollOverThreshold() {
                MCommonUtil.vibrate(getBaseContext(), 20);
            }
        });
    }


//    public void disableSwipeBack(boolean isDisable) {
//        swipeBackLayout.setEnableGesture(!isDisable);
//    }


//    public void initHeaderView() {
//        mHeaderViewAble = new MHeaderView(this, getMRootView());
//    }


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(getActivityEnterAnim(true), getActivityEnterAnim(false));
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        overridePendingTransition(getActivityEnterAnim(true), getActivityEnterAnim(false));
    }


    private int getActivityEnterAnim(boolean isEnter) {
        int animation;
        if (isEnter) {
            animation = R.anim.slide_right_in;

        } else {
            animation = R.anim.slide_left_out;
        }

        return animation;
    }

    //再按一次退出程序
    protected long lastTime = 0;
    protected int exitTime = 2000;

    protected void repeatBackKeyExit() {
        if ((System.currentTimeMillis() - lastTime) < exitTime) {
            finish();
        } else {
            lastTime = System.currentTimeMillis();
            Toast.makeText(getApplicationContext(), "再按一次退出", Toast.LENGTH_SHORT).show();
        }
    }


//    public MHeaderViewAble getHeaderViewAble() {
//        return mHeaderViewAble;
//    }
//
//
//    public RelativeLayout getMRootView() {
//        return mRootView;
//    }

    public static void finishActivity(Context context) {
        ((Activity) context).finish();
    }

}
