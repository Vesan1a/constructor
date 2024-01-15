package com.example.constructor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.constructor.R;
import com.example.constructor.fragment.TestFragment;
import com.example.constructor.model.Answer;
import com.google.android.material.checkbox.MaterialCheckBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.MyViewHolder> {

    private Context context;

    private LayoutInflater inflater;

    private List<Answer> answerList;

    private Map<Answer, Boolean> studentAnswers;

    private boolean checkboxEnabled;


    public AnswerAdapter(Context context, List<Answer> answerList) {
        this.context = context;
        this.answerList = answerList;
        inflater = LayoutInflater.from(context);
        studentAnswers = new HashMap<>();
        for (int i = 0; i < answerList.size(); i++) {
            studentAnswers.put(answerList.get(i), false);
        }
        checkboxEnabled = true;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_answer, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Answer answer = answerList.get(position);
        holder.tvAnswerText.setText(answer.getAnswerText());
        holder.cbCheckBox.setEnabled(checkboxEnabled);
        holder.cbCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentAnswers.put(answer, holder.cbCheckBox.isChecked());
            }
        });
    }

    public boolean isAnswerCorrect() {
        for (Map.Entry<Answer, Boolean> answerBooleanEntry : studentAnswers.entrySet()) {
            if (answerBooleanEntry.getKey().isRight() != answerBooleanEntry.getValue())
                return false;
        }
        return true;
    }

    public void blockCheckBox() {
        checkboxEnabled = false;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return answerList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvAnswerText;
        private MaterialCheckBox cbCheckBox;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            tvAnswerText = itemView.findViewById(R.id.tv_answer_text);
            cbCheckBox = itemView.findViewById(R.id.cb_answer);
        }
    }
}
