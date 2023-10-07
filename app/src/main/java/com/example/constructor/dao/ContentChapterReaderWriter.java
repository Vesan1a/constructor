package com.example.constructor.dao;

import com.example.constructor.model.ContentChapter;

public interface ContentChapterReaderWriter extends ContentChapterReader {

    long insert (ContentChapter contentChapter);

}
