package com.si410702.java.zakatyuk;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.si410702.java.zakatyuk.model.Bayar;
import com.si410702.java.zakatyuk.model.User;
import com.si410702.java.zakatyuk.sharedpreference.SharedAccount;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BayarZakatStruk extends AppCompatActivity {

    private SharedAccount sharedAccount;

    TextView jumlahZakat, jenisZakat, waktuBayar, userId, namaUser;
    String type, jumlah, keterangan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar_zakat_struk);

        sharedAccount = new SharedAccount(this);

        NumberFormat format = NumberFormat.getCurrencyInstance();

        jumlahZakat = (TextView) findViewById(R.id.bayar_jumlah);
        userId = (TextView) findViewById(R.id.bayar_id);
        jenisZakat = (TextView) findViewById(R.id.bayar_jenis);
        waktuBayar = (TextView) findViewById(R.id.bayar_date);
        namaUser = (TextView) findViewById(R.id.bayar_name);

        String currentDate = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(new Date());
        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

        Intent optionIntent = getIntent();
        type = optionIntent.getStringExtra("typeZakat");
        jumlah = optionIntent.getStringExtra("jumlahZakat");

        userId.setText(sharedAccount.getAccountId());
        namaUser.setText(sharedAccount.getAccountName());
        waktuBayar.setText(currentDate + " " + currentTime);
        jenisZakat.setText(type);
        jumlahZakat.setText(format.format(Integer.parseInt(jumlah)));

    }

    public void toLoading(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(getResources().getText(R.string.confirm_title_alert));
        alertDialog.setMessage(getResources().getText(R.string.bayar_alert_text));
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getResources().getText(R.string.tidak_alert_btn),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getResources().getText(R.string.ya_alert_text),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        execute();
                    }
                });
        alertDialog.show();
    }

    public void execute() {

        int total = sharedAccount.getAccountAmount() - Integer.parseInt(jumlah);

        DatabaseReference drBayar = FirebaseDatabase.getInstance().getReference("Bayar");
        DatabaseReference drUser = FirebaseDatabase.getInstance().getReference("User").child(sharedAccount.getAccountId());

        String currentDate = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(new Date());
        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

        String idByr = drBayar.push().getKey();

        if (total < 0) {
            keterangan = getString(R.string.gagal_bayar_keterangan);

            Intent intent = new Intent(this, BayarZakatLoading.class);
            intent.putExtra("idByr", "-");
            intent.putExtra("tanggal", currentDate + " " + currentTime);
            intent.putExtra("nama", sharedAccount.getAccountName());
            intent.putExtra("jenis", type);
            intent.putExtra("nominal", jumlah);
            intent.putExtra("keterangan", keterangan);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            keterangan = getString(R.string.berhasil_bayar_keterangan);

            Bayar bayar = new Bayar(idByr, type, jumlah, sharedAccount.getAccountId(), currentDate, currentTime, "bayar");
            drBayar.child(idByr).setValue(bayar);

            User user = new User(sharedAccount.getAccountId(), sharedAccount.getAccountEmail(), sharedAccount.getAccountName(),
                    String.valueOf(total), sharedAccount.getAccountTelp(), sharedAccount.getAccountAlamat(), sharedAccount.getAccountJenis());
            drUser.setValue(user);
            drUser.getDatabase();

            sharedAccount.setAccountAmount(total);

            Intent intent = new Intent(this, BayarZakatLoading.class);
            intent.putExtra("idByr", idByr);
            intent.putExtra("tanggal", currentDate + " " + currentTime);
            intent.putExtra("nama", sharedAccount.getAccountName());
            intent.putExtra("jenis", type);
            intent.putExtra("nominal", jumlah);
            intent.putExtra("keterangan", keterangan);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
