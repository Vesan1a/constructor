package com.example.constructor.dao;

import com.example.constructor.model.ImageUrl;

import java.util.List;

public interface ImageUrlReaderWriter {

    List<ImageUrl> findAll();

    List<ImageUrl> findByContentChapterId(long id);

    long insert(ImageUrl url);

}
