package com.zeroplusone.order_managerment_system.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Item implements AutoCloseable {
    
    @Setter(value = AccessLevel.NONE)
    private Long itemId;


    @Setter(value = AccessLevel.NONE)
    private String itemName;
    
    
    @Setter(value = AccessLevel.NONE)
    private Long price;

    @JsonIgnore
    private Integer quantity;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Item other = (Item) obj;
        if (itemName == null) {
            if (other.itemName != null)
                return false;
        } else if (!itemName.equals(other.itemName))
            return false;
        return true;
    }

    @Override
    public void close() throws Exception {
        System.out.println("closed");
    }

    
}
