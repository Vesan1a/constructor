package com.example.constructor.dao.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.example.constructor.dao.LinkUrlReaderWriter;
import com.example.constructor.db.ConstructorDbOpenHelper;
import com.example.constructor.db.ConstructorReaderContract;
import com.example.constructor.model.LinkUrl;

import java.util.ArrayList;
import java.util.List;

public class LinkUrlReaderWriterSqlite implements LinkUrlReaderWriter {

    private final ConstructorDbOpenHelper openHelper;

    public LinkUrlReaderWriterSqlite(Context context) {
        this.openHelper = new ConstructorDbOpenHelper(context);
    }

    @Override
    public long insert(LinkUrl url) {

        SQLiteDatabase writableDatabase = openHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(
                ConstructorReaderContract.LinkUrlEntry.COLUMN_Url, url.getUrl()
        );
        contentValues.put(
                ConstructorReaderContract.LinkUrlEntry.COLUMN_CONTENT_CHAPTER_ID, url.getContentChapter_id()
        );

        long id = writableDatabase.insert(
                ConstructorReaderContract.LinkUrlEntry.TABLE_NAME,
                null,
                contentValues
        );
        writableDatabase.close();
        return id;
    }

    @Override
    public List<LinkUrl> findAll() {

        SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();

        Cursor cursor = readableDatabase.query(
                ConstructorReaderContract.LinkUrlEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        List<LinkUrl> linkUrlList = getLinkUrlsList(cursor);

        cursor.close();
        readableDatabase.close();
        return linkUrlList;
    }

    @Override
    public List<LinkUrl> findByContentChapterId(long id) {

        SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();

        Cursor cursor = readableDatabase.query(
                ConstructorReaderContract.LinkUrlEntry.TABLE_NAME,
                null,
                ConstructorReaderContract.LinkUrlEntry.COLUMN_CONTENT_CHAPTER_ID + " = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );

        List<LinkUrl> linkUrlList = getLinkUrlsList(cursor);

        cursor.close();
        readableDatabase.close();
        return linkUrlList;
    }

    @NonNull
    private static List<LinkUrl> getLinkUrlsList(Cursor cursor) {
        List<LinkUrl> linkUrlList = new ArrayList<>();

        if (cursor.moveToFirst()) {

            int columnIndexId = cursor.
                    getColumnIndex(ConstructorReaderContract.LinkUrlEntry.COLUMN_ID);
            int columnIndexUrl = cursor.
                    getColumnIndex(ConstructorReaderContract.LinkUrlEntry.COLUMN_Url);
            int columnIndexContentChapterId = cursor.
                    getColumnIndex(ConstructorReaderContract.LinkUrlEntry.COLUMN_CONTENT_CHAPTER_ID);

            do {

                LinkUrl linkUrl = new LinkUrl(
                        cursor.getInt(columnIndexId),
                        cursor.getString(columnIndexUrl),
                        cursor.getInt(columnIndexContentChapterId)
                );

                linkUrlList.add(linkUrl);
            } while (cursor.moveToNext());

        }
        return linkUrlList;
    }
}
