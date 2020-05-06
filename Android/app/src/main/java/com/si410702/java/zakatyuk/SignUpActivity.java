package com.si410702.java.zakatyuk;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.si410702.java.zakatyuk.model.User;
import com.si410702.java.zakatyuk.sharedpreference.SharedAccount;

public class SignUpActivity extends AppCompatActivity {

    private Button btnRegister;
    private EditText editTextUsername;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextRetype;

    private ProgressDialog pgDialog;
    private SharedAccount sharedAccount;

    private User user;

    DatabaseReference databaseUser;
    FirebaseAuth fbAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        databaseUser = FirebaseDatabase.getInstance().getReference("User");
        fbAuth = FirebaseAuth.getInstance();

        sharedAccount = new SharedAccount(this);

        setTitle("Register");
        pgDialog = new ProgressDialog(this);

        btnRegister = findViewById(R.id.btnRegister);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextRetype = findViewById(R.id.editTextRetype);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

    }

    private void registerUser() {
        final String username = editTextUsername.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString();
        final String saldo = "0";
        String retype = editTextRetype.getText().toString();

        pgDialog.setMessage(getResources().getText(R.string.signup_activity_loading));
        pgDialog.show();

        if (TextUtils.isEmpty(username)) {
            Toast.makeText(SignUpActivity.this, getResources().getText(R.string.signup_activity_alert_1), Toast.LENGTH_SHORT).show();
            pgDialog.dismiss();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(SignUpActivity.this, getResources().getText(R.string.signup_activity_alert_2), Toast.LENGTH_SHORT).show();
            pgDialog.dismiss();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(SignUpActivity.this, getResources().getText(R.string.signup_activity_alert_3), Toast.LENGTH_SHORT).show();
            pgDialog.dismiss();
            return;
        }

        if (TextUtils.isEmpty(retype)) {
            Toast.makeText(SignUpActivity.this, getResources().getText(R.string.signup_activity_alert_4), Toast.LENGTH_SHORT).show();
            pgDialog.dismiss();
            return;
        }

        if (!password.equals(retype)) {
            Toast.makeText(SignUpActivity.this, getResources().getText(R.string.signup_activity_alert_5), Toast.LENGTH_SHORT).show();
            pgDialog.dismiss();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(SignUpActivity.this, getResources().getText(R.string.signup_activity_alert_6), Toast.LENGTH_SHORT).show();
            pgDialog.dismiss();
            return;
        }

        if (password.length() > 20) {
            Toast.makeText(SignUpActivity.this, getResources().getText(R.string.signup_activity_alert_7), Toast.LENGTH_SHORT).show();
            pgDialog.dismiss();
        } else {
            fbAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        String id = task.getResult().getUser().getUid();
                        user = new User(id, email, username, saldo, "0", "-", "user");

                        databaseUser.child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                sharedAccount.setAccountId(user.getId());
                                sharedAccount.setAccountEmail(user.getEmail());
                                sharedAccount.setAccountName(user.getUsername());
                                sharedAccount.setAccountAmount(Integer.parseInt(user.getSaldo()));
                                sharedAccount.setAccountAlamat(user.getAlamat());
                                sharedAccount.setAccountTelp(user.getTelp());
                                sharedAccount.setAccountJenis(user.getJenis());

                                Toast.makeText(SignUpActivity.this, getResources().getText(R.string.signup_activity_notif_1), Toast.LENGTH_SHORT).show();
                                pgDialog.dismiss();

                                finish();
                                startActivity(new Intent(getApplicationContext(), HomeUserActivity.class));
                            }
                        });
                    } else {
                        Toast.makeText(SignUpActivity.this, getResources().getText(R.string.signup_activity_notif_2), Toast.LENGTH_SHORT).show();
                        pgDialog.dismiss();
                    }
                }
            });
        }
    }

    public void Login(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
