package com.si410702.java.zakatyuk;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class BayarZakatLoading extends AppCompatActivity {

    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar_zakat_loading);

        final Intent StrukIntent = getIntent();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), BayarZakatNota.class);
                intent.putExtra("idByr", StrukIntent.getStringExtra("idByr"));
                intent.putExtra("tanggal", StrukIntent.getStringExtra("tanggal"));
                intent.putExtra("nama", StrukIntent.getStringExtra("nama"));
                intent.putExtra("jenis", StrukIntent.getStringExtra("jenis"));
                intent.putExtra("nominal", StrukIntent.getStringExtra("nominal"));
                intent.putExtra("keterangan", StrukIntent.getStringExtra("keterangan"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }, 4000);
    }
}
