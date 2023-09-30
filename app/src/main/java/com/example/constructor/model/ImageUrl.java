package com.example.constructor.model;

public class ImageUrl {
    private int id;
    private String url;
    private int contentChapter_Id;

    public ImageUrl(int id, String url, int contentChapter_Id) {
        this.id = id;
        this.url = url;
        this.contentChapter_Id = contentChapter_Id;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public int getContentChapter_Id() {
        return contentChapter_Id;
    }
}
