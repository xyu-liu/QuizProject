package com.example.quizproject.service;

import com.example.quizproject.dao.QuizQuestionDao;
import com.example.quizproject.domain.Question;
import com.example.quizproject.domain.QuizQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizQuestionService {

    @Autowired
    private QuizQuestionDao quizQuestionDao;

    public void createNewQuizQuestion(int quiz_id, int question_id) {
        this.quizQuestionDao.createNewQuizQuestion(quiz_id, question_id);
    }

    public void createNewQuizQuestions(int quiz_id, List<Question> questions) {
        this.quizQuestionDao.createNewQuizQuestionsGivenList(quiz_id, questions);
    }

    public List<QuizQuestion> getQuizQuestionsByQuizId(int quiz_id) {
        return this.quizQuestionDao.getQuizQuestionsByQuizId(quiz_id);
    }

    public void addUserChoice(int user_choice_id, int quiz_id, int question_id) {
        this.quizQuestionDao.addUserChoice(user_choice_id, quiz_id, question_id);
    }
}
