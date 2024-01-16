package com.example.constructor.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.constructor.R;
import com.example.constructor.adapter.HomeAdapter;
import com.example.constructor.dao.sqlite.ChapterReaderWriterSqlite;
import com.example.constructor.model.Chapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private List<Chapter> chapterList = new ArrayList<>();
    private ChapterReaderWriterSqlite chapterReaderWriterSqlite;
    private HomeAdapter homeAdapter;



    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_list);
        chapterReaderWriterSqlite = new ChapterReaderWriterSqlite(getContext());
        chapterList = chapterReaderWriterSqlite.findAll();
        homeAdapter = new HomeAdapter(chapterList, getContext(), this);
        recyclerView.setAdapter(homeAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        chapterList.clear();
        chapterList.addAll(chapterReaderWriterSqlite.findAll());
        homeAdapter.notifyDataSetChanged();
    }
}
