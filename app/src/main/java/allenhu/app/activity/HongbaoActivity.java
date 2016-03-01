package allenhu.app.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

import allenhu.app.R;
import allenhu.app.util.HongBaoAlgorithm;
import allenhu.app.util.LogUtil;

public class HongbaoActivity extends AppCompatActivity {

    private EditText edtYuan, edtCount;
    private Button btnShare;
    private TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hongbao);
        initView();


    }

    private void initView() {
        edtYuan = (EditText) findViewById(R.id.edt_yuan);
        edtCount = (EditText) findViewById(R.id.edt_count);
        btnShare = (Button) findViewById(R.id.btn_random);
        tvShow = (TextView) findViewById(R.id.tv_content);

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtYuan.getText()) || TextUtils.isEmpty(edtCount.getText())) {
                    tvShow.setText("金额或个数不能为空");
                    return;
                }
                int count = Integer.valueOf(edtCount.getText().toString());
                float yuan = Float.valueOf(edtYuan.getText().toString());
                int fen = (int) (yuan * 100);
                if (count > fen) {
                    tvShow.setText("单个红包不能低于0.01元");
                    tvShow.setTextColor(Color.RED);
                    return;
                }
                hongbao1(count, fen);
//                hongbao2(count, fen);
            }
        });
    }

    private void hongbao2(int count, int fen) {
        long[] result = HongBaoAlgorithm.generate(fen, count);
        long total = 0;
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            buffer.append(result[i] + "   ");
            total += result[i];
        }
        tvShow.setText(buffer.toString() + "   总额:" + total);
    }

    /**
     * 生成随机红包
     * @param count 红包个数
     * @param fen   红包金额（分）
     */
    private void hongbao1(int count, int fen) {
        int max;
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < count - 1; i++) {
            max = fen - (count - i - 1);
            int result = random.nextInt(max) + 1;
            buffer.append("   " + result);
            fen -= result;
        }

        buffer.append("   " + fen);
        tvShow.setText(buffer.toString());
    }
}
