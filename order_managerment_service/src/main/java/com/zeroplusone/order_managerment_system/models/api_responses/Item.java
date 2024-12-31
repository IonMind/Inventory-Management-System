package com.zeroplusone.order_managerment_system.models.api_responses;

// import com.fasterxml.jackson.annotation.JsonIgnore;

// import jakarta.persistence.Embeddable;
// import lombok.AccessLevel;
import lombok.AllArgsConstructor;
// import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
// import lombok.Setter;
import lombok.ToString;

// @Builder
@Getter
// @Setter
@NoArgsConstructor
@AllArgsConstructor
// @Embeddable
@ToString
public class Item  {
    
    // @Setter(value = AccessLevel.NONE)
    private Long id;


    // @Setter(value = AccessLevel.NONE)
    private String name;
    
    
    // @Setter(value = AccessLevel.NONE)
    private Double price;

    // @JsonIgnore
    private Integer stock;

    // @Override
    // public boolean equals(Object obj) {
    //     if (this == obj)
    //         return true;
    //     if (obj == null)
    //         return false;
    //     if (getClass() != obj.getClass())
    //         return false;
    //     Item other = (Item) obj;
    //     if (itemName == null) {
    //         if (other.itemName != null)
    //             return false;
    //     } else if (!itemName.equals(other.itemName))
    //         return false;
    //     return true;
    // }


    
}
