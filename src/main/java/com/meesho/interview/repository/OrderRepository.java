package com.meesho.interview.repository;

import com.meesho.interview.model.Order;

import java.util.concurrent.ConcurrentHashMap;

public class OrderRepository {
    public static ConcurrentHashMap<String, Order> orderRepository = new ConcurrentHashMap<>();
}
