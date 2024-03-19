package com.example.constructor.model;

import java.util.List;
import java.util.Objects;

public class Question {
    private long id;
    private String questionText;
    private long test_id;
    private List<Answer> answerList;

    public Question(
            long id,
            String questionText,
            long test_id,
            List<Answer> answerList
    ) {
        this.id = id;
        this.questionText = questionText;
        this.test_id = test_id;
        this.answerList = answerList;
    }

    public long getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public long getTest_id() {
        return test_id;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionText='" + questionText + '\'' +
                ", test_id=" + test_id +
                ", answerList=" + answerList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id && test_id == question.test_id && Objects.equals(questionText, question.questionText) && Objects.equals(answerList, question.answerList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionText, test_id, answerList);
    }
}
