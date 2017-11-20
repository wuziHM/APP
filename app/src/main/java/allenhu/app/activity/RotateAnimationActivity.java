package allenhu.app.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.ImageView;

import allenhu.app.R;
import allenhu.app.activity.base.BaseActivity;

public class RotateAnimationActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rotate_animation;
    }

    @Override
    protected void onMCreate(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView imageView = (ImageView) findViewById(R.id.iv_rotate);
//        ObjectAnimator.ofArgb(imageView,"backgroundColor",-90,270);
        Animator anim1 = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
        anim1.setDuration(2000);
        anim1.start();
    }
}
