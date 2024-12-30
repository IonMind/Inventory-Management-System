package com.zeroplusone.order_managerment_system.services;


import com.zeroplusone.order_managerment_system.models.NewOrder;
import com.zeroplusone.order_managerment_system.models.Order;

public interface OrderService {
    public Order placeOrder(NewOrder newOrder);
    public Order updateOrderStatus(Long id, String status);
    public Order getOrderDetails(Long id);
}
