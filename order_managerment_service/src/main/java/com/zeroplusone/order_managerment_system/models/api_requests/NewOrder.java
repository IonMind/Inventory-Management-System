package com.zeroplusone.order_managerment_system.models.api_requests;


import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class NewOrder {
    Long itemId;
    String deliveryAddress;
}
