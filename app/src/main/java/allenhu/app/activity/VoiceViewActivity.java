package allenhu.app.activity;

import android.os.Bundle;

import allenhu.app.R;
import allenhu.app.activity.base.BaseActivity;

public class VoiceViewActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_voice_view;
    }

    @Override
    protected void onMCreate(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_view);
    }
}
