package com.zeroplusone.data_excel_service.services;

import java.io.File;

public interface ExcelService {

    public File downloadAllOrdersInExcel();

    public File downloadAllItemsInExcel();
}
