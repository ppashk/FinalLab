package com.lab.dao;

import com.lab.persistance.DataSourceConnectionPool;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T> implements EntityDao<T> {
    private static final Logger LOG = Logger.getLogger(AbstractDao.class);

    protected static final String ID = "id";

    @Override
    public T getById(int id, boolean full) {
        return null;
    }

    @Override
    public List<T> getAll(boolean full) {
        return null;
    }

    public T getById(String query, StatementMapper<T> statementMapper, EntityMapper<T> mapper){
        T result = null;

        try (PreparedStatement preparedStatement = DataSourceConnectionPool.getPreparedStatement(query);) {
            statementMapper.map(preparedStatement);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    result = mapper.map(resultSet);
                }
            }
        } catch (SQLException e) {
            LOG.error("Exception while getting all entities", e);
        }

        return result;
    }

    public List<T> getAll(String query, EntityMapper<T> mapper) {
        List<T> result = new ArrayList<>();

        try (PreparedStatement preparedStatement = DataSourceConnectionPool.getPreparedStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                T entity = mapper.map(resultSet);

                result.add(entity);
            }

        } catch (SQLException e) {
            LOG.error("Exception while getting all entities", e);
        }

        return result;
    }

    public boolean createUpdate(String query, StatementMapper<T> statementMapper){
        try(PreparedStatement preparedStatement = DataSourceConnectionPool.getPreparedStatement(query);) {
            statementMapper.map(preparedStatement);

            int result = preparedStatement.executeUpdate();

            return result == 1;
        } catch (SQLException e) {
            LOG.error("Could not create entity!!", e);
        }

        return false;
    }
}
