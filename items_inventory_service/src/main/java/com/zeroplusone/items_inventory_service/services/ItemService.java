package com.zeroplusone.items_inventory_service.services;

import com.zeroplusone.items_inventory_service.models.Item;

public interface ItemService {
    public Item getItem(Long id);
    public Item saveItem(Item item);
    public Item updateItem(Long id, Double newPrice, Integer updatedStock);
    public Item deleteItem(Long id);
}
