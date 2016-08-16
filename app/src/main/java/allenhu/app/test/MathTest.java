package allenhu.app.test;

import android.test.AndroidTestCase;
import android.util.Log;

/**
 * Author：燕青 $ on 2016/7/12  15:44
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class MathTest extends AndroidTestCase {
    protected int i1;
    protected int i2;
    static final String LOG_TAG = "MathTest";

    public void setUp() {
        i1 = 2;
        i2 = 3;
    }

    public void testAdd() {
        Log.e(LOG_TAG, "testAdd");
        assertTrue(LOG_TAG + "1", ((i1 + i2) == 5));
    }

    public void testAndroidTestCaseSetupProperly() {
        super.testAndroidTestCaseSetupProperly();
        Log.e(LOG_TAG, "testAndroidTestCaseSetupProperly");
    }
}