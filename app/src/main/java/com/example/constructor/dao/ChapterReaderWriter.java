package com.example.constructor.dao;

import com.example.constructor.model.Chapter;

public interface ChapterReaderWriter extends ChapterReader{

    long insert (Chapter chapter);

    void update (long id, boolean accepted);

}
