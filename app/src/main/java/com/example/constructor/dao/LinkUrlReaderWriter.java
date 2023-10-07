package com.example.constructor.dao;

import com.example.constructor.model.LinkUrl;

public interface LinkUrlReaderWriter extends LinkUrlReader{

    long insert (LinkUrl url);
}
