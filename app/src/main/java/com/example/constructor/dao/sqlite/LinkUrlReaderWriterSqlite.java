package com.example.constructor.dao.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.constructor.dao.LinkUrlReaderWriter;
import com.example.constructor.db.ConstructorDbOpenHelper;
import com.example.constructor.db.ConstructorReaderContract;
import com.example.constructor.model.LinkUrl;

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

        return writableDatabase.insert(
                ConstructorReaderContract.LinkUrlEntry.TABLE_NAME,
                null,
                contentValues
        );
    }

    @Override
    public List<LinkUrl> findAll() {
        return null;
    }
}
