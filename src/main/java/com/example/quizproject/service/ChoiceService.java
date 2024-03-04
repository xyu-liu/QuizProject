package com.example.quizproject.service;

import com.example.quizproject.dao.ChoiceDao;
import com.example.quizproject.domain.Choice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChoiceService {
    @Autowired
    private ChoiceDao choiceDao;

    public Choice getChoiceById(int choice_id) {
        return this.choiceDao.getChoiceById(choice_id);
    }

    public List<Choice> getChoiceByQuestionId(int question_id) {
        return this.choiceDao.getChoiceByQuestionId(question_id);
    }

    public boolean isCorrect(int choice_id) {
        return this.choiceDao.isCorrect(choice_id);
    }

    public int getQuestionIdByChoiceID(int choice_id) {
        return this.choiceDao.getChoiceById(choice_id).getQuestion_id();
    }

    public Choice getRightChoiceByQuestionId(int question_id) {
        return this.choiceDao.getRightChoiceByQuestionId(question_id);
    }
}
