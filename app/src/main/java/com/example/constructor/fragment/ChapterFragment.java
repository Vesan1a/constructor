package com.example.constructor.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.constructor.R;
import com.example.constructor.dao.ContentChapterReaderWriter;
import com.example.constructor.dao.sqlite.ContentChapterReaderWriterSqlite;
import com.example.constructor.model.ContentChapter;

public class ChapterFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        long id = getArguments().getLong("chapter_id");
        try {
            ContentChapter contentChapter = new ContentChapterReaderWriterSqlite(getContext()).findByChapterId(id);
        } catch (Exception e) {
            Log.e("ChapterFragment", e.getMessage());
        }
        return view;
    }
}
