package com.example.quizproject.domain;

import java.sql.Time;
import java.sql.Timestamp;

public class Quiz {

    private int quiz_id;
    private int user_id;
    private int category_id;
    private String name;
    private Timestamp time_start;
    private Timestamp time_end;

    public Quiz(int user_id, int category_id, String name, Timestamp time_start) {
        this.user_id = user_id;
        this.category_id = category_id;
        this.name = name;
        this.time_start = time_start;
    }

    public Quiz() {
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "quiz_id=" + quiz_id +
                ", user_id=" + user_id +
                ", category_id=" + category_id +
                ", name='" + name + '\'' +
                ", time_start=" + time_start +
                ", time_end=" + time_end +
                '}';
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getTime_start() {
        return time_start;
    }

    public void setTime_start(Timestamp time_start) {
        this.time_start = time_start;
    }

    public Timestamp getTime_end() {
        return time_end;
    }

    public void setTime_end(Timestamp time_end) {
        this.time_end = time_end;
    }
}
