package com.notorein.bt;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

public class ViewMenu extends Dialog implements View.OnClickListener {


    private final Context c;
    public ConstraintLayout layout;
    public CheckBox one;
    public CheckBox two;
    public CheckBox three;
    public CheckBox four;
    public CheckBox five;


    public ViewMenu(Activity activity) {
        super(activity);
        c = activity.getApplicationContext();
        int layoutWidth = (int) (SessionParameters.displayWidth * 0.85);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.view_results_settings_layout);
//        layout = (ConstraintLayout) LayoutInflater.from(c).inflate(R.layout.view_results_settings_layout, null);
        layout = findViewById(R.id.view_results);
        one = layout.findViewById(R.id.showPercentage);
        two = layout.findViewById(R.id.showNBack);
        three = layout.findViewById(R.id.showDayNBack);
        four = layout.findViewById(R.id.showSessionNBack);
        five = layout.findViewById(R.id.showTrialNBack);
        setCheckBoxesSelected();
        setOnClickListenersToSettingsView();
        setCheckBoxesText();


    }

    public void setCheckBoxesText() {
        one.setText(Strings.showDayInResultsPercentageText);
        two.setText(Strings.showSessionInResultsPercentageText);
        three.setText(Strings.showDayInResultsPercentageText);
        four.setText(Strings.showSessionInResultsText);
        five.setText(Strings.showTrialInResultsText);
    }

    public void setCheckBoxesSelected() {
        one.setChecked(SessionParameters.showPercentageInResults);
        two.setChecked(SessionParameters.showNBackInResults);
        three.setChecked(SessionParameters.showDayInResults);
        four.setChecked(SessionParameters.showSessionInResults);
        five.setChecked(SessionParameters.showTrialInResults);
    }

    private void setOnClickListenersToSettingsView() {
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.showPercentage) {
            SessionParameters.showPercentageInResults = one.isSelected();
            Toast.makeText(c,"showPercentageInResults " +  SessionParameters.showPercentageInResults,Toast.LENGTH_SHORT).show();
        }
        if (v.getId() == R.id.showNBack) {
            SessionParameters.showNBackInResults = two.isSelected();
            Toast.makeText(c,"showNBackInResults " +  SessionParameters.showNBackInResults,Toast.LENGTH_SHORT).show();
        }
        if (v.getId() == R.id.showDayNBack) {
            SessionParameters.showDayInResults = three.isSelected();
            Toast.makeText(c,"showDayInResults " +  SessionParameters.showDayInResults,Toast.LENGTH_SHORT).show();
        }
        if (v.getId() == R.id.showSessionNBack) {
            SessionParameters.showSessionInResults = four.isSelected();
            Toast.makeText(c,"showSessionInResults " +  SessionParameters.showSessionInResults,Toast.LENGTH_SHORT).show();
        }
        if (v.getId() == R.id.showTrialNBack) {
            SessionParameters.showTrialInResults = five.isSelected();
            Toast.makeText(c,"showTrialInResults " +  SessionParameters.showTrialInResults,Toast.LENGTH_SHORT).show();
        }
    }


}
