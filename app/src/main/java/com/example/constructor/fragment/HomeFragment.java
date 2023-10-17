package com.example.constructor.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.constructor.R;
import com.example.constructor.adapter.HomeAdapter;
import com.example.constructor.dao.sqlite.ChapterReaderWriterSqlite;
import com.example.constructor.db.ConstructorDbOpenHelper;
import com.example.constructor.model.Chapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private List<Chapter> chapterList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_list);
        chapterList = new ChapterReaderWriterSqlite(getContext()).findAll();
        HomeAdapter homeAdapter = new HomeAdapter(chapterList, getContext());
        recyclerView.setAdapter(homeAdapter);
        return view;
    }
}
