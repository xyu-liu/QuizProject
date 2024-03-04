package com.example.quizproject.dao;

import com.example.quizproject.dao.rowMapper.QuizQuestionRowMapper;
import com.example.quizproject.dao.rowMapper.QuizRowMapper;
import com.example.quizproject.domain.Question;
import com.example.quizproject.domain.QuizQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuizQuestionDao {

    JdbcTemplate jdbcTemplate;
    QuizQuestionRowMapper quizQuestionRowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public QuizQuestionDao(JdbcTemplate jdbcTemplate, QuizQuestionRowMapper quizQuestionRowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.quizQuestionRowMapper = quizQuestionRowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void createNewQuizQuestion(int quiz_id, int question_id) {
        String query = "INSERT INTO quizquestion (quiz_id, question_id) VALUES (?, ?)";
        jdbcTemplate.update(query, quiz_id, question_id);
    }

    public void createNewQuizQuestionsGivenList(int quiz_id, List<Question> questions) {
        for (Question question : questions) {
            createNewQuizQuestion(quiz_id, question.getQuestion_id());
        }
    }

    public List<QuizQuestion> getQuizQuestionsByQuizId(int quiz_id) {
        String query = "SELECT * FROM quizquestion WHERE quiz_id = ?";
        List<QuizQuestion> quizQuestionList = jdbcTemplate.query(query, quizQuestionRowMapper, quiz_id);
        return quizQuestionList;
    }

    public void addUserChoice(int user_choice_id, int quiz_id, int question_id) {
        String query = "UPDATE quizquestion SET user_choice_id = ? WHERE quiz_id = ? AND question_id = ?";
        jdbcTemplate.update(query, user_choice_id, quiz_id, question_id);
    }


}
