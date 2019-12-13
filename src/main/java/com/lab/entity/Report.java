package com.lab.entity;

import com.lab.enums.ReportType;

import java.util.ArrayList;
import java.util.List;

public class Report {
    private int id;
    private int totalPrice;
    private boolean isClosed;
    private ReportType reportType;
    private String username;
    private List<ReportLine> reportLines;

    public Report() {
    }

    public Report(int totalPrice, boolean isClosed, ReportType reportType, String username) {
        this.totalPrice = totalPrice;
        this.isClosed = isClosed;
        this.reportType = reportType;
        this.username = username;
        this.reportLines = new ArrayList<>();
    }

    public Report(int id, int totalPrice, boolean isClosed, ReportType reportType, String username) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.isClosed = isClosed;
        this.reportType = reportType;
        this.username = username;
        this.reportLines = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ReportLine> getReportLines() {
        return reportLines;
    }

    public void setReportLines(List<ReportLine> reportLines) {
        this.reportLines = reportLines;
    }

    public void addLine(ReportLine reportLine) {
        reportLines.add(reportLine);
    }
}
