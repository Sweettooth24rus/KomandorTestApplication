package com.kkoz.komandortestapplication.model.tables;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

public class ShoppingTable {
    private String product;
    private Integer amount;
    private BigDecimal cost;
    private BigDecimal sumCost;

    public ShoppingTable(String product, Integer amount, BigDecimal cost, BigDecimal sumCost) {
        this.product = product;
        this.amount = amount;
        this.cost = cost;
        this.sumCost = sumCost;
    }

    public ShoppingTable(GoodsTable origin) {
        this.product = origin.getProduct();
        this.amount = 1;
        this.cost = origin.getCost();
        this.sumCost = origin.getCost();
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getSumCost() {
        return sumCost;
    }

    public void setSumCost(BigDecimal sumCost) {
        this.sumCost = sumCost;
    }

    public boolean equals(GoodsTable item) {
        return product.equals(item.getProduct());
    }

    public void incAmount() {
        amount++;
        sumCost = sumCost.add(cost);
    }

    public Boolean decAmount() {
        amount--;
        sumCost = sumCost.subtract(cost);
        return amount.equals(0);
    }
}

