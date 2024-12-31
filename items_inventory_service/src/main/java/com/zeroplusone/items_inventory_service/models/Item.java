package com.zeroplusone.items_inventory_service.models;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(value = AccessLevel.NONE)
    private Long id;

    @NotNull
    @Setter(value = AccessLevel.NONE)
    private String name;

    @NotNull
    private Double price;

    @NotNull
    private Integer stock;

    @CreationTimestamp
    @Setter(value = AccessLevel.NONE)
    private Date creationDate;

    private Boolean isItemDeleted = false;

    public Item updateItem(Double newPrice, Integer updatedStock) {
        if (newPrice != null) {
            if (newPrice > 0) {
                this.setPrice(newPrice);
            }
        }
        if (updatedStock != null) {
            if (updatedStock > 0) {
                this.setStock(updatedStock);
            }
        }
        return this;
    }

    public Item markItemDeleted() {
        this.setIsItemDeleted(true);
        return this;
    }

}
