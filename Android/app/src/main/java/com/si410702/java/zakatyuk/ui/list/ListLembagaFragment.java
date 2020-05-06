package com.si410702.java.zakatyuk.ui.list;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.si410702.java.zakatyuk.AlokasiZakatMenu;
import com.si410702.java.zakatyuk.InfoZakatActivity;
import com.si410702.java.zakatyuk.R;
import com.si410702.java.zakatyuk.sharedpreference.SharedAccount;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListLembagaFragment extends Fragment {

    private TextView tvName;

    private SharedAccount sharedAccount;

    public ListLembagaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_lembaga, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvName = view.findViewById(R.id.tv_name_list_lembaga);
        CardView cvAlokasi = view.findViewById(R.id.cv_alokasi);

        sharedAccount = new SharedAccount(view.getContext());

        tvName.setText(sharedAccount.getAccountName());

        cvAlokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AlokasiZakatMenu.class);
                view.getContext().startActivity(intent);
            }
        });
    }
}
