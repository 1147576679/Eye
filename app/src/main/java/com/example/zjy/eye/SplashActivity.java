package com.example.zjy.eye;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.zjy.niklauslibrary.base.BaseActivity;

import butterknife.Bind;

public class SplashActivity extends BaseActivity {
    @Bind(R.id.iv_splash)
    ImageView imageView;
    private ScaleAnimation scaleAnimation;
    @Override
    public int getContentId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
//        SystemClock.sleep(1000);
        //属性动画用代码实现
        imageView.animate()
                .scaleXBy(1)
                .scaleX(1.2f)
                .scaleYBy(1)
                .scaleY(1.2f)
                .setDuration(2000)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        startActivity(new Intent(SplashActivity.this,MainActivity.class));
                        finish();
                    }
                });
//        scaleAnimation = new ScaleAnimation(1.0f,1.2f,1.0f,1.2f, Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
//        scaleAnimation.setDuration(3000);
//        scaleAnimation.setInterpolator(new LinearInterpolator());
//        scaleAnimation.setFillAfter(true);
//        imageView.startAnimation(scaleAnimation);
//        imageView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
////                imageView.clearAnimation();
//
//            }
//        },2000);

    }
}
