package allenhu.app;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.TypedValue;

import com.hlib.util.MLogUtil;

import allenhu.app.util.ToastUtils;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testUnit() {

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 10, getApplication().getResources().getDisplayMetrics());
        MLogUtil.e("px:" + left);

        left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getApplication().getResources().getDisplayMetrics());
        MLogUtil.e("dip:" + left);

        left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getApplication().getResources().getDisplayMetrics());
        MLogUtil.e("sp:" + left);
    }

    public void testX() {
        MLogUtil.e("可以测试么");
    }


    public void testX1() {
        ToastUtils.ToastMessage(getContext(),"哈哈哈");
        MLogUtil.e("我再试一下  操蛋啊");
    }
}