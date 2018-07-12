package allenhu.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestOptions;

import allenhu.app.R;
import allenhu.app.activity.base.BaseActivity;

public class HyperTranslateActivity extends BaseActivity {

    private final static String KEY_CONTENT = "content";
    private TextView mTextView;
    private ImageView imageView;
    String url ="http://i2.meizitu.net/2018/07/06a04.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hyper_translate);


        mTextView = (TextView) findViewById(R.id.text_view);
        imageView = (ImageView) findViewById(R.id.iv_image);

        mTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

//                ClipboardManager cm =(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
//                cm.setText(“要复制的内容”);
                return false;
            }
        });
        RequestOptions options = new RequestOptions();
        options.fitCenter();
        options.placeholder(R.mipmap.ic_beauty);
        options.diskCacheStrategy(DiskCacheStrategy.ALL);

        GlideUrl glideUrl = new GlideUrl(url, new LazyHeaders.Builder()
                .addHeader("referer", url)
                .addHeader("user-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36")
                .build()
        );

//        Picasso.with(this).load(url).placeholder(R.mipmap.ic_beauty).into(imageView);
        Glide.with(this)
                .asBitmap()
                .load(url)
                .thumbnail(0.2f)
                .apply(options)//
                .into(imageView);

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
        tryToShowContent(intent);
    }
}
