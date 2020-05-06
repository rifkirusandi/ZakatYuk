package com.si410702.java.zakatyuk.ui.profile;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.si410702.java.zakatyuk.LoginActivity;
import com.si410702.java.zakatyuk.R;
import com.si410702.java.zakatyuk.UpdateUserAccount;
import com.si410702.java.zakatyuk.adapter.HistoryZakatAdapter;
import com.si410702.java.zakatyuk.model.Bayar;
import com.si410702.java.zakatyuk.sharedpreference.SharedAccount;

import java.util.ArrayList;

import com.si410702.java.zakatyuk.viewmodel.ProfileViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private RecyclerView rvHistory;

    private SharedAccount sharedAccount;

    private HistoryZakatAdapter historyZakatAdapter;

    private FirebaseAuth firebaseAuth;

    private ArrayList<Bayar> bayars = new ArrayList<>();

    private TextView textViewOpenProfile;
    private TextView tvName;

    private ImageView ivLogout;
    private ImageView ivProfile;

    private ProfileViewModel profileViewModel;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewOpenProfile = view.findViewById(R.id.tv_openprofile);
        tvName = view.findViewById(R.id.tv_name_profile_user);
        ivLogout = view.findViewById(R.id.iv_home_profile_user_logout);
        ivProfile = view.findViewById(R.id.img_profile_user);

        firebaseAuth = FirebaseAuth.getInstance();

        rvHistory = view.findViewById(R.id.rv_history_zakat);
        rvHistory.setHasFixedSize(true);

        sharedAccount = new SharedAccount(view.getContext());

        historyZakatAdapter = new HistoryZakatAdapter();

        rvHistory.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rvHistory.setAdapter(historyZakatAdapter);

        textViewOpenProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentOpenProfile = new Intent(view.getContext(), UpdateUserAccount.class);
                intentOpenProfile.putExtra("jenis", "user");
                view.getContext().startActivity(intentOpenProfile);
            }
        });

        ivLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();
            }
        });

        profileViewModel.setData(sharedAccount.getAccountId());
        profileViewModel.getBayarLiveData().observe(getActivity(), getProfile);
    }

    private final Observer<ArrayList<Bayar>> getProfile = new Observer<ArrayList<Bayar>>() {
        @Override
        public void onChanged(@Nullable ArrayList<Bayar> bayarArrayList) {
            if (bayarArrayList != null) {
                historyZakatAdapter.setBayar(bayarArrayList);
                historyZakatAdapter.notifyDataSetChanged();
            }

        }
    };

    private void check() {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle("Log Out");
        alertDialog.setMessage("Apakah anda ingin Logout?");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Tidak",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Iya",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        firebaseAuth.signOut();

                        Intent intentOpenProfile = new Intent(getContext(), LoginActivity.class);
                        getActivity().finish();
                        getContext().startActivity(intentOpenProfile);
                    }
                });
        alertDialog.show();

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();

        tvName.setText(sharedAccount.getAccountName());

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Glide.with(this)
                .load(user.getDisplayName())
                .into(ivProfile);
    }
}
