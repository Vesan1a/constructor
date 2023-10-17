package com.example.constructor.dao.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.constructor.dao.ContentChapterReaderWriter;
import com.example.constructor.dao.ImageUrlReaderWriter;
import com.example.constructor.dao.LinkUrlReaderWriter;
import com.example.constructor.db.ConstructorDbOpenHelper;
import com.example.constructor.db.ConstructorReaderContract;
import com.example.constructor.model.ContentChapter;

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
                ConstructorReaderContract.ContentChapterEntry.COLUMN_CONTENT_TEXT, contentChapter.getContentText()
        );
        contentValues.put(
                ConstructorReaderContract.ContentChapterEntry.COLUMN_CHAPTER_ID, contentChapter.getChapter_id()
        );

        long id = writableDatabase.insert(
                ConstructorReaderContract.ContentChapterEntry.TABLE_NAME,
                null,
                contentValues
        );
        writableDatabase.close();
        return id;

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

        List<ContentChapter> contentChapterList = new ArrayList<>();

        if (cursor.moveToFirst()) {

            int columnIndexId = cursor.
                    getColumnIndex(ConstructorReaderContract.ContentChapterEntry.COLUMN_ID);
            int columnIndexContent = cursor.
                    getColumnIndex(ConstructorReaderContract.ContentChapterEntry.COLUMN_CONTENT_TEXT);
            int columnIndexChapterId = cursor.
                    getColumnIndex(ConstructorReaderContract.ContentChapterEntry.COLUMN_CHAPTER_ID);

            do {

                long contentChapterId = cursor.getLong(columnIndexId);
                ContentChapter contentChapter = new ContentChapter(
                        contentChapterId,
                        cursor.getString(columnIndexContent),
                        imageUrlReaderWriter.findByContentChapterId(contentChapterId),
                        linkUrlReaderWriter.findByContentChapterId(contentChapterId),
                        cursor.getLong(columnIndexChapterId)
                );

                contentChapterList.add(contentChapter);
            } while (cursor.moveToNext());

        }

        cursor.close();
        readableDatabase.close();
        return contentChapterList;

    }

    @Override
    public ContentChapter findByChapterId(long id) throws Exception {
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
        ContentChapter contentChapter = null;
        if (cursor.moveToFirst()) {

            int columnIndexId = cursor.
                    getColumnIndex(ConstructorReaderContract.ContentChapterEntry.COLUMN_ID);
            int columnIndexContent = cursor.
                    getColumnIndex(ConstructorReaderContract.ContentChapterEntry.COLUMN_CONTENT_TEXT);
            int columnIndexChapterId = cursor.
                    getColumnIndex(ConstructorReaderContract.ContentChapterEntry.COLUMN_CHAPTER_ID);


            long contentChapterId = cursor.getLong(columnIndexId);
            contentChapter = new ContentChapter(
                    contentChapterId,
                    cursor.getString(columnIndexContent),
                    imageUrlReaderWriter.findByContentChapterId(contentChapterId),
                    linkUrlReaderWriter.findByContentChapterId(contentChapterId),
                    cursor.getLong(columnIndexChapterId)
            );
        } else throw new Exception("ContentChapter not found!");
        cursor.close();
        readableDatabase.close();
        return contentChapter;
    }
}
