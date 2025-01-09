package com.zeroplusone.data_excel_service.models;

import java.util.Date;
import java.util.List;

public class Order implements ApiResponse {

    public Long orderId;
    public Long itemId;
    public String deliveryAddress;
    public String status;
    public Date purchaseDate;

    @Override
    public List<String> getAllValuesInListString() {
        return List.of("" + orderId, itemId.toString(), deliveryAddress, status, purchaseDate.toString());
    }

}
