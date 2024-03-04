package com.example.quizproject.dao;

import com.example.quizproject.dao.rowMapper.CategoryRowMapper;
import com.example.quizproject.dao.rowMapper.UserRowMapper;
import com.example.quizproject.domain.Category;
import com.example.quizproject.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDao {

    JdbcTemplate jdbcTemplate;
    CategoryRowMapper categoryRowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public CategoryDao(JdbcTemplate jdbcTemplate, CategoryRowMapper categoryRowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.categoryRowMapper = categoryRowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Category getCategoryById(int id) {
        String query = "SELECT * From category WHERE category_id = ?";
        List<Category> categories = jdbcTemplate.query(query, categoryRowMapper, id);
        return categories.size() == 0 ? null : categories.get(0);
    }

    public List<Category> getAllCategory() {
        String query = "SELECT * FROM category";
        List<Category> categories = jdbcTemplate.query(query, categoryRowMapper);
        return categories;
    }





}
