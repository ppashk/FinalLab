package com.lab.dao;

import com.lab.entity.Report;
import com.lab.enums.ReportType;
import org.apache.log4j.Logger;

import java.util.List;

public class ReportDao extends AbstractDao<Report> {
    private static final Logger LOG = Logger.getLogger(ReceiptDao.class);

    private static final String TOTAL_PRICE = "totalPrice";
    private static final String IS_CLOSED = "isClosed";
    private static final String REPORT_TYPE = "reportType";
    private static final String USERNAME = "username";

    private static final String SELECT_ALL = "SELECT * FROM `report`";

    private static final String INSERT_INTO = "INSERT INTO `report` ("
            + TOTAL_PRICE + ", "
            + IS_CLOSED + ", "
            + REPORT_TYPE + ", "
            + USERNAME + ") VALUE (?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE `report` SET"
            + TOTAL_PRICE + "= ?, "
            + IS_CLOSED + "= ?, "
            + REPORT_TYPE + "= ?, "
            + USERNAME + "= ? WHERE "
            + ID + " = ?";

    private static final String DELETE = "DELETE FROM `report` "
            + "WHERE " + ID + " = ?";


    @Override
    public Report getById(int id, boolean full) {
        return getById("SELECT * FROM `report` WHERE id = ?",
                ps -> ps.setInt(1, id),
                getMapper());
    }

    @Override
    public List<Report> getAll(){
        return getAll(SELECT_ALL, getMapper());
    }

    private EntityMapper<Report> getMapper() {
        return resultSet -> new Report(resultSet.getInt(ID),
                resultSet.getInt(TOTAL_PRICE),
                resultSet.getBoolean(IS_CLOSED),
                ReportType.valueOf(resultSet.getString(REPORT_TYPE)),
                resultSet.getString(USERNAME)
        );
    }

    @Override
    public boolean create(Report entity) {
        LOG.debug("Create report: + " + entity);
        return createUpdate(INSERT_INTO, ps -> {
            ps.setInt(1, entity.getTotalPrice());
            ps.setBoolean(2, entity.isClosed());
            ps.setString(3, entity.getReportType().toString());
            ps.setString(4, entity.getUsername());
        });
    }

    @Override
    public boolean update(Report entity) {
        LOG.debug("Update report: " + entity);
        return createUpdate(UPDATE, ps -> {
            ps.setInt(1, entity.getTotalPrice());
            ps.setBoolean(2, entity.isClosed());
            ps.setString(3, entity.getReportType().toString());
            ps.setString(4, entity.getUsername());
            ps.setInt(5, entity.getId());
        });
    }

    @Override
    public boolean remove(Report entity){
        LOG.debug("Delete report: " + entity);
        return createUpdate(DELETE, ps -> ps.setInt(1, entity.getId()));
    }
}
