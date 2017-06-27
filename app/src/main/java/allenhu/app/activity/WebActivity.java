package allenhu.app.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import allenhu.app.R;
import allenhu.app.base.BaseActivity;
import allenhu.app.util.JavaScriptObject;

public class WebActivity extends BaseActivity {

    private WebView mWebView;
    private Button btnWeb;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void onMCreate(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        mWebView.loadUrl("file:///android_asset/web/FirstWeb/web/h5test.html");
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.web);
        btnWeb = (Button) findViewById(R.id.btn_to_web);
        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl("javascript:funFromjs()");
                Toast.makeText(WebActivity.this, "调用javascript:funFromjs()", Toast.LENGTH_LONG).show();
            }
        });
    }
}
