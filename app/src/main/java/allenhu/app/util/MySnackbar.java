package allenhu.app.util;

import android.support.design.widget.Snackbar;
import android.view.View;

import allenhu.app.view.ganhuo.ColoredSnackbar;

/**
 * Created by maning on 16/1/18.
 * <p/>
 * 提示框
 */
public class MySnackbar {

    public static void makeSnackBarBlack(View view, String message) {

        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        ColoredSnackbar.defaultInfo(snackbar).show();
    }

    public static void makeSnackBarRed(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        ColoredSnackbar.alert(snackbar).show();
    }


}
