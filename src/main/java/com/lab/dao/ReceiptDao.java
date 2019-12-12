package com.lab.dao;

import com.lab.entity.Receipt;
import org.apache.log4j.Logger;

import java.util.List;

public class ReceiptDao extends AbstractDao<Receipt> {
    private static final Logger LOG = Logger.getLogger(ReceiptDao.class);

    private static final String TOTAL_PRICE = "totalPrice";
    private static final String IS_CLOSED = "isClosed";
    private static final String SELECT_ALL = "SELECT * FROM receipt";

    private static final String INSERT_INTO = "INSERT INTO receipt ("
            + TOTAL_PRICE + ", "
            + IS_CLOSED + ") VALUE (?, ?)";

    private static final String UPDATE = "UPDATE receipt SET"
            + TOTAL_PRICE + "= ?, "
            + IS_CLOSED + "= ? WHERE "
            + ID + " = ?";

    private static final String DELETE = "DELETE FROM receipt "
            + "WHERE " + ID + " = ?";


    @Override
    public Receipt getById(int id, boolean full) {
        return getById("SELECT * FROM receipt WHERE id = ?",
                ps -> ps.setInt(1, id),
                getMapper());
    }

    @Override
    public List<Receipt> getAll(){
        return getAll(SELECT_ALL, getMapper());
    }

    private EntityMapper<Receipt> getMapper() {
        return resultSet -> new Receipt(resultSet.getInt(ID),
                resultSet.getInt(TOTAL_PRICE),
                resultSet.getBoolean(IS_CLOSED)
        );
    }

    @Override
    public boolean create(Receipt entity) {
        LOG.debug("Create receipt: + " + entity);
        return createUpdate(INSERT_INTO, ps -> {
            ps.setInt(1, entity.getTotalPrice());
            ps.setBoolean(2, entity.isClosed());
        });
    }

    @Override
    public boolean update(Receipt entity) {
        LOG.debug("Update receipt: " + entity);
        return createUpdate(UPDATE, ps -> {
            ps.setInt(1, entity.getTotalPrice());
            ps.setBoolean(2, entity.isClosed());
            ps.setInt(3, entity.getId());
        });
    }

    @Override
    public boolean remove(Receipt entity){
        LOG.debug("Delete receipt: " + entity);
        return createUpdate(DELETE, ps -> ps.setInt(1, entity.getId()));
    }
}
