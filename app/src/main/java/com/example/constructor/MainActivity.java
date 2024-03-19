package com.example.constructor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.constructor.dao.LoaderJsonSqlite;
import com.example.constructor.dao.json.ChapterReaderJson;
import com.example.constructor.db.ConstructorReaderContract;
import com.example.constructor.model.Answer;
import com.example.constructor.model.Chapter;
import com.example.constructor.model.ContentChapter;
import com.example.constructor.model.ImageUrl;
import com.example.constructor.model.LinkUrl;
import com.example.constructor.model.Question;
import com.example.constructor.model.Test;
import com.google.gson.Gson;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new LoaderJsonSqlite(this).loadData();
    }


}