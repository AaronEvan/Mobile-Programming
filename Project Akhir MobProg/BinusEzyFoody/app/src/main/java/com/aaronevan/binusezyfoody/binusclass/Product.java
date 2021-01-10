package com.aaronevan.binusezyfoody.binusclass;

import java.io.Serializable;

public class Product implements Serializable {
    private Integer IdProduct;
    private Integer IdResto;
    private String NamaProduct;
    private String JenisProduct;
    private Integer ImageProduct;
    private Integer HargaProduct;
    private Integer StockProduct;
    private Integer QuantityProduct;

    public Product(Integer idProduct, Integer idResto, String namaProduct, String jenisProduct, Integer imageProduct, Integer hargaProduct, Integer stockProduct) {
        IdProduct = idProduct;
        IdResto = idResto;
        NamaProduct = namaProduct;
        JenisProduct = jenisProduct;
        ImageProduct = imageProduct;
        HargaProduct = hargaProduct;
        StockProduct = stockProduct;
        QuantityProduct = 0;
    }

    public Integer getIdProduct() {
        return IdProduct;
    }

    public void setIdProduct(Integer idProduct) {
        IdProduct = idProduct;
    }

    public Integer getIdResto() {
        return IdResto;
    }

    public void setIdResto(Integer idResto) {
        IdResto = idResto;
    }

    public String getNamaProduct() {
        return NamaProduct;
    }

    public void setNamaProduct(String namaProduct) {
        NamaProduct = namaProduct;
    }

    public String getJenisProduct() {
        return JenisProduct;
    }

    public void setJenisProduct(String jenisProduct) {
        JenisProduct = jenisProduct;
    }

    public Integer getImageProduct() {
        return ImageProduct;
    }

    public void setImageProduct(Integer imageProduct) {
        ImageProduct = imageProduct;
    }

    public Integer getHargaProduct() {
        return HargaProduct;
    }

    public void setHargaProduct(Integer hargaProduct) {
        HargaProduct = hargaProduct;
    }

    public Integer getStockProduct() {
        return StockProduct;
    }

    public void setStockProduct(Integer stockProduct) {
        StockProduct = stockProduct;
    }

    public Integer getQuantityProduct() {
        return QuantityProduct;
    }

    public void setQuantityProduct(Integer quantityProduct) {
        QuantityProduct = quantityProduct;
    }
}
