package com.zeroplusone.items_inventory_service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zeroplusone.items_inventory_service.models.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    public List<Item> findByNameContaining(String name);
    
}
