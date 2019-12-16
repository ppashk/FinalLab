package com.lab.dao;

import com.lab.entity.ReportLine;
import org.apache.log4j.Logger;

import java.util.List;

public class ReportLineDao extends AbstractDao<ReportLine> {
    private static final Logger LOG = Logger.getLogger(LineDao.class);

    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String QUANTITY = "quantity";
    private static final String REPORT_ID = "reportId";

    private static final String SELECT_ALL = "SELECT * FROM reportLine";

    private static final String INSERT_INTO = "INSERT INTO reportLine ("
            + NAME + ", "
            + PRICE + ", "
            + QUANTITY + ", "
            + REPORT_ID + ") VALUE (?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE reportLine SET "
            + NAME + "= ?, "
            + PRICE + "= ?, "
            + QUANTITY + "= ?, "
            + REPORT_ID + "= ? WHERE "
            + ID + " = ?";

    private static final String DELETE = "DELETE FROM reportLine "
            + "WHERE " + ID + " = ?";

    @Override
    public ReportLine getById(int id, boolean full) {
        return getById("SELECT * FROM reportLine WHERE reportId = ?",
                ps -> ps.setInt(1, id),
                getMapper());
    }

    @Override
    public List<ReportLine> getAll(){
        return getAll(SELECT_ALL, getMapper());
    }

    private EntityMapper<ReportLine> getMapper() {
        return resultSet -> new ReportLine(resultSet.getInt(ID),
                resultSet.getString(NAME),
                resultSet.getInt(PRICE),
                resultSet.getInt(QUANTITY),
                resultSet.getInt(REPORT_ID)
        );
    }

    @Override
    public boolean create(ReportLine entity) {
        LOG.debug("Create report line: + " + entity);
        return createUpdate(INSERT_INTO, ps -> {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getPrice());
            ps.setInt(3, entity.getQuantity());
            ps.setInt(4, entity.getReportId());
        });
    }

    @Override
    public boolean update(ReportLine entity) {
        LOG.debug("Update report line: " + entity);
        return createUpdate(UPDATE, ps -> {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getPrice());
            ps.setInt(3, entity.getQuantity());
            ps.setInt(4, entity.getReportId());
            ps.setInt(5, entity.getId());
        });
    }

    @Override
    public boolean remove(ReportLine entity){
        LOG.debug("Delete report line: " + entity);
        return createUpdate(DELETE, ps -> ps.setInt(1, entity.getId()));
    }
}
