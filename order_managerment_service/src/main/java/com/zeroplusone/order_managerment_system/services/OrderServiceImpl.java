package com.zeroplusone.order_managerment_system.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeroplusone.order_managerment_system.Utils.Helpers;
import com.zeroplusone.order_managerment_system.models.Order;
import com.zeroplusone.order_managerment_system.models.Order.STATUS;
import com.zeroplusone.order_managerment_system.models.api_requests.NewOrder;
import com.zeroplusone.order_managerment_system.models.api_responses.Item;
import com.zeroplusone.order_managerment_system.repositories.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    @Transactional
    public Order placeOrder(NewOrder newOrder) throws RuntimeException {
        Item item = recieveitem(newOrder.getItemId());
        if (item.getStock() < 1) {
            throw new IndexOutOfBoundsException("Item is not available in stock right now");
        }
        Order orderToPlace = Order.builder()
                .deliveryAddress(newOrder.getDeliveryAddress())
                .itemId(item.getId())
                .status(STATUS.PLACED)
                .build();

        orderRepository.save(orderToPlace);
        if (!updateItemStock(orderToPlace.getItemId(), item.getStock() - 1)) {
            throw new RuntimeException(
                    "Order not processed due to backend error -\n Error id : 10021(itemstock not updating )");
        }
        return orderToPlace;
    }

    @Override
    @Transactional
    public Order updateOrderStatus(Long orderId, STATUS newStatus) {
        Order order = this.findBy(orderId);
        Item item = recieveitem(order.getItemId());
        if (!order.changeStatus(newStatus)) {
            throw new IllegalArgumentException("Cannot change status from " + order.getStatus() + " to " + newStatus);
        }
        if (newStatus == STATUS.CANCELLED || newStatus == STATUS.RETURNED) {
            updateItemStock(order.getItemId(), item.getStock() + 1);
        }
        return order;
    }

    @Override
    public Order getOrderDetails(Long id) {
        return this.findBy(id);
    }

    private Order findBy(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No Order found with id :" + id));
    }

    ////////////////////// call to external api to recieve item

    private Item recieveitem(Long itemId) throws IndexOutOfBoundsException {

        Item recievedItem = Helpers.restClient.get()
                .uri("/item/" + itemId)
                .retrieve()
                .body(Item.class);

        return recievedItem;

    }

    private Boolean updateItemStock(Long itemId, Integer newStock) {
        return Helpers.restClient.put()
                .uri(uriBuilder -> uriBuilder.path("/item/" + itemId)
                        .queryParam("updatedStock", newStock)
                        .queryParam("newPrice", "")
                        .build())

                .retrieve()
                .toBodilessEntity().getStatusCode().is2xxSuccessful();

    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

}
