package com.meesho.interview.service;


import com.meesho.interview.exception.ProductAlreadyExistsException;
import com.meesho.interview.model.Inventory;
import com.meesho.interview.model.Product;
import com.meesho.interview.repository.InventoryRepository;
import com.meesho.interview.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProductService {

    public void addProduct(String productId, String name, Integer count) {
        Product newProduct = new Product(productId, name, count);
        Product existing = ProductRepository.productRepository.putIfAbsent(productId, newProduct);

        Inventory inventory = InventoryRepository.inventoryRepository.putIfAbsent(productId, new Inventory(productId, new AtomicInteger(count)));
        if(existing != null)
            throw new ProductAlreadyExistsException();
        return;

    }
}
