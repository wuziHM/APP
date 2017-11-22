package allenhu.app.activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hlib.util.MLogUtil;

import java.io.File;

import allenhu.app.R;
import allenhu.app.activity.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FlagDActivity extends BaseActivity {

    @BindView(R.id.btn_a)
    Button btnA;
    @BindView(R.id.btn_b)
    Button btnB;
    @BindView(R.id.btn_c)
    Button btnC;
    @BindView(R.id.btn_d)
    Button btnD;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.btn_operate)
    Button btnOperate;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_flag;
    }

    @Override
    protected void onMCreate(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        text.setText("D界面");
    }

    @OnClick({R.id.btn_a, R.id.btn_b, R.id.btn_c, R.id.btn_d, R.id.btn_operate})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_a:
                startActivity(new Intent(this, FlagAActivity.class));
                break;
            case R.id.btn_b:
                intent = new Intent(this, FlagBActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.btn_c:
                startActivity(new Intent(this, FlagCActivity.class));
                break;
            case R.id.btn_d:
                startActivity(new Intent(this, FlagDActivity.class));
                break;

            case R.id.btn_operate:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(
                        new File("/storage/emulated/0/DCIM/P60701-145610.jpg")));
                shareIntent.setType("image/*");
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(Intent.createChooser(shareIntent, "hello"));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Uri uri = data.getData();
            String path;
            if (!TextUtils.isEmpty(uri.getAuthority())) {
                Cursor cursor = getContentResolver().query(uri,
                        new String[]{MediaStore.Images.Media.DATA}, null, null, null);
                if (null == cursor) {
                    Toast.makeText(this, "图片没找到", Toast.LENGTH_SHORT).show();
                    return;
                }
                cursor.moveToFirst();
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                cursor.close();
            } else {
                path = uri.getPath();
            }
            MLogUtil.e("path:" + path);
        } else {
            Toast.makeText(this, "图片没找到", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
