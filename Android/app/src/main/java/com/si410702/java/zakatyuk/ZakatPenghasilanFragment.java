package com.si410702.java.zakatyuk;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.si410702.java.zakatyuk.adapter.AlokasiZakatListAdapter;
import com.si410702.java.zakatyuk.model.Alokasi;
import com.si410702.java.zakatyuk.model.Bayar;
import com.si410702.java.zakatyuk.model.User;
import com.si410702.java.zakatyuk.viewmodel.AlokasiViewModel;
import com.si410702.java.zakatyuk.viewmodel.BayarViewModel;
import com.si410702.java.zakatyuk.viewmodel.UserViewModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ZakatPenghasilanFragment extends Fragment {
    private RecyclerView rvUserListBayar;
    private AlokasiZakatListAdapter alokasiZakatListAdapter;
    private ArrayList<Bayar> bayarUserList = new ArrayList<>();
    private ArrayList<User> userList = new ArrayList<>();
    private ArrayList<Alokasi> alokasiList = new ArrayList<>();
    private BayarViewModel bayarViewModel;
    private UserViewModel userViewModel;
    private AlokasiViewModel alokasiViewModel;


    public ZakatPenghasilanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bayarViewModel = ViewModelProviders.of(this).get(BayarViewModel.class);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        alokasiViewModel = ViewModelProviders.of(this).get(AlokasiViewModel.class);
        return inflater.inflate(R.layout.tab_zakat_penghasilan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvUserListBayar = view.findViewById(R.id.rv_list_zakatpenghasilan);
        rvUserListBayar.setHasFixedSize(true);

        alokasiZakatListAdapter = new AlokasiZakatListAdapter();
        rvUserListBayar.setLayoutManager(new LinearLayoutManager(getContext()));
        rvUserListBayar.setAdapter(alokasiZakatListAdapter);

        bayarViewModel.setData("zakat penghasilan");
        bayarViewModel.getBayarLiveData().observe(getActivity(), getBayarProfile);
        userViewModel.setData();
        userViewModel.getUserLiveData().observe(getActivity(), getUserProfile);
        alokasiViewModel.setData();
        alokasiViewModel.getalokasiLiveData().observe(getActivity(), getAlokasiProfile);
    }

    private final Observer<ArrayList<Bayar>> getBayarProfile = new Observer<ArrayList<Bayar>>() {
        @Override
        public void onChanged(@Nullable ArrayList<Bayar> userBayarArrayList) {
            if (userBayarArrayList != null) {
                alokasiZakatListAdapter.setUserListBayar(userBayarArrayList);
                alokasiZakatListAdapter.notifyDataSetChanged();

            }

        }
    };

    private final Observer<ArrayList<User>> getUserProfile = new Observer<ArrayList<User>>() {
        @Override
        public void onChanged(@Nullable ArrayList<User> userArrayList) {
            if (userArrayList != null) {
                alokasiZakatListAdapter.setUserList(userArrayList);
                alokasiZakatListAdapter.notifyDataSetChanged();

            }

        }
    };

    private final Observer<ArrayList<Alokasi>> getAlokasiProfile = new Observer<ArrayList<Alokasi>>() {
        @Override
        public void onChanged(@Nullable ArrayList<Alokasi> alokasiArrayList) {
            if (alokasiArrayList != null) {
                alokasiZakatListAdapter.setAlokasiList(alokasiArrayList);
                alokasiZakatListAdapter.notifyDataSetChanged();

            }

        }
    };

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() { super.onResume(); }
}
