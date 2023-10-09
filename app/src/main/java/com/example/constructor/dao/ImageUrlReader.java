package com.example.constructor.dao;

import com.example.constructor.model.ImageUrl;

import java.util.List;

public interface ImageUrlReader {
    List<ImageUrl> findAll();
    List<ImageUrl> findByContentChapterId(long id);
}
