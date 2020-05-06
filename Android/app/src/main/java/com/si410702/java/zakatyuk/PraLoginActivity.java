package com.si410702.java.zakatyuk;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;

import com.si410702.java.zakatyuk.sharedpreference.SharedAccount;

import io.github.dreierf.materialintroscreen.MaterialIntroActivity;
import io.github.dreierf.materialintroscreen.SlideFragmentBuilder;
import io.github.dreierf.materialintroscreen.animations.IViewTranslation;


public class PraLoginActivity extends MaterialIntroActivity {

    private SharedAccount sharedAccount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableLastSlideAlphaExitTransition(true);

        sharedAccount = new SharedAccount(this);

        getBackButtonTranslationWrapper()
                .setEnterTranslation(new IViewTranslation() {
                    @Override
                    public void translate(View view, @FloatRange(from = 0, to = 1.0) float percentage) {
                        view.setAlpha(percentage);
                    }
                });

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.bgColor)
                .buttonsColor(R.color.buttonColor)
                .image(R.drawable.ic_app_hello)
                .title(getString(R.string.slide1_title))
                .description(getString(R.string.slide1_desc))
                .build()
        );

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.bgColor)
                .buttonsColor(R.color.buttonColor)
                .image(R.drawable.ip_app_calculate)
                .title(getString(R.string.slide2_title))
                .description(getString(R.string.slide2_desc))
                .build()
        );

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.bgColor)
                .buttonsColor(R.color.buttonColor)
                .image(R.drawable.ip_app_topup)
                .title(getString(R.string.slide3_title))
                .description(getString(R.string.slide3_desc))
                .build()
        );

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.bgColor)
                .buttonsColor(R.color.buttonColor)
                .image(R.drawable.ip_app_pay)
                .title(getString(R.string.slide4_title))
                .description(getString(R.string.slide4_desc))
                .build()
        );


    }

    @Override
    public void onFinish () {
        super.onFinish();
        sharedAccount.setAccountStatus(true);
        startActivity(new Intent(this, LoginActivity.class));
    }

}
