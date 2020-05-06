package com.si410702.java.zakatyuk;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.si410702.java.zakatyuk.model.Alokasi;
import com.si410702.java.zakatyuk.model.Bayar;
import com.si410702.java.zakatyuk.model.User;

import java.text.NumberFormat;

public class TrackingActivity extends AppCompatActivity {

    private String bayarId, userId = "";
    TextView tv_jnsZakat, tv_tglBayar, tv_nmnlZakat, tv_nmLembaga, tv_tglPenyerahan, tv_nmPenerima, tv_almtPenerima;
    ImageView ivStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        setTitle("Tracking");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        bayarId = getIntent().getStringExtra("bayarId");

        DatabaseReference refBayar = FirebaseDatabase.getInstance().getReference("Bayar").child(bayarId);
        DatabaseReference refAlokasi = FirebaseDatabase.getInstance().getReference("Alokasi");
        final DatabaseReference refUser = FirebaseDatabase.getInstance().getReference("User");
        refBayar.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Bayar bayar = dataSnapshot.getValue(Bayar.class);

                tv_jnsZakat = findViewById(R.id.tv_jenis_zakat);
                tv_tglBayar = findViewById(R.id.tv_tanggal_bayar);
                tv_nmnlZakat = findViewById(R.id.tv_nominal);
                tv_nmLembaga = findViewById(R.id.tv_nama_lembaga);
                tv_tglPenyerahan = findViewById(R.id.tv_tanggal_penyerahan);
                tv_nmPenerima = findViewById(R.id.tv_penerima);
                tv_almtPenerima = findViewById(R.id.tv_alamat);
                ivStatus = findViewById(R.id.Alokasi);

                tv_jnsZakat.setText(bayar.getJenisZakat());
                tv_tglBayar.setText(bayar.getBayarDate());
                NumberFormat format = NumberFormat.getCurrencyInstance();
                tv_nmnlZakat.setText(format.format(Integer.parseInt(bayar.getBayarNominal())));

                tv_nmLembaga.setText("-");
                tv_tglPenyerahan.setText("-");
                tv_nmPenerima.setText("-");
                tv_almtPenerima.setText("-");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        refAlokasi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Alokasi alokasi = postSnapshot.getValue(Alokasi.class);


                    if (alokasi.getIdBayar().equals(bayarId)) {

                        userId = alokasi.getLembagaId();
                        tv_tglPenyerahan.setText(alokasi.getAlokasiDate());
                        tv_nmPenerima.setText(alokasi.getAlokasiNamaPenerima());
                        tv_almtPenerima.setText(alokasi.getAlokasiAlamatPenerima());
                        ivStatus.setImageResource(R.drawable.image_ctg);

                        refUser.child(userId).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                User user = dataSnapshot.getValue(User.class);

                                tv_nmLembaga.setText(user.getUsername());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
