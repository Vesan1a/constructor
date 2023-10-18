package com.example.constructor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.constructor.dao.LoaderJsonSqlite;
import com.example.constructor.dao.json.ChapterReaderJson;
import com.example.constructor.model.Answer;
import com.example.constructor.model.Chapter;
import com.example.constructor.model.ContentChapter;
import com.example.constructor.model.ImageUrl;
import com.example.constructor.model.LinkUrl;
import com.example.constructor.model.Question;
import com.example.constructor.model.Test;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new LoaderJsonSqlite(this).loadData();
        /*List<ImageUrl> imageUrls = List.of(
                new ImageUrl(1, "ImageUrl1.com", 1),
                new ImageUrl(2, "ImageUrl2.com", 1)
        );
        List<LinkUrl> linkUrls = List.of(
                new LinkUrl(1, "LinkUrl1.com", 1),
                new LinkUrl(2, "LinkUrl2.com", 1)
        );
        List<Answer> answers1 = List.of(
                new Answer(1, "int", 1, true),
                new Answer(2, "double", 1, true),
                new Answer(3, "text", 1, false)
        );
        List<Answer> answers2 = List.of(
                new Answer(3, "byte", 2, true),
                new Answer(4, "long", 2, false)
        );
        List<Question> questions = List.of(
                new Question(1, "Выберите существующие типы данных", 1, answers1),
                new Question(2, "Выберите наименьший целочисленный тип", 1, answers2)
        );
        Test test = new Test(
                1,
                "Тест1",
                1,
                questions,
                false
        );
        ContentChapter contentChapter = new ContentChapter(
                1,
                "Тип данных (тип) — множество значений и операций над этими значениями (IEEE Std 1320.2-1998)[1].",
                imageUrls,
                linkUrls,
                1);
        Chapter chapter1 = new Chapter(
                1,
                "Глава 1",
                contentChapter,
                test,
                false
                );
        Chapter chapter2 = new Chapter(
                2,
                "Глава 2",
                contentChapter,
                test,
                false
        );
        List<Chapter> chapters = List.of(chapter1, chapter2);*/

        //Log.e("json", new ChapterReaderJson(this).findAll().toString());
    }


}