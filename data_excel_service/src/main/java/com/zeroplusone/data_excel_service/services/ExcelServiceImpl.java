package com.zeroplusone.data_excel_service.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDateTime;

import java.util.List;
import java.util.ListIterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.zeroplusone.data_excel_service.models.ApiResponse;
import com.zeroplusone.data_excel_service.models.Item;
import com.zeroplusone.data_excel_service.models.Order;

// import jakarta.persistence.criteria.CriteriaBuilder.In;

@Service
public class ExcelServiceImpl implements ExcelService {
    
    @Override
    public File downloadAllOrdersInExcel() {
        RestClient restClient = RestClient.builder().baseUrl("http://localhost:8001").build();
        return createExcel(restClient.get().uri("/orders/all").retrieve()
        .body(new ParameterizedTypeReference<List<Order>>() {
        }));
    }

    @Override
    public File downloadAllItemsInExcel() {
        RestClient restClient = RestClient.builder().baseUrl("http://localhost:8002").build();
        return createExcel(restClient.get().uri("/item/all").retrieve()
                .body(new ParameterizedTypeReference<List<Item>>() {
                }));

    }

    ///// Creating Generic Excel
    /// for Items and Orders
    ///
    ///
    private <T extends ApiResponse> File createExcel(List<T> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("No Data to export");
        }

        Class<? extends Object> pojoClass = list.get(0).getClass();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(pojoClass.getSimpleName());
        for (int i = -1; i < list.size(); i++) {
            if (i == -1) {
                Row headerRow = sheet.createRow(0);
                int currentHeaderCellIndex = 0;
                for (Field declaredField : pojoClass.getDeclaredFields()) {
                    headerRow.createCell(currentHeaderCellIndex++).setCellValue(declaredField.getName().toUpperCase());

                }
                continue;
            }
            Row row = sheet.createRow(i + 1);
            ListIterator<String> listItr = list.get(i).getAllValuesInListString().listIterator();
            while (listItr.hasNext()) {
                row.createCell(listItr.nextIndex()).setCellValue(listItr.next());
            }

        }
        File file = new File(
                "/mnt/c/Users/achaud54/Desktop/Items_temp_" + LocalDateTime.now().toString() + ".xlsx");
        try {

            workbook.write(new FileOutputStream(file));
            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return file;
    }



}
