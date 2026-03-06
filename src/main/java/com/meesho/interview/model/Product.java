package com.meesho.interview.model;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class Product {
    private String productId;
    private String productName;
    private Integer initialProductCount;

    public Product(String productId, String productName, Integer initialProductCount) {
        this.productId = productId;
        this.productName = productName;
        this.initialProductCount = initialProductCount;
    }
}
