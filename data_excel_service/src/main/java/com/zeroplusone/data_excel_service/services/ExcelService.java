package com.zeroplusone.data_excel_service.services;

import java.io.ByteArrayOutputStream;

public interface ExcelService {

    public ByteArrayOutputStream downloadAllOrdersInExcel();

    public ByteArrayOutputStream downloadAllItemsInExcel();
}
