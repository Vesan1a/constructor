package com.example.constructor.model;

import java.util.List;

public class ContentChapter {
    private int id;
    private String name;
    private String content;
    private List<String> imageUrlList;
    private List<String> linkUrlList;

    public ContentChapter(int id, String name, String content, List<String> imageUrlList, List<String> linkUrlList) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.imageUrlList = imageUrlList;
        this.linkUrlList = linkUrlList;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public List<String> getImageUrlList() {
        return imageUrlList;
    }

    public List<String> getLinkUrlList() {
        return linkUrlList;
    }
}
