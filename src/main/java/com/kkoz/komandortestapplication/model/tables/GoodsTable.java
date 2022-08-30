package com.kkoz.komandortestapplication.model.tables;

import java.math.BigDecimal;

public class GoodsTable {
    private String product;
    private BigDecimal cost;

    public GoodsTable(String product, BigDecimal cost) {
        this.product = product;
        this.cost = cost;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}

