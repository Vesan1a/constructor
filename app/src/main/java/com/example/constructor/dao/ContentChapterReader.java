package com.example.constructor.dao;

import com.example.constructor.model.ContentChapter;

import java.util.List;

public interface ContentChapterReader {
    List<ContentChapter> findAll();
    ContentChapter findByChapterId(long id) throws Exception;
}
