package com.example.constructor.model;

public class Chapter {
    private long id;
    private String name;
    private ContentChapter content;
    private Test test;
    private boolean accepted;


    public Chapter(long id, String name, ContentChapter content, Test test, boolean accepted) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.test = test;
        this.accepted = accepted;
    }

    public Chapter(String name, ContentChapter content, Test test, boolean accepted) {
        this.name = name;
        this.content = content;
        this.test = test;
        this.accepted = accepted;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ContentChapter getContent() {
        return content;
    }

    public Test getTest() {
        return test;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
