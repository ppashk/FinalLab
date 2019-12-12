package com.lab.dao;

import com.lab.entity.Product;
import org.apache.log4j.Logger;

import java.util.List;

public class ProductDao extends AbstractDao<Product> {
    private static final Logger LOG = Logger.getLogger(ProductDao.class);

    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String QUANTITY = "quantity";

    private static final String SELECT_ALL = "SELECT * FROM storage";

    private static final String INSERT_INTO = "INSERT INTO storage ("
            + NAME + ", "
            + PRICE + ", "
            + QUANTITY + ") VALUE (?, ?, ?)";

    private static final String UPDATE = "UPDATE storage SET"
            + NAME + "= ?, "
            + PRICE + "= ?, "
            + QUANTITY + "= ? WHERE "
            + ID + " = ?";

    private static final String DELETE = "DELETE FROM storage "
            + "WHERE " + ID + " = ?";

    @Override
    public Product getById(int id, boolean full) {
        return getById("SELECT * FROM storage WHERE id = ?",
                ps -> ps.setInt(1, id),
                getMapper());
    }

    @Override
    public List<Product> getAll(){
        return getAll(SELECT_ALL, getMapper());
    }

    private EntityMapper<Product> getMapper() {
        return resultSet -> new Product(resultSet.getInt(ID),
                resultSet.getString(NAME),
                resultSet.getInt(PRICE),
                resultSet.getInt(QUANTITY)
        );
    }

    @Override
    public boolean create(Product entity) {
        LOG.debug("Create product: + " + entity);
        return createUpdate(INSERT_INTO, ps -> {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getPrice());
            ps.setInt(3, entity.getQuantity());
        });
    }

    @Override
    public boolean update(Product entity) {
        LOG.debug("Update storage: " + entity);
        return createUpdate(UPDATE, ps -> {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getPrice());
            ps.setInt(3, entity.getQuantity());
            ps.setInt(4, entity.getId());
        });
    }

    @Override
    public boolean remove(Product entity){
        LOG.debug("Delete storage: " + entity);
        return createUpdate(DELETE, ps -> ps.setInt(1, entity.getId()));
    }
}
