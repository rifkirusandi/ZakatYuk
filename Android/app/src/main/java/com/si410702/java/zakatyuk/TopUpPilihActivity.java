package com.si410702.java.zakatyuk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;

import com.si410702.java.zakatyuk.adapter.TopUpPilihAdapter;
import com.si410702.java.zakatyuk.model.PilihTopUp;

import java.util.ArrayList;

public class TopUpPilihActivity extends AppCompatActivity {

    private ArrayList<PilihTopUp> pilihTopUps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up_pilih);

        getSupportActionBar().setTitle("Top Up Zakat");

        RecyclerView rvListPilihTopUp = findViewById(R.id.rv_topup_saldo_pilih);

        getDataSaldo();

        rvListPilihTopUp.setLayoutManager(new LinearLayoutManager(this));
        rvListPilihTopUp.setAdapter(new TopUpPilihAdapter(pilihTopUps));
    }

    private void getDataSaldo() {
        String[] pilihName = getResources()
                .getStringArray(R.array.list_top_up_name);
        TypedArray pilihImage =
                getResources().obtainTypedArray(R.array.list_top_up_image);

        pilihTopUps.clear();

        for(int i=0;i<pilihName.length;i++){
            pilihTopUps.add(new PilihTopUp(pilihImage.getResourceId(i,0), pilihName[i]));
        }

        pilihImage.recycle();
    }
}
