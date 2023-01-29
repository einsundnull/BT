package com.notorein.bt;

import static com.notorein.bt.ResultsFiles.initialiseStoringFilePaths;
import static com.notorein.bt.SessionParameters.convertedTrialToTime;
import static com.notorein.bt.SessionParameters.countDownInterval;
import static com.notorein.bt.SessionParameters.darkModeTraining;
import static com.notorein.bt.SessionParameters.dateOfCurrentUse;
import static com.notorein.bt.SessionParameters.daySession;
import static com.notorein.bt.SessionParameters.duration;
import static com.notorein.bt.SessionParameters.fadeoutAnimationDuration;
import static com.notorein.bt.SessionParameters.firstStart;
import static com.notorein.bt.SessionParameters.includeAudio;
import static com.notorein.bt.SessionParameters.includeColor;
import static com.notorein.bt.SessionParameters.includePosition;
import static com.notorein.bt.SessionParameters.includedModes;
import static com.notorein.bt.SessionParameters.nBack;
import static com.notorein.bt.SessionParameters.nBackBegin;
import static com.notorein.bt.SessionParameters.orientation;
import static com.notorein.bt.SessionParameters.paused;
import static com.notorein.bt.SessionParameters.sessionWasCanceledEarly;
import static com.notorein.bt.SessionParameters.stringToStore;
import static com.notorein.bt.SessionParameters.stringToStoreInitial;
import static com.notorein.bt.SessionParameters.trialsMax;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;



public class ActivityMenu extends AppCompatActivity implements View.OnClickListener {

    private Button btnStart;
    private Button btnResults;
    private Button btnSave;

    private Button btn_manual;
    private Button btn_about;
    private Button btn_exit;

    private EditText editTextNBackLevel;
    private EditText editTextDuration;

    private TextView textViewInfo;
    private TextView textViewTime;

    private Switch switchPosition;
    private Switch switchAudio;
    private Switch switchColor;
    private boolean allowToStartSession = true;
    private boolean startWithoutModeHasBeenReseted = true;

    private RadioButton btnLand;
    private RadioButton btnPort;
    private int testCounter = 0;
    private AlertDialog.Builder builder;
    private ConstraintLayout layout;
    private Runnable fadeAction;
    private Animation fadeOut;
    private TextView hintTextViewDuration;
    private TextView hintTextViewNBack;
    private View dividerMenu1;
    private View dividerMenu2;
    private View dividerMenu3;
    private View[] views;
    private ImageView nouImage;
    private SoundPool appSounds;
    private int buttonSound;
    private int dingSound;
    private View decorView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(7);
        decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_menu_layout);
        FileLogicSettings.readSettings(ActivityMenu.this);
        getViews();
        setImageNou();
        setSounds();
        setFadeInAnimationAndDingSound();
        setOnClickListeners();
        setTextToInput();
        convertedTrialToTimeMethod();
        setSwitchesInPosition();
        getDisplaySize();
        btnStart.setEnabled(true);
        btnSave.setEnabled(!sessionWasCanceledEarly);
        if (!sessionWasCanceledEarly) {
            btnSave.setTextColor(getResources().getColor(R.color.button_save_enabled));
        }

        initialiseStoringFilePaths();
        ResultsFiles.readResults(ActivityMenu.this);
        daySession = RepeatStorage.getDay();
