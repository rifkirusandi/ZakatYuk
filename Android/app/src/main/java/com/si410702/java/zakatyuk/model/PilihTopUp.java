package com.si410702.java.zakatyuk.model;

public class PilihTopUp {
    private int gambarPilihTopUp;
    private String namaPilihTopUp;

    public PilihTopUp(int gambarPilihTopUp, String namaPilihTopUp) {
        this.gambarPilihTopUp = gambarPilihTopUp;
        this.namaPilihTopUp = namaPilihTopUp;
    }

    public int getGambarPilihTopUp() {
        return gambarPilihTopUp;
    }

    public String getNamaPilihTopUp() {
        return namaPilihTopUp;
    }
}
