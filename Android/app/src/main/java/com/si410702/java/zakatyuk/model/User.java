package com.si410702.java.zakatyuk.model;

public class User {
    private String id, email, username, saldo, telp, alamat, jenis;

    public User() {
    }

    public User(String id, String email, String username, String saldo, String telp, String alamat, String jenis) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.saldo = saldo;
        this.telp = telp;
        this.alamat = alamat;
        this.jenis = jenis;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getSaldo() {
        return saldo;
    }

    public String getId() {
        return id;
    }

    public String getTelp() {
        return telp;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getJenis() {
        return jenis;
    }
}
