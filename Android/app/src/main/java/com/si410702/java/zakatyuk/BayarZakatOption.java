package com.si410702.java.zakatyuk;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.si410702.java.zakatyuk.model.Bayar;
import com.si410702.java.zakatyuk.model.User;
import com.si410702.java.zakatyuk.sharedpreference.SharedAccount;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class BayarZakatOption extends AppCompatActivity {

    String tipe, type;
    double total;
    EditText jumlahZakat;
    CheckBox setuju;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar_zakat_option);

        tipe = "Zakat Fitrah";
        MaterialSpinner spinner = findViewById(R.id.spinner);
        spinner.setItems("Zakat Fitrah", "Zakat Penghasilan");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                tipe = item;
            }
        });

        jumlahZakat = (EditText) findViewById(R.id.jumlah_zakat);
        setuju = (CheckBox) findViewById(R.id.checkbox_setuju);

        type = getIntent().getStringExtra("type");
        total = getIntent().getDoubleExtra("value", 0);
        if (type != null) {
            int sp = Integer.parseInt(type);
            spinner.setSelectedIndex(sp);
            int zakat = (int) total;
            jumlahZakat.setText(String.valueOf(zakat));
            if (sp == 0){
                tipe = "Zakat Fitrah";
            } else if (sp == 1){
                tipe = "Zakat Penghasilan";
            }

        } else {
            spinner.setSelectedIndex(0);
        }
    }

    public void toStruk(View view) {
        if (setuju.isChecked()) {

            if (!jumlahZakat.getText().toString().isEmpty() && Integer.parseInt(jumlahZakat.getText().toString()) > 0) {
                Intent intent = new Intent(this, BayarZakatStruk.class);
                intent.putExtra("typeZakat", tipe);
                intent.putExtra("jumlahZakat", jumlahZakat.getText().toString());
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), getResources().getText(R.string.zero_input_zakat), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), getResources().getText(R.string.confirm_next_zakat), Toast.LENGTH_SHORT).show();
        }

    }


}
