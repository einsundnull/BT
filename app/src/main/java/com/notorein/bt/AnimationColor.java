package com.notorein.bt;

import android.animation.Animator;
import android.animation.IntEvaluator;
import android.animation.ValueAnimator;

import androidx.constraintlayout.widget.ConstraintLayout;

public class AnimationColor {

    public AnimationColor(ConstraintLayout layout, int duration, int delay , int colorFrom, int colorTo) {


      final  ValueAnimator  colorAnimation = new ValueAnimator();
        colorAnimation.setEvaluator(new IntEvaluator());
        colorAnimation.setIntValues(colorFrom,colorTo);
        colorAnimation.setDuration(duration); // milliseconds
//        colorAnimation.setStartDelay(delay);


        colorAnimation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                layout.setBackgroundColor(colorFrom);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){


            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                layout.setBackgroundColor((int) colorAnimation.getAnimatedValue());
            }

        });



        colorAnimation.start();
    }
}
