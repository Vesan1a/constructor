package com.example.constructor.model;

import java.util.List;

public class Question {
    private int id;
    private int name;
    private int test_id;
    private List<Answer> answerList;

    public Question(int id, int name, int test_id, List<Answer> answerList) {
        this.id = id;
        this.name = name;
        this.test_id = test_id;
        this.answerList = answerList;
    }

    public int getId() {
        return id;
    }

    public int getName() {
        return name;
    }

    public int getTest_id() {
        return test_id;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }
}
