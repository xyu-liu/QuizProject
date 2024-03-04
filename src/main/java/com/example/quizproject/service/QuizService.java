package com.example.quizproject.service;

import com.example.quizproject.dao.QuizDao;
import com.example.quizproject.domain.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;



    public int createQuizByCategoryAndUser(int user_id, int category_id) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String name = "Quiz at " + timestamp.toString();
        return this.quizDao.createNewQuiz(user_id,category_id, name, timestamp);
    }

    public void updateQuizEndTime(int quiz_id, Timestamp time_end) {
        this.quizDao.updateQuizEndTime(quiz_id, time_end);
    }
    public List<Quiz> getAllQuizResultByUser(int user_id){
        return this.quizDao.getAllQuizResultByUser(user_id);
    }
    public Quiz getQuizById(int quiz_id){
        return this.quizDao.getQuizById(quiz_id);
    }

    public List<Quiz> getAllQuiz() {
        return this.quizDao.getAllQuiz();
    }
}
