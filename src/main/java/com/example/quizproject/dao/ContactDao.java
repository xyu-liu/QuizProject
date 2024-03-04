package com.example.quizproject.dao;

import com.example.quizproject.dao.rowMapper.ContactRowMapper;
import com.example.quizproject.dao.rowMapper.QuestionRowMapper;
import com.example.quizproject.domain.Category;
import com.example.quizproject.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactDao {

    JdbcTemplate jdbcTemplate;
    ContactRowMapper contactRowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ContactDao(JdbcTemplate jdbcTemplate, ContactRowMapper contactRowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.contactRowMapper = contactRowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Contact> getAllContacts() {
        String query = "SELECT * FROM contact";
        List<Contact> contacts = jdbcTemplate.query(query, contactRowMapper);
        return contacts;
    }
}
