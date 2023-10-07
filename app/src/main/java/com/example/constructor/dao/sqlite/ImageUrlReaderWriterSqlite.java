package com.example.constructor.dao.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.constructor.dao.ImageUrlReaderWriter;
import com.example.constructor.db.ConstructorDbOpenHelper;
import com.example.constructor.db.ConstructorReaderContract;
import com.example.constructor.model.ImageUrl;

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

        return writableDatabase.insert(
                ConstructorReaderContract.ImageUrlEntry.TABLE_NAME,
                null,
                contentValues
        );

    }

    @Override
    public List<ImageUrl> findAll() {
        return null;
    }
}
