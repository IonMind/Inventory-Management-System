package com.zeroplusone.order_managerment_system.services;


import java.util.List;

import com.zeroplusone.order_managerment_system.models.Order;
import com.zeroplusone.order_managerment_system.models.Order.STATUS;
import com.zeroplusone.order_managerment_system.models.api_requests.NewOrder;

public interface OrderService {
    public Order placeOrder(NewOrder newOrder);
    public Order updateOrderStatus(Long orderId, STATUS newStatus);
    public Order getOrderDetails(Long id);
    public List<Order> getAllOrders();
}
