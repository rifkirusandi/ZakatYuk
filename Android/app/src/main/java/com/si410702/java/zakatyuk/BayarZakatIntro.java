package com.si410702.java.zakatyuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.si410702.java.zakatyuk.sharedpreference.SharedAccount;

import java.text.NumberFormat;

public class BayarZakatIntro extends AppCompatActivity {

    private SharedAccount sharedAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar_zakat_intro);

        sharedAccount = new SharedAccount(this);

        TextView etKet = findViewById(R.id.tv_ket_bayar);

        int saldo = sharedAccount.getAccountAmount();
        NumberFormat format = NumberFormat.getCurrencyInstance();
        etKet.setText(getResources().getText(R.string.zakat_intro_set_text) + format.format(saldo));
    }

    public void toTopUp(View view) {
//        Toast.makeText(getApplicationContext(), "ke Top Up", Toast.LENGTH_SHORT).show();
        Intent intent =  new Intent(this, TopUpSaldoActivity.class);
        startActivity(intent);
        finish();
    }

    public void toBayar(View view) {
        Intent intent =  new Intent(this, BayarZakatOption.class);
        startActivity(intent);
    }
}
