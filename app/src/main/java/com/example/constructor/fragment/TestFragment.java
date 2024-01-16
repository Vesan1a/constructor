package com.example.constructor.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.constructor.R;
import com.example.constructor.adapter.QuestionAdapter;
import com.example.constructor.dao.sqlite.TestReaderWriterSqlite;
import com.example.constructor.model.Test;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class TestFragment extends Fragment {

    private Test test;

    private Context context;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        context = getContext();
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        long id = getArguments().getLong("chapter_id");
        try {
            test = new TestReaderWriterSqlite(context).findByChapterId(id);
        } catch (Exception e) {
            Log.e("TestFragment", e.getMessage());
        }
        TextView tvTestName = view.findViewById(R.id.tv_test_name);
        tvTestName.setText(test.getName());
        ViewPager2 viewPager = view.findViewById(R.id.vp_test);
        viewPager.setAdapter(new QuestionAdapter(context, test.getQuestionList(), this));
        TabLayout tabDots = view.findViewById(R.id.tl_dots);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabDots,
                viewPager,
                true,
                (tab, position) -> {
                }
        );
        tabLayoutMediator.attach();

        return view;

    }
}
