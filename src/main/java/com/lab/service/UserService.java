package com.lab.service;

import com.lab.dao.EntityDao;
import com.lab.entity.User;
import com.lab.enums.DaoType;
import com.lab.enums.Role;
import com.lab.factory.DaoFactory;

import java.util.List;
import java.util.Optional;

public class UserService {
    private EntityDao<User> userDao;

    public UserService() {
        this.userDao = DaoFactory.getEntityDao(DaoType.USER);
    }

    public Optional<User> validateUser(String login, String password) {
        List<User> all = userDao.getAll();

        return all.stream()
                .filter(u -> u.getUsername().equals(login)
                        && u.getPassword().equals(password))
                .findFirst();
    }

    public boolean isCashier(User user) {
        return user.getRole().equals(Role.CASHIER);
    }
}
