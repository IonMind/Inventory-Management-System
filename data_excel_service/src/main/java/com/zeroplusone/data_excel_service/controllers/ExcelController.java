package com.zeroplusone.data_excel_service.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zeroplusone.data_excel_service.services.ExcelService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/download/excel")
public class ExcelController {

    @Autowired
    ExcelService excelService;

    @GetMapping("/items")
    public ResponseEntity<Resource> downloadItems() throws IOException {

        return ResponseEntity.ok()
                .contentType(
                        MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=items.xlsx")
                .body(new ByteArrayResource(excelService.downloadAllItemsInExcel().toByteArray()));

    }

    @GetMapping("/orders")
    public ResponseEntity<ByteArrayResource> downloadOrders() throws IOException {

        return ResponseEntity.ok()
                .contentType(
                        MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=orders.xlsx")
                .body(new ByteArrayResource(excelService.downloadAllOrdersInExcel().toByteArray()));

    }

}
