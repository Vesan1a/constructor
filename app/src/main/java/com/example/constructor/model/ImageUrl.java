package com.example.constructor.model;

public class ImageUrl {
    private long id;
    private String url;
    private long contentChapter_Id;

    public ImageUrl(
            long id,
            String url,
            long contentChapter_Id
    ) {
        this.id = id;
        this.url = url;
        this.contentChapter_Id = contentChapter_Id;
    }

    public long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public long getContentChapter_Id() {
        return contentChapter_Id;
    }

    @Override
    public String toString() {
        return "ImageUrl{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", contentChapter_Id=" + contentChapter_Id +
                '}';
    }
}
