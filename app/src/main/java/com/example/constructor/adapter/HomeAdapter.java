package com.example.constructor.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.constructor.R;
import com.example.constructor.fragment.HomeFragment;
import com.example.constructor.model.Chapter;
import com.example.constructor.model.ContentChapter;
import com.google.android.material.checkbox.MaterialCheckBox;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private List<Chapter> chapterList;
    private Context context;
    private LayoutInflater inflater;

    private HomeFragment homeFragment;

    public HomeAdapter(List<Chapter> chapterList, Context context, HomeFragment homeFragment) {
        this.chapterList = chapterList;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.homeFragment = homeFragment;
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
        holder.tvPreviewContent.setText(contentChapter.getContentText().substring(0, 40) + "...");
        holder.tvChapterTitle.setText(chapter.getName());
        if (chapter.isAccepted()) {
            holder.cbCheckBox.setChecked(true);
            holder.cbCheckBox.setVisibility(View.VISIBLE);
        } else {
            holder.cbCheckBox.setChecked(false);
            holder.cbCheckBox.setVisibility(View.INVISIBLE);
        }
        holder.cbCheckBox.setClickable(false);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putLong("chapter_id", chapter.getId());
                bundle.putString("chapter_name", chapter.getName());

                NavHostFragment
                        .findNavController(homeFragment)
                        .navigate(R.id.action_homeFragment_to_chapterFragment, bundle);
            }
        });

    }

    @Override
    public int getItemCount() {
        return chapterList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvChapterTitle;

        private TextView tvPreviewContent;

        private MaterialCheckBox cbCheckBox;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            tvChapterTitle = itemView.findViewById(R.id.tv_chapter_title);
            tvPreviewContent = itemView.findViewById(R.id.tv_preview_content);
            cbCheckBox = itemView.findViewById(R.id.cb_chapter_checkBox);
        }
    }


}
