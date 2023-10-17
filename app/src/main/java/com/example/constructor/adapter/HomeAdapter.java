package com.example.constructor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.constructor.R;
import com.example.constructor.model.Chapter;
import com.example.constructor.model.ContentChapter;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.divider.MaterialDivider;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private List<Chapter> chapterList;
    private Context context;
    private LayoutInflater inflater;

    public HomeAdapter(List<Chapter> chapterList, Context context) {
        this.chapterList = chapterList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_chapter, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Chapter chapter = chapterList.get(position);
        ContentChapter contentChapter = chapter.getContent();
        holder.tvChapterTitle.setText(chapter.getName());
        holder.tvPreviewContent.setText(contentChapter.getContentText());
        holder.cbCheckBox.setChecked(false);
    }

    @Override
    public int getItemCount() {
        return chapterList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvChapterTitle;

        private TextView tvPreviewContent;

        private MaterialCheckBox cbCheckBox;

        private MaterialDivider mdBottomLine;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            tvChapterTitle = itemView.findViewById(R.id.tv_chapterTitle);
            tvPreviewContent = itemView.findViewById(R.id.tv_previewContent);
            cbCheckBox = itemView.findViewById(R.id.cb_chapter_checkBox);
            mdBottomLine = itemView.findViewById(R.id.md_chapter_bottomLine);
        }
    }


}
