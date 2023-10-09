package com.example.constructor.dao.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.example.constructor.dao.AnswerReaderWriter;
import com.example.constructor.dao.QuestionReaderWriter;
import com.example.constructor.db.ConstructorDbOpenHelper;
import com.example.constructor.db.ConstructorReaderContract;
import com.example.constructor.model.ContentChapter;
import com.example.constructor.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionReaderWriterSqlite implements QuestionReaderWriter {

    private final ConstructorDbOpenHelper openHelper;
    private final AnswerReaderWriter answerReaderWriter;

    public QuestionReaderWriterSqlite(Context context) {
        this.openHelper = new ConstructorDbOpenHelper(context);
        answerReaderWriter = new AnswerReaderWriterSqlite(context);
    }

    @Override
    public long insert(Question question) {

        SQLiteDatabase writableDatabase = openHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(
                ConstructorReaderContract.QuestionEntry.COLUMN_NAME, question.getName()
        );
        contentValues.put(
                ConstructorReaderContract.QuestionEntry.COLUMN_TEST_ID, question.getTest_id()
        );

        return writableDatabase.insert(
                ConstructorReaderContract.QuestionEntry.TABLE_NAME,
                null,
                contentValues
        );
    }

    @Override
    public List<Question> findAll() {
        SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();

        Cursor cursor = readableDatabase.query(
                ConstructorReaderContract.QuestionEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        List<Question> questionList = getQuestionList(cursor);

        cursor.close();
        readableDatabase.close();
        return questionList;
    }

    @Override
    public List<Question> findByTestId(long id) {
        SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();

        Cursor cursor = readableDatabase.query(
                ConstructorReaderContract.QuestionEntry.TABLE_NAME,
                null,
                ConstructorReaderContract.QuestionEntry.COLUMN_TEST_ID + " = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );

        List<Question> questionList = getQuestionList(cursor);

        cursor.close();
        readableDatabase.close();
        return questionList;
    }

    @NonNull
    private List<Question> getQuestionList(Cursor cursor) {
        List<Question> questionList = new ArrayList<>();

        if (cursor.moveToFirst()) {

            int columnIndexId = cursor.
                    getColumnIndex(ConstructorReaderContract.QuestionEntry.COLUM_ID);
            int columnIndexName = cursor.
                    getColumnIndex(ConstructorReaderContract.QuestionEntry.COLUMN_NAME);
            int columnIndexTestId = cursor.
                    getColumnIndex(ConstructorReaderContract.QuestionEntry.COLUMN_TEST_ID);

            do {

                long questionId = cursor.getLong(columnIndexId);
                Question question = new Question(
                        questionId,
                        cursor.getString(columnIndexName),
                        cursor.getLong(columnIndexTestId),
                        answerReaderWriter.findByQuestionId(questionId)
                );

                questionList.add(question);
            } while (cursor.moveToNext());

        }
        return questionList;
    }

}
