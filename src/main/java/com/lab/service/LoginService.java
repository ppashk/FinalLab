package com.lab.service;

import com.lab.dao.EntityDao;
import com.lab.entity.User;
import com.lab.enums.DaoType;
import com.lab.factory.DaoFactory;

import java.util.List;

public class LoginService {
    private EntityDao<User> userDao;

    public LoginService() {
        this.userDao = DaoFactory.getEntityDao(DaoType.USER);
    }

    public int validateUser(String login, String password){
        List<User> all = userDao.getAll();

        User user = all.stream().filter(u -> u.getUsername().equals(login) && u.getPassword().equals(password))
                .findFirst().orElseGet(User::new);
        return user.getId();
    }

    public User getUser(int id){
        return userDao.getById(id, false);
    }
}
