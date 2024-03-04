package com.example.quizproject.dao;

import com.example.quizproject.dao.rowMapper.CategoryRowMapper;
import com.example.quizproject.dao.rowMapper.QuizRowMapper;
import com.example.quizproject.domain.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class QuizDao {
    JdbcTemplate jdbcTemplate;
    QuizRowMapper quizRowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public QuizDao(JdbcTemplate jdbcTemplate, QuizRowMapper quizRowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.quizRowMapper = quizRowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public int createNewQuiz(int user_id, int category_id, String name, Timestamp time_start){
        /*String query = "INSERT INTO quiz (user_id, category_id, name, time_start) VALUES (?,?,?,?)";
        jdbcTemplate.update(query, user_id,category_id,name,time_start);*/

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String sql = "INSERT INTO quiz (user_id, category_id, name, time_start) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, user_id);
                ps.setInt(2, category_id);
                ps.setString(3, name);
                ps.setTimestamp(4, time_start);
                return ps;
            }
        }, keyHolder);

        // Retrieve the generated key
        return keyHolder.getKey().intValue();


    }

    public void updateQuizEndTime(int quiz_id, Timestamp time_end) {
        String query = "UPDATE quiz SET time_end = ? WHERE quiz_id = ?";
        jdbcTemplate.update(query, time_end, quiz_id);
    }

    public Quiz getQuizById(int quiz_id){
        String query = "SELECT * FROM quiz WHERE quiz_id = ?";
        List<Quiz> quizzes = jdbcTemplate.query(query,quizRowMapper, quiz_id);
        return quizzes.size() == 0 ? null : quizzes.get(0);
    }

    public List<Quiz> getAllQuiz() {
        String query = "SELECT * FROM quiz";

        List<Quiz> quizzes = jdbcTemplate.query(query, quizRowMapper);

        return quizzes;
    }

    public List<Quiz> getAllQuizResultByUser(int user_id){
        String query = "SELECT * FROM quiz WHERE user_id = ? and time_end IS NOT NULL ORDER BY time_start DESC";
        List<Quiz> quizzes = jdbcTemplate.query(query, quizRowMapper, user_id);
        return quizzes;

    }
}
