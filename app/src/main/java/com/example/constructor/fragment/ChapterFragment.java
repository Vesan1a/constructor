package com.example.constructor.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.constructor.R;
import com.example.constructor.dao.sqlite.ContentChapterReaderWriterSqlite;
import com.example.constructor.model.ContentChapter;
import com.example.constructor.model.ImageUrl;

import java.util.List;

public class ChapterFragment extends Fragment {

    private ContentChapter contentChapter;

    private Context context;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        context = getContext();
        View view = inflater.inflate(R.layout.fragment_chapter, container, false);
        long id = getArguments().getLong("chapter_id");
        try {
            contentChapter = new ContentChapterReaderWriterSqlite(context).findByChapterId(id);
        } catch (Exception e) {
            Log.e("ChapterFragment", e.getMessage());
        }
        LinearLayout linearLayout = view.findViewById(R.id.ll_chapter_content);
        String[] splitContent = contentChapter.getContentText().split("image_r");
        int numberView = 0;
        for (int i = 0; i < splitContent.length; i++) {

            TextView textView = new TextView(context);
            textView.setText(splitContent[i]);
            textView.setTextAppearance(R.style.ts_chapter_text);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            linearLayout.addView(textView, numberView, params);
            numberView++;

            List<ImageUrl> imageUrlList = contentChapter.getImageUrlList();
            if (imageUrlList.size() > 0 && i < imageUrlList.size()) {
                ImageView imageView = new ImageView(context);
                int identifier = context.getResources().getIdentifier(
                        imageUrlList.get(i).getUrl(),
                        "drawable",
                        context.getPackageName()
                );
                imageView.setImageResource(identifier);
                linearLayout.addView(imageView, numberView, params);
                numberView++;
            }
        }
        Button button = view.findViewById(R.id.btn_start_test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putLong("chapter_id", id);

                NavHostFragment
                        .findNavController(ChapterFragment.this)
                        .navigate(R.id.action_chapterFragment_to_testFragment, bundle);
            }
        });

        return view;
    }
}
