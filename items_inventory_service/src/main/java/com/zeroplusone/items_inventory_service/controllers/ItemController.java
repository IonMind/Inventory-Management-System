package com.zeroplusone.items_inventory_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zeroplusone.items_inventory_service.models.Item;
import com.zeroplusone.items_inventory_service.services.ItemService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import com.zeroplusone.items_inventory_service.configuration.utils.Constants;

@RestController
@RequestMapping(Constants.PUBLIC_API_BASE_PATH)
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Long id) {
        return ResponseEntity.ok().body(itemService.getItem(id));
    }

    @PostMapping()
    public ResponseEntity<Item> saveItem(@RequestBody Item item) {
        return ResponseEntity.ok().body(itemService.saveItem(item));
    }

    @PutMapping(value = "/{id}", params = { "updatedStock", "newPrice" })
    public ResponseEntity<Item> updateItem(@PathVariable Long id,
            @RequestParam(required = false, defaultValue = "") Integer updatedStock,
            @RequestParam(required = false, defaultValue = "") Double newPrice) {
        return ResponseEntity.ok().body(itemService.updateItem(id, newPrice, updatedStock));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        return ResponseEntity.ok().body("Following Item is delete \n " + itemService.deleteItem(id));
    }

}
