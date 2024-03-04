package com.example.quizproject.service;

import com.example.quizproject.dao.QuestionDao;
import com.example.quizproject.domain.Question;
import com.example.quizproject.domain.Quiz;
import com.example.quizproject.domain.QuizQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionDao questionDao;

    public List<Question> getFiveQuestionsByCategory(int category_id) {
        return this.questionDao.getFiveQuestionsByCategoryId(category_id);
    }

    public List<Question> getQuestionsByQQ(List<QuizQuestion> quizQuestions){
        List<Question> questions = new ArrayList<>();

        for (QuizQuestion quizQuestion : quizQuestions) {
            Question questionById = this.questionDao.getQuestionById(quizQuestion.getQuestion_id());
            questions.add(questionById);
        }
        return questions;
    }

    public String getDescriptionByQuestionId(int question_id) {
        return this.questionDao.getDescriptionByQuestionId(question_id);
    }

    public List<Question> getAllQuestions() {
        return this.questionDao.getAllQuestions();
    }

    public void changeStatusById(int question_id) {
        this.questionDao.changeStatusById(question_id);
    }

    public void addNewQuestion(int category_id, String description) {
        this.questionDao.addNewQuestion(category_id, description);
    }
}
