package com.example.constructor.dao.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.constructor.dao.ChapterReaderWriter;
import com.example.constructor.db.ConstructorDbOpenHelper;
import com.example.constructor.db.ConstructorReaderContract;
import com.example.constructor.model.Chapter;

import java.util.List;

public class ChapterReaderWriterSqlite implements ChapterReaderWriter {

    private final ConstructorDbOpenHelper openHelper;

    public ChapterReaderWriterSqlite(Context context) {
        this.openHelper = new ConstructorDbOpenHelper(context);
    }

    @Override
    public long insert(Chapter chapter) {

        SQLiteDatabase writableDatabase = openHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(
                ConstructorReaderContract.ChapterEntry.COLUMN_NAME, chapter.getName()
        );
        contentValues.put(
                ConstructorReaderContract.ChapterEntry.COLUMN_ACCEPTED, chapter.isAccepted()
        );

        return writableDatabase.insert(
                ConstructorReaderContract.ChapterEntry.TABLE_NAME,
                null,
                contentValues
        );

    }

    @Override
    public List<Chapter> findAll() {
        return null;
    }

    @Override
    public void update(int id, boolean accepted) {

    }
}
