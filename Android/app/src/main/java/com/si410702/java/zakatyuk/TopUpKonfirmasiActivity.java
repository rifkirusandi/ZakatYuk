package com.si410702.java.zakatyuk;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.si410702.java.zakatyuk.model.TopUp;
import com.si410702.java.zakatyuk.model.User;
import com.si410702.java.zakatyuk.sharedpreference.SharedAccount;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TopUpKonfirmasiActivity extends AppCompatActivity {

    private String jenisTopUp, kodeTopUp, jumlahTopUp;
    private TextView tvConfirm;
    private SharedAccount sharedAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up_konfirmasi);

        sharedAccount = new SharedAccount(this);

        jenisTopUp = getIntent().getStringExtra("JenisTopUp");
        kodeTopUp = getIntent().getStringExtra("KodeTopUp");
        jumlahTopUp = getIntent().getStringExtra("JumlahTopUp");

        tvConfirm = findViewById(R.id.tv_topup_konfirmasi);

        getSupportActionBar().setTitle("Top Up Zakat");

        String message = "Yth. Anda akan melakukan Top Up saldo\n\nTop Up Melalui: " + jenisTopUp + "\n" +
                "\nNO PELANGGAN: " + kodeTopUp + "\nNAMA PELANGGAN: " + sharedAccount.getAccountName() + "\n" +
                "\nNILAI TOP UP: Rp " + jumlahTopUp + "\nADMIN: Rp 1.000\n" +
                "\n\nTotal TopUp: Rp " + (Integer.parseInt(jumlahTopUp) + 1000) + "\n\nApabila anda setuju, tekan ‘Lanjut’.";

        tvConfirm.setText(message);
    }

    public void clickTopUpKonfirmasi(final View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Konfirmasi Top Up");
        alertDialog.setMessage("Apakah anda yakin Top Up saldo?");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Tidak",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Iya",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        execute();
                    }
                });
        alertDialog.show();

    }

    private void execute() {
        DatabaseReference drTopUp = FirebaseDatabase.getInstance().getReference("TopUp");
        DatabaseReference drUser = FirebaseDatabase.getInstance().getReference("User").child(sharedAccount.getAccountId());

        String currentDate = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(new Date());
        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

        String idTr = drTopUp.push().getKey();
        TopUp topUp = new TopUp(idTr, jenisTopUp, kodeTopUp, jumlahTopUp, sharedAccount.getAccountId(), currentDate, currentTime);
        drTopUp.child(idTr).setValue(topUp);

        int total = sharedAccount.getAccountAmount() + Integer.parseInt(jumlahTopUp);

        User user = new User(sharedAccount.getAccountId(), sharedAccount.getAccountEmail(), sharedAccount.getAccountName(),
                String.valueOf(total), sharedAccount.getAccountTelp(), sharedAccount.getAccountAlamat(), sharedAccount.getAccountJenis());
        drUser.setValue(user);
        drUser.getDatabase();

        sharedAccount.setAccountAmount(total);

        Intent intent = new Intent(this, TopUpBuktiActivity.class);
        intent.putExtra("JenisTopUp", jenisTopUp);
        intent.putExtra("KodeTopUp", kodeTopUp);
        intent.putExtra("JumlahTopUp", jumlahTopUp);
        intent.putExtra("IdTopUp", idTr);
        intent.putExtra("DateTopUp", currentDate);
        intent.putExtra("TimeTopUp", currentTime);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
