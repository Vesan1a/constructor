package com.example.constructor.dao.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.constructor.dao.ChapterReaderWriter;
import com.example.constructor.dao.ContentChapterReaderWriter;
import com.example.constructor.dao.TestReaderWriter;
import com.example.constructor.db.ConstructorDbOpenHelper;
import com.example.constructor.db.ConstructorReaderContract;
import com.example.constructor.model.Chapter;

import java.util.ArrayList;
import java.util.List;

public class ChapterReaderWriterSqlite implements ChapterReaderWriter {

    private final ConstructorDbOpenHelper openHelper;
    private final ContentChapterReaderWriter contentChapterReaderWriter;
    private final TestReaderWriter testReaderWriter;
    private Context context;

    public ChapterReaderWriterSqlite(Context context) {
        this.context = context;
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
                    getColumnIndex(ConstructorReaderContract.ChapterEntry.COLUMN_ID);
            int columnIndexName = cursor.
                    getColumnIndex(ConstructorReaderContract.ChapterEntry.COLUMN_NAME);
            int columnIndexAccepted = cursor.
                    getColumnIndex(ConstructorReaderContract.ChapterEntry.COLUMN_ACCEPTED);


            do {

                long chapterId = cursor.getLong(columnIndexId);
                Chapter chapter = null;
                try {
                    chapter = new Chapter(
                            chapterId,
                            cursor.getString(columnIndexName),
                            contentChapterReaderWriter.findByChapterId(chapterId),
                            testReaderWriter.findByChapterId(chapterId),
                            cursor.getInt(columnIndexAccepted) == 1

                    );
                } catch (Exception e) {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                chapterList.add(chapter);
            } while (cursor.moveToNext());

        }
        return chapterList;
    }

    @Override
    public void update(long id, boolean accepted) {
        SQLiteDatabase writableDatabase = openHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(
                ConstructorReaderContract.ChapterEntry.COLUMN_ACCEPTED, accepted
        );

        writableDatabase.update(
                ConstructorReaderContract.ChapterEntry.TABLE_NAME,
                contentValues,
                ConstructorReaderContract.ChapterEntry.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)}
        );

    }
}
