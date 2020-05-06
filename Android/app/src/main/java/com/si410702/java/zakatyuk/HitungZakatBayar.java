package com.si410702.java.zakatyuk;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class HitungZakatBayar extends AppCompatActivity {

    String tipe;
    double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hitung_zakat_bayar);

        tipe = getIntent().getStringExtra("type");
        total = getIntent().getDoubleExtra("value", 0);

        AlertDialog.Builder myAlertBuilder = new
                AlertDialog.Builder(this);

        myAlertBuilder.setCancelable(false);
        myAlertBuilder.setTitle("Ayo Zakat!");
        myAlertBuilder.setMessage("Sekarang kamu sudah tahu berapa zakat yang harus kamu bayarkan");
        myAlertBuilder.setPositiveButton("BAYAR", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(HitungZakatBayar.this, BayarZakatOption.class);
                        intent.putExtra("type", tipe);
                        intent.putExtra("value", total);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });

        myAlertBuilder.setNegativeButton("TOP UP", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(HitungZakatBayar.this, TopUpSaldoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        myAlertBuilder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Intent intent = new Intent(HitungZakatBayar.this, HitungActivity.class);
                startActivity(intent);
            }
        });

        myAlertBuilder.show();


//        myAlertBuilder.setPositiveButton("CONTINUE", new
//        DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                new AlertDialog.Builder(HitungZakatBayar.this)
//                        .setTitle("Ayo Zakat!")
//                        .setMessage("Sekarang kamu sudah tahu berapa zakat yang harus kamu bayarkan")
//                        .setPositiveButton("BAYAR", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Intent intent = new Intent(HitungZakatBayar.this, BayarZakatOption.class);
//                                intent.putExtra("type", tipe);
//                                intent.putExtra("value", total);
//                                startActivity(intent);
//                            }
//                        })
//                        .setNegativeButton("TOP UP", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Intent intent = new Intent(HitungZakatBayar.this, TopUpSaldoActivity.class);
//                                startActivity(intent);
//                            }
//                        })
//                        .show();
//            }
//        });
//        myAlertBuilder.show();
    }
}
