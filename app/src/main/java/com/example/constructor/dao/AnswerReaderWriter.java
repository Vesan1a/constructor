package com.example.constructor.dao;

import com.example.constructor.model.Answer;

import java.util.List;

public interface AnswerReaderWriter {

    List<Answer> findALL();

    List<Answer> findByQuestionId(long id);

    long insert(Answer answer);
}
