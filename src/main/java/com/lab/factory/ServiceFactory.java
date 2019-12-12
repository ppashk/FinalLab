package com.lab.factory;

import com.lab.service.ReceiptService;
import com.lab.service.ReportService;
import com.lab.service.StorageService;
import com.lab.service.UserService;

public class ServiceFactory {
    private static StorageService storageService = new StorageService();
    private static ReceiptService receiptService = new ReceiptService();
    private static ReportService reportService = new ReportService();
    private static UserService userService = new UserService();

    private ServiceFactory() {
    }

    public static StorageService getStorageService() {
        return storageService;
    }

    public static ReceiptService getReceiptService() {
        return receiptService;
    }

    public static ReportService getReportService() {
        return reportService;
    }

    public static UserService getUserService() {
        return userService;
    }
}
