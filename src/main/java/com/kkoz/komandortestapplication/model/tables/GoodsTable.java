package com.kkoz.komandortestapplication.model.tables;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;


@AllArgsConstructor
@Data
public class GoodsTable {
    private String product;
    private UUID productId;
    private BigDecimal cost;

}

