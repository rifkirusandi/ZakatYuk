package com.si410702.java.zakatyuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class BayarZakatNota extends AppCompatActivity {


    TextView jumlahZakat, jenisZakat, waktuBayar, idPembayaran, namaUser, keterangan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar_zakat_nota);

        Intent StrukIntent = getIntent();
        NumberFormat format = NumberFormat.getCurrencyInstance();

        jumlahZakat = (TextView) findViewById(R.id.bayar_jumlah);
        jenisZakat = (TextView) findViewById(R.id.bayar_jenis);
        waktuBayar = (TextView) findViewById(R.id.bayar_date);
        idPembayaran = (TextView) findViewById(R.id.bayar_id);
        namaUser = (TextView) findViewById(R.id.bayar_name);
        keterangan = (TextView) findViewById(R.id.bayar_keterangan);

        idPembayaran.setText(StrukIntent.getStringExtra("idByr"));
        waktuBayar.setText(StrukIntent.getStringExtra("tanggal"));
        namaUser.setText(StrukIntent.getStringExtra("nama"));
        jenisZakat.setText(StrukIntent.getStringExtra("jenis"));
        jumlahZakat.setText(format.format(Integer.parseInt(StrukIntent.getStringExtra("nominal"))));
        keterangan.setText(StrukIntent.getStringExtra("keterangan"));


    }

    public void toHome(View view) {

        Intent intent = new Intent(this, HomeUserActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeUserActivity.class);
        startActivity(intent);
        finish();
    }
}
