package com.si410702.java.zakatyuk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class HitungActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private TextView tvTextView2, tvTextView3, tvTextView4, tvTextView9, tvTextView10, tvOpsional3, tvOpsional4;
    private EditText etGaji, etPendapatanlain, etHutang, etJumlah;
    private Button btPenghasilan, btFitrah;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Hitung Zakat");
        setContentView(R.layout.activity_hitung);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvTextView2 = findViewById(R.id.textView2);
        tvTextView3 = findViewById(R.id.textView3);
        tvTextView4 = findViewById(R.id.textView4);
        tvTextView9 = findViewById(R.id.textView9);
        tvTextView10 = findViewById(R.id.textView10);
        tvOpsional3 = findViewById(R.id.opsional3);
        tvOpsional4 = findViewById(R.id.opsional4);

        etGaji = findViewById(R.id.gaji);
        etPendapatanlain = findViewById(R.id.pendapatanlain);
        etHutang = findViewById(R.id.hutang);
        etJumlah = findViewById(R.id.jmlh);

        btPenghasilan = findViewById(R.id.hitungPenghasilan);
        btFitrah = findViewById(R.id.hitungFitrah);

        spinner = findViewById(R.id.spZk);

        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this,
                R.array.zakat, android.R.layout.simple_spinner_item);

        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapterSpinner);

        spinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i){

            case 0:
                tvTextView2.setVisibility(View.GONE);
                tvTextView3.setVisibility(View.GONE);
                tvTextView4.setVisibility(View.GONE);
                tvOpsional3.setVisibility(View.GONE);
                tvOpsional4.setVisibility(View.GONE);
                etGaji.setVisibility(View.GONE);
                etGaji.setText("");
                etPendapatanlain.setVisibility(View.GONE);
                etPendapatanlain.setText("");
                etHutang.setVisibility(View.GONE);
                etHutang.setText("");
                btPenghasilan.setVisibility(View.GONE);


                tvTextView9.setVisibility(View.GONE);
                etJumlah.setVisibility(View.GONE);
                tvTextView10.setVisibility(View.GONE);
                btFitrah.setVisibility(View.GONE);

                break;

            case 1:
                tvTextView9.setVisibility(View.VISIBLE);
                etJumlah.setVisibility(View.VISIBLE);
                tvTextView10.setVisibility(View.VISIBLE);
                btFitrah.setVisibility(View.VISIBLE);

                tvTextView2.setVisibility(View.GONE);
                tvTextView3.setVisibility(View.GONE);
                tvTextView4.setVisibility(View.GONE);
                tvOpsional3.setVisibility(View.GONE);
                tvOpsional4.setVisibility(View.GONE);
                etGaji.setVisibility(View.GONE);
                etGaji.setText("");
                etPendapatanlain.setVisibility(View.GONE);
                etPendapatanlain.setText("");
                etHutang.setVisibility(View.GONE);
                etHutang.setText("");
                btPenghasilan.setVisibility(View.GONE);
                break;

            case 2:
                tvTextView2.setVisibility(View.VISIBLE);
                tvTextView3.setVisibility(View.VISIBLE);
                tvTextView4.setVisibility(View.VISIBLE);
                tvOpsional3.setVisibility(View.VISIBLE);
                tvOpsional4.setVisibility(View.VISIBLE);
                etGaji.setVisibility(View.VISIBLE);
                etGaji.setText("");
                etPendapatanlain.setVisibility(View.VISIBLE);
                etPendapatanlain.setText("");
                etHutang.setVisibility(View.VISIBLE);
                etHutang.setText("");
                btPenghasilan.setVisibility(View.VISIBLE);

                tvTextView9.setVisibility(View.GONE);
                etJumlah.setVisibility(View.GONE);
                etJumlah.setText("");
                tvTextView10.setVisibility(View.GONE);
                btFitrah.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    public void hitungPenghasilan(View view) {

        String gK = etGaji.getText().toString();
        String pK = etPendapatanlain.getText().toString();
        String hK = etHutang.getText().toString();

        if (gK.equals("")){
            Toast.makeText(this, getResources().getText(R.string.hitung_kolom_pendapatan), Toast.LENGTH_SHORT).show();
        }else if (gK.equals("0")){
            Toast.makeText(this, getResources().getText(R.string.hitung_kolom_pendapatan), Toast.LENGTH_SHORT).show();
        }else if (pK.equalsIgnoreCase("")&&hK.equalsIgnoreCase("")){
            int gaji = Integer.parseInt(etGaji.getText().toString());

            final double result1 = gaji*0.025;
            final String type = "1";

            Intent intent = new Intent(HitungActivity.this, HitungZakatHasil.class);
            intent.putExtra("type", type);
            intent.putExtra("value", result1);
            startActivity(intent);
        }else if (pK.equalsIgnoreCase("")){
            int gaji = Integer.parseInt(etGaji.getText().toString());
            int hutang = Integer.parseInt(etHutang.getText().toString());

            if (gaji > hutang){
                final double result1 = (gaji - hutang)*0.025;
                final String type = "1";

                Intent intent = new Intent(HitungActivity.this, HitungZakatHasil.class);
                intent.putExtra("type", type);
                intent.putExtra("value", result1);
                startActivity(intent);
            }else if(gaji < hutang){
                Toast.makeText(this, getResources().getText(R.string.hitung_tidak_wajib_bayar), Toast.LENGTH_SHORT).show();
            }else if(gaji == hutang){
                Toast.makeText(this, getResources().getText(R.string.hitung_tidak_wajib_bayar), Toast.LENGTH_SHORT).show();
            }
        }else if (hK.equalsIgnoreCase("")){
            int gaji = Integer.parseInt(etGaji.getText().toString());
            int pendapatanlain = Integer.parseInt(etPendapatanlain.getText().toString());

            final double result1 = (gaji+pendapatanlain)*0.025;
            final String type = "1";

            Intent intent = new Intent(HitungActivity.this, HitungZakatHasil.class);
            intent.putExtra("type", type);
            intent.putExtra("value", result1);
            startActivity(intent);
        }else{
            int gaji = Integer.parseInt(etGaji.getText().toString());
            int pendapatanlain = Integer.parseInt(etPendapatanlain.getText().toString());
            int hutang = Integer.parseInt(etHutang.getText().toString());

            if ((gaji + pendapatanlain) > hutang){
                final double result1 = ((gaji+pendapatanlain)-hutang)*0.025;
                final String type = "1";

                Intent intent = new Intent(HitungActivity.this, HitungZakatHasil.class);
                intent.putExtra("type", type);
                intent.putExtra("value", result1);
                startActivity(intent);
            }else if (gaji + pendapatanlain < hutang){
                Toast.makeText(this, getResources().getText(R.string.hitung_tidak_wajib_bayar), Toast.LENGTH_SHORT).show();
            }

        }
    }


    public void hitungFitrah(View view) {
        String hgBeras = etJumlah.getText().toString();

        if (hgBeras.equals("")){
            Toast.makeText(this, getResources().getText(R.string.hitung_kolom_harga_beras), Toast.LENGTH_SHORT).show();
        }else if (hgBeras.equals("0")){
            Toast.makeText(this, getResources().getText(R.string.hitung_kolom_harga_beras), Toast.LENGTH_SHORT).show();
        }else{
            int beras = Integer.parseInt(etJumlah.getText().toString());

            final double result1 = beras*2.5;
            final String type = "0";

            Intent intent = new Intent(HitungActivity.this, HitungZakatHasil.class);
            intent.putExtra("type", type);
            intent.putExtra("value", result1);
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return true;
        }
    }
}
