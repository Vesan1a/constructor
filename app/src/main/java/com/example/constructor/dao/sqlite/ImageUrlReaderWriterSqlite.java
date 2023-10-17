package com.example.constructor.dao.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.example.constructor.dao.ImageUrlReaderWriter;
import com.example.constructor.db.ConstructorDbOpenHelper;
import com.example.constructor.db.ConstructorReaderContract;
import com.example.constructor.model.ImageUrl;

import java.util.ArrayList;
import java.util.List;

public class ImageUrlReaderWriterSqlite implements ImageUrlReaderWriter {

    private final ConstructorDbOpenHelper openHelper;

    public ImageUrlReaderWriterSqlite(Context context) {
        this.openHelper = new ConstructorDbOpenHelper(context);
    }

    @Override
    public long insert(ImageUrl url) {

        SQLiteDatabase writableDatabase = openHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(
                ConstructorReaderContract.ImageUrlEntry.COLUMN_Url, url.getUrl()
        );
        contentValues.put(
                ConstructorReaderContract.ImageUrlEntry.COLUMN_CONTENT_CHAPTER_ID, url.getContentChapter_Id()
        );

        long id = writableDatabase.insert(
                ConstructorReaderContract.ImageUrlEntry.TABLE_NAME,
                null,
                contentValues
        );
        writableDatabase.close();
        return id;

    }

    @Override
    public List<ImageUrl> findAll() {

        SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();

        Cursor cursor = readableDatabase.query(
                ConstructorReaderContract.ImageUrlEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        List<ImageUrl> imageUrlList = getImageUrlsList(cursor);

        cursor.close();
        readableDatabase.close();
        return imageUrlList;
    }

    @Override
    public List<ImageUrl> findByContentChapterId(long id) {

        SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();

        Cursor cursor = readableDatabase.query(
                ConstructorReaderContract.ImageUrlEntry.TABLE_NAME,
                null,
                ConstructorReaderContract.ImageUrlEntry.COLUMN_CONTENT_CHAPTER_ID + " = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );

        List<ImageUrl> imageUrlList = getImageUrlsList(cursor);

        cursor.close();
        readableDatabase.close();
        return imageUrlList;
    }

    @NonNull
    private static List<ImageUrl> getImageUrlsList(Cursor cursor) {
        List<ImageUrl> imageUrlList = new ArrayList<>();

        if (cursor.moveToFirst()) {

            int columnIndexId = cursor.
                    getColumnIndex(ConstructorReaderContract.ImageUrlEntry.COLUMN_ID);
            int columnIndexUrl = cursor.
                    getColumnIndex(ConstructorReaderContract.ImageUrlEntry.COLUMN_Url);
            int columnIndexContentChapterId = cursor.
                    getColumnIndex(ConstructorReaderContract.ImageUrlEntry.COLUMN_CONTENT_CHAPTER_ID);

            do {

                ImageUrl imageUrl = new ImageUrl(
                        cursor.getLong(columnIndexId),
                        cursor.getString(columnIndexUrl),
                        cursor.getInt(columnIndexContentChapterId)
                );

                imageUrlList.add(imageUrl);
            } while (cursor.moveToNext());

        }
        return imageUrlList;
    }
}
