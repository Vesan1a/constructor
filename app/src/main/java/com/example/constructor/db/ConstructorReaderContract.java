package com.example.constructor.db;

public class ConstructorReaderContract {

    private ConstructorReaderContract() {}

    public static final String DATABASE_NAME = "Constructor.db";
    public static final int DATABASE_VERSION = 2;

    public static class AnswerEntry{
        public static final String TABLE_NAME = "Answer";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_ANSWER_TEXT = "answer_text";
        public static final String COLUMN_QUESTION_ID = "question_id";
        public static final String COLUMN_IS_RIGHT = "is_right";
    }

    public static class ChapterEntry{
        public static final String TABLE_NAME = "Chapter";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ACCEPTED = "accepted";
    }

    public static class ContentChapterEntry{
        public static final String TABLE_NAME = "ContentChapter";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_CONTENT_TEXT = "content_text";
        public static final String COLUMN_CHAPTER_ID = "chapter_id";
    }

    public static class ImageUrlEntry{
        public static final String TABLE_NAME = "ImageUrl";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_Url = "url";
        public static final String COLUMN_CONTENT_CHAPTER_ID = "contentChapter_Id";
    }

    public static class LinkUrlEntry{
        public static final String TABLE_NAME = "LinkUrl";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_Url = "url";
        public static final String COLUMN_CONTENT_CHAPTER_ID = "contentChapter_Id";
    }

    public static class QuestionEntry{
        public static final String TABLE_NAME = "Question";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_QUESTION_TEXT = "question_text";
        public static final String COLUMN_TEST_ID = "test_id";
    }

    public static class TestEntry{
        public static final String TABLE_NAME = "Test";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_CHAPTER_ID = "chapter_id";
        public static final String COLUMN_IS_DONE = "is_done";
    }

}
