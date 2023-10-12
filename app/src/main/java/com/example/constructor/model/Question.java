package com.example.constructor.model;

import java.util.List;

public class Question {
    private long id;
    private String questionText;
    private long test_id;
    private List<Answer> answerList;

    public Question(long id, String questionText, long test_id, List<Answer> answerList) {
        this.id = id;
        this.questionText = questionText;
        this.test_id = test_id;
        this.answerList = answerList;
    }

    public long getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public long getTest_id() {
        return test_id;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionText='" + questionText + '\'' +
                ", test_id=" + test_id +
                ", answerList=" + answerList +
                '}';
    }
}
