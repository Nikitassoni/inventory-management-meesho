package com.meesho.interview.service;

import com.meesho.interview.exception.InventoryLimitExceedException;
import com.meesho.interview.exception.InventoryNotFoundException;
import com.meesho.interview.exception.OrderNotFoundException;
import com.meesho.interview.model.Inventory;
import com.meesho.interview.model.Order;
import com.meesho.interview.model.OrderStatus;
import com.meesho.interview.repository.InventoryRepository;
import com.meesho.interview.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class InventoryService {

    private static final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(10);

    private static final ConcurrentHashMap<String, ScheduledFuture<?>> releaseTasks = new ConcurrentHashMap<>();

    public Integer getInventory(String productId) {
        String key = productId;
        Inventory inventory = InventoryRepository.inventoryRepository.get(key);
        if (inventory == null)
            throw new InventoryNotFoundException();
        return inventory.getQuantity().get();

    }


    public Boolean updateInventory(String productId, Integer count) {
        String key = productId;
        Inventory inventory = InventoryRepository.inventoryRepository.get(key);
        if (inventory == null)
            throw new InventoryNotFoundException();
        inventory.getQuantity().addAndGet(count);
        return true;
    }

    /*
    Will be called when the user initiates payment for an order. Block the inventory for the given product and for the given order reference for 5min
    */


    public Boolean blockInventory(String productId, Integer quantity, String orderId) {
        Inventory inventory = InventoryRepository.inventoryRepository.get(productId);
        if (inventory == null) throw new InventoryNotFoundException();

        AtomicInteger qty = inventory.getQuantity();

        // Atomic subtraction (CAS)
        while (true) {
            int current = qty.get();
            int newCount = current - quantity;
            if (newCount < 0) throw new InventoryLimitExceedException();
            if (qty.compareAndSet(current, newCount)) break;
        }

        // Schedule release after 5 minutes
        Runnable releaseTask = () -> {
            Order o = OrderRepository.orderRepository.get(orderId);
            if (o == null || o.getStatus() != OrderStatus.Completed) {
                qty.addAndGet(quantity);
            }
            releaseTasks.remove(orderId);
        };

        ScheduledFuture<?> future = scheduler.schedule(releaseTask, 5, TimeUnit.MINUTES);
        releaseTasks.put(orderId, future);

        return true;
    }

    public Boolean confirmOrder(String orderId) {
        Order order = OrderRepository.orderRepository.get(orderId);
        if (order == null) throw new OrderNotFoundException();
        order.setStatus(OrderStatus.Completed);

        ScheduledFuture<?> future = releaseTasks.remove(orderId);
        if (future != null) future.cancel(false);

        return true;
    }
}

