package com.zeroplusone.order_managerment_system.services;


import com.zeroplusone.order_managerment_system.models.NewOrder;
import com.zeroplusone.order_managerment_system.models.Order;
import com.zeroplusone.order_managerment_system.models.Order.STATUS;

public interface OrderService {
    public Order placeOrder(NewOrder newOrder);
    public Order updateOrderStatus(Long orderId, STATUS newStatus);
    public Order getOrderDetails(Long id);
}
