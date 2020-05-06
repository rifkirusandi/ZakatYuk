package com.si410702.java.zakatyuk.model;

public class Bayar{

    private String bayarId, jenisZakat, bayarNominal, bayarUserId, bayarDate, bayarTime, bayarStatus;

    public Bayar() {
    }

    public Bayar(String bayarId, String jenisZakat, String bayarNominal, String bayarUserId, String bayarDate, String bayarTime, String bayarStatus) {
        this.bayarId = bayarId;
        this.jenisZakat = jenisZakat;
        this.bayarNominal = bayarNominal;
        this.bayarUserId = bayarUserId;
        this.bayarDate = bayarDate;
        this.bayarTime = bayarTime;
        this.bayarStatus = bayarStatus;
    }

    public String getBayarId() { return bayarId; }

    public String getJenisZakat() { return jenisZakat; }

    public String getBayarNominal() { return bayarNominal; }

    public String getBayarUserId() { return bayarUserId; }

    public String getBayarDate() { return bayarDate; }

    public String getBayarTime() { return bayarTime; }

    public String getBayarStatus() {
        return bayarStatus;
    }
}