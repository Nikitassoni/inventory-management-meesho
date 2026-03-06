package com.meesho.interview.repository;

import com.meesho.interview.model.Product;

import java.util.concurrent.ConcurrentHashMap;

public class ProductRepository {
    public static ConcurrentHashMap<String, Product> productRepository = new ConcurrentHashMap<>();
}