//        ResultsFiles.checkForLastDayOfUse();
        new ResultsFiles().calculateResultsForDisplay();
    }


    private void getDisplaySize() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        SessionParameters.displayWidth = size.x;
        SessionParameters.displayHeight = size.y;
    }

    private void setSounds() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setUsage(AudioAttributes.USAGE_GAME).build();
            appSounds = new SoundPool.Builder().setMaxStreams(4).setAudioAttributes(attributes).build();

        } else {
            appSounds = new SoundPool(2, AudioAttributes.CONTENT_TYPE_MUSIC, 0);
        }
        buttonSound = appSounds.load(this, R.raw.click, 1);
        dingSound = appSounds.load(this,R.raw.ding,1);

    }


    private void setImageNou() {
        nouImage.setBackgroundResource(R.drawable.genkou);
    }

    private void setSwitchesInPosition() {
        switchPosition.setChecked(includePosition);
        switchAudio.setChecked(includeAudio);
        switchColor.setChecked(includeColor);
        if (orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            btnPort.setChecked(false);
            btnLand.setChecked(true);
        } else {
            btnPort.setChecked(true);
            btnLand.setChecked(false);
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
    }

    private void setTextToInput() {
        nBack = nBackBegin;
        editTextNBackLevel.setText("" + nBackBegin);
        editTextDuration.setText("" + duration);
    }

    private void getViews() {

        layout = (ConstraintLayout)findViewById(R.id.menu_layout);
        nouImage = (ImageView) findViewById(R.id.nouImage);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnResults = (Button) findViewById(R.id.btnResults);
        btnSave = (Button) findViewById(R.id.btnSave);

        hintTextViewNBack = (TextView) findViewById(R.id.hintTextViewNBack);
        hintTextViewDuration = (TextView) findViewById(R.id.hintTextViewDuration);

        dividerMenu1 = (View) findViewById(R.id.dividerMenu1);
        dividerMenu2 = (View) findViewById(R.id.dividerMenu2);
        dividerMenu3 = (View) findViewById(R.id.dividerMenu3);

        editTextNBackLevel = (EditText) findViewById(R.id.editTextNBackLevel);
        editTextDuration = (EditText) findViewById(R.id.editTextDuration);

        textViewTime = (TextView) findViewById(R.id.textViewTime);
        textViewInfo = (TextView) findViewById(R.id.infoTextView);

        switchPosition = (Switch) findViewById(R.id.switchPosition);
        switchAudio = (Switch) findViewById(R.id.switchAudio);
        switchColor = (Switch) findViewById(R.id.switchColor);

        btnPort = (RadioButton) findViewById(R.id.radioButtonPort);
        btnLand = (RadioButton) findViewById(R.id.radioButtonLand);

        views = new View[]{btnStart, btnResults, btnSave, hintTextViewNBack, hintTextViewDuration,
                dividerMenu1, dividerMenu2, dividerMenu3,
                editTextNBackLevel, editTextDuration,
                textViewTime, textViewInfo,
                switchPosition, switchAudio, switchColor,
                btnPort, btnLand
        };
    }

    private void setOnClickListeners() {
        btnStart.setOnClickListener(this);
        btnResults.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        textViewInfo.setOnClickListener(this);
        switchPosition.setOnClickListener(this);
        switchAudio.setOnClickListener(this);
        switchColor.setOnClickListener(this);
        btnLand.setOnClickListener(this);
        btnPort.setOnClickListener(this);
        editTextDuration.setOnClickListener(this);
        editTextNBackLevel.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnStart) {
            startButtonLogic();
            appSounds.play(buttonSound, 1, 1, 1, 0, 1);
        }

        if (v.getId() == R.id.btnResults) {
            appSounds.play(buttonSound, 1, 1, 1, 0, 1);
            stringToStore = stringToStoreInitial + stringToStore;
            Intent intent = new Intent(this, ActivityResults.class);
            startActivity(intent);
        }

        if (v.getId() == R.id.btnSave) {
            appSounds.play(buttonSound, 1, 1, 1, 0, 1);
            btnSave.setEnabled(false);
            btnSave.setTextColor(getResources().getColor(R.color.button_save_disabled));
            ResultsFiles.saveResults(ActivityMenu.this);
        }

        if (v.getId() == R.id.infoTextView) {
            showAboutDialog();
//            final MediaPlayer player = MediaPlayer.create(this, R.raw.click);
//            player.start();
            appSounds.play(buttonSound, 1, 1, 1, 0, 1);
        }

        if (v.getId() == R.id.editTextDuration) {
            preventEmptyEditTextOrZero();
            convertedTrialToTimeMethod();
        }

        if (v.getId() == R.id.editTextNBackLevel) {
            preventEmptyEditTextOrZero();
            convertedTrialToTimeMethod();
        }

        if (v.getId() == R.id.switchPosition) {
            appSounds.play(buttonSound, 1, 1, 1, 0, 1);
            switchPositionLogic();
        }

        if (v.getId() == R.id.switchAudio) {
            appSounds.play(buttonSound, 1, 1, 1, 0, 1);
            switchAudioLogic();
        }

        if (v.getId() == R.id.switchColor) {
            appSounds.play(buttonSound, 1, 1, 1, 0, 1);
            switchColorLogic();
        }

        if (v.getId() == R.id.radioButtonLand) {
            appSounds.play(buttonSound, 1, 1, 1, 0, 1);
            orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        }

        if (v.getId() == R.id.radioButtonPort) {
            appSounds.play(buttonSound, 1, 1, 1, 0, 1);
            orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        }


    }

    private void switchPositionLogic() {
        if (!btnSave.isEnabled()) {
            includedModes++;
            includePosition = !includePosition;
            stringToStoreInitial = ResultsFiles.readResults(ActivityMenu.this);
            btnSave.setEnabled(false);
            btnSave.setTextColor(getResources().getColor(R.color.button_save_disabled));
            stringToStore = "";
        } else {
            showAlertPleaseSaveResults(() -> {
                btnSave.setEnabled(false);
                btnSave.setTextColor(getResources().getColor(R.color.button_save_disabled));
                switchPositionLogic();
            }, () -> {
                FileLogicSettings.readSettings(ActivityMenu.this);
                setSwitchesInPosition();
            });
        }
    }

    private void switchAudioLogic() {
        if (!btnSave.isEnabled()) {
            includedModes++;
            includeAudio = !includeAudio;
            stringToStoreInitial = ResultsFiles.readResults(ActivityMenu.this);
            btnSave.setEnabled(false);
            btnSave.setTextColor(getResources().getColor(R.color.button_save_disabled));
            stringToStore = "";
        } else {
            showAlertPleaseSaveResults(() -> {
                btnSave.setEnabled(false);
                btnSave.setTextColor(getResources().getColor(R.color.button_save_disabled));
                switchAudioLogic();
            }, () -> {
                FileLogicSettings.readSettings(ActivityMenu.this);
                setSwitchesInPosition();
            });
        }
    }

    private void switchColorLogic() {
        if (!btnSave.isEnabled()) {
            includedModes++;
            includeColor = !includeColor;
            stringToStoreInitial = ResultsFiles.readResults(ActivityMenu.this);
            btnSave.setEnabled(false);
            btnSave.setTextColor(getResources().getColor(R.color.button_save_disabled));
            stringToStore = "";
        } else {
            showAlertPleaseSaveResults(() -> {
                btnSave.setEnabled(false);
                btnSave.setTextColor(getResources().getColor(R.color.button_save_disabled));
                switchColorLogic();
            }, () -> {
                FileLogicSettings.readSettings(ActivityMenu.this);
                setSwitchesInPosition();
            });
        }
    }


    private void startButtonLogic() {
        if (!btnSave.isEnabled()) {
            preventStartWithoutMode();
            if (allowToStartSession) {
                paused = true;
                btnStart.setEnabled(false);
                btnResults.setEnabled(false);
                btnSave.setEnabled(false);
                try {
                    nBackBegin = Integer.parseInt(editTextNBackLevel.getText().toString());
                    nBack = nBackBegin;
                    duration = Integer.parseInt(editTextDuration.getText().toString());
                    if (duration < 1) {
                        duration = 1;
                    }
                    trialsMax = duration;
                    if (trialsMax < 1) {
                        trialsMax = 1;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                dateOfCurrentUse = RepeatStorage.getDay();
                setFadeOutAnimation(fadeoutAnimationDuration, 0, views);
                setTransitionToBlack();
            } else {
                resetStartWithoutMode();
                setTextToInput();
                setSwitchesInPosition();
            }
        } else {
            showAlertPleaseSaveResults(() -> {
                btnSave.setEnabled(false);
                btnSave.setTextColor(getResources().getColor(R.color.button_save_disabled));
                startButtonLogic();
                stringToStoreInitial = ResultsFiles.readResults(ActivityMenu.this);
                stringToStore = "";
            }, null);
        }
    }

    private void setTransitionToBlack() {
        if(darkModeTraining) {
            TransitionActivityAToB transitionActivityAToB = new TransitionActivityAToB();
            int colorFrom = getResources().getColor(R.color.menu_background_color);
            int colorTo = getResources().getColor(R.color.black);
            transitionActivityAToB.setTransitionToBlack(this,layout,colorFrom,colorTo, false);
        }
    }

    private void setFadeInAnimationTextView(View v) {
        Animation mLoadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
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

    private void setFadeInAnimationAndDingSound() {
        if (firstStart) {
            firstStart = false;
            for (View i : views) {
                i.setAlpha(0);
            }

            setFadeOutAnimationLogo(1000, 2500, nouImage);
            CountDownTimer tmr = new CountDownTimer(80, 20) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    appSounds.play(dingSound, 1, 1, 1, 0, 1);
                }
            };
            tmr.start();
        } else {
            nouImage.setVisibility(View.INVISIBLE);
            setFadeInAnimationAndDingSound(1800, 50, views);
        }
    }


    private void setFadeOutAnimationLogo(long duration, long delay, View... v) {

        Animation mLoadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_out);
        Animation fadeOut = new AlphaAnimation(1f, 0f);
        mLoadAnimation.setInterpolator(new AccelerateInterpolator()); //and this
        mLoadAnimation.setDuration(duration);
        mLoadAnimation.setStartOffset(delay);
        mLoadAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
//                main_menu.setAlpha(0);
                // This three lines set the image invisible after fade_out
                for (View i : v) {
                    i.setAlpha(0f);
                }
                // Here the delay sets the time when the image disappeared and the buttons start to appear.
                setFadeInAnimationFirst(1200, 0, views);
                for (View i : views) {
                    i.setAlpha(1f);
                }
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationStart(Animation arg0) {
            }
        });
        for (View i : v) {
            i.startAnimation(mLoadAnimation);
        }

