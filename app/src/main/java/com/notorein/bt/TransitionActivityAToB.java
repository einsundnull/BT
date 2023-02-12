package com.notorein.bt;

import static com.notorein.bt.SessionParameters.darkModeTraining;
import static com.notorein.bt.SessionParameters.fadeoutAnimationDuration;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class TransitionActivityAToB {
    private TextView textView;
    private Animation mLoadAnimation;
    private View[] v;
    private Context c;
    private Activity activity;
    private ConstraintLayout layout;
    private long delay;
    private long duration;
    private Animation mLoadAnimationIn;

//    public static void setTransitionToBlack(Context c, ConstraintLayout layout, int colorFrom, int colorTo, boolean reverse) {
//        if (darkModeTraining) {
////            int colorFrom = c.getResources().getColor(R.color.menu_background_color);
////            int colorTo = c.getResources().getColor(R.color.black);
//            textView = new TextView(c);
//            textView.setWidth(layout.getWidth());
//            textView.setHeight(layout.getHeight());
//            try {
//                layout.removeView(textView);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            layout.addView(textView);
//            if (reverse) {
//                textView.setBackgroundColor(colorFrom);
//            } else {
//                textView.setBackgroundColor(colorTo);
//            }
//            setFadeInAnimationTextView(c, layout, textView);
//        }
//    }

    private final void setTransitionToBlack(int colorFrom, int colorTo) {
        if (darkModeTraining) {
            textView = new TextView(c);
            textView.setWidth(layout.getWidth());
            textView.setHeight(layout.getHeight());
            try {
                layout.removeView(textView);
            } catch (Exception e) {
                e.printStackTrace();
            }
            layout.addView(textView);
            textView.setBackgroundColor(colorTo);
            setFadeInAnimationTextView(c, layout, textView);
        }
    }


    private void setFadeInAnimationTextView(Context c, ConstraintLayout layout, View v) {
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

    public void setTransitionAToB(Context c, Activity activity, ConstraintLayout layout, long duration, long delay, Runnable onStart, Runnable onEnd, View... v) {
        // This is the transition from ActivityMenu to ActivityTraining
        this.v = v;
        this.c = c;
        this.activity = activity;
        this.layout = layout;
        this.duration = duration;
        this.delay = delay;

        mLoadAnimation = AnimationUtils.loadAnimation(c.getApplicationContext(), android.R.anim.fade_out);
        mLoadAnimation.setDuration(duration);
        mLoadAnimation.setStartOffset(delay);
        mLoadAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                for (int i = 0; i < v.length; i++) {
                    v[i].setAlpha(0);
                }
                if (onEnd != null) {
                    onEnd.run();
                }


            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationStart(Animation arg0) {
                if (onStart != null) {
                    onStart.run();
                }
//                FileLogicSettings.saveSettings(activity);
            }
        });
//        for (View i : v) {
//            i.startAnimation(mLoadAnimation);
//        }

    }

    public void startAnimation() {
        mLoadAnimation.start();
        for (View i : v) {
            i.startAnimation(mLoadAnimation);
        }
    }

    public void startAnimation(int colorFrom, int colorTo) {
        mLoadAnimation.start();
        setTransitionToBlack(colorFrom, colorTo);
        for (View i : v) {
            i.startAnimation(mLoadAnimation);
        }
    }

    public void stopAnimation() {
        mLoadAnimation.reset();
    }

//    public void setFadeInAnimationFirst(Runnable onStart, Runnable onEnd) {
//        mLoadAnimation = AnimationUtils.loadAnimation(c, android.R.anim.fade_in);
//        mLoadAnimation.setDuration(duration);
//        mLoadAnimation.setStartOffset(delay);
//        mLoadAnimation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationEnd(Animation arg0) {
//                if(onEnd != null){
//
//                }
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation arg0) {
//            }
//
//            @Override
//            public void onAnimationStart(Animation arg0) {
//                if(onStart != null){
//
//                }
//            }
//        });
//        for (View i : v) {
//            i.startAnimation(mLoadAnimation);
//        }
//    }

    public void setFadeInAnimationFirst(Runnable onStart, Runnable onEnd) {
        mLoadAnimationIn = AnimationUtils.loadAnimation(c, android.R.anim.fade_in);
        mLoadAnimationIn.setDuration(duration);
        mLoadAnimationIn.setStartOffset(delay);
        mLoadAnimationIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                if(onEnd != null){

                }
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationStart(Animation arg0) {
                if(onStart != null){

                }
            }
        });
        for (View i : v) {
            i.startAnimation(mLoadAnimationIn);
        }
    }
    public void setFadeInAnimationFirst() {
        mLoadAnimationIn = AnimationUtils.loadAnimation(c, android.R.anim.fade_in);
        mLoadAnimationIn.setDuration(duration);
        mLoadAnimationIn.setStartOffset(delay);
        mLoadAnimationIn.setAnimationListener(new Animation.AnimationListener() {
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

    }

    public void startAnimationIn() {
        for (View i : v) {
            i.startAnimation(mLoadAnimationIn);
        }
    }
}
