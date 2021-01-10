package com.aaronevan.binusezyfoody.binusclass;

public class HistoryDetail {
    private Integer IdDetail;
    private Integer IdTransaction;
    private Integer IdProduct;
    private Integer Qty;

    public HistoryDetail(Integer idDetail, Integer idTransaction, Integer idProduct, Integer qty) {
        IdDetail = idDetail;
        IdTransaction = idTransaction;
        IdProduct = idProduct;
        Qty = qty;
    }

    public Integer getIdDetail() {
        return IdDetail;
    }

    public void setIdDetail(Integer idDetail) {
        IdDetail = idDetail;
    }

    public Integer getIdTransaction() {
        return IdTransaction;
    }

    public void setIdTransaction(Integer idTransaction) {
        IdTransaction = idTransaction;
    }

    public Integer getIdProduct() {
        return IdProduct;
    }

    public void setIdProduct(Integer idProduct) {
        IdProduct = idProduct;
    }

    public Integer getQty() {
        return Qty;
    }

    public void setQty(Integer qty) {
        Qty = qty;
    }
}
