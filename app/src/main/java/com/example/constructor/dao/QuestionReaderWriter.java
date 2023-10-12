package com.example.constructor.dao;

import com.example.constructor.model.Question;

import java.util.List;

public interface QuestionReaderWriter {

    List<Question> findAll();

    List<Question> findByTestId(long id);

    long insert(Question question);
}
