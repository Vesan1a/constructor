package com.example.constructor.model;

import java.util.List;

public class ContentChapter {
    private long id;
    private String name;
    private String content;
    private List<ImageUrl> imageUrlList;
    private List<LinkUrl> linkUrlList;
    private long chapter_id;

    public ContentChapter(long id, String name, String content, List<ImageUrl> imageUrlList, List<LinkUrl> linkUrlList, long chapter_id) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.imageUrlList = imageUrlList;
        this.linkUrlList = linkUrlList;
        this.chapter_id = chapter_id;
    }

    public ContentChapter(String name, String content, List<ImageUrl> imageUrlList, List<LinkUrl> linkUrlList, long chapter_id) {
        this.name = name;
        this.content = content;
        this.imageUrlList = imageUrlList;
        this.linkUrlList = linkUrlList;
        this.chapter_id = chapter_id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public List<ImageUrl> getImageUrlList() {
        return imageUrlList;
    }

    public List<LinkUrl> getLinkUrlList() {
        return linkUrlList;
    }

    public long getChapter_id() {
        return chapter_id;
    }
}
