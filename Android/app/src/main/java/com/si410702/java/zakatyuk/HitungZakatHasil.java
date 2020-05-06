package com.si410702.java.zakatyuk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class HitungZakatHasil extends AppCompatActivity {

    String tipe;
    double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hitung_zakat_hasil);

        tipe = getIntent().getStringExtra("type");
        total = getIntent().getDoubleExtra("value", 0);

        AlertDialog.Builder myAlertBuilder = new
                AlertDialog.Builder(this);

        myAlertBuilder.setCancelable(false);
        myAlertBuilder.setTitle("Success!");
        myAlertBuilder.setMessage("Zakat yang harus kamu bayar sebesar"+"\n"+"\n"+"Rp. "+total);

        myAlertBuilder.setPositiveButton("CONTINUE", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(HitungZakatHasil.this, HitungZakatBayar.class);
                        intent.putExtra("type", tipe);
                        intent.putExtra("value", total);
                        startActivity(intent);
                    }
                });

        myAlertBuilder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Intent intent = new Intent(HitungZakatHasil.this, HitungActivity.class);
                startActivity(intent);
            }
        });

        myAlertBuilder.show();
    }
}
