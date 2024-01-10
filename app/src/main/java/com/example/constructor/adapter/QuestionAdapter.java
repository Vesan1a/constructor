package com.example.constructor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.constructor.R;
import com.example.constructor.model.Question;
import com.google.android.material.button.MaterialButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyViewHolder> {

    private List<Question> questionList;

    private Context context;

    private LayoutInflater inflater;

    private Map<Question, Boolean> resultQuestion;

    public QuestionAdapter(List<Question> questionList, Context context) {
        this.questionList = questionList;
        this.context = context;
        inflater = LayoutInflater.from(context);
        resultQuestion = new HashMap<>();
        for (int i = 0; i < questionList.size(); i++) {
            resultQuestion.put(questionList.get(i), false);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_question, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Question question = questionList.get(position);
        AnswerAdapter answerAdapter = new AnswerAdapter(context, question.getAnswerList());
        holder.rvQuestionAnswer.setAdapter(answerAdapter);
        holder.tvQuestionText.setText(question.getQuestionText());
        holder.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultQuestion.put(question, answerAdapter.isAnswerCorrect());
                holder.btnAccept.setClickable(false);
                holder.btnAccept.setVisibility(View.INVISIBLE);
                answerAdapter.blockCheckBox();
            }
        });
    }


    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvQuestionText;

        private RecyclerView rvQuestionAnswer;

        private MaterialButton btnAccept;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestionText = itemView.findViewById(R.id.tv_question_text);
            rvQuestionAnswer = itemView.findViewById(R.id.rv_question_answer);
            btnAccept = itemView.findViewById(R.id.btn_accept);

        }
    }

}
