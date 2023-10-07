package com.example.constructor.dao;

import com.example.constructor.model.Test;

import java.util.List;

public interface TestReader {
    List<Test> findAll();
}
