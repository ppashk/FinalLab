package com.lab.factory;

import com.lab.service.*;

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
