package com.example.constructor.model;

import java.util.Objects;

public class Answer {
    private long id;
    private String answerText;
    private long question_id;
    private boolean isRight;

    public Answer(long id, String answerText, long question_id, boolean isRight) {
        this.id = id;
        this.answerText = answerText;
        this.question_id = question_id;
        this.isRight = isRight;
    }

    public long getId() {
        return id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public long getQuestion_id() {
        return question_id;
    }

    public boolean isRight() {
        return isRight;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", answerText='" + answerText + '\'' +
                ", question_id=" + question_id +
                ", isRight=" + isRight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return id == answer.id && question_id == answer.question_id && isRight == answer.isRight && Objects.equals(answerText, answer.answerText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, answerText, question_id, isRight);
    }
}
