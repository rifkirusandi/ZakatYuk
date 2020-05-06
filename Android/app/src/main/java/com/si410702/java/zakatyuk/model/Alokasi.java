package com.si410702.java.zakatyuk.model;

public class Alokasi {

    private String alokasiId, lembagaId, alokasiDate, alokasiNamaPenerima, alokasiAlamatPenerima, alokasiTelpPenerima, pembayarId, idBayar;

    public Alokasi() {

    }

    public Alokasi(String alokasiId,String lembagaId,String alokasiDate,String alokasiNamaPenerima,
                   String alokasiAlamatPenerima,String alokasiTelpPenerima,String pembayarId, String idBayar){
        this.alokasiId = alokasiId;
        this.lembagaId = lembagaId;
        this.alokasiDate = alokasiDate;
        this.alokasiNamaPenerima = alokasiNamaPenerima;
        this.alokasiAlamatPenerima = alokasiAlamatPenerima;
        this.alokasiTelpPenerima = alokasiTelpPenerima;
        this.pembayarId = pembayarId;
        this.idBayar = idBayar;
    }

    public String getAlokasiId() {
        return alokasiId;
    }

    public String getLembagaId() {
        return lembagaId;
    }

    public String getAlokasiDate() {
        return alokasiDate;
    }

    public String getAlokasiNamaPenerima() {
        return alokasiNamaPenerima;
    }

    public String getAlokasiAlamatPenerima() {
        return alokasiAlamatPenerima;
    }

    public String getAlokasiTelpPenerima() {
        return alokasiTelpPenerima;
    }

    public String getPembayarId() {
        return pembayarId;
    }

    public String getIdBayar() {
        return idBayar;
    }
}
