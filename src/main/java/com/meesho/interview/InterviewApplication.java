package com.meesho.interview;

import com.meesho.interview.model.Order;
import com.meesho.interview.service.InventoryService;
import com.meesho.interview.service.ProductService;
import com.meesho.interview.service.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InterviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterviewApplication.class, args);
        System.out.println("Hello World");

        ProductService productService = new ProductService();
        InventoryService inventoryService = new InventoryService();
        OrderService orderService = new OrderService();


        //Product 1
        productService.addProduct("1", "Product 1", 10);
        inventoryService.updateInventory("1", 10);
         Integer inventory1Count = inventoryService.getInventory("1");
        System.out.println("Inventory of Product1 -" +  inventory1Count);
        Order order1  = orderService.placeOrder("1", 1, "1");

        inventoryService.blockInventory("1", 1, order1.getOrderId());
        inventory1Count = inventoryService.getInventory("1");
        System.out.println("Inventory of Product1 -" +  inventory1Count);

        inventoryService.confirmOrder(order1.getOrderId());

        inventory1Count = inventoryService.getInventory("1");
        System.out.println("Inventory of Product1 -" +  inventory1Count);









    }

}
