package com.example.constructor.model;

import java.util.List;

public class Test {
    private long id;
    private String name;
    private long chapter_id;
    private List<Question> questionList;
    private boolean isDone;

    public Test(
            long id,
            String name,
            long chapter_id,
            List<Question> questionList,
            boolean isDone
    ) {
        this.id = id;
        this.name = name;
        this.chapter_id = chapter_id;
        this.questionList = questionList;
        this.isDone = isDone;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getChapter_id() {
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

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", chapter_id=" + chapter_id +
                ", questionList=" + questionList +
                ", isDone=" + isDone +
                '}';
    }
}
