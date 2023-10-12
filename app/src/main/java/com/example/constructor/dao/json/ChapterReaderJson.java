package com.example.constructor.dao.json;

import android.content.Context;
import android.util.Log;

import com.example.constructor.R;
import com.example.constructor.dao.ChapterReader;
import com.example.constructor.model.Chapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

public class ChapterReaderJson implements ChapterReader {

    private final Context context;

    public ChapterReaderJson(Context context) {
        this.context = context;
    }

    @Override
    public List<Chapter> findAll() {
        InputStream inputStream = context.getResources().openRawResource(R.raw.content);
        Reader reader = new InputStreamReader(inputStream);
        Gson gson = new Gson();

        Type listType = new TypeToken<List<Chapter>>() {
        }.getType();
        List<Chapter> chapterList = gson.fromJson(reader, listType);

        try {
            inputStream.close();
            reader.close();
        } catch (IOException e) {
            Log.e("ChapterReaderJson", e.getMessage());
        }
        return chapterList;
    }
}
