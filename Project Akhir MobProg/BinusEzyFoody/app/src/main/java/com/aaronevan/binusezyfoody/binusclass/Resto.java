package com.aaronevan.binusezyfoody.binusclass;

public class Resto {
    private Integer IdResto;
    private String nama;
    private double LongResto;
    private double LatResto;

    public Resto(Integer idResto, String nama, double longResto, double latResto) {
        IdResto = idResto;
        this.nama = nama;
        LongResto = longResto;
        LatResto = latResto;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getIdResto() {
        return IdResto;
    }

    public void setIdResto(Integer idResto) {
        IdResto = idResto;
    }

    public double getLongResto() {
        return LongResto;
    }

    public void setLongResto(double longResto) {
        LongResto = longResto;
    }

    public double getLatResto() {
        return LatResto;
    }

    public void setLatResto(double latResto) {
        LatResto = latResto;
    }
}
