package com.si410702.java.zakatyuk.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

import com.si410702.java.zakatyuk.ui.profile.ProfileFragment;

public class SharedAccount {
    private static final String PREFS_NAME = "account_pref";

    private final String KEY_ACCOUNT_ID = "key_account_id";
    private final String KEY_ACCOUNT_NAME = "key_account_name";
    private final String KEY_ACCOUNT_AMOUNT = "key_account_amount";
    private final String KEY_ACCOUNT_EMAIL = "key_account_email";
    private final String KEY_ACCOUNT_TELP = "key_account_telp";
    private final String KEY_ACCOUNT_ALAMAT = "key_account_alamat";
    private final String KEY_ACCOUNT_JENIS = "key_account_jenis";

    private final String KEY_ACCOUNT_STATUS = "key_account_status";

    private final SharedPreferences preferences;

    public SharedAccount(Context context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void setAccountId(String id){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_ACCOUNT_ID, id);
        editor.apply();
    }

    public void setAccountName(String name){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_ACCOUNT_NAME, name);
        editor.apply();
    }

    public void setAccountAmount(int amount){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(KEY_ACCOUNT_AMOUNT, amount);
        editor.apply();
    }

    public void setAccountEmail(String  email){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_ACCOUNT_EMAIL, email);
        editor.apply();
    }

    public void setAccountStatus(boolean status){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY_ACCOUNT_STATUS, status);
        editor.apply();
    }

    public void setAccountAlamat(String alamat){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_ACCOUNT_ALAMAT, alamat);
        editor.apply();
    }

    public void setAccountTelp(String telp){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_ACCOUNT_TELP, telp);
        editor.apply();
    }

    public void setAccountJenis(String jenis){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_ACCOUNT_JENIS, jenis);
        editor.apply();
    }

    public String getAccountId(){
        return preferences.getString(KEY_ACCOUNT_ID, "");
    }

    public String getAccountName(){
        return preferences.getString(KEY_ACCOUNT_NAME, "");
    }

    public int getAccountAmount(){
        return preferences.getInt(KEY_ACCOUNT_AMOUNT, 0);
    }

    public String getAccountEmail(){
        return preferences.getString(KEY_ACCOUNT_EMAIL, "");
    }

    public boolean getAccountStatus(){
        return preferences.getBoolean(KEY_ACCOUNT_STATUS, false);
    }

    public String getAccountAlamat(){
        return preferences.getString(KEY_ACCOUNT_ALAMAT, "");
    }

    public String getAccountTelp(){
        return preferences.getString(KEY_ACCOUNT_TELP, "");
    }

    public String getAccountJenis(){
        return preferences.getString(KEY_ACCOUNT_JENIS, "");
    }
}
