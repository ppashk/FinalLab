package com.lab.dao;

import com.lab.entity.User;
import com.lab.enums.Role;
import org.apache.log4j.Logger;

import java.util.List;

public class UserDao extends AbstractDao<User>{
    private static final Logger LOG = Logger.getLogger(UserDao.class);

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role";

    private static final String SELECT_ALL = "SELECT * FROM `user`";

    private static final String INSERT_INTO = "INSERT INTO `user` ("
            + USERNAME + ", "
            + PASSWORD + ", "
            + ROLE + ") VALUE (?, ?, ?)";

    private static final String UPDATE = "UPDATE `user` SET "
            + USERNAME + "= ?, "
            + PASSWORD + "= ?, "
            + ROLE + "= ? WHERE "
            + ID + " = ?";

    private static final String DELETE = "DELETE FROM `user` "
            + "WHERE " + ID + " = ?";

    @Override
    public User getById(int id, boolean full) {
        return getById("SELECT * FROM `user` WHERE id = ?",
                ps -> ps.setInt(1, id),
                getMapper());
    }

    @Override
    public List<User> getAll(){
        return getAll(SELECT_ALL, getMapper());
    }

    private EntityMapper<User> getMapper() {
        return resultSet -> new User(resultSet.getInt(ID),
                resultSet.getString(USERNAME),
                resultSet.getString(PASSWORD),
                Role.valueOf(resultSet.getString(ROLE)));
    }

    @Override
    public boolean create(User entity) {
        LOG.debug("Create user: + " + entity);
        return createUpdate(INSERT_INTO, ps -> {
            ps.setString(1, entity.getUsername());
            ps.setString(2, entity.getPassword());
            ps.setString(3, entity.getRole().toString());
        });
    }

    @Override
    public boolean update(User entity) {
        LOG.debug("Update user: " + entity);
        return createUpdate(UPDATE, ps -> {
            ps.setString(1, entity.getUsername());
            ps.setString(2, entity.getPassword());
            ps.setString(3, entity.getRole().toString());
            ps.setInt(6, entity.getId());
        });
    }

    @Override
    public boolean remove(User entity){
        LOG.debug("Delete user: " + entity);
        return createUpdate(DELETE, ps -> ps.setInt(1, entity.getId()));
    }
}
