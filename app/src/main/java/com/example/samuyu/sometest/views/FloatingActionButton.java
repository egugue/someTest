package com.example.samuyu.sometest.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.samuyu.sometest.R;


/**
 * Created by toyamaosamuyu on 2014/12/24.
 */
public class FloatingActionButton extends CheckableFrameLayout{
    private static final String TAG = FloatingActionButton.class.getSimpleName();
    private ImageView mIconImageView;

    public FloatingActionButton(Context context) {
        this(context, null);
    }

    public FloatingActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupView(context);
    }

    /*
    @Override
    public void setChecked(boolean checked) {
        super.setChecked(checked);
        toggleIconWithAnimation();
    }

    @Override
    public void toggle() {
        super.toggle();
        toggleIconWithAnimation();
    }
    */

    private void setupView(Context context) {
        View rootView = View.inflate(context, R.layout.view_floating_action_button, null);
        mIconImageView = (ImageView) rootView.findViewById(R.id.my_selector_icon);
        addView(rootView);
    }

    public void toggleIconWithAnimation() {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(getOutAnimator(), getInAnimator());
        animatorSet.start();
    }

    private Animator getOutAnimator() {

        final int duration = 500;
        final int iconResId = isChecked()
                ? R.drawable.add_schedule_button_icon_checked
                : R.drawable.add_schedule_button_icon_unchecked;

        //一旦消すアニメーション
        Animator outAnimator = ObjectAnimator.ofFloat(mIconImageView, View.ALPHA, 0f);
        outAnimator.setDuration(duration/500);
        outAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mIconImageView.setImageResource(iconResId);
            }
        });

        return outAnimator;
    }

    private AnimatorSet getInAnimator() {

        final int duration = 500;

        //変更した画像をアニメーションを使って表示する
        AnimatorSet inAnimator = new AnimatorSet();
        inAnimator.setDuration(duration);
        inAnimator.playTogether(
                ObjectAnimator.ofFloat(mIconImageView, View.ALPHA, 1f),
                ObjectAnimator.ofFloat(mIconImageView, View.SCALE_X, 0f, 1f),
                ObjectAnimator.ofFloat(mIconImageView, View.SCALE_Y, 0f, 1f)
        );

        return inAnimator;
    }
}
