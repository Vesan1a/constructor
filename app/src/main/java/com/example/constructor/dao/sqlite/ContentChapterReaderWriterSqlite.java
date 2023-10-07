package com.example.constructor.dao.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.constructor.dao.ContentChapterReaderWriter;
import com.example.constructor.db.ConstructorDbOpenHelper;
import com.example.constructor.db.ConstructorReaderContract;
import com.example.constructor.model.ContentChapter;

import java.util.List;

public class ContentChapterReaderWriterSqlite implements ContentChapterReaderWriter {

    private final ConstructorDbOpenHelper openHelper;

    public ContentChapterReaderWriterSqlite(Context context) {
        this.openHelper = new ConstructorDbOpenHelper(context);
    }

    @Override
    public long insert(ContentChapter contentChapter) {

        SQLiteDatabase writableDatabase = openHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(
                ConstructorReaderContract.ContentChapterEntry.COLUMN_NAME, contentChapter.getName()
        );
        contentValues.put(
                ConstructorReaderContract.ContentChapterEntry.COLUMN_CONTENT, contentChapter.getContent()
        );
        contentValues.put(
                ConstructorReaderContract.ContentChapterEntry.COLUMN_CHAPTER_ID, contentChapter.getChapter_id()
        );

        return writableDatabase.insert(
                ConstructorReaderContract.ContentChapterEntry.TABLE_NAME,
                null,
                contentValues
        );

    }

    @Override
    public List<ContentChapter> findAll() {
        return null;
    }
}
