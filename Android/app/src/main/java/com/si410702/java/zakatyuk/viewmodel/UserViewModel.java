package com.si410702.java.zakatyuk.viewmodel;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.si410702.java.zakatyuk.model.User;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {

    private MutableLiveData<ArrayList<User>> userLiveData = new MutableLiveData<>();
    public MutableLiveData<ArrayList<User>> getUserLiveData() {
        return userLiveData;
    }


    public void setData(){
        final ArrayList<User> users = new ArrayList<>();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("User");
        ValueEventListener valueListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    final User user = postSnapshot.getValue(User.class);
                    users.add(user);
                }

                userLiveData.postValue(users);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("gagal bos", databaseError.getMessage());
            }
        };
        userRef.addListenerForSingleValueEvent(valueListener);
    }
}
