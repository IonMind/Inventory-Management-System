package com.zeroplusone.items_inventory_service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zeroplusone.items_inventory_service.models.Item;
import com.zeroplusone.items_inventory_service.services.ItemService;
import com.zeroplusone.items_inventory_service.configuration.utils.Constants;

@RestController
@RequestMapping(Constants.INTERNAL_API_BASE_PATH)
public class ItemsInternalController {
    @Autowired
    ItemService itemService;

    @GetMapping("/items/all")
    public ResponseEntity<List<Item>> getAllItems() {
        return ResponseEntity.ok().body(itemService.getAllItems());
    }
}
