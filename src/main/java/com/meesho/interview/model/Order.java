package com.meesho.interview.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Order {
    private String orderId;
    private String productId;
    private Integer quantity;
    private OrderStatus status;
    private String userId;

    public Order(String productId, int quantity, OrderStatus status, String userId) {
        this.orderId =  UUID.randomUUID().toString();
        this.productId = productId;
        this.quantity = quantity;
        this.status = status;
        this.userId = userId;
    }
}
