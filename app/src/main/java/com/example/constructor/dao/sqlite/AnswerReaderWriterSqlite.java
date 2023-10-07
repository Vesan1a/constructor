package com.example.constructor.dao.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.constructor.dao.AnswerReaderWriter;
import com.example.constructor.db.ConstructorDbOpenHelper;
import com.example.constructor.db.ConstructorReaderContract;
import com.example.constructor.model.Answer;

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

        return writableDatabase.insert(
                ConstructorReaderContract.AnswerEntry.TABLE_NAME,
                null,
                contentValues
        );

    }

    @Override
    public List<Answer> findALL() {
        return null;
    }
}
