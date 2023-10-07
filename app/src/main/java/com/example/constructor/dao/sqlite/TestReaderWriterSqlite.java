package com.example.constructor.dao.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.constructor.dao.TestReaderWriter;
import com.example.constructor.db.ConstructorDbOpenHelper;
import com.example.constructor.db.ConstructorReaderContract;
import com.example.constructor.model.Test;

import java.util.List;

public class TestReaderWriterSqlite implements TestReaderWriter {

    private final ConstructorDbOpenHelper openHelper;

    public TestReaderWriterSqlite(Context context) {
        this.openHelper = new ConstructorDbOpenHelper(context);
    }

    @Override
    public long insert(Test test) {

        SQLiteDatabase writableDatabase = openHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(
                ConstructorReaderContract.TestEntry.COLUMN_NAME, test.getName()
        );
        contentValues.put(
                ConstructorReaderContract.TestEntry.COLUMN_CHAPTER_ID, test.getChapter_id()
        );
        contentValues.put(
                ConstructorReaderContract.TestEntry.COLUMN_IS_DONE, test.isDone()
        );

        return writableDatabase.insert(
                ConstructorReaderContract.TestEntry.TABLE_NAME,
                null,
                contentValues
        );
    }

    @Override
    public List<Test> findAll() {
        return null;
    }

    @Override
    public void update(int id, boolean done) {

    }
}
