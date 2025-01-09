package com.zeroplusone.data_excel_service.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zeroplusone.data_excel_service.services.ExcelService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
    public ResponseEntity<ByteArrayResource> downloadItems() throws IOException {

        ByteArrayResource resource = new ByteArrayResource(
                Files.readAllBytes(Paths.get(excelService.downloadAllItemsInExcel().getAbsolutePath())));
        // Resource

        return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=items.xlsx")
                .body(resource);

    }

    @GetMapping("/orders")
    public ResponseEntity<ByteArrayResource> downloadOrders() throws IOException {

        ByteArrayResource resource = new ByteArrayResource(
                Files.readAllBytes(Paths.get(excelService.downloadAllOrdersInExcel().getAbsolutePath())));

        return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=items.xlsx")
                .body(resource);

    }

}
