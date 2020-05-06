package com.si410702.java.zakatyuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.si410702.java.zakatyuk.sharedpreference.SharedAccount;

import org.w3c.dom.Text;

public class TopUpBuktiActivity extends AppCompatActivity {

    private TextView tvBukti;
    private String jenisTopUp, kodeTopUp, jumlahTopUp, idTopUp, dateTopUp, timeTopUp;

    private SharedAccount sharedAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up_bukti);

        sharedAccount = new SharedAccount(this);

        jenisTopUp = getIntent().getStringExtra("JenisTopUp");
        kodeTopUp = getIntent().getStringExtra("KodeTopUp");
        jumlahTopUp = getIntent().getStringExtra("JumlahTopUp");
        idTopUp = getIntent().getStringExtra("IdTopUp");
        dateTopUp = getIntent().getStringExtra("DateTopUp");
        timeTopUp = getIntent().getStringExtra("TimeTopUp");

        tvBukti = findViewById(R.id.tv_topup_bukti);

        TextView tvTitile = findViewById(R.id.tv_topup_title);

        String message = "Status: BERHASIL\n\n" +
                "Nomor Transaksi: "+ idTopUp+"\n" +
                "Tanggal Transaksi: "+ dateTopUp+" "+ timeTopUp+"\n\n" +
                "Top Up: "+ jenisTopUp+" \n" +
                "No Pelanggan: "+ kodeTopUp+"\n" +
                "Nama Pelanggan: "+ sharedAccount.getAccountName()+"\n" +
                "Nilai Top Up: Rp. "+ jumlahTopUp +",-\n" +
                "ADMIN FEE: Rp.1000\n" +
                "TOTAL DIBAYAR: Rp. "+ (1000+ Integer.parseInt(jumlahTopUp))+",-";

        tvBukti.setText(message);
        tvTitile.setText("Bukti Top Up " + jenisTopUp);
    }

    public void clickTopUpBukti(View view) {
        Intent intent = new Intent(this, TopUpSaldoActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, TopUpSaldoActivity.class);
        startActivity(intent);
        finish();
    }
}
