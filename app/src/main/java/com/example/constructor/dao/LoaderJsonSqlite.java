package com.example.constructor.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.constructor.dao.json.ChapterReaderJson;
import com.example.constructor.dao.sqlite.AnswerReaderWriterSqlite;
import com.example.constructor.dao.sqlite.ChapterReaderWriterSqlite;
import com.example.constructor.dao.sqlite.ContentChapterReaderWriterSqlite;
import com.example.constructor.dao.sqlite.ImageUrlReaderWriterSqlite;
import com.example.constructor.dao.sqlite.LinkUrlReaderWriterSqlite;
import com.example.constructor.dao.sqlite.QuestionReaderWriterSqlite;
import com.example.constructor.dao.sqlite.TestReaderWriterSqlite;
import com.example.constructor.db.ConstructorReaderContract;
import com.example.constructor.model.Answer;
import com.example.constructor.model.Chapter;
import com.example.constructor.model.ContentChapter;
import com.example.constructor.model.ImageUrl;
import com.example.constructor.model.LinkUrl;
import com.example.constructor.model.Question;
import com.example.constructor.model.Test;

import java.io.File;
import java.util.List;

public class LoaderJsonSqlite implements Loader {

    private final Context context;

    private final ChapterReader chapterReader;

    private final AnswerReaderWriter answerReaderWriter;

    private final ChapterReaderWriter chapterReaderWriter;

    private final ContentChapterReaderWriter contentChapterReaderWriter;

    private final ImageUrlReaderWriter imageUrlReaderWriter;

    private final LinkUrlReaderWriter linkUrlReaderWriter;

    private final QuestionReaderWriter questionReaderWriter;

    private final TestReaderWriter testReaderWriter;

    public LoaderJsonSqlite(Context context) {
        this.context = context;
        this.chapterReader = new ChapterReaderJson(context);
        this.answerReaderWriter = new AnswerReaderWriterSqlite(context);
        this.chapterReaderWriter = new ChapterReaderWriterSqlite(context);
        this.contentChapterReaderWriter = new ContentChapterReaderWriterSqlite(context);
        this.imageUrlReaderWriter = new ImageUrlReaderWriterSqlite(context);
        this.linkUrlReaderWriter = new LinkUrlReaderWriterSqlite(context);
        this.questionReaderWriter = new QuestionReaderWriterSqlite(context);
        this.testReaderWriter = new TestReaderWriterSqlite(context);
    }

    @Override
    public void loadData() {

        if (!currentVersion(ConstructorReaderContract.DATABASE_NAME)) {
            List<Chapter> chapterList = chapterReader.findAll();

            for (int i = 0; i < chapterList.size(); i++) {
                Chapter chapter = chapterList.get(i);
                chapterReaderWriter.insert(chapter);
                ContentChapter content = chapter.getContent();
                contentChapterReaderWriter.insert(content);
                List<ImageUrl> imageUrlList = content.getImageUrlList();
                for (int j = 0; j < imageUrlList.size(); j++) {
                    ImageUrl imageUrl = imageUrlList.get(j);
                    imageUrlReaderWriter.insert(imageUrl);
                }
                List<LinkUrl> linkUrlList = content.getLinkUrlList();
                for (int j = 0; j < linkUrlList.size(); j++) {
                    LinkUrl linkUrl = linkUrlList.get(j);
                    linkUrlReaderWriter.insert(linkUrl);
                }
                Test test = chapter.getTest();
                testReaderWriter.insert(test);
                List<Question> questionList = test.getQuestionList();
                for (int j = 0; j < questionList.size(); j++) {
                    Question question = questionList.get(j);
                    questionReaderWriter.insert(question);
                    List<Answer> answerList = question.getAnswerList();
                    for (int k = 0; k < answerList.size(); k++) {
                        Answer answer = answerList.get(k);
                        answerReaderWriter.insert(answer);
                    }
                }
            }

        }

    }

    public boolean currentVersion(String dbName) {
        File file = context.getDatabasePath(ConstructorReaderContract.DATABASE_NAME);
        if (file.exists()) {
            SQLiteDatabase sqLiteDatabase = SQLiteDatabase.openDatabase(
                    file.getPath(),
                    null,
                    SQLiteDatabase.OPEN_READONLY
            );
            return sqLiteDatabase.getVersion() == ConstructorReaderContract.DATABASE_VERSION;
        }
        return false;
    }

}
