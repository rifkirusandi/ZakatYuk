package com.si410702.java.zakatyuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class AlokasiZakatBerhasil extends AppCompatActivity {

    TextView idPembayaran, tglAlokasi, namaUser, lembaga, jumlahZakat, namaPenerima, alamatPenerima, noTelp;
    NumberFormat format = NumberFormat.getCurrencyInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alokasi_zakat_berhasil);

        Intent berhasil = getIntent();

        idPembayaran = findViewById(R.id.alokasi_id);
        tglAlokasi = findViewById(R.id.alokasi_date);
        namaUser = findViewById(R.id.alokasi_name);
        lembaga = findViewById(R.id.alokasi_lembaga);
        jumlahZakat = findViewById(R.id.alokasi_jumlah);
        namaPenerima = findViewById(R.id.alokasi_penerima);
        alamatPenerima = findViewById(R.id.alokasi_alamat);
        noTelp = findViewById(R.id.alokasi_telp);

        idPembayaran.setText(berhasil.getStringExtra("idPembayaran"));
        tglAlokasi.setText(berhasil.getStringExtra("tgl"));
        namaUser.setText(berhasil.getStringExtra("username"));
        lembaga.setText(berhasil.getStringExtra("lembaga"));
        jumlahZakat.setText(format.format(Integer.parseInt(berhasil.getStringExtra("jumlahZakat"))));
        namaPenerima.setText(berhasil.getStringExtra("namaPenerima"));
        alamatPenerima.setText(berhasil.getStringExtra("alamat"));
        noTelp.setText(berhasil.getStringExtra("noTelp"));

        setTitle("Alokasi " + berhasil.getStringExtra("jenis"));

    }

    public void toHome(View view) {
        Intent intent = new Intent(this, AlokasiZakatMenu.class);
        finish();
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, AlokasiZakatMenu.class);
        finish();
        startActivity(intent);
    }
}
