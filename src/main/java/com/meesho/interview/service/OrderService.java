package com.meesho.interview.service;

import com.meesho.interview.exception.OrderAlreadyExistsException;
import com.meesho.interview.exception.OrderNotFoundException;
import com.meesho.interview.model.Order;
import com.meesho.interview.model.OrderStatus;
import com.meesho.interview.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {


    public Order placeOrder(String productId, Integer quantity, String userId){
        Order neworder = new Order(productId, quantity, OrderStatus.InProgress, userId);
        Order existing =  OrderRepository.orderRepository.putIfAbsent(neworder.getOrderId(), neworder);
        if(existing!=null){
            throw new OrderAlreadyExistsException();
        }
        return neworder;
    }


    /*
    Will be called when the user completes payment for his order.
    Reduce the ordered quantity permanently for the product corresponding to given orderId. If this method is not called within 5min from blockInventory, inventory should be released back
    */
    //whe the payment is completed ---


    //in the case payment fails ---
}
