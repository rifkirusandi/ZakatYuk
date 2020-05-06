package com.si410702.java.zakatyuk;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.si410702.java.zakatyuk.model.User;
import com.si410702.java.zakatyuk.sharedpreference.SharedAccount;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateUserAccount extends AppCompatActivity {

    private SharedAccount sharedAccount;
    private StorageReference mStorageRef;

    private EditText etNama, etEmail, etAlamat, etTelp;
    private CircleImageView ivGambar;

    private ProgressDialog pgDialog;

    private String jenis;
    private static final int GALLERY_PICTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_account);

        sharedAccount = new SharedAccount(this);
        mStorageRef = FirebaseStorage.getInstance().getReference();

        pgDialog = new ProgressDialog(this);

        etNama = findViewById(R.id.account_user_name);
        etEmail = findViewById(R.id.account_user_email);
        etAlamat = findViewById(R.id.account_user_alamat);
        etTelp = findViewById(R.id.account_user_telp);
        ivGambar = findViewById(R.id.account_user_image);

        setTitle("Edit Profile");
        etNama.setText(sharedAccount.getAccountName());
        etEmail.setText(sharedAccount.getAccountEmail());
        etAlamat.setText(sharedAccount.getAccountAlamat());
        etTelp.setText(sharedAccount.getAccountTelp());

        etEmail.setEnabled(false);

        jenis = getIntent().getStringExtra("jenis");

        setImage();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        5);
            }
        }
    }

    private void setImage() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String name = user.getDisplayName();
            System.out.println("Ini adalah " + name);
            Glide.with(this)
                    .load(name)
                    .apply(new RequestOptions().override(200, 200))
                    .into(ivGambar);
        }
    }

    public void saveProfile(View view) {

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Update Profile");
        alertDialog.setMessage("Apakah anda yakin untuk update profile?");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Tidak",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Iya",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        updateProfile();
                    }
                });
        alertDialog.show();

    }

    private void updateProfile() {
        String name = etNama.getText().toString();
        String alamat = etAlamat.getText().toString();
        String notelp = etTelp.getText().toString();

        DatabaseReference drUser = FirebaseDatabase.getInstance().getReference("User").child(sharedAccount.getAccountId());
        User user = new User(sharedAccount.getAccountId(), sharedAccount.getAccountEmail(), name,
                String.valueOf(sharedAccount.getAccountAmount()), notelp, alamat, jenis);
        drUser.setValue(user);
        drUser.getDatabase();

        sharedAccount.setAccountName(name);
        sharedAccount.setAccountTelp(notelp);
        sharedAccount.setAccountAlamat(alamat);

        finish();
        startActivity(getIntent());
    }


    public void changeProfilePic(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, GALLERY_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == GALLERY_PICTURE) {
            if (data != null) {
                Uri filePath = data.getData();

                if (filePath != null) {
                    pgDialog.setMessage("Uploaded ...");
                    pgDialog.show();

                    StorageReference riversRef = mStorageRef.child("images/" + sharedAccount.getAccountId() + "/profile.jpg");
                    riversRef.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                            result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String imageUrl = uri.toString();
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                            .setDisplayName(imageUrl)
                                            .build();

                                    user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(getApplicationContext(), "Berhasil Update Foto", Toast.LENGTH_LONG).show();
                                            }
                                            pgDialog.dismiss();
                                            finish();
                                            startActivity(getIntent());
                                        }
                                    });
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                            pgDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    });
                }
            }
        }
    }
}
