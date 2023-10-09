package com.example.constructor.model;

import java.util.List;

public class Question {
    private long id;
    private String name;
    private long test_id;
    private List<Answer> answerList;

    public Question(long id, String name, long test_id, List<Answer> answerList) {
        this.id = id;
        this.name = name;
        this.test_id = test_id;
        this.answerList = answerList;
    }

    public Question(String name, long test_id, List<Answer> answerList) {
        this.name = name;
        this.test_id = test_id;
        this.answerList = answerList;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getTest_id() {
        return test_id;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }
}
