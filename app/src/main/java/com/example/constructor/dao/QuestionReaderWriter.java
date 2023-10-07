package com.example.constructor.dao;

import com.example.constructor.model.Question;

public interface QuestionReaderWriter extends QuestionReader{

    long insert (Question question);
}
