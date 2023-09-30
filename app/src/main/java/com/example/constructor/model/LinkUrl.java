package com.example.constructor.model;

public class LinkUrl {
    private int id;
    private String url;
    private int contentChapter_id;

    public LinkUrl(int id, String url, int contentChapter_id) {
        this.id = id;
        this.url = url;
        this.contentChapter_id = contentChapter_id;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public int getContentChapter_id() {
        return contentChapter_id;
    }
}
