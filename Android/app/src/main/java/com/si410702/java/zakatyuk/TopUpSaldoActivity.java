package com.si410702.java.zakatyuk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.si410702.java.zakatyuk.adapter.TopUpSaldoAdapter;
import com.si410702.java.zakatyuk.model.TopUp;
import com.si410702.java.zakatyuk.sharedpreference.SharedAccount;

import java.text.NumberFormat;
import java.util.ArrayList;

public class TopUpSaldoActivity extends AppCompatActivity {

    private RecyclerView rvSaldo;
    private TextView etTopUpMoney;

    private SharedAccount sharedAccount;

    private TopUpSaldoAdapter topUpSaldoAdapter;
    private ArrayList<TopUp> topUps = new ArrayList<>();

    private ProgressDialog pgDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up_saldo);

        setTitle("Top Up Zakat");

        etTopUpMoney = findViewById(R.id.tv_topup_saldo_money);
        rvSaldo = findViewById(R.id.rv_topup_saldo);
        rvSaldo.setHasFixedSize(true);

        sharedAccount = new SharedAccount(this);
        topUpSaldoAdapter = new TopUpSaldoAdapter();

        rvSaldo.setLayoutManager(new LinearLayoutManager(this));
        rvSaldo.setAdapter(topUpSaldoAdapter);
    }

    private void getDataHistory() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Hei","Tayoooo");
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("TopUp");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    TopUp topUp = postSnapshot.getValue(TopUp.class);

                    if (topUp.getTopUpUserId().equals(sharedAccount.getAccountId())){
                        topUps.add(topUp);
                    }
                }

                topUpSaldoAdapter.setTopUps(topUps);
                topUpSaldoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        NumberFormat format = NumberFormat.getCurrencyInstance();
        etTopUpMoney.setText(format.format(sharedAccount.getAccountAmount()));
    }

    public void clickTopUpSaldo(View view) {
        Intent intent = new Intent(this, TopUpPilihActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeUserActivity.class);
        startActivity(intent);
        finish();
    }
}
