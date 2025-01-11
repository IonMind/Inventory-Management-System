package com.zeroplusone.data_excel_service.models;

import java.util.Date;
import java.util.List;

import lombok.Getter;

@Getter
public class Item implements ApiResponse {
    public Long id;
    public String name;
    public Double price;
    public Integer stock;
    public Date creationDate;
    public Boolean isItemDeleted;

    public List<String> getAllValuesInListString() {
        return List.of(""+id, name, price.toString(), stock.toString(), creationDate.toString(),
                isItemDeleted.toString());
    }
}
