package com.example.constructor.dao.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.example.constructor.dao.ContentChapterReaderWriter;
import com.example.constructor.dao.ImageUrlReaderWriter;
import com.example.constructor.dao.LinkUrlReaderWriter;
import com.example.constructor.db.ConstructorDbOpenHelper;
import com.example.constructor.db.ConstructorReaderContract;
import com.example.constructor.model.ContentChapter;
import com.example.constructor.model.ImageUrl;

import java.util.ArrayList;
import java.util.List;

public class ContentChapterReaderWriterSqlite implements ContentChapterReaderWriter {

    private final ConstructorDbOpenHelper openHelper;

    private final ImageUrlReaderWriter imageUrlReaderWriter;

    private final LinkUrlReaderWriter linkUrlReaderWriter;

    public ContentChapterReaderWriterSqlite(Context context) {
        this.openHelper = new ConstructorDbOpenHelper(context);
        imageUrlReaderWriter = new ImageUrlReaderWriterSqlite(context);
        linkUrlReaderWriter = new LinkUrlReaderWriterSqlite(context);

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

        SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();

        Cursor cursor = readableDatabase.query(
                ConstructorReaderContract.ContentChapterEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        List<ContentChapter> contentChapterList = getContentChaptersList(cursor);

        cursor.close();
        readableDatabase.close();
        return contentChapterList;

    }

    @Override
    public List<ContentChapter> findByChapterId(long id) {
        SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();

        Cursor cursor = readableDatabase.query(
                ConstructorReaderContract.ContentChapterEntry.TABLE_NAME,
                null,
                ConstructorReaderContract.ContentChapterEntry.COLUMN_CHAPTER_ID + " = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );

        List<ContentChapter> contentChapterList = getContentChaptersList(cursor);

        cursor.close();
        readableDatabase.close();
        return contentChapterList;
    }

    @NonNull
    private List<ContentChapter> getContentChaptersList(Cursor cursor) {
        List<ContentChapter> contentChapterList = new ArrayList<>();

        if (cursor.moveToFirst()) {

            int columnIndexId = cursor.
                    getColumnIndex(ConstructorReaderContract.ContentChapterEntry.COLUM_ID);
            int columnIndexName = cursor.
                    getColumnIndex(ConstructorReaderContract.ContentChapterEntry.COLUMN_NAME);
            int columnIndexContent = cursor.
                    getColumnIndex(ConstructorReaderContract.ContentChapterEntry.COLUMN_CONTENT);
            int columnIndexChapterId = cursor.
                    getColumnIndex(ConstructorReaderContract.ContentChapterEntry.COLUMN_CHAPTER_ID);

            do {

                long contentChapterId = cursor.getLong(columnIndexId);
                ContentChapter contentChapter = new ContentChapter(
                        contentChapterId,
                        cursor.getString(columnIndexName),
                        cursor.getString(columnIndexContent),
                        imageUrlReaderWriter.findByContentChapterId(contentChapterId),
                        linkUrlReaderWriter.findByContentChapterId(contentChapterId),
                        cursor.getLong(columnIndexChapterId)
                );

                contentChapterList.add(contentChapter);
            } while (cursor.moveToNext());

        }
        return contentChapterList;
    }
}
