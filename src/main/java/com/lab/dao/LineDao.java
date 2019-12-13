package com.lab.dao;

import com.lab.entity.Line;
import org.apache.log4j.Logger;

import java.util.List;

public class LineDao extends AbstractDao<Line> {
    private static final Logger LOG = Logger.getLogger(LineDao.class);

    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String QUANTITY = "quantity";
    private static final String RECEIPT_ID = "receiptId";

    private static final String SELECT_ALL = "SELECT * FROM line";

    private static final String INSERT_INTO = "INSERT INTO line ("
            + NAME + ", "
            + PRICE + ", "
            + QUANTITY + ", "
            + RECEIPT_ID + ") VALUE (?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE line SET "
            + NAME + "= ?, "
            + PRICE + "= ?, "
            + QUANTITY + "= ?, "
            + RECEIPT_ID + "= ? WHERE "
            + ID + " = ?";

    private static final String DELETE = "DELETE FROM line "
            + "WHERE " + ID + " = ?";

    @Override
    public Line getById(int id, boolean full) {
        return getById("SELECT * FROM line WHERE id = ?",
                ps -> ps.setInt(1, id),
                getMapper());
    }

    @Override
    public List<Line> getAll(){
        return getAll(SELECT_ALL, getMapper());
    }

    private EntityMapper<Line> getMapper() {
        return resultSet -> new Line(resultSet.getInt(ID),
                resultSet.getString(NAME),
                resultSet.getInt(PRICE),
                resultSet.getInt(QUANTITY),
                resultSet.getInt(RECEIPT_ID)
        );
    }

    @Override
    public boolean create(Line entity) {
        LOG.debug("Create line: + " + entity);
        return createUpdate(INSERT_INTO, ps -> {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getPrice());
            ps.setInt(3, entity.getQuantity());
            ps.setInt(4, entity.getReceiptId());
        });
    }

    @Override
    public boolean update(Line entity) {
        LOG.debug("Update product: " + entity);
        return createUpdate(UPDATE, ps -> {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getPrice());
            ps.setInt(3, entity.getQuantity());
            ps.setInt(4, entity.getReceiptId());
            ps.setInt(5, entity.getId());
        });
    }

    @Override
    public boolean remove(Line entity){
        LOG.debug("Delete product: " + entity);
        return createUpdate(DELETE, ps -> ps.setInt(1, entity.getId()));
    }
}
