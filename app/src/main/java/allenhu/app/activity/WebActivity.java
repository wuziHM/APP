package allenhu.app.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import allenhu.app.R;
import allenhu.app.activity.base.BaseActivity;
import allenhu.app.util.JavaScriptObject;

public class WebActivity extends BaseActivity {

    private WebView mWebView;
    private Button btnWeb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
        //设置编码
        mWebView.getSettings().setDefaultTextEncodingName("utf-8");
        //支持js
        mWebView.getSettings().setJavaScriptEnabled(true);
        //设置背景颜色 透明
        mWebView.setBackgroundColor(Color.argb(0, 0, 0, 0));
        //设置本地调用对象及其接口
        mWebView.addJavascriptInterface(new JavaScriptObject(this), "myObj");
        //载入js
//        mWebView.loadUrl("http://www.hao123.com");
//        mWebView.loadUrl("http://app-health.daanlab.com/view/home/index.html");
        mWebView.loadUrl("file:///android_asset/web/FirstWeb/index.html");
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.web);
        btnWeb = (Button) findViewById(R.id.btn_to_web);


        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl("javascript:funFromjs(\"从安卓里面传过去的文字，你让我说点什么好呢？\")");
                Toast.makeText(WebActivity.this, "调用javascript:funFromjs(content)", Toast.LENGTH_LONG).show();
            }
        });

        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }


        });


//        mWebView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Logger.d("y:" + mWebView.getScrollY());
//                return false;
//            }
//        });
    }
}
