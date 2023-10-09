package com.example.constructor.model;

public class Answer {
    private long id;
    private String name;
    private long question_id;
    private boolean isRight;

    public Answer(long id, String name, long question_id, boolean isRight) {
        this.id = id;
        this.name = name;
        this.question_id = question_id;
        this.isRight = isRight;
    }

    public Answer(String name, long question_id, boolean isRight) {
        this.name = name;
        this.question_id = question_id;
        this.isRight = isRight;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getQuestion_id() {
        return question_id;
    }

    public boolean isRight() {
        return isRight;
    }
}
