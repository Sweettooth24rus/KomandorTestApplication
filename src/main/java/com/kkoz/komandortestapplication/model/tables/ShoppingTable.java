package com.kkoz.komandortestapplication.model.tables;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;


@Data
public class ShoppingTable {
    private String product;
    private Integer amount;
    private UUID productId;
    private BigDecimal cost;
    private BigDecimal sumCost;

    public ShoppingTable(GoodsTable origin) {
        this.product = origin.getProduct();
        this.productId = origin.getProductId();
        this.amount = 1;
        this.cost = origin.getCost();
        this.sumCost = origin.getCost();
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

