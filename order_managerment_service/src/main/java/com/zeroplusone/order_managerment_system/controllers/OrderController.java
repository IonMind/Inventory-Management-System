package com.zeroplusone.order_managerment_system.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zeroplusone.order_managerment_system.models.NewOrder;
import com.zeroplusone.order_managerment_system.models.Order;
import com.zeroplusone.order_managerment_system.models.Order.STATUS;
import com.zeroplusone.order_managerment_system.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping(path = "/save")
    public ResponseEntity<Order> placeOrder(@RequestBody NewOrder newOrder) {

        return ResponseEntity.ok().body(orderService.placeOrder(newOrder));
    }

    @PutMapping(path = "/{id}", params = "status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestParam STATUS status) {

        return ResponseEntity.ok().body(orderService.updateOrderStatus(id, status));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderDetails(@PathVariable Long orderId) {
        return ResponseEntity.ok().body(orderService.getOrderDetails(orderId));
    }

}
