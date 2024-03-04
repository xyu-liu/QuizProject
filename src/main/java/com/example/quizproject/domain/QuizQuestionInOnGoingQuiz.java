package com.example.quizproject.domain;

public class QuizQuestionInOnGoingQuiz {
    private int qq_id;
    private int quiz_id;
    private int question_id;
    private String description;

    public QuizQuestionInOnGoingQuiz(int qq_id, int quiz_id, int question_id, String description) {
        this.qq_id = qq_id;
        this.quiz_id = quiz_id;
        this.question_id = question_id;
        this.description = description;
    }

    public QuizQuestionInOnGoingQuiz() {
    }

    public int getQq_id() {
        return qq_id;
    }

    public void setQq_id(int qq_id) {
        this.qq_id = qq_id;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
