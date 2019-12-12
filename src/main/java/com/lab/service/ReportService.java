package com.lab.service;

import com.lab.dao.EntityDao;
import com.lab.entity.Report;
import com.lab.entity.ReportLine;
import com.lab.entity.User;
import com.lab.enums.DaoType;
import com.lab.enums.ReportType;
import com.lab.enums.Role;
import com.lab.factory.DaoFactory;

import java.util.List;

public class ReportService {
    private EntityDao<Report> reportDao;
    private EntityDao<ReportLine> reportLineDao;

    public ReportService() {
        this.reportDao = DaoFactory.getEntityDao(DaoType.REPORT);
        this.reportLineDao = DaoFactory.getEntityDao(DaoType.REPORT_LINE);
    }

    public List<Report> getXReport() {
        return getReport(ReportType.X_REPORT);
    }

    public List<Report> getZReport() {
        return getReport(ReportType.Z_REPORT);
    }

    private List<Report> getReport(ReportType reportType) {
        List<Report> reports = reportDao.getAll();
        List<ReportLine> reportLines = reportLineDao.getAll();

        for (Report report : reports) {
            if (report.getReportType().equals(reportType)) {
                for (ReportLine reportLine : reportLines) {
                    if (report.getId() == reportLine.getReportId())
                        report.addLine(reportLine);
                }
            } else reports.remove(report);
        }
        return reports;
    }

    public boolean createXReport() {
        closeReport(ReportType.X_REPORT);
        return reportDao.create(new Report(0, false, ReportType.X_REPORT, "chief"));
    }

    public boolean createZReport(User user) {
        return reportDao.create(new Report(0, false, ReportType.Z_REPORT, user.getUsername()));
    }

    public boolean updateReports(String name, int price, int quantity) {
        List<Report> reports = reportDao.getAll();
        for (Report report : reports) {
            if (!report.isClosed()) {
                reportLineDao.create(new ReportLine(name, price, quantity, report.getId()));
                report.setTotalPrice(report.getTotalPrice() + price * quantity);
                reportDao.update(report);
            }
        }
        return true;
    }

    public boolean closeZReport() {
        return closeReport(ReportType.Z_REPORT);
    }

    private boolean closeReport(ReportType reportType) {
        List<Report> reports = reportDao.getAll();
        for (Report report : reports) {
            if (!report.isClosed() && report.getReportType().equals(reportType)) {
                report.setClosed(true);
                return reportDao.update(report);
            }
        }
        return false;
    }
}
