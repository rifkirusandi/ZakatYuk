package com.si410702.java.zakatyuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TopUpInputDataActivity extends AppCompatActivity {

    private String jenisTopUp;
    private EditText etCode, etJumlah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up_input_data);

        getSupportActionBar().setTitle("Top Up Zakat");

        etCode= findViewById(R.id.et_input_kode);
        etJumlah= findViewById(R.id.et_input_jumlah);

        jenisTopUp = getIntent().getStringExtra("JenisTopUp");

    }

    public void clickTopUpInputData(View view) {
        String kode = etCode.getText().toString();
        String jumlah = etJumlah.getText().toString();

        if (TextUtils.isEmpty(kode) || TextUtils.isEmpty(jumlah)){
            Toast.makeText(this, getResources().getText(R.string.top_up_data_tidak_lengkap), Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, TopUpKonfirmasiActivity.class);
            intent.putExtra("JenisTopUp", jenisTopUp);
            intent.putExtra("KodeTopUp", kode);
            intent.putExtra("JumlahTopUp", jumlah);
            startActivity(intent);
//            finish();
        }

    }
}
