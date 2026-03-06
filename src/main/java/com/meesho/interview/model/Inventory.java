package com.meesho.interview.model;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class Inventory {
    private String productId;
    private AtomicInteger quantity;

    public Inventory(String productId, AtomicInteger quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
