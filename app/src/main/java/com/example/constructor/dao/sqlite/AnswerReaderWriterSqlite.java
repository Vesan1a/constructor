package com.example.constructor.dao.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.example.constructor.dao.AnswerReaderWriter;
import com.example.constructor.db.ConstructorDbOpenHelper;
import com.example.constructor.db.ConstructorReaderContract;
import com.example.constructor.model.Answer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AnswerReaderWriterSqlite implements AnswerReaderWriter {

    private final ConstructorDbOpenHelper openHelper;

    public AnswerReaderWriterSqlite(Context context) {
        this.openHelper = new ConstructorDbOpenHelper(context);
    }

    @Override
    public long insert(Answer answer) {

        SQLiteDatabase writableDatabase = openHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(
                ConstructorReaderContract.AnswerEntry.COLUMN_NAME, answer.getName()
        );
        contentValues.put(
                ConstructorReaderContract.AnswerEntry.COLUMN_QUESTION_ID, answer.getQuestion_id()
        );
        contentValues.put(
                ConstructorReaderContract.AnswerEntry.COLUMN_IS_RIGHT, answer.isRight()
        );


        long id = writableDatabase.insert(
                ConstructorReaderContract.AnswerEntry.TABLE_NAME,
                null,
                contentValues
        );
        writableDatabase.close();
        return id;
    }

    @Override
    public List<Answer> findALL() {

        SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();

        Cursor cursor = readableDatabase.query(
                ConstructorReaderContract.AnswerEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        List<Answer> answerList = getAnswersList(cursor);

        cursor.close();
        readableDatabase.close();
        return answerList;
    }

    @Override
    public List<Answer> findByQuestionId(long id) {
        SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();

        Cursor cursor = readableDatabase.query(
                ConstructorReaderContract.AnswerEntry.TABLE_NAME,
                null,
                ConstructorReaderContract.AnswerEntry.COLUMN_QUESTION_ID + " = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );

        List<Answer> answerList = getAnswersList(cursor);

        cursor.close();
        readableDatabase.close();
        return answerList;
    }

    @NonNull
    private static List<Answer> getAnswersList(Cursor cursor) {
        List<Answer> answerList = new ArrayList<>();

        if (cursor.moveToFirst()) {

            int columnIndexId = cursor.
                    getColumnIndex(ConstructorReaderContract.AnswerEntry.COLUM_ID);
            int columnIndexName = cursor.
                    getColumnIndex(ConstructorReaderContract.AnswerEntry.COLUMN_NAME);
            int columnIndexRight = cursor.
                    getColumnIndex(ConstructorReaderContract.AnswerEntry.COLUMN_IS_RIGHT);
            int columnIndexQuestionId = cursor.
                    getColumnIndex(ConstructorReaderContract.AnswerEntry.COLUMN_QUESTION_ID);

            do {

                Answer answer = new Answer(
                        cursor.getLong(columnIndexId),
                        cursor.getString(columnIndexName),
                        cursor.getLong(columnIndexQuestionId),
                        cursor.getInt(columnIndexRight) == 1
                );

                answerList.add(answer);
            } while (cursor.moveToNext());

        }
        return answerList;
    }
}
