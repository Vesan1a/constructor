package com.example.constructor.model;

public class Answer {
    private long id;
    private String answerText;
    private long question_id;
    private boolean isRight;

    public Answer(long id, String answerText, long question_id, boolean isRight) {
        this.id = id;
        this.answerText = answerText;
        this.question_id = question_id;
        this.isRight = isRight;
    }

    public long getId() {
        return id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public long getQuestion_id() {
        return question_id;
    }

    public boolean isRight() {
        return isRight;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", answerText='" + answerText + '\'' +
                ", question_id=" + question_id +
                ", isRight=" + isRight +
                '}';
    }
}
