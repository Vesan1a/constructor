package com.example.constructor.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConstructorDbOpenHelper extends SQLiteOpenHelper {

    public ConstructorDbOpenHelper(@Nullable Context context) {
        super(
                context,
                ConstructorReaderContract.DATABASE_NAME,
                null,
                ConstructorReaderContract.DATABASE_VERSION
        );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS " + ConstructorReaderContract.AnswerEntry.TABLE_NAME + " (" +
                        ConstructorReaderContract.AnswerEntry.COLUM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        ConstructorReaderContract.AnswerEntry.COLUMN_NAME + " TEXT, " +
                        ConstructorReaderContract.AnswerEntry.COLUMN_IS_RIGHT + " BOOLEN, " +
                        ConstructorReaderContract.AnswerEntry.COLUMN_QUESTION_ID + " INTEGER, " +
                        "FOREIGN KEY(" + ConstructorReaderContract.AnswerEntry.COLUMN_QUESTION_ID + ") " +
                        "REFERENCES " + ConstructorReaderContract.QuestionEntry.TABLE_NAME +
                        "(" + ConstructorReaderContract.QuestionEntry.COLUM_ID + ")" + ");"
        );

        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS " + ConstructorReaderContract.ChapterEntry.TABLE_NAME + " (" +
                        ConstructorReaderContract.ChapterEntry.COLUM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        ConstructorReaderContract.ChapterEntry.COLUMN_NAME + " TEXT, " +
                        ConstructorReaderContract.ChapterEntry.COLUMN_ACCEPTED + " BOOLEN );"
        );

        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS " + ConstructorReaderContract.ContentChapterEntry.TABLE_NAME + " (" +
                        ConstructorReaderContract.ContentChapterEntry.COLUM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        ConstructorReaderContract.ContentChapterEntry.COLUMN_NAME + " TEXT, " +
                        ConstructorReaderContract.ContentChapterEntry.COLUMN_CONTENT + " TEXT, " +
                        ConstructorReaderContract.ContentChapterEntry.COLUMN_CHAPTER_ID + " INTEGER, " +
                        "FOREIGN KEY(" + ConstructorReaderContract.ContentChapterEntry.COLUMN_CHAPTER_ID + ") " +
                        "REFERENCES " + ConstructorReaderContract.ChapterEntry.TABLE_NAME +
                        "(" + ConstructorReaderContract.ChapterEntry.COLUM_ID + ")" + ");"
        );

        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS " + ConstructorReaderContract.ImageUrlEntry.TABLE_NAME + " (" +
                        ConstructorReaderContract.ImageUrlEntry.COLUM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        ConstructorReaderContract.ImageUrlEntry.COLUMN_Url + " TEXT, " +
                        ConstructorReaderContract.ImageUrlEntry.COLUMN_CONTENT_CHAPTER_ID + " INTEGER, " +
                        "FOREIGN KEY(" + ConstructorReaderContract.ImageUrlEntry.COLUMN_CONTENT_CHAPTER_ID + ") " +
                        "REFERENCES " + ConstructorReaderContract.ContentChapterEntry.TABLE_NAME +
                        "(" + ConstructorReaderContract.ContentChapterEntry.COLUM_ID + ")" + ");"
        );

        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS " + ConstructorReaderContract.LinkUrlEntry.TABLE_NAME + " (" +
                        ConstructorReaderContract.LinkUrlEntry.COLUM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        ConstructorReaderContract.LinkUrlEntry.COLUMN_Url + " TEXT, " +
                        ConstructorReaderContract.LinkUrlEntry.COLUMN_CONTENT_CHAPTER_ID + " INTEGER, " +
                        "FOREIGN KEY(" + ConstructorReaderContract.LinkUrlEntry.COLUMN_CONTENT_CHAPTER_ID + ") " +
                        "REFERENCES " + ConstructorReaderContract.ContentChapterEntry.TABLE_NAME +
                        "(" + ConstructorReaderContract.ContentChapterEntry.COLUM_ID + ")" + ");"
        );

        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS " + ConstructorReaderContract.QuestionEntry.TABLE_NAME + " (" +
                        ConstructorReaderContract.QuestionEntry.COLUM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        ConstructorReaderContract.QuestionEntry.COLUMN_NAME + " TEXT, " +
                        ConstructorReaderContract.QuestionEntry.COLUMN_TEST_ID + " INTEGER, " +
                        "FOREIGN KEY(" + ConstructorReaderContract.QuestionEntry.COLUMN_TEST_ID + ") " +
                        "REFERENCES " + ConstructorReaderContract.TestEntry.TABLE_NAME +
                        "(" + ConstructorReaderContract.TestEntry.COLUM_ID + ")" + ");"
        );

        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS " + ConstructorReaderContract.TestEntry.TABLE_NAME + " (" +
                        ConstructorReaderContract.TestEntry.COLUM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        ConstructorReaderContract.TestEntry.COLUMN_NAME + " TEXT, " +
                        ConstructorReaderContract.TestEntry.COLUMN_IS_DONE + " BOOLEN, " +
                        ConstructorReaderContract.TestEntry.COLUMN_CHAPTER_ID + " INTEGER, " +
                        "FOREIGN KEY(" + ConstructorReaderContract.TestEntry.COLUMN_CHAPTER_ID + ") " +
                        "REFERENCES " + ConstructorReaderContract.ChapterEntry.TABLE_NAME +
                        "(" + ConstructorReaderContract.ChapterEntry.COLUM_ID + ")" + ");"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(
                "DROP TABLE IF EXISTS " + ConstructorReaderContract.AnswerEntry.TABLE_NAME
        );

        sqLiteDatabase.execSQL(
                "DROP TABLE IF EXISTS " + ConstructorReaderContract.ChapterEntry.TABLE_NAME
        );

        sqLiteDatabase.execSQL(
                "DROP TABLE IF EXISTS " + ConstructorReaderContract.ContentChapterEntry.TABLE_NAME
        );

        sqLiteDatabase.execSQL(
                "DROP TABLE IF EXISTS " + ConstructorReaderContract.ImageUrlEntry.TABLE_NAME
        );

        sqLiteDatabase.execSQL(
                "DROP TABLE IF EXISTS " + ConstructorReaderContract.LinkUrlEntry.TABLE_NAME
        );

        sqLiteDatabase.execSQL(
                "DROP TABLE IF EXISTS " + ConstructorReaderContract.QuestionEntry.TABLE_NAME
        );

        sqLiteDatabase.execSQL(
                "DROP TABLE IF EXISTS " + ConstructorReaderContract.TestEntry.TABLE_NAME
        );
        onCreate(sqLiteDatabase);
    }
}
