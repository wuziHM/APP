package allenhu.app.activity;

import android.content.res.AssetManager;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import allenhu.app.R;
import allenhu.app.base.BaseActivity;
import allenhu.app.bean.PBean;
import allenhu.app.bean.ProvinceBean;
import allenhu.app.util.LogUtil;
import allenhu.app.util.ToastUtils;

public class CityPickerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_picker);
        TextView textView = (TextView) findViewById(R.id.tv_test);



    }
}
