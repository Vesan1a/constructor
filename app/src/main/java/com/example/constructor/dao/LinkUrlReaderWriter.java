package com.example.constructor.dao;

import com.example.constructor.model.LinkUrl;

import java.util.List;

public interface LinkUrlReaderWriter {

    List<LinkUrl> findAll();

    List<LinkUrl> findByContentChapterId(long id);

    long insert(LinkUrl url);
}
