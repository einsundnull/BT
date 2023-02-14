package com.notorein.bt;

import static com.notorein.bt.SessionParameters.darkModeMenu;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class ActivityAbout extends AppCompatActivity {
    private AdView mAdView;
    private ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_layout);
        this.layout = this.findViewById(R.id.layout);
        TextView textView = findViewById(R.id.textView);
        TextView textView2 = findViewById(R.id.textView2);
        textView.setBackground(getResources().getDrawable(R.drawable.alert_background));
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        if(!SessionParameters.openManual){
            textView.setText(Strings.recommendationsText);
            textView2.setText(Strings.recommendationsTextHeader);
        } else {
            textView.setText(Strings.aboutText);
            textView2.setText(Strings.aboutTextHeader);
        }
        textView.setMovementMethod(new ScrollingMovementMethod());
        if (darkModeMenu) {
            textView.setBackground(getResources().getDrawable(R.drawable.alert_background_dark));
            textView.setTextColor(getResources().getColor(R.color.buttonTextColor_dark));
            textView2.setBackgroundColor(getResources().getColor(R.color.menu_background_color_dark));
            layout.setBackgroundColor(getResources().getColor(R.color.menu_background_color_dark));
//            hintTextViewNBack.setBackgroundColor(getResources().getColor(R.color.menu_background_color_dark));
//            hintTextViewDuration.setBackgroundColor(getResources().getColor(R.color.menu_background_color_dark));
        } else {
            textView.setBackground(getResources().getDrawable(R.drawable.alert_background));
            textView.setTextColor(getResources().getColor(R.color.buttonTextColor));
            textView2.setBackgroundColor(getResources().getColor(R.color.menu_background_color));
            textView2.setTextColor(getResources().getColor(R.color.buttonTextColor));
            layout.setBackgroundColor(getResources().getColor(R.color.menu_background_color));
//            hintTextViewNBack.setBackgroundColor(getResources().getColor(R.color.menu_background_color));
//            hintTextViewDuration.setBackgroundColor(getResources().getColor(R.color.menu_background_color));
        }
    }


}