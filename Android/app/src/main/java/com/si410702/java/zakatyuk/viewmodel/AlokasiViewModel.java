package com.si410702.java.zakatyuk.viewmodel;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.si410702.java.zakatyuk.model.Alokasi;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AlokasiViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Alokasi>> alokasiLiveData = new MutableLiveData<>();
    public MutableLiveData<ArrayList<Alokasi>> getalokasiLiveData() {
        return alokasiLiveData;
    }


    public void setData(){
        final ArrayList<Alokasi> alokasis = new ArrayList<>();
        DatabaseReference alokasiRef = FirebaseDatabase.getInstance().getReference("Alokasi");
        ValueEventListener alokasiValueListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    final Alokasi alokasi = postSnapshot.getValue(Alokasi.class);
                    alokasis.add(alokasi);
                }

                alokasiLiveData.postValue(alokasis);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("gagal bos", databaseError.getMessage());
            }
        };
        alokasiRef.addListenerForSingleValueEvent(alokasiValueListener);
    }
}
