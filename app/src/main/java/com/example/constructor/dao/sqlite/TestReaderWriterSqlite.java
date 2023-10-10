package com.example.constructor.dao.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.example.constructor.dao.QuestionReaderWriter;
import com.example.constructor.dao.TestReaderWriter;
import com.example.constructor.db.ConstructorDbOpenHelper;
import com.example.constructor.db.ConstructorReaderContract;
import com.example.constructor.model.ContentChapter;
import com.example.constructor.model.Test;

import java.util.ArrayList;
import java.util.List;

public class TestReaderWriterSqlite implements TestReaderWriter {

    private final ConstructorDbOpenHelper openHelper;
    private final QuestionReaderWriter questionReaderWriter;

    public TestReaderWriterSqlite(Context context) {
        this.openHelper = new ConstructorDbOpenHelper(context);
        questionReaderWriter = new QuestionReaderWriterSqlite(context);
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
        SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();

        Cursor cursor = readableDatabase.query(
                ConstructorReaderContract.TestEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        List<Test> testList = new ArrayList<>();

        if (cursor.moveToFirst()) {

            int columnIndexId = cursor.
                    getColumnIndex(ConstructorReaderContract.TestEntry.COLUMN_ID);
            int columnIndexName = cursor.
                    getColumnIndex(ConstructorReaderContract.TestEntry.COLUMN_NAME);
            int columnIndexChapterId = cursor.
                    getColumnIndex(ConstructorReaderContract.TestEntry.COLUMN_CHAPTER_ID);
            int columnIndexIsDone = cursor.
                    getColumnIndex(ConstructorReaderContract.TestEntry.COLUMN_IS_DONE);

            do {

                long testId = cursor.getLong(columnIndexId);
                Test test = new Test(
                        testId,
                        cursor.getString(columnIndexName),
                        cursor.getLong(columnIndexChapterId),
                        questionReaderWriter.findByTestId(testId),
                        cursor.getInt(columnIndexIsDone) == 1
                );

                testList.add(test);
            } while (cursor.moveToNext());

        }

        cursor.close();
        readableDatabase.close();
        return testList;
    }

    @Override
    public Test findByChapterId(long id) throws Exception {
        SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();

        Cursor cursor = readableDatabase.query(
                ConstructorReaderContract.TestEntry.TABLE_NAME,
                null,
                ConstructorReaderContract.TestEntry.COLUMN_CHAPTER_ID + " = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );

        Test test = null;
        if (cursor.moveToFirst()) {

            int columnIndexId = cursor.
                    getColumnIndex(ConstructorReaderContract.TestEntry.COLUMN_ID);
            int columnIndexName = cursor.
                    getColumnIndex(ConstructorReaderContract.TestEntry.COLUMN_NAME);
            int columnIndexChapterId = cursor.
                    getColumnIndex(ConstructorReaderContract.TestEntry.COLUMN_CHAPTER_ID);
            int columnIndexIsDone = cursor.
                    getColumnIndex(ConstructorReaderContract.TestEntry.COLUMN_IS_DONE);


            long testID = cursor.getLong(columnIndexId);
            test = new Test(
                    testID,
                    cursor.getString(columnIndexName),
                    cursor.getLong(columnIndexChapterId),
                    questionReaderWriter.findByTestId(testID),
                    cursor.getInt(columnIndexIsDone) == 1
            );
        } else throw new Exception("Test not found!");

        cursor.close();
        readableDatabase.close();
        return test;
    }


    @Override
    public void update(long id, boolean done) {

        SQLiteDatabase writableDatabase = openHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(
                ConstructorReaderContract.TestEntry.COLUMN_IS_DONE, done
        );

        writableDatabase.update(
                ConstructorReaderContract.TestEntry.TABLE_NAME,
                contentValues,
                ConstructorReaderContract.TestEntry.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)}
        );

    }



}
