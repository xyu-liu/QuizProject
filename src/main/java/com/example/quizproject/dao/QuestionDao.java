package com.example.quizproject.dao;

import com.example.quizproject.dao.rowMapper.QuestionRowMapper;
import com.example.quizproject.dao.rowMapper.QuizRowMapper;
import com.example.quizproject.domain.Question;
import com.example.quizproject.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionDao {

    JdbcTemplate jdbcTemplate;
    QuestionRowMapper questionRowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public QuestionDao(JdbcTemplate jdbcTemplate, QuestionRowMapper questionRowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.questionRowMapper = questionRowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Question getQuestionById(int question_id) {
        String query = "SELECT * FROM question WHERE question_id = ?";
        List<Question> questions = jdbcTemplate.query(query,questionRowMapper, question_id);
        return questions.size() == 0 ? null : questions.get(0);
    }

    public List<Question> getFiveQuestionsByCategoryId(int category_id) {
        String query = "SELECT * FROM question WHERE category_id = ? AND is_active = true ORDER BY RAND() LIMIT 5";
        List<Question> questions = jdbcTemplate.query(query,questionRowMapper, category_id);
        return questions;
    }

    public String getDescriptionByQuestionId(int question_id) {
        String sql = "SELECT description FROM question WHERE question_id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, question_id);
    }

    public List<Question> getAllQuestions() {
        String query = "SELECT * From question";
        List<Question> questions = jdbcTemplate.query(query, questionRowMapper);
        return questions;
    }

    public void changeStatusById(int question_id) {
        String query = "UPDATE question SET is_active = NOT is_active WHERE question_id = ?";
        jdbcTemplate.update(query, question_id);
    }


    public void addNewQuestion(int category_id, String description) {
        String query = "INSERT INTO question (category_id, description, is_active) VALUES (?, ?, false)";
        jdbcTemplate.update(query, category_id, description);
    }



}
