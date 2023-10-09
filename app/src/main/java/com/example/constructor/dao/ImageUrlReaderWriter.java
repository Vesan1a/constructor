package com.example.constructor.dao;

import com.example.constructor.model.ImageUrl;

public interface ImageUrlReaderWriter extends ImageUrlReader{

    long insert (ImageUrl url);

}
