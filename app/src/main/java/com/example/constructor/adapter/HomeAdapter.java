package com.example.constructor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.constructor.R;
import com.example.constructor.model.Chapter;

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
        holder.tvChapterTitle.setText(chapter.getName());
        holder.ivChapterCheckBox.setImageDrawable(context.getDrawable(R.drawable.check_box));
        holder.ivChapterBottomLine.setImageDrawable(context.getDrawable(R.drawable.line));
    }

    @Override
    public int getItemCount() {
        return chapterList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvChapterTitle;
        private ImageView ivChapterCheckBox;
        private ImageView ivChapterBottomLine;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            tvChapterTitle = itemView.findViewById(R.id.tv_chapterTitle);
            ivChapterCheckBox = itemView.findViewById(R.id.iv_chapter_checkBox);
            ivChapterBottomLine = itemView.findViewById(R.id.iv_chapter_bottomLine);
        }
    }


}
