package com.example.constructor.dao;

import com.example.constructor.model.Test;

import java.util.List;

public interface TestReaderWriter {

    List<Test> findAll();

    Test findByChapterId(long id) throws Exception;

    long insert(Test test);

    void update(long id, boolean done);
}
