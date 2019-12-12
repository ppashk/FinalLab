package com.lab.service;

import com.lab.dao.EntityDao;
import com.lab.entity.User;
import com.lab.enums.DaoType;
import com.lab.factory.DaoFactory;
import com.lab.web.form.RegistrationForm;
import com.lab.web.form.mapper.FormEntityMapper;

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

    public Optional<User> findByLogin(String login) {
        List<User> all = userDao.getAll();

        return all.stream()
                .filter(u -> u.getUsername().equals(login))
                .findFirst();
    }

    public boolean isExist(String login) {
        List<User> all = userDao.getAll();

        return all.stream()
                .anyMatch(u -> u.getUsername().equals(login));
    }

    public Optional<User> createUser(RegistrationForm registrationForm) {
        boolean result = userDao.create(getFormEntityMapper().map(registrationForm));

        return result ?
                findByLogin(registrationForm.getUsername())
                : Optional.empty();
    }

    public User getUser(int id) {
        return userDao.getById(id, false);
    }

    public FormEntityMapper<User, RegistrationForm> getFormEntityMapper() {
        return form -> new User(0, form.getUsername(),
                form.getPassword(),
                form.getRole());
    }

    public void setUserDao(EntityDao<User> userDao) {
        this.userDao = userDao;
    }
}
