package com.aaronevan.binusezyfoody.binusclass;

import java.io.Serializable;

public class HistoryHeader implements Serializable {
    private Integer IdTransaksi;
    private Integer IdUser;
    private String Alamat;
    private String Tanggal;
    private Integer Total;

    public HistoryHeader(Integer idTransaksi, Integer idUser, String alamat, String tanggal, Integer total) {
        IdTransaksi = idTransaksi;
        IdUser = idUser;
        Alamat = alamat;
        Tanggal = tanggal;
        Total = total;
    }

    public Integer getIdTransaksi() {
        return IdTransaksi;
    }

    public void setIdTransaksi(Integer idTransaksi) {
        IdTransaksi = idTransaksi;
    }

    public Integer getIdUser() {
        return IdUser;
    }

    public void setIdUser(Integer idUser) {
        IdUser = idUser;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String alamat) {
        Alamat = alamat;
    }

    public String getTanggal() {
        return Tanggal;
    }

    public void setTanggal(String tanggal) {
        Tanggal = tanggal;
    }

    public Integer getTotal() {
        return Total;
    }

    public void setTotal(Integer total) {
        Total = total;
    }
}
