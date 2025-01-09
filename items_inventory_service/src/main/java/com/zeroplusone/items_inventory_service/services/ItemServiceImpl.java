package com.zeroplusone.items_inventory_service.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeroplusone.items_inventory_service.models.Item;
import com.zeroplusone.items_inventory_service.repositories.ItemRepository;

import jakarta.transaction.Transactional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Override
    public Item getItem(Long id) {
        return this.findBy(id);
    }

    @Override
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    // @SuppressWarnings("null")
    @Override
    @Transactional
    public Item updateItem(Long id, Double newPrice, Integer updatedStock) {
        if (newPrice == null && updatedStock == null) {

            throw new IllegalArgumentException("Need atleast one positive value from 'price' or 'stock' to update");
        }

        return this.findBy(id).updateItem(newPrice, updatedStock);
    }

    @Override
    @Transactional
    public Item deleteItem(Long id) {
        Item itemToMarkDelete = this.findBy(id);
        if (itemToMarkDelete.getIsItemDeleted()) {
            throw new IllegalArgumentException("Item with id '"+id+"' is alreday marked deleted");
        }
        return itemToMarkDelete.markItemDeleted();
    }

    private Item findBy(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No Item with id : " + id));
    }

    @Override
    public List<Item> getAllItems() {
       return itemRepository.findAll();
    }

}
