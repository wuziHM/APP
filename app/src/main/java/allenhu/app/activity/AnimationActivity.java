package allenhu.app.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import allenhu.app.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimationActivity extends Activity {

    @Bind(R.id.btn_rotate)
    Button btnRotate;
    @Bind(R.id.iv_home_down)
    ImageView ivHomeDown;
    private ObjectAnimator animator;
    private ObjectAnimator animator1;
    private AnimatorSet set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);
//        playAnimator();

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

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
//        playAnimator();
        handler.sendEmptyMessage(1);
    }

    private void playAnimator() {
        if (animator == null) {
            animator = ObjectAnimator.ofFloat(ivHomeDown, "translationY", -ivHomeDown.getHeight(), ivHomeDown.getHeight());
            animator.setDuration(1200);
            animator.setInterpolator(new LinearInterpolator());
        }
        if (animator1 == null) {
            animator1 = ObjectAnimator.ofFloat(ivHomeDown, "alpha", 1.0f, 0.6f);
            animator.setDuration(1200);
            animator.setInterpolator(new LinearInterpolator());
        }
        if (set == null) {
            set = new AnimatorSet();
            set.playTogether(animator, animator1);
        }
        set.start();
        handler.sendEmptyMessageDelayed(1, 1400);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                playAnimator();
            }
        }
    };
}
