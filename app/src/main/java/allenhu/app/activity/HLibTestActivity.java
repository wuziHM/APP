package allenhu.app.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.hlib.http.request.MHttpResponseImpl;

import allenhu.app.R;
import allenhu.app.base.BaseActivity;
import allenhu.app.net.TestAPI;
import butterknife.Bind;
import butterknife.ButterKnife;


public class HLibTestActivity extends BaseActivity {

    @Bind(R.id.textView)
    TextView textView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hlib_test;
    }

    @Override
    protected void onMCreate(Bundle savedInstanceState) {
        TestAPI api = new TestAPI(this);
        api.testServer(new MHttpResponseImpl() {
            @Override
            public void onSuccessResult(int statusCode, Object object) {
                if (object instanceof String) {
                    textView.setText((String) object);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);


    }
}
