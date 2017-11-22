package allenhu.app;

import org.junit.Test;

import allenhu.app.util.FileUtils;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the TestUtil Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void fileTest() throws Exception {

//        File file = Environment.getRootDirectory();
//        File[] files = file.listFiles();
//        for (File f : files) {
//            LogUtil.e(f.getAbsolutePath() + "       " + f.getName());
//        }


        assertEquals(true,
                FileUtils.isFileExist("/system"));
    }
}