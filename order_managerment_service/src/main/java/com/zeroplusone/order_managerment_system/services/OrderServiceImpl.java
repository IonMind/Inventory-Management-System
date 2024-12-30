package com.zeroplusone.order_managerment_system.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeroplusone.order_managerment_system.models.Item;
import com.zeroplusone.order_managerment_system.models.NewOrder;
import com.zeroplusone.order_managerment_system.models.Order;
import com.zeroplusone.order_managerment_system.models.Order.STATUS;
import com.zeroplusone.order_managerment_system.repositories.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    List<Item> itemsInInventory = new ArrayList<>();

    public OrderServiceImpl() {
        itemsInInventory.addAll(
                Arrays.asList(
                        Item.builder().itemId(1L).itemName("Toothpaste").price(222l).quantity(5).build(),
                        Item.builder().itemId(2L).itemName("Brush").price(10L).quantity(5).build(),
                        Item.builder().itemId(3L).itemName("Soap").price(20L).quantity(5).build(),
                        Item.builder().itemId(4L).itemName("Paper").price(65L).quantity(2).build(),
                        Item.builder().itemId(6L).itemName("Ball Pen").price(89L).quantity(5).build(),
                        Item.builder().itemId(7L).itemName("Washing Powder").price(500L).quantity(5).build(),
                        Item.builder().itemId(9L).itemName("Black Shoe").price(1000L).quantity(5).build(),
                        Item.builder().itemId(10L).itemName("White Shoe").price(1000L).quantity(5).build(),
                        Item.builder().itemId(11L).itemName("Sprite").price(50L).quantity(5).build(),
                        Item.builder().itemId(12L).itemName("Chair").price(222l).quantity(2).build(),
                        Item.builder().itemId(13L).itemName("Table").price(2500L).quantity(5).build(),
                        Item.builder().itemId(14L).itemName("Battery").price(850L).quantity(5).build(),
                        Item.builder().itemId(15L).itemName("Shampoo").price(12L).quantity(10).build()));
    }

    @Override
    @Transactional
    public Order placeOrder(NewOrder newOrder) {

        // External api call to recieve item check
        Item recievedItem = checkAndRecieveitem(newOrder.getItemName());

        Order orderToPlace = Order.builder()
                .deliveryAddress(newOrder.getDeliveryAddress())
                .item(recievedItem)
                .status(STATUS.PLACED)
                .build();

        try {
            orderRepository.save(orderToPlace);
            recievedItem.setQuantity(recievedItem.getQuantity() - 1);
        } catch (Exception e) {
            throw e;
        }
        return orderToPlace;
    }

    @Override
    public Order updateOrderStatus(Long id, String status) {
        Order order = this.findBy(id);
        try {
            order.setStatus(STATUS.valueOf(status));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Status value can be only from (case-senstive) : " + EnumSet.allOf(STATUS.class));
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

    private Item checkAndRecieveitem(String itemName) throws IndexOutOfBoundsException {

        try {
            Item storedItem = itemsInInventory.get(itemsInInventory.indexOf(Item.builder().itemName(itemName).build()));

            if (storedItem.getQuantity() == 0) {
                throw new IllegalArgumentException("Item is out of stock");
            }
            return storedItem;
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException(itemName + " not found in database");
        }

    }

}
