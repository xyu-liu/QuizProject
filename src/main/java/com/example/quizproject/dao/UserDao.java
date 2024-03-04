package com.example.quizproject.dao;

import com.example.quizproject.dao.rowMapper.UserRowMapper;
import com.example.quizproject.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    JdbcTemplate jdbcTemplate;
    UserRowMapper userRowMapper;

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserDao() {
    }

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate, UserRowMapper userRowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = userRowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public User getUserById(int id) {
        String query = "SELECT * From user WHERE user_id = ?";
        List<User> users = jdbcTemplate.query(query, userRowMapper, id);
        return users.size() == 0 ? null : users.get(0);
    }

    public User getUserByEmail(String email) {
        String query = "SELECT * From user WHERE email = ?";
        List<User> users = jdbcTemplate.query(query, userRowMapper, email);
        return users.size() == 0 ? null : users.get(0);
    }

    public List<User> getAllUsers() {
        String query = "SELECT * From user";
        List<User> users = jdbcTemplate.query(query, userRowMapper);
        return users;
    }

    public void createNewUser(String email, String password, String firstname, String lastname) {
        String query = "INSERT INTO user (email, password, firstname, lastname) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query, email, password, firstname, lastname);
    }

    public void changeStatusById(int user_id) {
        String query = "UPDATE user SET is_active = NOT is_active WHERE user_id = ?";
        jdbcTemplate.update(query, user_id);
    }



}
