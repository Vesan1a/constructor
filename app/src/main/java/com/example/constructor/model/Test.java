package com.example.constructor.model;

import java.util.List;

public class Test {
    private int id;
    private String name;
    private int chapter_id;
    private List<Question> questionList;
    private boolean isDone;

    public Test(int id, String name, int chapter_id, List<Question> questionList, boolean isDone) {
        this.id = id;
        this.name = name;
        this.chapter_id = chapter_id;
        this.questionList = questionList;
        this.isDone = isDone;
    }

    public Test(String name, int chapter_id, List<Question> questionList, boolean isDone) {
        this.name = name;
        this.chapter_id = chapter_id;
        this.questionList = questionList;
        this.isDone = isDone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getChapter_id() {
        return chapter_id;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
