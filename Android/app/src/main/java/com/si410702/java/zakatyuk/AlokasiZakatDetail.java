package com.si410702.java.zakatyuk;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

public class AlokasiZakatDetail extends AppCompatActivity {

    TextView tvUsername, tvJumlahZakat, tvJenisZakat, tvIdPembayaran, tvDate, tvNoTelp, tvEmail;
    String jumlahZakat, jenisZakat, userId, username;
    NumberFormat format = NumberFormat.getCurrencyInstance();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alokasi_zakat_detail);

        Intent detail = getIntent();
        tvUsername = findViewById(R.id.alokasi_username);
        tvJumlahZakat = findViewById(R.id.alokasi_jumlah_zakat);
        tvJenisZakat = findViewById(R.id.alokasi_jenis_zakat);
        tvIdPembayaran = findViewById(R.id.alokasi_id);
        tvDate = findViewById(R.id.alokasi_date);
        tvNoTelp = findViewById(R.id.alokasi_telp_user);
        tvEmail = findViewById(R.id.alokasi_email);

        jumlahZakat = detail.getStringExtra("jumlahZakat");
        jenisZakat = detail.getStringExtra("jenisZakat");
        userId = detail.getStringExtra("idUser");

        setTitle("Detail " + jenisZakat);

        tvUsername.setText(detail.getStringExtra("username")); // ganti
        tvJumlahZakat.setText(format.format(Integer.parseInt(detail.getStringExtra("jumlahZakat"))));
        tvJenisZakat.setText("Total " + detail.getStringExtra("jenisZakat"));
        tvIdPembayaran.setText(detail.getStringExtra("idPembayaran"));
        tvDate.setText(detail.getStringExtra("tgl"));
        tvNoTelp.setText(detail.getStringExtra("telp")); // ganti
        tvEmail.setText(detail.getStringExtra("email"));

        username = detail.getStringExtra("username");

        Button btn = findViewById(R.id.lanjut_btn);
        String status = detail.getStringExtra("status");

        if (status.equals("sudah")) {
            btn.setVisibility(View.GONE);
        }
    }

    public void lanjutAlokasi(View view) {
        Intent intent = new Intent(this, AlokasiZakatOption.class);
        intent.putExtra("jumlahZakat", jumlahZakat);
        intent.putExtra("jenisZakat", jenisZakat);
        intent.putExtra("username", username);
        intent.putExtra("userId", userId);
        intent.putExtra("idPembayaran", tvIdPembayaran.getText().toString());
        startActivity(intent);
    }
}
