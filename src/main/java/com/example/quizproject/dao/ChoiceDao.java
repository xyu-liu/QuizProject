package com.example.quizproject.dao;

import com.example.quizproject.dao.rowMapper.ChoiceRowMapper;
import com.example.quizproject.dao.rowMapper.QuestionRowMapper;
import com.example.quizproject.domain.Choice;
import com.example.quizproject.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChoiceDao {
    JdbcTemplate jdbcTemplate;
    ChoiceRowMapper choiceRowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ChoiceDao(JdbcTemplate jdbcTemplate, ChoiceRowMapper choiceRowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.choiceRowMapper = choiceRowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Choice getChoiceById(int choice_id) {
        String query = "SELECT * FROM choice WHERE choice_id = ?";
        List<Choice> choices = jdbcTemplate.query(query,choiceRowMapper, choice_id);
        return choices.size() == 0 ? null : choices.get(0);
    }

    public Choice getRightChoiceByQuestionId(int question_id) {
        String query = "SELECT * FROM choice WHERE question_id = ? and is_correct = true";
        List<Choice> choices = jdbcTemplate.query(query,choiceRowMapper, question_id);
        return choices.size() == 0 ? null : choices.get(0);
    }

    public List<Choice> getChoiceByQuestionId(int question_id) {
        String query = "SELECT * FROM choice WHERE question_id = ?";
        List<Choice> choices = jdbcTemplate.query(query,choiceRowMapper, question_id);
        return choices;
    }

    public boolean isCorrect(int choice_id) {
        Choice choiceById = getChoiceById(choice_id);
        if (choiceById == null) {
            return false;
        } else {
            return choiceById.isIs_correct();
        }
    }

}
