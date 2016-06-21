package allenhu.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import allenhu.app.R;
import allenhu.app.base.BaseActivity;
import allenhu.app.service.ListenClipboardService;
import allenhu.app.util.Utils;

public class HyperTranslateActivity extends BaseActivity {

    private final static String KEY_CONTENT = "content";
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hyper_translate);


        mTextView = (TextView) findViewById(R.id.text_view);

//        Intent intent = getIntent();
//        Utils.printIntent("MainActivity::onCreate()", intent);
//
//        tryToShowContent(intent);
//        ListenClipboardService.start(this);
    }


    public static void startForContent(Context context, String content) {
        Intent intent = new Intent(context, HyperTranslateActivity.class);
        intent.putExtra(KEY_CONTENT, content);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(intent);
    }

    private void tryToShowContent(Intent intent) {
        String content = intent.getStringExtra(KEY_CONTENT);
        if (!TextUtils.isEmpty(content)) {
            mTextView.setText(content);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Utils.printIntent("MainActivity::onNewIntent()", intent);

        tryToShowContent(intent);
    }
}
