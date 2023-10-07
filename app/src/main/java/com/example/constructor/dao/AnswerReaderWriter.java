package com.example.constructor.dao;

import com.example.constructor.model.Answer;

public interface AnswerReaderWriter extends AnswerReader {

    long insert(Answer answer);
}
