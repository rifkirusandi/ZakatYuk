package com.si410702.java.zakatyuk.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.si410702.java.zakatyuk.R;
import com.si410702.java.zakatyuk.adapter.PenerimaZakatAdapter;
import com.si410702.java.zakatyuk.sharedpreference.SharedAccount;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeLembagaFragment extends Fragment {

    private TextView tvName;
    private RecyclerView rvZakat;

    private SharedAccount sharedAccount;

    public HomeLembagaFragment() {
        // Required empty public constructor
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_lembaga, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvName = view.findViewById(R.id.tv_name_home_lembaga);
        rvZakat = view.findViewById(R.id.rv_penerima_zakat);

        ArrayList<String> penerima = new ArrayList<>();
        penerima.add("Fakir");
        penerima.add("Miskin");
        penerima.add("Amil");
        penerima.add("Muallaf");
        penerima.add("Riqab");
        penerima.add("Gharimin");
        penerima.add("Fi sabilillah");


        rvZakat.setLayoutManager(new LinearLayoutManager(getContext()));
        rvZakat.setAdapter(new PenerimaZakatAdapter(penerima));

        sharedAccount = new SharedAccount(view.getContext());

        tvName.setText(sharedAccount.getAccountName());
    }
}
