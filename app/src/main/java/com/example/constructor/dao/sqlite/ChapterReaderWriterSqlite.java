package com.example.constructor.dao.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.constructor.dao.ChapterReaderWriter;
import com.example.constructor.dao.ContentChapterReaderWriter;
import com.example.constructor.dao.TestReaderWriter;
import com.example.constructor.db.ConstructorDbOpenHelper;
import com.example.constructor.db.ConstructorReaderContract;
import com.example.constructor.model.Answer;
import com.example.constructor.model.Chapter;

import java.util.ArrayList;
import java.util.List;

public class ChapterReaderWriterSqlite implements ChapterReaderWriter {

    private final ConstructorDbOpenHelper openHelper;
    private final ContentChapterReaderWriter contentChapterReaderWriter;
    private final TestReaderWriter testReaderWriter;

    public ChapterReaderWriterSqlite(Context context) {
        this.openHelper = new ConstructorDbOpenHelper(context);
        contentChapterReaderWriter = new ContentChapterReaderWriterSqlite(context);
        testReaderWriter = new TestReaderWriterSqlite(context);
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

        SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();

        Cursor cursor = readableDatabase.query(
                ConstructorReaderContract.ChapterEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        List<Chapter> chapterList = new ArrayList<>();

        if (cursor.moveToFirst()) {

            int columnIndexId = cursor.
                    getColumnIndex(ConstructorReaderContract.ChapterEntry.COLUM_ID);
            int columnIndexName = cursor.
                    getColumnIndex(ConstructorReaderContract.ChapterEntry.COLUMN_NAME);
            int columnIndexAccepted = cursor.
                    getColumnIndex(ConstructorReaderContract.ChapterEntry.COLUMN_ACCEPTED);


            do {

                long chapterId = cursor.getLong(columnIndexId);
                Chapter chapter = new Chapter(
                        chapterId,
                        cursor.getString(columnIndexName),
                        contentChapterReaderWriter.findByChapterId(chapterId),
                        testReaderWriter.findByChapterId(chapterId),
                        cursor.getInt(columnIndexAccepted) == 1

                );


                chapterList.add(chapter);
            } while (cursor.moveToNext());

        }
        return chapterList;
    }

    @Override
    public void update(int id, boolean accepted) {

    }
}
