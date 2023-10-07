package com.example.constructor.dao.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.constructor.dao.QuestionReaderWriter;
import com.example.constructor.db.ConstructorDbOpenHelper;
import com.example.constructor.db.ConstructorReaderContract;
import com.example.constructor.model.Question;

import java.util.List;

public class QuestionReaderWriterSqlite implements QuestionReaderWriter {

    private final ConstructorDbOpenHelper openHelper;

    public QuestionReaderWriterSqlite(Context context) {
        this.openHelper = new ConstructorDbOpenHelper(context);
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
        return null;
    }
}
