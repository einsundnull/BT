package com.notorein.bt;

import static com.notorein.bt.SessionParameters.darkModeTraining;
import static com.notorein.bt.SessionParameters.fadeoutAnimationDuration;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class TransitionActivityAToB {
    static TextView textView;

    public static void setTransitionToBlack(Context c, ConstraintLayout layout, int colorFrom, int colorTo ,boolean reverse) {
        if (darkModeTraining) {
//            int colorFrom = c.getResources().getColor(R.color.menu_background_color);
//            int colorTo = c.getResources().getColor(R.color.black);
            textView = new TextView(c);
            textView.setWidth(layout.getWidth());
            textView.setHeight(layout.getHeight());
            try {
                layout.removeView(textView);
            } catch (Exception e){

            }
            layout.addView(textView);
            if(reverse){
                textView.setBackgroundColor(colorFrom);
            } else {
                textView.setBackgroundColor(colorTo);
            }

            setFadeInAnimationTextView(c, layout, textView);
        }
    }

    private static void setFadeInAnimationTextView(Context c, ConstraintLayout layout, View v) {
        Animation mLoadAnimation = AnimationUtils.loadAnimation(c.getApplicationContext(), android.R.anim.fade_in);
        mLoadAnimation.setDuration(fadeoutAnimationDuration);
        mLoadAnimation.setStartOffset(0);
        mLoadAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {

            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationStart(Animation arg0) {
            }
        });

        v.startAnimation(mLoadAnimation);
    }
}
