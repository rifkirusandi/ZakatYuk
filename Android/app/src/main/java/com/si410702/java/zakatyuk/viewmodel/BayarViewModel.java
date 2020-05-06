package com.si410702.java.zakatyuk.viewmodel;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.si410702.java.zakatyuk.model.Bayar;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BayarViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Bayar>> bayarLiveData = new MutableLiveData<>();
    public MutableLiveData<ArrayList<Bayar>> getBayarLiveData() {
        return bayarLiveData;
    }


    public void setData(final String jenis){
        final ArrayList<Bayar> bayars = new ArrayList<>();
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Bayar");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    final Bayar userBayar = postSnapshot.getValue(Bayar.class);

                    if(userBayar.getJenisZakat().equalsIgnoreCase(jenis)){
                        bayars.add(userBayar);
                    }
                }

                bayarLiveData.postValue(bayars);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        myRef.addListenerForSingleValueEvent(valueEventListener);
    }
}
