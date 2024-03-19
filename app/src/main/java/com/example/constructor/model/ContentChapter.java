package com.example.constructor.model;

import java.util.List;

public class ContentChapter {
    private long id;
    private String contentText;
    private List<ImageUrl> imageUrlList;
    private List<LinkUrl> linkUrlList;
    private long chapter_id;

    public ContentChapter(
            long id,
            String contentText,
            List<ImageUrl> imageUrlList,
            List<LinkUrl> linkUrlList,
            long chapter_id
    ) {
        this.id = id;
        this.contentText = contentText;
        this.imageUrlList = imageUrlList;
        this.linkUrlList = linkUrlList;
        this.chapter_id = chapter_id;
    }

    public long getId() {
        return id;
    }

    public String getContentText() {
        return contentText;
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

    @Override
    public String toString() {
        return "ContentChapter{" +
                "id=" + id +
                ", contentText='" + contentText + '\'' +
                ", imageUrlList=" + imageUrlList +
                ", linkUrlList=" + linkUrlList +
                ", chapter_id=" + chapter_id +
                '}';
    }
}
