package com.example.quizproject.service;

import com.example.quizproject.dao.UserDao;
import com.example.quizproject.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void createNewUser(String email, String password, String firstname, String lastname) {
        this.userDao.createNewUser(email, password, firstname, lastname);
    }

    public boolean login(String email, String password) {
        User userById = this.userDao.getUserByEmail(email);

        if (userById == null) {
            return false;
        }

        return userById.isIs_active() && userById.getPassword().equals(password);
    }

    public User getUserByEmail(String email) {
        return this.userDao.getUserByEmail(email);
    }

    public List<User> getAllUsers() {
        return this.userDao.getAllUsers();
    }

    public void updateStatusById(int user_id) {
        this.userDao.changeStatusById(user_id);
    }

    public User getUserById(int id) {
        return this.userDao.getUserById(id);
    }




}