//        main_menu.startAnimation(mLoadAnimation);
    }

    private void setFadeOutAnimation(long duration, long delay, View... v) {
        // This is the transiton from ActivityMenu to ActivityTraining

        Animation mLoadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_out);
        mLoadAnimation.setDuration(duration);
        mLoadAnimation.setStartOffset(delay);
        mLoadAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                for (int i = 0; i < v.length; i++) {
                    v[i].setAlpha(0);
                }
                FileLogicSettings.saveSettings(ActivityMenu.this);
                Intent intent = new Intent(ActivityMenu.this, ActivityTraining.class);
//                try {
//                    Thread.sleep(300);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationStart(Animation arg0) {
            }
        });
        for (View i : v) {
            i.startAnimation(mLoadAnimation);
        }

    }



    private void setFadeInAnimationFirst(long duration, long delay, View... v) {
        Animation mLoadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
        mLoadAnimation.setDuration(duration);
        mLoadAnimation.setStartOffset(delay);
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
        for (View i : v) {
            i.startAnimation(mLoadAnimation);
        }

//        main_menu.startAnimation(mLoadAnimation);
    }

    public void setFadeInAnimationAndDingSound(long duration, long delay, View... v) {
        Animation mLoadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
        mLoadAnimation.setDuration(duration);
        mLoadAnimation.setStartOffset(delay);
        for (View i : v) {
            i.startAnimation(mLoadAnimation);
        }
    }


    private void preventEmptyEditTextOrZero() {
        try {
            int i = Integer.parseInt(editTextDuration.getText().toString());
            if (i < 1) {
                i = 1;
                editTextDuration.setText("" + i);
            }
        } catch (Exception e) {
            editTextDuration.setText("1");
        }
        try {
            int i = Integer.parseInt(editTextNBackLevel.getText().toString());
            if (i < 1) {
                i = 1;
                editTextNBackLevel.setText("" + i);
            }
        } catch (Exception e) {
            editTextNBackLevel.setText("1");
        }
    }


    private void convertedTrialToTimeMethod() {
        double duration = Double.parseDouble(editTextDuration.getText().toString());
        double temp = duration * ((2 * countDownInterval) / 100000) * 60;
        convertedTrialToTime = (int) Math.round(temp);
        if (convertedTrialToTime <= 1) {
            convertedTrialToTime = 2;
        }
        textViewTime.setText(String.format(Strings.textViewTimeAdd_I + "%d - %d" + Strings.textViewTimeAdd_II, convertedTrialToTime, convertedTrialToTime + 1));
    }

    private void preventStartWithoutMode() {
        allowToStartSession = true;
        includedModes = 0;
        if (!includePosition && !includeAudio && !includeColor) {
            allowToStartSession = false;
        }
        if (includePosition) {
            includedModes++;
        }
        if (includeAudio) {
            includedModes++;
        }
        if (includeColor) {
            includedModes++;
        }
    }

    private void resetStartWithoutMode() {
        includePosition = true;
        includeAudio = true;
        includeColor = false;
    }

    private void showAboutDialog() {

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.view_menu_about);
        btn_manual = (Button) dialog.findViewById(R.id.btn_manual);
        btn_about = (Button) dialog.findViewById(R.id.btn_about);
        btn_exit = (Button) dialog.findViewById(R.id.btn_exit);
        btn_manual.setOnClickListener(c -> {

        });
        btn_about.setOnClickListener(c -> {
            Intent intent = new Intent(ActivityMenu.this, ActivityAbout.class);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            startActivity(intent);

        });
        btn_manual.setOnClickListener(c -> {
//            initialiseStoringFilePaths();
//            ViewMenu.testFile(this);
        });
        btn_exit.setOnClickListener(c -> {
            if (!btnSave.isEnabled()) {
                this.finish();
                finish();
            } else {
                showAlertPleaseSaveResults(() -> {
                    this.finish();
                    finish();
                }, null);
            }
        });
        dialog.show();
    }

    private void showAlertPleaseSaveResults(Runnable runContinue, Runnable runBack) {

        builder = new AlertDialog.Builder(ActivityMenu.this);
        builder.setMessage(Strings.pleaseSaveResultsText).setCancelable(true).setNegativeButton(Strings.goBackAndStore, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (runBack != null)
                    runBack.run();
            }
        }).setPositiveButton(Strings.dismissResults, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (runContinue != null)
                    runContinue.run();
            }
        });
        AlertDialog alertDialog = builder.create();
        builder.show();
    }

}