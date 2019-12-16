package com.lab.entity;

public class ReportLine extends Product {
    private int reportId;

    public ReportLine(String name, int price, int quantity, int reportId) {
        super(name, price, quantity);
        this.reportId = reportId;
    }

    public ReportLine(int id, String name, int price, int quantity, int reportId) {
        super(id, name, price, quantity);
        this.reportId = reportId;
    }

    public int getReportId() {
        return reportId;
    }
}
