package com.zeroplusone.order_managerment_system.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "CartOrders")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Order {

    public enum STATUS {
        PLACED, DELIVERED, CANCELLED, SHIPPING, RETURNED;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(value = AccessLevel.NONE)
    private Long orderId;

    @NotNull
    @Setter(value = AccessLevel.NONE)
    private Long itemId;

    @NotNull
    @Setter(value = AccessLevel.NONE)
    private String deliveryAddress;

    @NotNull
    private STATUS status;

    private String purchaseDate;

    @PrePersist
    public void prePersist() {
        this.purchaseDate = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public Boolean changeStatus(STATUS newStatus) {
        Boolean isStatusConditionSatisfied = false;
        switch (newStatus) {
            case CANCELLED:
                if (this.status == STATUS.PLACED || this.status == STATUS.SHIPPING) {
                    isStatusConditionSatisfied = true;
                }
                break;
            case DELIVERED:
                if (this.status == STATUS.SHIPPING) {
                    isStatusConditionSatisfied = true;
                }
                break;
            case SHIPPING:
                if (this.status == STATUS.PLACED) {
                    isStatusConditionSatisfied = true;
                }
                break;
            case RETURNED:
                if (this.status == STATUS.DELIVERED) {
                    isStatusConditionSatisfied = true;
                }
            default:
                break;
        }
        if (isStatusConditionSatisfied) {
            this.setStatus(newStatus);
        }

        return isStatusConditionSatisfied;
    }

}
