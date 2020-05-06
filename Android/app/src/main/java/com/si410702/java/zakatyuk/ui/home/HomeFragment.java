package com.si410702.java.zakatyuk.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.si410702.java.zakatyuk.R;
import com.si410702.java.zakatyuk.sharedpreference.SharedAccount;

public class HomeFragment extends Fragment {

    private TextView tvName;

    private SharedAccount sharedAccount;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvName = view.findViewById(R.id.tv_name_home_user);

        sharedAccount = new SharedAccount(view.getContext());

        tvName.setText(sharedAccount.getAccountName());
    }
}
