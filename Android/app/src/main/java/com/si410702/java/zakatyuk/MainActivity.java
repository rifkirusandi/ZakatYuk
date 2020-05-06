package com.si410702.java.zakatyuk;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.si410702.java.zakatyuk.sharedpreference.SharedAccount;
import com.si410702.java.zakatyuk.ui.profile.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private SharedAccount sharedAccount;

    private TextView tv_hasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        sharedAccount = new SharedAccount(this);

        tv_hasil = findViewById(R.id.text_header_hasil);

        String message = "Id : " + sharedAccount.getAccountId() + "\n"
                + "Name : " + sharedAccount.getAccountName() + "\n"
                + "Email : " + sharedAccount.getAccountEmail() + "\n"
                + "Amount : " + sharedAccount.getAccountAmount() + "\n"
                + "Status : " + sharedAccount.getAccountStatus() + "\n";

        tv_hasil.setText(message);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();

            Log.d("Ini", "Mufti " + name + " " + email + " " + uid + photoUrl);
        }
    }

    public void test(View view) {
        Intent intent = new Intent(this, HomeLembagaActivity.class);
        startActivity(intent);
    }


    public void test1(View view) {
        Intent intent1 = new Intent(this, InfoZakatActivity.class);
        startActivity(intent1);
    }

    public void clickLogOut(View view) {
        firebaseAuth.signOut();
        //closing activity

        startActivity(new Intent(this, LoginActivity.class));
        finish();
        //starting login activity
    }

    public void clickTopUp(View view) {
        Intent intent1 = new Intent(this, TopUpSaldoActivity.class);
        startActivity(intent1);
    }

    public void keBayar(View view) {
        Intent intent34 = new Intent(this, BayarZakatIntro.class);
        startActivity(intent34);
    }


    public void clickHome(View view) {
        Intent intent = new Intent(this, HomeUserActivity.class);
        startActivity(intent);
    }

    public void clickProfileUser(View view) {
        Intent intent = new Intent(this, UpdateUserAccount.class);
        startActivity(intent);
    }
}
