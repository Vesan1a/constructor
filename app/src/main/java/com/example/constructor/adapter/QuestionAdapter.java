package com.example.constructor.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.constructor.R;
import com.example.constructor.dao.sqlite.ChapterReaderWriterSqlite;
import com.example.constructor.fragment.TestFragment;
import com.example.constructor.model.Question;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyViewHolder> {

    private List<Question> questionList;

    private Context context;

    private LayoutInflater inflater;

    private Map<Question, Boolean> resultQuestion;

    private TestFragment testFragment;

    private int acceptCounter;

    public QuestionAdapter(Context context, List<Question> questionList, TestFragment testFragment) {
        this.context = context;
        this.questionList = questionList;
        this.testFragment = testFragment;
        inflater = LayoutInflater.from(context);
        resultQuestion = new HashMap<>();
        for (int i = 0; i < questionList.size(); i++) {
            resultQuestion.put(questionList.get(i), false);
        }
        acceptCounter = questionList.size();
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
                acceptCounter--;
                resultQuestion.put(question, answerAdapter.isAnswerCorrect());
                holder.btnAccept.setClickable(false);
                holder.btnAccept.setVisibility(View.INVISIBLE);
                answerAdapter.blockCheckBox();

                if (acceptCounter == 0) {
                    int result = calcResult();
                    new MaterialAlertDialogBuilder(context).setTitle(context.getResources().getString(R.string.result))
                            .setMessage(result + "%")
                            .setPositiveButton(context.getResources().getString(R.string.complete), (dialogInterface, i) -> {
                                if (result > 70) {
                                    new ChapterReaderWriterSqlite(context)
                                            .update(
                                                    testFragment.getArguments().getLong("chapter_id"),
                                                    true
                                            );
                                }
                                NavHostFragment.findNavController(testFragment)
                                        .navigate(R.id.action_testFragment_to_homeFragment3);

                            })
                            .show();

                }
            }
        });
    }

    private int calcResult() {
        double rightCounter = 0;

        for (Map.Entry<Question, Boolean> entry : resultQuestion.entrySet()) {
            if (entry.getValue()) rightCounter++;
        }
        return (int) (rightCounter / questionList.size() * 100);
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
