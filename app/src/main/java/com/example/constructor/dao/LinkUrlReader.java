package com.example.constructor.dao;

import com.example.constructor.model.ImageUrl;
import com.example.constructor.model.LinkUrl;

import java.util.List;

public interface LinkUrlReader {
    List<LinkUrl> findAll();
    List<LinkUrl> findByContentChapterId(long id);
}
