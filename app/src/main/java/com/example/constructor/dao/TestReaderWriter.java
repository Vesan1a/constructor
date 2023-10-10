package com.example.constructor.dao;

import com.example.constructor.model.Test;

public interface TestReaderWriter extends TestReader {

    long insert(Test test);

    void update(long id, boolean done);
}