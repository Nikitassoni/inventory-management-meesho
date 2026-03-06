package com.meesho.interview.repository;

import com.meesho.interview.model.Inventory;

import java.util.concurrent.ConcurrentHashMap;

public class InventoryRepository {
    public static ConcurrentHashMap<String, Inventory> inventoryRepository = new ConcurrentHashMap<>();
}
