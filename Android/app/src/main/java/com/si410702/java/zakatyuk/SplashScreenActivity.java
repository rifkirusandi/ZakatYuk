package com.si410702.java.zakatyuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.si410702.java.zakatyuk.sharedpreference.SharedAccount;

public class SplashScreenActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private SharedAccount sharedAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        firebaseAuth = FirebaseAuth.getInstance();
        sharedAccount = new SharedAccount(this);

        Thread welcomeThread = new Thread() {
            @Override
            public void run() {
                try {
                    super.run();
                    sleep(1500);  //Delay of 10 seconds
                } catch (Exception ignored) {

                } finally {
                    if (!sharedAccount.getAccountStatus()) {
                        startActivity(new Intent(SplashScreenActivity.this, PraLoginActivity.class));
                    } else if (firebaseAuth.getCurrentUser() != null) {
                        if (sharedAccount.getAccountJenis().equals("lembaga")){
                            startActivity(new Intent(SplashScreenActivity.this, HomeLembagaActivity.class));
                        } else {
                            startActivity(new Intent(SplashScreenActivity.this, HomeUserActivity.class));
                        }

                    } else {
                        startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                    }
                    finish();
                }
            }
        };
        welcomeThread.start();
    }
}
