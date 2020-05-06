package com.si410702.java.zakatyuk.ui.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.si410702.java.zakatyuk.BayarZakatIntro;
import com.si410702.java.zakatyuk.InfoZakatActivity;
import com.si410702.java.zakatyuk.R;
import com.si410702.java.zakatyuk.TopUpSaldoActivity;
import com.si410702.java.zakatyuk.sharedpreference.SharedAccount;

public class ListFragment extends Fragment {

    private CardView cardViewHitung;
    private CardView cardViewTopup;
    private CardView cardViewBayar;

    private TextView tvName;

    private SharedAccount sharedAccount;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvName = view.findViewById(R.id.tv_name_list);
        cardViewHitung = view.findViewById(R.id.cv_hitung);
        cardViewTopup = view.findViewById(R.id.cv_topup);
        cardViewBayar = view.findViewById(R.id.cv_bayar);

        sharedAccount = new SharedAccount(view.getContext());

        tvName.setText(sharedAccount.getAccountName());

        cardViewHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), InfoZakatActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        cardViewTopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent_topup = new Intent(view.getContext(), TopUpSaldoActivity.class);
              view.getContext().startActivity(intent_topup);
            }}
        );

        cardViewBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_bayar = new Intent(view.getContext(), BayarZakatIntro.class);
                view.getContext().startActivity(intent_bayar);
            }
        });
    }
}
