package com.example.quizproject.domain;

import java.util.List;

public class Question {
    private int question_id;
    private int category_id;
    private String description;
    private boolean is_active;

    public Question() {
    }

    public Question(int category_id, String description) {
        this.category_id = category_id;
        this.description = description;
    }

    public static String getDescription(List<Question> questionList, int question_id) {
        for (Question question : questionList) {
            if (question.getQuestion_id() == question_id) {
                return question.getDescription();
            }
        }
        return "";
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }
}
