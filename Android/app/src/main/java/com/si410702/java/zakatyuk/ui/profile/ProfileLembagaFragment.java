package com.si410702.java.zakatyuk.ui.profile;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

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
import com.si410702.java.zakatyuk.sharedpreference.SharedAccount;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileLembagaFragment extends Fragment {

    private SharedAccount sharedAccount;

    private FirebaseAuth firebaseAuth;

    private TextView textViewOpenProfile, tvName;
    private TextView ketName, ketEmail, ketAlamat, ketTelp;

    private ImageView ivLogout, ivProfile;

    public ProfileLembagaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_lembaga, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewOpenProfile = view.findViewById(R.id.tv_openprofilelemabaga);
        tvName = view.findViewById(R.id.tv_name_profile_lembaga);
        ivLogout = view.findViewById(R.id.iv_home_profile_lmg_logout);
        ivProfile = view.findViewById(R.id.img_profile_lembaga);

        ketName = view.findViewById(R.id.tv_name_lembaga);
        ketEmail = view.findViewById(R.id.tv_email_lembaga);
        ketAlamat = view.findViewById(R.id.tv_alamat_lembaga);
        ketTelp = view.findViewById(R.id.tv_telp_lembaga);

        firebaseAuth = FirebaseAuth.getInstance();
        sharedAccount = new SharedAccount(view.getContext());

        tvName.setText(sharedAccount.getAccountName());
        ketName.setText(sharedAccount.getAccountName());
        ketEmail.setText(sharedAccount.getAccountEmail());
        ketAlamat.setText(sharedAccount.getAccountAlamat());
        ketTelp.setText(sharedAccount.getAccountTelp());

        textViewOpenProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intentOpenProfile = new Intent(view.getContext(), UpdateUserAccount.class);
                intentOpenProfile.putExtra("jenis","lembaga");
                view.getContext().startActivity(intentOpenProfile);
            }
        });

        ivLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();
            }
        });
    }

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
    public void onResume() {
        super.onResume();
        tvName.setText(sharedAccount.getAccountName());
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Glide.with(this)
                .load(user.getDisplayName())
                .into(ivProfile);
    }
}
