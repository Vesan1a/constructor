package com.example.constructor.model;

public class LinkUrl {
    private long id;
    private String url;
    private long contentChapter_id;

    public LinkUrl(
            long id,
            String url,
            long contentChapter_id
    ) {
        this.id = id;
        this.url = url;
        this.contentChapter_id = contentChapter_id;
    }

    public long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public long getContentChapter_id() {
        return contentChapter_id;
    }

    @Override
    public String toString() {
        return "LinkUrl{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", contentChapter_id=" + contentChapter_id +
                '}';
    }
}
