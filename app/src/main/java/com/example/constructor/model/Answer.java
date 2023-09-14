package com.example.constructor.model;

public class Answer {
    private int id;
    private String name;
    private int question_id;
    private boolean isRight;

    public Answer(int id, String name, int question_id, boolean isRight) {
        this.id = id;
        this.name = name;
        this.question_id = question_id;
        this.isRight = isRight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public boolean isRight() {
        return isRight;
    }
}
