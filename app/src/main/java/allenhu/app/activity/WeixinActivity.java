package allenhu.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.hlib.util.MLogUtil;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import allenhu.app.R;
import allenhu.app.util.Constant;

public class WeixinActivity extends Activity {

    private ImageView ivChoose;
    private EditText edtText;
    private Button btnSub;
    private IWXAPI iwxapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weixin);
        iwxapi = WXAPIFactory.createWXAPI(this, Constant.APP_ID);
        initView();

    }

    private void initView() {
        ivChoose = (ImageView) findViewById(R.id.iv_choose_img);
        edtText = (EditText) findViewById(R.id.edt_text);
        btnSub = (Button) findViewById(R.id.btn_share);
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weiShare();
                btnSub.setVisibility(View.VISIBLE);
            }
        });

        ivChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, 100);
            }
        });

        edtText.setTypeface(Typeface.createFromAsset(getAssets(), "font/test.ttf"));
    }

    private void weiShare() {
        WXWebpageObject object = new WXWebpageObject();
        WXMediaMessage message = new WXMediaMessage(object);
        message.mediaObject = new WXImageObject(generateSpringCard());

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = message;
        req.scene = SendMessageToWX.Req.WXSceneTimeline;
        iwxapi.sendReq(req);
    }

    private Bitmap generateSpringCard() {
        //截图
        btnSub.setVisibility(View.INVISIBLE);
        View view = getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        return view.getDrawingCache();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 100) {
            if (data != null) {
                MLogUtil.e(data.getData().toString());
                Uri uri = data.getData();
                ivChoose.setImageURI(uri);
            }
        }
    }
}
