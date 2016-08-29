package allenhu.app.activity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import allenhu.app.R;
import allenhu.app.base.BaseActivity;
import allenhu.app.util.LogUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimationActivity extends BaseActivity {

    @Bind(R.id.btn_rotate)
    Button btnRotate;
    @Bind(R.id.iv_home_down)
    ImageView ivHomeDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);
        playAnimator();

    }

    @OnClick({R.id.btn_rotate, R.id.iv_home_down})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_rotate:
                intent = new Intent(AnimationActivity.this, RotateAnimationActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_home_down:
                break;
        }
    }

    private void playAnimator() {
        ValueAnimator animator = ValueAnimator.ofFloat(0.1f, 1.0f);
        animator.setDuration(3000);
        animator.setRepeatCount(5);
        animator.setTarget(ivHomeDown);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                LogUtil.e("" + value);
            }
        });
        animator.start();
    }
}
