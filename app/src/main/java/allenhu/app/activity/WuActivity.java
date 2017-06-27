package allenhu.app.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import allenhu.app.R;
import allenhu.app.base.BaseActivity;
import allenhu.app.view.WuziqiPanel;

/**
 * 五子棋的界面
 */
public class WuActivity extends BaseActivity {

    private WuziqiPanel panel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wu;
    }

    @Override
    protected void onMCreate(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        panel = (WuziqiPanel) findViewById(R.id.wuzi);
        findViewById(R.id.btn_again).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (panel.ismIsGameOver()) {
                    panel.reStart();
                } else {

                    AlertDialog.Builder dialog = new AlertDialog.Builder(WuActivity.this);
                    dialog.setTitle("重来");
                    dialog.setMessage("重新来一局？");
                    dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            panel.reStart();
                        }
                    });
                    dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            }
        });
    }
}
