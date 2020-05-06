package com.si410702.java.zakatyuk;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.si410702.java.zakatyuk.model.Alokasi;
import com.si410702.java.zakatyuk.sharedpreference.SharedAccount;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AlokasiZakatOption extends AppCompatActivity {

    private SharedAccount sharedAccount;
    String isi;
    TextView tvJumlahZakat, tvJenisZakat;
    EditText etNoTelp, etAlamat, etNamaPenerima, etTgl;
    String idPembayaran, username, userId, jenis;
    NumberFormat format = NumberFormat.getCurrencyInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alokasi_zakat_option);

        sharedAccount = new SharedAccount(this);

        String currentDate = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(new Date());

        Intent option = getIntent();

        tvJumlahZakat = findViewById(R.id.alokasi_jumlah_zakat);
        tvJenisZakat = findViewById(R.id.alokasi_jenis_zakat);
        etNoTelp = findViewById(R.id.alokasi_telp_penerima);
        etAlamat = findViewById(R.id.alokasi_alamat_penerima);
        etTgl = findViewById(R.id.alokasi_tgl_alokasi);
        etNamaPenerima = findViewById(R.id.alokasi_nama_penerima);

        idPembayaran = option.getStringExtra("idPembayaran");

        isi = option.getStringExtra("jumlahZakat");
        tvJumlahZakat.setText(format.format(Integer.parseInt(isi)));
        tvJenisZakat.setText(option.getStringExtra("jenisZakat"));
        etTgl.setText(currentDate);

        setTitle("Alokasi " + option.getStringExtra("jenisZakat"));

        username = option.getStringExtra("username");
        userId = option.getStringExtra("userId");
        jenis = option.getStringExtra("jenisZakat");

        etTgl.setEnabled(false);
    }

    public void alokasiConfirm(View view) {
        if (!etNamaPenerima.getText().toString().equals("") && !etAlamat.getText().toString().equals("") && !etNoTelp.getText().toString().equals("")) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle(getResources().getText(R.string.alokasi_activity_title));
            alertDialog.setMessage(getResources().getText(R.string.alokasi_activity_desc));
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getResources().getText(R.string.alokasi_activity_no), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getResources().getText(R.string.alokasi_activity_yes), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    execute();
                }
            });
            alertDialog.show();
        } else {
            Toast.makeText(this, getResources().getText(R.string.alokasi_activity_alert), Toast.LENGTH_SHORT).show();
        }
    }

    public void execute() {
        DatabaseReference drAlokasi = FirebaseDatabase.getInstance().getReference("Alokasi");

        String currentDate = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(new Date());
        String idAlokasi = drAlokasi.push().getKey();

        Alokasi alokasi = new Alokasi(idAlokasi, sharedAccount.getAccountId(), currentDate,
                etNamaPenerima.getText().toString(), etAlamat.getText().toString(),
                etNoTelp.getText().toString(), userId, idPembayaran);
        drAlokasi.child(idAlokasi).setValue(alokasi);

        Intent intent = new Intent(this, AlokasiZakatBerhasil.class);
        intent.putExtra("idPembayaran", idPembayaran);
        intent.putExtra("tgl", currentDate);
        intent.putExtra("lembaga", sharedAccount.getAccountName());
        intent.putExtra("jumlahZakat", isi);
        intent.putExtra("namaPenerima", etNamaPenerima.getText().toString());
        intent.putExtra("alamat", etAlamat.getText().toString());
        intent.putExtra("noTelp", etNoTelp.getText().toString());
        intent.putExtra("username", username);
        intent.putExtra("userId", userId);
        intent.putExtra("jenis", jenis);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
