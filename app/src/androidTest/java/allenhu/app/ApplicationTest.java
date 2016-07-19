package allenhu.app;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.TypedValue;

import allenhu.app.util.LogUtil;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testUnit() {

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 10, getApplication().getResources().getDisplayMetrics());
        LogUtil.e("px:" + left);

        left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getApplication().getResources().getDisplayMetrics());
        LogUtil.e("dip:" + left);

        left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getApplication().getResources().getDisplayMetrics());
        LogUtil.e("sp:" + left);
    }

    public void testX() {
        LogUtil.e("可以测试么");
    }
}