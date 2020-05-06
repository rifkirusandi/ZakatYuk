package com.si410702.java.zakatyuk;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.si410702.java.zakatyuk.model.User;
import com.si410702.java.zakatyuk.sharedpreference.SharedAccount;

public class LoginActivity extends AppCompatActivity {
    private TextView tvUsername;
    private TextView tvPassword;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog pgDialog;

    private SharedAccount sharedAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        pgDialog = new ProgressDialog(this);

        sharedAccount = new SharedAccount(this);

        setTitle("Login");
        tvUsername = findViewById(R.id.login_username);
        tvPassword = findViewById(R.id.login_pass);


    }

    public void clickLogin(View view) {
        String inputUsername = tvUsername.getText().toString().trim();
        String inputPassword = tvPassword.getText().toString().trim();

        boolean isEmptyFields = false;

        if (TextUtils.isEmpty(inputUsername)) {
            isEmptyFields = true;
            tvUsername.setError(getResources().getText(R.string.login_activity_alert_kosong));
        }
        if (TextUtils.isEmpty(inputPassword)) {
            isEmptyFields = true;
            tvPassword.setError(getResources().getText(R.string.login_activity_alert_kosong));
        }

        if (!isEmptyFields) {
            pgDialog.setMessage(getResources().getText(R.string.login_activity_auth_loading));
            pgDialog.show();
            pgDialog.setCanceledOnTouchOutside(true);
            processLogin(inputUsername, inputPassword);
        }
    }

    private void processLogin(String user, String pass) {
        tvUsername.setText("");
        tvPassword.setText("");
        firebaseAuth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        final String uid = user.getUid();

                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("User").child(uid);

                        myRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                User user = dataSnapshot.getValue(User.class);

                                sharedAccount.setAccountId(user.getId());
                                sharedAccount.setAccountEmail(user.getEmail());
                                sharedAccount.setAccountName(user.getUsername());
                                sharedAccount.setAccountAmount(Integer.parseInt(user.getSaldo()));
                                sharedAccount.setAccountAlamat(user.getAlamat());
                                sharedAccount.setAccountTelp(user.getTelp());
                                sharedAccount.setAccountJenis(user.getJenis());

                                Toast.makeText(LoginActivity.this, getResources().getText(R.string.login_activity_berhasil), Toast.LENGTH_SHORT).show();
                                finish();
                                pgDialog.dismiss();
                                if (user.getJenis().equals("lembaga")) {
                                    startActivity(new Intent(getApplicationContext(), HomeLembagaActivity.class));
                                } else {
                                    startActivity(new Intent(getApplicationContext(), HomeUserActivity.class));
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                pgDialog.dismiss();
                            }
                        });
                    }
                } else {
                    Toast.makeText(LoginActivity.this, getResources().getText(R.string.login_activity_eror_wrong), Toast.LENGTH_SHORT).show();
                    pgDialog.dismiss();
                }
            }
        });
    }

    public void clickSignUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }
}
